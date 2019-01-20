package org.itstep;

import org.itstep.model.ContactSet;
import org.itstep.model.JsonUtils;
import org.itstep.model.entities.Contact;
import org.itstep.model.entities.SocialNetwork;
import org.itstep.model.entities.SocialNetworkLink;

import java.util.*;

import static org.itstep.App.FILENAME;

public class SaveRunner {

    public static void main(String[] args) {
        ContactSet contacts = initContacts();
        JsonUtils.saveContacts(contacts, FILENAME);
    }

    private static ContactSet initContacts() {
        ContactSet contacts = new ContactSet();
        contacts.addAll(Arrays.asList(
                Contact.builder()
                        .name("first")
                        .surname("1")
                        .email(new HashSet<>(Arrays.asList("email@gmail.com")))
                        .phone(new HashSet<>(Arrays.asList("0987654321", "1234")))
                        .skype("qwerty")
                        .socialNetworks(new HashSet<>(Arrays.asList(SocialNetworkLink.of(SocialNetwork.TWITTER, "tw"))))
                        .build()
                ,
                Contact.builder()
                        .name("second")
                        .surname("2")
                        .email(new HashSet<>(Arrays.asList("email@gil.com")))
                        .phone(new HashSet<>(Arrays.asList("09876541")))
                        .skype("qwrty")
                        .socialNetworks(new HashSet<>(Arrays.asList(SocialNetworkLink.of(SocialNetwork.INSTAGRAM, "tw"))))
                        .build()
                ,
                Contact.builder()
                        .name("third")
                        .surname("3")
                        .email(new HashSet<>(Arrays.asList("mail@gmail.com")))
                        .phone(new HashSet<>(Arrays.asList("09876321")))
                        .skype("qwty")
                        .socialNetworks(new HashSet<>(Arrays.asList(SocialNetworkLink.of(SocialNetwork.VK, "tw"))))
                        .build()
        ));
        return contacts;
    }
}
