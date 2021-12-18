package com.epam.library.connections;

import com.epam.configurations.Configuration;
import com.mysql.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPoolProvider {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    public static Connection getConnection() throws SQLException {
        if (ds==null){
            Properties applicationProperties = Configuration.getApplicationProperties();
            config.setJdbcUrl(applicationProperties.getProperty("jdbc.url"));
            config.setUsername(applicationProperties.getProperty("jdbc.name"));
            config.setPassword(applicationProperties.getProperty("jdbc.password"));
            config.setMaximumPoolSize(5);//max count of allowed connections
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            DriverManager.registerDriver(new Driver());
            ds = new HikariDataSource(config);
        }
        return ds.getConnection();
    }

    private ConnectionPoolProvider(){
    }
}
