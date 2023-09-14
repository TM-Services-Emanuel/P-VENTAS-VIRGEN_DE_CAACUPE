package Componentes;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author TM-SERVICES
 */
public class DataSourceService1 {

    private static BasicDataSource basicDataSource = null;
    private final String servidor = Config.getServer()+":"+Config.getPort();
    private final String bd = Config.getBDM();
    private final String user = Config.getUser();
    private final String pass = Config.getPassword();
    private final String url = "jdbc:mariadb://" + servidor + "/" + bd + "";

    public DataSourceService1() {
        if (null == basicDataSource) {
            basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
            basicDataSource.setUsername(user);
            basicDataSource.setPassword("");
            basicDataSource.setUrl(url);
            basicDataSource.setMaxTotal(200);
            basicDataSource.setMinIdle(50);
            basicDataSource.setMaxIdle(100);
        }
    }

    public BasicDataSource getDataSource() {
        return basicDataSource;

    }
    
}
