package org.itstep.controller;

import lombok.AllArgsConstructor;
import org.itstep.model.ContactService;
import org.itstep.model.ContactSet;
import org.itstep.model.JsonUtils;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
public class ListCommand implements Command {

    ContactService contactService;

    @Override
    public void execute(String args, HttpServletResponse resp) {
        ContactSet contacts = contactService.getContacts();
        resp.setHeader("contacts", JsonUtils.contactSetToJson(contacts));
    }
}
