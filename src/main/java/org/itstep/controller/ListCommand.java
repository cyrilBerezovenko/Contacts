package org.itstep.controller;

import lombok.AllArgsConstructor;
import org.itstep.model.ContactService;
import org.itstep.model.JsonUtils;
import org.itstep.model.entities.Contact;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class ListCommand implements Command {

    private ContactService contactService;

    @Override
    public ResponseEntity execute(String requestBody) throws IOException{
        try {
            List<Contact> list = contactService.list();
            String json = JsonUtils.contactListToJson(list);
            return  ResponseEntity.ok(json);
        } catch(SQLException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
