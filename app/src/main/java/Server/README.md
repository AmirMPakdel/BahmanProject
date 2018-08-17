# Replicated SocketIO: 

This class handles all communication between Client and Server. 
### Connecting and Listening: 

You Can Start By Creating a Connection: 

```Java
SocketIO soc = new SocketIO("ws://myserver.com");
```

this will initialize resources needed to perofm future tasks. 

now you can **Register Events** and start listening to that event. the callback will provide you a `JSONObject` which comes from server:
```Java
soc.on("connected", (data) -> {
    log(data.toString())
});
```

These events are built-in and you can override them: 

+ ***connected***: when socket accepted by server and connection successfuly stablished.
+ ***disconnecting***: when one of the peers tries to initialize a gracefull closure of the connection.
+ ***disconnected*** when the connection is completely closed.


**NOTE** that the `POST` event is a keyEvent and you **MUST NOT** override this event.

```Java
soc.on("POST", (data) -> {
    // WRONG, Dont Do this at all
    // this will break the http post method (which you see later)
});
```

You can also register ansy custom events:
```Java
soc.on("update data", (data) -> {
    log(data);
});
```


You can remove a registered event and stop listening to that event: 

```Java
soc.off("update data");
```

Now You can initialize the connection: 
```Java
sock.connect();
```
and at any time you can check the connectivity either by built-in events or by: 
```Java
soc.isConnected()
```

***
### Sending and Recieving Data: 
its easy as below: 
```Java
soc.send("update data", someJsonObject);
```

**More Development Information :**
the data will be formed and sent in this structure to server:
```json
{
    "event" : "update data",
    "data": someJsonObject
}
```

and data which comes from server **MUST** have this structure: 
```json
{
    "event" : "update data",
    "data": {}
}
```

***
## Sending HTTP Request Through the Socket :

you can use a conneted socket to send **POST HTTP Request Only** :
```Java
int timeout = 5000;
soc.post("/myRoute/", userJsonData, timeout, (status, resp) -> {
    log("int: " + status);
    log("JSONObject: " + resp)
});
```


Provided callback has a `int` HTPP Status Code and `JSONObject` response from server containig the **body**.

this library only use these status codes and its respective values: 
+ `HTTP_OK` (**200**)
+ `HTTP_BAD_REQUEST`  (**400**)
+ `HTTP_UNAUTHORIZED` (**401**)
+ `HTTP_FORBIDDEN` (**403**)
+ `HTTP_NOT_FOUND` (**404**)
+ `HTTP_REQUEST_TIMEOUT`  (**408**)
+ `HTTP_INTERNAL_ERROR`   (**500**)

**More Development Information :**
the data will be formed and sent in this structure to server:
```json
{
    "event" : "POST",
    "header" : {
        "token": "XJudl=65S5d1ad5gJapka3113fsgLh==",
        "http_id": 5462135746,
        "url": "/user/profile",
        "timeout": 3000
    },
    "body": {}
}
```

and data which comes from server **MUST** have this structure: 
```json
{
    "event" : "POST",
    "header" : {
        "http_id": 5462135746,
        "url": "/user/profile",
        "timeout": 3000,
        "status" : 200
    },
    "body": {}
}
```
***


### Closing And Releasing Allocated Resources: 

you can close the connection gracefully :
```Java
soc.close(closure_status_code);
```
The `close()` method takes a `int` as a **[Closure Status Code](https://tools.ietf.org/html/rfc6455#section-7.4)** and define the connection clozing excuse. these are all CSCs: 

+ NORMAL_CLOSURE    (**1000**)
+ GOING_AWAY        (**1001**)
+ PROTOCOL_ERROR    (**1002**)

You can also shutdown a connection, this will violently terminate the connection (**USE WITH CAUTION**): 
```Java
soc.shutdown();
```
and after all, you should clean the resources. after calling this method, you can't call other methods or convinienlty send and recieve any message from or to server. you must initialize and start everything all over again for that.
```Java 
soc.release();
```
