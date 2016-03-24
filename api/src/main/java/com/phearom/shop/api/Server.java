package com.phearom.shop.api;

/**
 * Created by phearom on 3/15/16.
 */
public class Server {
    private static Server instance;
//    private static final String serverId = "http://jsonplaceholder.typicode.com/";
    private static final String serverId = "http://192.168.1.102:8000/";
    public static Server init(){
        if (null == instance)
            instance = new Server();
        return instance;
    }

    public String getServerHost(){
        return serverId;
    }
}
