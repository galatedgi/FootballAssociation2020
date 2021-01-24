package Server.Config;

public class Configuration {
    private int port;
    private String Webroot;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebroot() {
        return Webroot;
    }

    public void setWebroot(String webroot) {
        Webroot = webroot;
    }
}
