package com.it.academy.controllers;

import com.it.academy.db.ConnectionManager;
import com.it.academy.service.InitService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;


/**
 * Class DeployListener contains methods which run on start and on end of deployment.
 */
@WebListener
public class DeployListener implements ServletContextListener {

    /**
     * Runs on deployment
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        InitService init = new InitService();
        init.initDataBases();
    }

    /**
     * Runs on end of deployment
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionManager.closeAllConnections();
    }
}
