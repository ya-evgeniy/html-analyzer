package evgeniy.html.analyzer.db;

public class DatabaseConfig {

    private final boolean enabled;
    private final String url;
    private final String host;
    private final int port;
    private final String db;
    private final String login;
    private final String password;

    public DatabaseConfig() {
        this(false, null, null, 0, null, null, null);
    }

    public DatabaseConfig(boolean enabled, String url, String host, int port, String db, String login, String password) {
        this.enabled = enabled;
        this.url = url;
        this.host = host;
        this.port = port;
        this.db = db;
        this.login = login;
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getUrl() {
        return url;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDb() {
        return db;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
