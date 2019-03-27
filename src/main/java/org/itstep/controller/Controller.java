package org.itstep.controller;

import lombok.*;
import org.itstep.model.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

import static org.itstep.model.JsonUtils.readFile;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    ContactService contactService;

    @Autowired
    CommandFactory commandFactory;

    @GetMapping("/contacts")
    public ResponseEntity getHtml() throws FileNotFoundException {
        String body = readFile("./src/main/webapp/index.html");
        return ResponseEntity.ok(body);
    }

    @GetMapping("/bundle.js")
    public ResponseEntity getJS() throws FileNotFoundException {
        String body = readFile("./src/main/webapp/dist/bundle.js");
        return ResponseEntity.ok(body);
    }

    @GetMapping("/service/{command}")
    public ResponseEntity doGet(@PathVariable String command, @RequestBody(required = false) String body) throws Exception {
        Command c = commandFactory.getCommand(command);
        if(c == null)
            return ResponseEntity.badRequest().build();
        return c.execute(body);
    }

    @PostMapping("/service/{command}")
    public ResponseEntity doPost(@PathVariable String command, @RequestBody(required = false) String body) throws Exception {
        Command c = commandFactory.getCommand(command);
        if(c == null)
            return ResponseEntity.badRequest().build();
        return c.execute(body);
    }

}
