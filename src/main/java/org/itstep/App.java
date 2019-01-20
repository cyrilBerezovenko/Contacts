package org.itstep;

import org.itstep.controller.Controller;
import org.itstep.model.ContactSet;
import org.itstep.model.JsonUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class App {

    public static final String FILENAME = "src\\main\\resources\\contacts.json";

    public static void main(String[] args) throws ServletException {
        Controller cont = new Controller();
        cont.init();
        System.out.println(cont.getCommandFactory());
        System.out.println(cont.getContactService());
        System.out.println(cont.getContext());
    }
}
