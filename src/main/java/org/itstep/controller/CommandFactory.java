package org.itstep.controller;

import org.itstep.view.AppException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CommandFactory implements ApplicationContextAware {

    private ApplicationContext context;

    public Command getCommand(String input) {
        try {
            return (Command) context.getBean(input.toLowerCase() + "Command");
        } catch(NoSuchBeanDefinitionException ex) {
            throw new AppException("Invalid command");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}