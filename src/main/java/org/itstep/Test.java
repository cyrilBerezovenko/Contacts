package org.itstep;

import org.itstep.model.JsonUtils;
import org.itstep.model.entities.Contact;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("G:\\IntelliJ IDEA\\Projects\\ContactsWeb\\src\\main\\resources\\contacts.json"));
        String s = r.lines().reduce("", (a,b) -> a + b);
        Contact contact = JsonUtils.getContactFromJson(s);
        System.out.println(contact);
        r.close();

    }
}
