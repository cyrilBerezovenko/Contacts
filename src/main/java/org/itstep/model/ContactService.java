package org.itstep.model;

import lombok.*;
import org.itstep.model.entities.Contact;
import org.itstep.model.entities.SocialNetwork;
import org.itstep.model.entities.SocialNetworkLink;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.itstep.model.JsonUtils.readFile;

@Setter
@Getter
@Component
public class ContactService {

    public static final String URL = "jdbc:postgresql://localhost:5433/contactbase";
    public static final String LOGIN = "postgres";
    public static final String PASSWORD = "qwertyy6";

    private Connection connection;

    public ContactService() throws SQLException {
        connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    @PreDestroy
    public void onDestroy() throws SQLException {
        connection.close();
    }

    public List<Contact> list() throws SQLException, IOException {
        PreparedStatement query = connection.prepareStatement("select * from contactbase.contacts");
        ResultSet resultSet = query.executeQuery();
        List<Contact> list = new ArrayList<>();

        while(resultSet.next()) {
            Contact contact = Contact.builder()
                                .name(resultSet.getString("name"))
                                .surname(resultSet.getString("surname"))
                                .skype(resultSet.getString("skype"))
                                .build();

            int id = Integer.parseInt(resultSet.getString("id"));
            
            PreparedStatement emailQuery = connection.prepareStatement(
                    readFile("./src/main/resources/static/select_emails.sql"));
            emailQuery.setInt(1, id);
            emailQuery.setInt(2, id);
            ResultSet emailRes = emailQuery.executeQuery();
            while(emailRes.next()) {
                contact.getEmail().add(
                        emailRes.getString("email"));
            }
            emailQuery.close();

            PreparedStatement phoneQuery = connection.prepareStatement(
                    readFile("./src/main/resources/static/select_phones.sql"));
            phoneQuery.setInt(1, id);
            phoneQuery.setInt(2, id);
            ResultSet phoneRes = phoneQuery.executeQuery();
            while(phoneRes.next()) {
                contact.getPhone().add(
                        phoneRes.getString("phone"));
            }
            phoneQuery.close();

            PreparedStatement networkQuery = connection.prepareStatement(
                    readFile("./src/main/resources/static/select_networks.sql"));
            networkQuery.setInt(1, id);
            networkQuery.setInt(2, id);
            ResultSet networkRes = networkQuery.executeQuery();
            while(networkRes.next()) {
                SocialNetwork network = SocialNetwork.valueOf(networkRes.getString("network").toUpperCase());
                String link = networkRes.getString("link");
                contact.getSocialNetworks().add(
                        SocialNetworkLink.of(network, link));
            }
            networkQuery.close();

            list.add(contact);
        }

        query.close();
        return list;
    }

    public void add(Contact contact) throws SQLException, IOException {
        String sql = readFile("./src/main/resources/static/contact_insert.sql");
        sql = String.format(sql, contact.getName(), contact.getSurname(), contact.getSkype());
        PreparedStatement query = connection.prepareStatement(sql);
        query.executeUpdate();
        query.close();
    }
}
