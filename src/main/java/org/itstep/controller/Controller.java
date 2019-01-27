package org.itstep.controller;

import lombok.*;
import org.itstep.model.ContactService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    public void init() {
        setApplicationContext(new ClassPathXmlApplicationContext("context.xml"));
        this.contactService = (ContactService) context.getBean("contactService");
        this.commandFactory = (CommandFactory) context.getBean("commandFactory");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String commandString = req.getParameter("command");
        Command command = commandFactory.getCommand("add");
        command.execute(req, resp);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
