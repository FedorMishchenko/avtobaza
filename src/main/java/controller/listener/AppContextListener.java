package controller.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class AppContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    private static final Logger LOGGER = Logger.getLogger("AppContextListener");

    // Public constructor is required by servlet spec
    public AppContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        LOGGER.debug("context initialized");
        LOGGER.trace("context initialized");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        LOGGER.info("context destroyed");
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        LOGGER.info("new session created");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        LOGGER.info("session destroyed");
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */

      LOGGER.info(sbe.getName());
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
      LOGGER.info("attribute removed from session with id=" + sbe.getSession().getId());
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
        LOGGER.info("attribute removed in session with id=" + sbe.getSession().getId());
    }
}
