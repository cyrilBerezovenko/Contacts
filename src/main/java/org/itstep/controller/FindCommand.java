package org.itstep.controller;

import lombok.AllArgsConstructor;
import org.itstep.model.ContactService;
import org.itstep.model.ContactSet;
import org.itstep.model.JsonUtils;
import org.itstep.model.entities.Contact;
import org.itstep.view.AppException;
import org.itstep.view.View;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
public class FindCommand implements Command {

    ContactService contactService;

    @Override
    public void execute(String args, HttpServletResponse resp) {
        Contact matchingContact = JsonUtils.getContactFromJson(args);
        ContactSet foundContacts = contactService.findContacts(matchingContact);
        resp.setHeader("contacts", JsonUtils.contactSetToJson(foundContacts));
    }
}
