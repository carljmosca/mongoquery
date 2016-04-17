/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moscaville.data;

import java.io.Serializable;

/**
 *
 * @author moscac
 */
public class DbConnectionProps implements Serializable {
 
    private String server;
    private Integer port;
    private String userName;
    private String password;
    private String authenticationDb;
    
    public DbConnectionProps() {
        
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthenticationDb() {
        return authenticationDb;
    }

    public void setAuthenticationDb(String authenticationDb) {
        this.authenticationDb = authenticationDb;
    }
    
}
