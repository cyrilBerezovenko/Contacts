package org.itstep.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import org.itstep.model.ContactService;
import org.itstep.model.JsonUtils;
import org.itstep.model.entities.Contact;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
public class AddCommand implements Command {

    ContactService contactService;

    @Override
    public void execute(String args, HttpServletResponse resp) {
        Contact contact = JsonUtils.getContactFromJson(args);
        contactService.addContact(contact);
    }
}
