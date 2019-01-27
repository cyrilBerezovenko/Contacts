package org.itstep.controller;

import lombok.AllArgsConstructor;
import org.itstep.model.ContactService;
import org.itstep.model.ContactSet;
import org.itstep.model.JsonUtils;
import org.itstep.model.entities.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class FindCommand implements Command {

    ContactService contactService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        contactService.setContacts(JsonUtils.readContacts(contactService.getFilename()));
        String json = req.getReader().lines().reduce("", (a,b) -> a + b);
        Contact matchingContact = JsonUtils.getContactFromJson(json);
        ContactSet foundContacts = contactService.findContacts(matchingContact);
        resp.getWriter().print(JsonUtils.contactSetToJson(foundContacts));
    }
}
