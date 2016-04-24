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
 
    private String host;
    private Integer port;
    private String userName;
    private String password;
    private String authenticationDb;
    
    public DbConnectionProps() {
        port = new Integer(27017);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    @Override
    public String toString() {
        return host ;
    }
    
}
