/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moscaville.ui;

import com.moscaville.data.DbConnectionProps;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @PropertyId("host")
    private TextField tfHost;
    @PropertyId("port")
    private TextField tfPort;
    @PropertyId("userName")
    private TextField tfUserName;
    @PropertyId("password")
    private TextField tfPassword;
    @PropertyId("authenticationDb")
    private TextField tfAuthenticationDb;
    private Button btnOk;
    private Button btnCancel;
    private BeanFieldGroup<DbConnectionProps> binder;
    private DbConnectionProps dbConnectionProps;
    private boolean connected;

    public ConnectionWindow() {
    }

    private void doBinding() {
        binder.setItemDataSource(dbConnectionProps);
        binder.bindMemberFields(this);
        binder.setBuffered(true);
    }

    @PostConstruct
    private void init() {
        setCaption("MongoDb Connection");
        setClosable(false);
        setModal(true);
        setResizable(false);
        setResizeLazy(true);
        setHeight("-1px");
        setWidth("350px");
        mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        addFields();
        addButtons();
        setContent(mainLayout);
        binder = new BeanFieldGroup<>(DbConnectionProps.class);
        dbConnectionProps = new DbConnectionProps();
        doBinding();
    }

    private void addFields() {
        tfHost = addField("Host");
        tfPort = addField("Port");
        tfUserName = addField("Username");
        tfPassword = addField("Password");
        tfAuthenticationDb = addField("Authentication Database");
        //binder.bi commitHandler);
    }

    private TextField addField(String caption) {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setHeight("40px");
        hl.setWidth("100%");
        TextField tf = new TextField();
        tf.setWidth("300px");
        tf.setInputPrompt(caption);
        tf.setNullRepresentation("");
        hl.addComponent(tf);
        hl.setComponentAlignment(tf, Alignment.BOTTOM_CENTER);
        mainLayout.addComponent(hl);
        return tf;
    }

    private void addButtons() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setHeight("40px");
        buttonLayout.setWidth("100%");
        buttonLayout.setSpacing(true);
        btnOk = new Button("Connect", FontAwesome.DATABASE);
        btnOk.addClickListener((Button.ClickEvent event) -> {
            try {
                binder.commit();
                connected = connectToDatabase();
                close();
            } catch (FieldGroup.CommitException ex) {
                Logger.getLogger(ConnectionWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        buttonLayout.addComponent(btnOk);
        buttonLayout.setComponentAlignment(btnOk, Alignment.MIDDLE_CENTER);
        btnCancel = new Button("Cancel", FontAwesome.CLOSE);
        btnCancel.addClickListener((Button.ClickEvent event) -> {
            connected = false;
            close();
        });
        buttonLayout.addComponent(btnCancel);
        buttonLayout.setComponentAlignment(btnCancel, Alignment.MIDDLE_CENTER);
        mainLayout.addComponent(buttonLayout);
    }

    public DbConnectionProps getDbConnectionProps() {
        return dbConnectionProps;
    }

    public void setDbConnectionProps(DbConnectionProps dbConnectionProps) {
        this.dbConnectionProps = dbConnectionProps;
        doBinding();
    }

    public boolean isConnected() {
        return connected;
    }

    private boolean connectToDatabase() {
        return true;
    }
    
}
