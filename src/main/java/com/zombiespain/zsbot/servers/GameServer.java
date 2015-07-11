package com.zombiespain.zsbot.servers;


import org.jongo.marshall.jackson.oid.MongoId;

public class GameServer {

    @MongoId
    private String name;
    private int port;
    private String ip;
    private String rconPass;
    private int rconPort;

    public GameServer() {

    }

    public GameServer(String name, String ip, int port, String rconPass, int rconPort) {
        super();
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.rconPass = rconPass;
        this.rconPort = rconPort;
    }

    public GameServer(String name, String ip, int port) {
        super();
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.rconPass = "";
        this.rconPort = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getRconPass() {
        return rconPass;
    }

    public String getIp() {
        return ip;
    }

    public int getRconPort() {
        return rconPort;
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "name='" + name + '\'' +
                ", port=" + port +
                ", ip='" + ip + '\'' +
                ", rconPass='" + rconPass + '\'' +
                ", rconPort=" + rconPort +
                '}';
    }
}
