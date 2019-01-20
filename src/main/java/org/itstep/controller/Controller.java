package org.itstep.controller;

import lombok.*;
import org.itstep.model.ContactService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;

@RequiredArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Controller extends HttpServlet implements ApplicationContextAware {

    @NonNull ContactService contactService;
    @NonNull CommandFactory commandFactory;
    Scanner scanner = new Scanner(System.in);

    private ApplicationContext context;

    @Override
    public void init() throws ServletException {
        setApplicationContext(new ClassPathXmlApplicationContext("context.xml"));
        this.contactService = (ContactService) context.getBean("contactService");
        this.commandFactory = (CommandFactory) context.getBean("commandFactory");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandString = req.getHeader("command");
        Command command = commandFactory.getCommand(commandString);
        String args = req.getHeader("args");
        command.execute(args, resp);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
