/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moscaville.ui;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author moscac
 */
public class DbCollectionComponent extends VerticalLayout {

    private final int BATCH_SIZE = 50;
    private final DBCollection dbCollection;
    private Button btnPrev;
    private Button btnNext;

    public DbCollectionComponent(DBCollection dbCollection) {
        this.dbCollection = dbCollection;
        init();
    }

    private void init() {
        addHeader();
        addBody();
    }

    private void addHeader() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        btnPrev = new Button(FontAwesome.ARROW_LEFT);
        btnPrev.setDescription("Previous");
        hl.addComponent(btnPrev);
        btnNext = new Button(FontAwesome.ARROW_RIGHT);
        btnNext.setDescription("Next");
        hl.addComponent(btnNext);
        addComponent(hl);
    }

    private void addBody() {
        VerticalLayout vl = new VerticalLayout();
        DBCursor dbCursor = dbCollection.find();
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            vl.addComponent(new Label(dbObject.toString()));
        }
        addComponent(vl);
    }
}
