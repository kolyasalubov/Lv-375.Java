package com.it.academy.controllers;

import com.it.academy.service.InitService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DeployListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        InitService init = new InitService();
        init.initDataBases();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
