/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moscaville.ui;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.moscaville.data.DbConnectionProps;
import com.moscaville.data.MongoConfig;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author moscac
 */
@SpringUI
public class MainView extends Panel implements View {

    public static final String NAME = "main";
    private VerticalLayout mainLayout;
    private HorizontalLayout buttonLayout;
    private HorizontalSplitPanel spWorkArea;
    private Button btnConnections;
    private Button btnNewQuery;
    private Button btnRun;
    private Tree trWorkArea;
    private TabSheet tsWorkArea;
    @Autowired
    ConnectionWindow connectionWindow;
    @Autowired
    MongoConfig mongoConfig;

    @PostConstruct
    private void init() {
        mainLayout = new VerticalLayout();
        setContent(mainLayout);
        setSizeFull();
        mainLayout.setHeight("100%");
        buildButtonBar();
        buildWorkArea();
    }

    private void buildButtonBar() {
        buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(true);
        buttonLayout.setHeight("50px");
        btnConnections = createButton(buttonLayout, FontAwesome.DATABASE, "Connections");
        buttonLayout.addComponent(btnConnections);
        btnNewQuery = createButton(buttonLayout, FontAwesome.FILES_O, "New Query");
        buttonLayout.addComponent(btnNewQuery);
        btnNewQuery.addClickListener((Button.ClickEvent event) -> {
            createQuery();
        });
        btnRun = createButton(buttonLayout, FontAwesome.PLAY, "Run");
        btnConnections.addClickListener((Button.ClickEvent event) -> {
            connectionWindow.center();
            ((MainUI) UI.getCurrent()).addWindow(connectionWindow);
        });
        connectionWindow.addCloseListener((Window.CloseEvent e) -> {
            ((MainUI) UI.getCurrent()).removeWindow(connectionWindow);
            if (connectionWindow.isConnected()) {
                addDbConnection();
            }
        });
        buttonLayout.addComponent(btnRun);
        mainLayout.addComponent(buttonLayout);
    }

    private void buildWorkArea() {
        spWorkArea = new HorizontalSplitPanel();
        spWorkArea.setSizeFull();
        spWorkArea.setSplitPosition(225, Unit.PIXELS);

        buildTrWorkArea();
        buildTsWorkArea();
        spWorkArea.setFirstComponent(trWorkArea);
        spWorkArea.setSecondComponent(tsWorkArea);
        mainLayout.addComponent(spWorkArea);
        mainLayout.setExpandRatio(spWorkArea, 1.0f);
    }

    private void buildTrWorkArea() {
        trWorkArea = new Tree();
        trWorkArea.setMultiSelect(false);
        trWorkArea.addExpandListener((Tree.ExpandEvent event) -> {

            if (trWorkArea.getValue() instanceof DbConnectionProps) {
                DbConnectionProps dbConnectionProps = (DbConnectionProps) trWorkArea.getValue();
            } else if (trWorkArea.getValue() instanceof String) {

            }
        });
        trWorkArea.addItemClickListener((ItemClickEvent event) -> {
            if (event.getItemId() instanceof DBCollection) {
                DBCollection dbCollection = (DBCollection) event.getItemId();
                tsWorkArea.addTab(new DbCollectionComponent(dbCollection));
            }
        });
    }

    private void buildTsWorkArea() {
        tsWorkArea = new TabSheet();
        tsWorkArea.setSizeFull();
        tsWorkArea.setHeight("100%");
        tsWorkArea.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tsWorkArea.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
    }

    private void createQuery() {
        final VerticalLayout layout = new VerticalLayout();
        layout.setHeight("100%");
        layout.setMargin(true);
        TextArea textArea = new TextArea();
        textArea.setSizeFull();
        layout.addComponent(textArea);
        layout.setExpandRatio(textArea, 1.0f);
        tsWorkArea.addTab(layout, "Untitled");
    }

    private Button createButton(HorizontalLayout horizontalLayout, Resource icon, String description) {
        Button button = new Button(icon);
        button.setDescription(description);
        horizontalLayout.addComponent(button);
        return button;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    private void addDbConnection() {
        try {
            DbConnectionProps dbConnectionProps = (DbConnectionProps) BeanUtils.cloneBean(connectionWindow.getDbConnectionProps());
            trWorkArea.addItem(dbConnectionProps);
            trWorkArea.setChildrenAllowed(dbConnectionProps, true);
            Mongo mongo = mongoConfig.mongo(dbConnectionProps);
            List<String> databases = mongo.getDatabaseNames();
            for (String databaseName : databases) {
                DB database = mongo.getDB(databaseName);
                trWorkArea.addItem(database);
                trWorkArea.setParent(database, dbConnectionProps);
                for (String collectionName : database.getCollectionNames()) {
                    DBCollection collection = database.getCollection(collectionName);
                    trWorkArea.addItem(collection);
                    trWorkArea.setParent(collection, database);
                }
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | UnknownHostException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
