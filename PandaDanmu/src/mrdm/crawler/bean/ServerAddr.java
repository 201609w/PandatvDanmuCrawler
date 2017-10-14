package mrdm.crawler.bean;

/**
 * Created by Wping on 2017/10/1.
 */
public class ServerAddr {
    private String host;
    private int port;

    public ServerAddr(String host,int port){
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ServerAddr{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
