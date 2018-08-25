package Server;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import Gameplay.FragmentBookChoosing;
import RealmObjects.Match;
import Utils.Consts;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class SocketIO {

    //region Private Variables

    private Map<String, Callback> registeredEvents;
    private Map<Long, Response> httpResponseStack;
    private Map<Long, Runnable> timeouts;
    private String server_url;


    private boolean isConnected;
    private WebSocket webSocket;
    private OkHttpClient client;
    private Handler incomingMessageHandler;
    private Handler requestTimeoutHandler;
    //endregion


    //region Public static variables

    public static SocketIO socketIO;

    public static final boolean LOG = false;

    public static final int NORMAL_CLOSURE = 1000;
    public static final int GOING_AWAY = 1001;
    public static final int PROTOCOL_ERROR = 1002;

    public static final int WRONG_TYPE = 1003;
    public static final int HTTP_OK = 200;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_REQUEST_TIMEOUT = 408;
    public static final int HTTP_INTERNAL_ERROR = 500;

    //endregion


    //region Interfaces and Helper Classes and Functions

    private static void log(String text) {
        if (!LOG) return;
        Log.d("FuckThisShit", text);
    }

    public interface Callback {
        void onCall(JSONObject obj);
    }

    public interface Response {
        void onResponse(int status, JSONObject response);
    }

    private class SocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, okhttp3.Response response) {

            isConnected = true;
            incomingMessageHandler.post(() -> {
                if (registeredEvents.containsKey("connected")) {
                    Callback cb = registeredEvents.get("connected");

                    if (cb != null) {
                        cb.onCall(null);
                    }
                }
            });

            log("onOpen: " + webSocket.toString() + " - " + response.toString());
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            incomingMessageHandler.post(() -> {
                try {
                    JSONObject obj = new JSONObject(text);
                    String event = obj.getString("event");

                    if (event.equals("POST")) {
                        JSONObject header = obj.getJSONObject("header");
                        JSONObject body = obj.getJSONObject("body");
                        int status = header.getInt("status");
                        long httpID = header.getLong("http_id");

                        Response resp = httpResponseStack.remove(httpID);
                        if (resp != null) {
                            detachTimeoutForRequest(httpID);
                            resp.onResponse(status, body);
                        }

                    } else if (registeredEvents.containsKey(event)) {
                        Callback cb = registeredEvents.get(event);

                        if (cb != null) {
                            cb.onCall(obj.getJSONObject("data"));
                        }
                    }

                } catch (Exception e) {
                    log(e.getMessage());
                }
            });


            log("onMessage: " + webSocket.toString() + " - " + text);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {

            isConnected = false;
            incomingMessageHandler.post(() -> {
                try {
                    if (registeredEvents.containsKey("disconnecting")) {
                        Callback cb = registeredEvents.get("disconnecting");
                        if (cb != null) {
                            JSONObject result = new JSONObject();
                            result.put("reason", reason);
                            cb.onCall(result);
                        }
                    }

                } catch (Exception e) {
                    log(e.getMessage());
                }
            });
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {

            isConnected = false;
            incomingMessageHandler.post(() -> {
                try {
                    if (registeredEvents.containsKey("disconnected")) {
                        Callback cb = registeredEvents.get("disconnected");
                        if (cb != null) {
                            JSONObject result = new JSONObject();
                            result.put("reason", reason);
                            cb.onCall(result);
                        }
                    }

                } catch (Exception e) {
                    log(e.getMessage());
                }
            });
            log("onClosed: " + webSocket.toString() + " - " + reason);

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {

            isConnected = false;
            incomingMessageHandler.post(() -> {
                try {
                    if (registeredEvents.containsKey("error")) {
                        Callback cb = registeredEvents.get("error");
                        if (cb != null) {
                            JSONObject result = new JSONObject();
                            result.put("reason", response.toString());
                            cb.onCall(result);
                        }
                    }

                } catch (Exception e) {
                    log(e.getMessage());
                }
            });
            log("onFailure: " + webSocket.toString() + " - " + response);
        }
    }

    //endregion


    //region Constructors

    public static void init() {

        if (socketIO != null) {

            socketIO = new SocketIO(Consts.SOCKET_URL);
        }
    }

    public static SocketIO getInstance() {
        if(socketIO == null){
            throw new NullPointerException("You Must Initialize the SocketIO");
        }
        return socketIO;
    }

    private SocketIO(String server_url) {
        this.registeredEvents = new HashMap<>();
        this.httpResponseStack = new HashMap<>();
        this.timeouts = new HashMap<>();
        this.server_url = server_url;
        client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        this.isConnected = false;
    }

    //endregion


    //region Public Methods

    public void connect() {
        Request request = new Request.Builder()
                .url(this.server_url)
                .build();
        incomingMessageHandler = new Handler(Looper.getMainLooper());
        requestTimeoutHandler = new Handler();
        webSocket = client.newWebSocket(request, new SocketListener());
    }

    public void on(String event, Callback callback) {
        if (callback == null) throw new NullPointerException("callback can not be Null");
        this.registeredEvents.put(event, callback);
    }

    public void off(String event) {
        if (event == null) throw new NullPointerException("event can not be Null");
        this.registeredEvents.remove(event);
    }

    public void send(String event, JSONObject message) {
        try {
            JSONObject data = new JSONObject();
            data.put("event", event);
            data.put("data", message);
            webSocket.send(data.toString());

        } catch (JSONException e) {
            log(e.getMessage());
        }

    }

    public boolean isConnected() {
        return isConnected;
    }

    /**
     * initiate connection termination
     *
     * @param code the closure status code
     *             read <a href="https://tools.ietf.org/html/rfc6455#section-7.4">this</a> for more information
     */
    public void disconnect(int code) {
        try {
            webSocket.close(code, null);
            isConnected = false;
        } catch (Exception e) {
            log(e.getMessage());
        }

    }

    public void shutdown() {
        try {
            webSocket.cancel();
            isConnected = false;
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    /**
     * release all resources
     */
    public void release() {
        try {
            if (isConnected) {
                disconnect(NORMAL_CLOSURE);
            }
            incomingMessageHandler.removeCallbacksAndMessages(null);
            requestTimeoutHandler.removeCallbacksAndMessages(null);
            client.dispatcher().executorService().shutdown();

        } catch (Exception e) {
            log(e.getMessage());
        } finally {
            httpResponseStack.clear();
            httpResponseStack = null;
            registeredEvents.clear();
            registeredEvents = null;
            timeouts.clear();
            timeouts = null;
            webSocket = null;
        }
    }

    public void post(String token, String url, JSONObject body, int timeout, Response resp) {

        //// TODO: 8/17/18 read Token (user_id) instead of getting it from function arguments
        //region POST data structure
        /*  ===>
         *  {
         *      event : "POST",
         *      header : {
         *          token: "XJudl=65S5d1ad5gJapka3113fsgLh==",
         *          http_id: 5462135746,
         *          url: "/user/profile",
         *          timeout: 3000
         *      },
         *      body: {}
         *  }
         *
         *
         *
         *  <===
         *    {
         *      event : "POST",
         *      header : {
         *          http_id: 5462135746,
         *          url: "/user/profile",
         *          timeout: 3000,
         *          status: 200
         *      },
         *      body: {}
         *  }
         */
        //endregion

        try {
            JSONObject data = new JSONObject();

            long httpID = System.currentTimeMillis();
            JSONObject header = new JSONObject()
                    .put("http_id", httpID)
                    .put("url", url)
                    .put("timeout", timeout)
                    .put("token", token);


            data.put("event", "POST");
            data.put("header", header);
            data.put("body", body);

            if (isConnected) {

                httpResponseStack.put(httpID, resp);
                webSocket.send(data.toString());
                attachTimeoutToRequest(httpID, timeout);
            } else {
                throw new RuntimeException("Socket is not connected to server to sent a POST request");
            }


        } catch (Exception e) {
            log(e.getMessage());
        }

    }

    //endregion


    //region Private Methods

    /**
     * this method attached a runnable to a Specific HTTP_ID and handle the timeout with an Handler.
     * the timeoutRunnable will be stored in a map, so in case of receiving the response we can cancel the timeoutRunnable
     */
    private void attachTimeoutToRequest(long http_id, int timeout) {

        Runnable runnable = () -> {
            if (httpResponseStack.containsKey(http_id)) {
                Response r = httpResponseStack.remove(http_id);
                if (r != null) {
                    r.onResponse(HTTP_REQUEST_TIMEOUT, null);
                    timeouts.remove(http_id);
                }
            }
        };

        timeouts.put(http_id, runnable);
        requestTimeoutHandler.postDelayed(runnable, timeout);

    }

    private void detachTimeoutForRequest(long http_id) {

        Runnable timeoutRunnable = timeouts.remove(http_id);
        if (timeoutRunnable != null) {
            requestTimeoutHandler.removeCallbacks(timeoutRunnable);
        }

    }

    //endregion


}
