package org.itstep.controller;

import lombok.AllArgsConstructor;
import org.itstep.model.ContactService;
import org.itstep.model.JsonUtils;
import org.itstep.model.entities.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
public class AddCommand implements Command {

    ContactService contactService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InputStream r = req.getInputStream();
        HttpServletRequestWrapper w = new HttpServletRequestWrapper(req);
        int c;
        String json = "aaa";
        while((c = r.read()) != -1) {
            json += (char)c;
        }
//        Contact contact = JsonUtils.getContactFromJson(json);
        FileWriter fw = new FileWriter("G:\\IntelliJ IDEA\\Projects\\ContactsWeb\\src\\main\\resources\\out.txt");
        fw.write(json);
        fw.close();
//        contactService.addContact(contact);
//        JsonUtils.saveContacts(contactService.getContacts(), contactService.getFilename());
    }
}
