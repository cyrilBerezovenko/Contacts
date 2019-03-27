package org.itstep.model;

import org.itstep.model.entities.Contact;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

@Component
public class ContactSet extends TreeSet<Contact> {

    public ContactSet() {
        super(Comparator.comparing(Contact::getName));
    }

    @Override
    public boolean contains(Object o) {
        return stream().anyMatch(c -> Objects.equals(c, o));
    }

    @Override
    public boolean add(Contact contact) {
        if(contact == null || contact.getName() == null || contact.getName().equals("")
                || contact.getSocialNetworks().stream().anyMatch(
                link -> link.getSocialNetwork() == null))
            return false;
        Object[] sameContact = stream()
                .filter(cont -> cont.getName().equals(contact.getName()))
                .toArray();
        if(sameContact.length == 0)
            return super.add(contact);
        Contact newContact = (Contact)sameContact[0];
        if(contact.equals(newContact)) return false;
        remove(newContact);
        newContact.setSurname(contact.getSurname() == null ? newContact.getSurname() : contact.getSurname());
        newContact.setSkype(contact.getSkype() == null ? newContact.getSkype() : contact.getSkype());
        newContact.getEmail().addAll(contact.getEmail());
        newContact.getPhone().addAll(contact.getPhone());
        newContact.getSocialNetworks().addAll(contact.getSocialNetworks());
        return super.add(newContact);
    }
}
