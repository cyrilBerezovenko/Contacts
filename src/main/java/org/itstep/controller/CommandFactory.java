package org.itstep.controller;

import lombok.Getter;
import lombok.Setter;
import org.itstep.model.ContactService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;

@Component
@Setter
@Getter
public class CommandFactory {

    @Autowired
    private ContactService contactService;

    private final HashMap commands;

    public CommandFactory(ContactService contactService) {
        this.contactService = contactService;
        commands = new HashMap<String, Command>();
        commands.put("list", new ListCommand(contactService));
        commands.put("add", new AddCommand(contactService));
    }

    public Command getCommand(String input) {
        return (Command)commands.get(input);
    }

}
