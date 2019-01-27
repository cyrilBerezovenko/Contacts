package org.itstep.controller;

import lombok.AllArgsConstructor;
import org.itstep.model.ContactService;
import org.itstep.model.JsonUtils;
import org.itstep.model.entities.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
public class AddCommand implements Command {

    ContactService contactService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        contactService.setContacts(JsonUtils.readContacts(contactService.getFilename()));
        String json = req.getReader().lines().reduce("", (a,b) -> a + b);
        Contact contact = JsonUtils.getContactFromJson(json);
        boolean added = contactService.addContact(contact);
        if(added) JsonUtils.saveContacts(contactService.getContacts(), contactService.getFilename());
        resp.getWriter().print(added);
    }
}
