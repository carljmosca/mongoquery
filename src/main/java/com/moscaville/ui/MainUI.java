/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moscaville.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author moscac
 */
@SpringUI
@Theme("mytheme")
public class MainUI extends UI {

    @Autowired
    MainView mainView;
    Navigator navigator;
    @Autowired
    private HttpSession httpSession;

    @Override
    protected void init(VaadinRequest request) {
        // Create a navigator to control the views
        navigator = new Navigator(this, this);
        this.navigator.setErrorView(mainView);

        // Create and register the views
        navigator.addView(MainView.NAME, mainView);
    }
}
