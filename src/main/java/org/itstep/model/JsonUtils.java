package org.itstep.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.itstep.model.entities.Contact;

import java.io.*;
import java.util.*;

public class JsonUtils {

    public static String readFile(String file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder str = new StringBuilder();
        reader.lines().forEach(s -> str.append(s).append("\n"));
        return str.toString();
    }

    public static void saveContacts(ContactSet contacts, String fileName) {
        try(FileWriter writer = new FileWriter(fileName)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, contacts);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ContactSet readContacts(String fileName) {
        try(FileReader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            ContactSet set = new ContactSet();
            set.addAll(Arrays.asList(gson.fromJson(reader, Contact[].class)));
            return set;
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Contact getContactFromJson(String json) {
        StringReader reader = new StringReader(json);
        Gson gson = new Gson();
        return gson.fromJson(reader, Contact.class);
    }

    public static String contactListToJson(List<Contact> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
