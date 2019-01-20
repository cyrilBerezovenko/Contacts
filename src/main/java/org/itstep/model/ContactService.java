package org.itstep.model;


import lombok.*;
import org.itstep.controller.ContactParser;
import org.itstep.model.entities.Contact;
import org.itstep.model.entities.SocialNetworkLink;
import org.itstep.view.AppException;

import java.util.HashSet;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
public class ContactService {

    private ContactSet contacts = new ContactSet();

    public ContactService(String filename) {
        this.contacts = JsonUtils.readContacts(filename);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void addContacts(ContactSet contactSet) {
        contacts.addAll(contactSet);
    }

    public ContactSet findContacts(Contact matchingContact) {
        ContactSet foundContacts = new ContactSet();

        if(matchingContact.getPhone().size() > 1 ||
                matchingContact.getEmail().size() > 1 ||
                matchingContact.getSocialNetworks().size() > 1) {
            throw new AppException("find request cannot have multiple arguments");
        }

        foundContacts.addAll(
                contacts.stream()
                        .filter(contact -> matchesStringProperty(contact.getName(), matchingContact.getName()))
                        .filter(contact -> matchesStringProperty(contact.getSurname(), matchingContact.getSurname()))
                        .filter(contact -> matchesStringProperty(contact.getSkype(), matchingContact.getSkype()))
                        .filter(contact -> matchesSetProperty(contact.getPhone(), matchingContact.getPhone()))
                        .filter(contact -> matchesSetProperty(contact.getEmail(), matchingContact.getEmail()))
                        .filter(contact -> matchesSocialNetworks(contact.getSocialNetworks(), matchingContact.getSocialNetworks()))
                        .collect(Collectors.toSet())
        );

        return foundContacts;
    }

    boolean matchesStringProperty(String name, String matchingName) {
        if(name == null)
            return matchingName == null;

        return matchingName == null || name.contains(matchingName);
    }

    boolean matchesSetProperty(HashSet<String> phone, HashSet<String> matchingPhoneSet) {
        if(phone.isEmpty())
            return matchingPhoneSet.isEmpty();

        return matchingPhoneSet.isEmpty() ||
                phone.stream().anyMatch(
                        str -> str.contains((String)matchingPhoneSet.toArray()[0]));
    }

    boolean matchesSocialNetworks(HashSet<SocialNetworkLink> networks,  HashSet<SocialNetworkLink> matchingNetworkSet) {
        if(networks.isEmpty() || matchingNetworkSet.isEmpty())
            return matchingNetworkSet.isEmpty();

        SocialNetworkLink matchingLink = (SocialNetworkLink)matchingNetworkSet.toArray()[0];
        return networks.stream().anyMatch(link ->
                        link.getSocialNetwork().equals(matchingLink.getSocialNetwork()) &&
                                link.getLink().contains(matchingLink.getLink()));
    }
}
