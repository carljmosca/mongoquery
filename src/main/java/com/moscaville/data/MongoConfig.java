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
import java.util.Arrays;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author moscac
 */
@Configuration
public class MongoConfig {

    public Mongo mongo(DbConnectionProps dbConnectionProps) throws UnknownHostException {

        MongoClient mongo;
        if (dbConnectionProps.getUserName() != null && !dbConnectionProps.getUserName().isEmpty()) {
            mongo = new MongoClient(
                    new ServerAddress(dbConnectionProps.getHost(), dbConnectionProps.getPort()),
                    Arrays.asList(MongoCredential.createMongoCRCredential(
                            dbConnectionProps.getAuthenticationDb(),
                            dbConnectionProps.getUserName(),
                            dbConnectionProps.getPassword().toCharArray())));
        } else {
            mongo = new MongoClient(dbConnectionProps.getHost(), dbConnectionProps.getPort());
        }
        return mongo;
//        return new MongoClient(singletonList(new ServerAddress(dbConnectionProps.getHost(), dbConnectionProps.getPort())),
//                singletonList(MongoCredential.createCredential(dbConnectionProps.getUserName(), 
//                        dbConnectionProps.getAuthenticationDb(), dbConnectionProps.getPassword().toCharArray())));
    }
}
