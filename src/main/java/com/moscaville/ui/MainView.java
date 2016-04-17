/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moscaville.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import javax.annotation.PostConstruct;

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
    private Button btnRun;
    private Tree trWorkArea;
    private TabSheet tsWorkArea;

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
        btnRun = createButton(buttonLayout, FontAwesome.PLAY, "Run");
        buttonLayout.addComponent(btnRun);
        mainLayout.addComponent(buttonLayout);
    }

    private void buildWorkArea() {
        spWorkArea = new HorizontalSplitPanel();
        spWorkArea.setSizeFull();
        spWorkArea.setSplitPosition(150, Unit.PIXELS);

        buildTrWorkArea();
        buildTsWorkArea();
        spWorkArea.setFirstComponent(trWorkArea);
        spWorkArea.setSecondComponent(tsWorkArea);
        mainLayout.addComponent(spWorkArea);
        mainLayout.setExpandRatio(spWorkArea, 1.0f);
    }

    private void buildTrWorkArea() {
        trWorkArea = new Tree();
    }
    
    private void buildTsWorkArea() {
        tsWorkArea = new TabSheet();
        tsWorkArea.setSizeFull();
        tsWorkArea.setHeight("-1px");
        tsWorkArea.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tsWorkArea.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        for (int i = 1; i < 8; i++) {
            final VerticalLayout layout = new VerticalLayout(new Label("test"));
            layout.setMargin(true);
            tsWorkArea.addTab(layout, "Tab " + i);
        }
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

}
