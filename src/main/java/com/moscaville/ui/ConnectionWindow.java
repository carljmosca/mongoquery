/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moscaville.ui;

import com.moscaville.data.DbConnectionProps;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 *
 * @author moscac
 */
@SpringUI
@Component
public class ConnectionWindow extends Window {

    private VerticalLayout mainLayout;
    private TextField tfServer;
    private TextField tfPort;
    private TextField tfUserName;
    private TextField tfPassword;
    private TextField tfAuthenticationDb;
    private Button btnOk;
    final BeanFieldGroup<DbConnectionProps> binder;
    private DbConnectionProps dbConnectionProps;

    public ConnectionWindow() {
        this.binder = new BeanFieldGroup<>(DbConnectionProps.class);
        dbConnectionProps = new DbConnectionProps();
        doBinding();
    }

    private void doBinding() {
        binder.setItemDataSource(dbConnectionProps);        
    }
    
    @PostConstruct
    private void init() {
        setCaption("MongoDb Connection");
        setClosable(false);
        setModal(true);
        setResizable(false);
        setResizeLazy(true);
        setHeight("-1px");
        setWidth("600px");
        mainLayout = new VerticalLayout();
        addFields();
        addButtons();
        setContent(mainLayout);
    }
    
    private void addFields() {
        tfServer = new TextField();
        tfPort = new TextField();
        tfUserName = new TextField();
        tfPassword = new TextField();
        tfAuthenticationDb = new TextField();
        //binder.bi commitHandler);
    }
    
    private void addButtons() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        btnOk = new Button(FontAwesome.CLOSE);
        buttonLayout.addComponent(btnOk);
        mainLayout.addComponent(buttonLayout);
    }

    public DbConnectionProps getDbConnectionProps() {
        return dbConnectionProps;
    }

    public void setDbConnectionProps(DbConnectionProps dbConnectionProps) {
        this.dbConnectionProps = dbConnectionProps;
        doBinding();
    }

}
