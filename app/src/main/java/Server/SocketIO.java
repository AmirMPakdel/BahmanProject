package Server;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import Utils.Consts;
import Utils.log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class SocketIO {

    //region Private Variables

    private Map<String, Callback> registeredEvents;
    private String server_url;


    private boolean isConnected;
    private WebSocket webSocket;
    private OkHttpClient client;
    private Handler incomingMessageHandler;
    //endregion


    //region Public static variables

    public static SocketIO socketIO = null;

    public static boolean LOG = true;

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
        Log.d(Consts.DEBUG_TAG, text);
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
            log("Connection Stablished");
            isConnected = true;
            incomingMessageHandler.post(() -> {
                if (registeredEvents.containsKey("connected")) {
                    Callback cb = registeredEvents.get("connected");

                    try {
                        JSONObject userInfo = new JSONObject();
                        userInfo.put("username", "alireza");
                        userInfo.put("token", "673fe3b39c7346e89ce6d17c18956596");
                        SocketIO.getInstance().send("ONCONNECT", userInfo);
                        log("onOpen(): user info message send");
                        if (cb != null) {
                            cb.onCall(null);
                        }
                    } catch (Exception e) {
                        log("onOpen(): " + e.getMessage());
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
                    obj = obj.getJSONObject("message"); //todo the decryption stuff goes here!!!
                    String event = obj.getString("event");

                    if (registeredEvents.containsKey(event)) {
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
            log("onFailure: " + webSocket.toString() + " - " + response + " - " + t.getMessage());
        }
    }

    //endregion


    //region Constructors

    public static void init() {
        if (socketIO == null) {
            socketIO = new SocketIO(Consts.SOCKET_URL);
        }
    }

    public static SocketIO getInstance() {
        if (socketIO == null) {
            throw new NullPointerException("You Must Initialize the SocketIO");
        }
        return socketIO;
    }

    private SocketIO(String server_url) {
        this.registeredEvents = new HashMap<>();
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
            JSONObject data = new JSONObject(), wrapper = new JSONObject();
            data.put("event", event);
            data.put("data", message);

            wrapper.put("message", data);

            webSocket.send(wrapper.toString());

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
            client.dispatcher().executorService().shutdown();

        } catch (Exception e) {
            log(e.getMessage());
        } finally {
            registeredEvents.clear();
            registeredEvents = null;
            webSocket = null;
        }
    }

    //endregion
}
