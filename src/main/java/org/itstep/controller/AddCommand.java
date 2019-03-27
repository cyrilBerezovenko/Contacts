package org.itstep.controller;

import lombok.AllArgsConstructor;
import org.itstep.model.ContactService;
import org.itstep.model.JsonUtils;
import org.itstep.model.entities.Contact;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class AddCommand implements Command {

    private ContactService contactService;

    @Override
    public ResponseEntity execute(String requestBody) {
        try {
            Contact contact = JsonUtils.getContactFromJson(requestBody);
            contactService.add(contact);
            return ResponseEntity.ok().build();
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
