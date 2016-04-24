/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moscaville.data;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.net.UnknownHostException;
import static java.util.Collections.singletonList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author moscac
 */
@Configuration
public class MongoConfig {

    @Bean
    public Mongo mongo(DbConnectionProps dbConnectionProps) throws UnknownHostException {
        return new MongoClient(singletonList(new ServerAddress(dbConnectionProps.getHost(), dbConnectionProps.getPort())),
                singletonList(MongoCredential.createCredential(dbConnectionProps.getUserName(), 
                        dbConnectionProps.getAuthenticationDb(), dbConnectionProps.getPassword().toCharArray())));
    }
}
