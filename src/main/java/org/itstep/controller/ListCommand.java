package org.itstep.controller;

import lombok.AllArgsConstructor;
import org.itstep.model.ContactService;
import org.itstep.model.ContactSet;
import org.itstep.model.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class ListCommand implements Command {

    ContactService contactService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ContactSet contacts = JsonUtils.readContacts(contactService.getFilename());
        resp.getWriter().print(JsonUtils.contactSetToJson(contacts));
    }
}
