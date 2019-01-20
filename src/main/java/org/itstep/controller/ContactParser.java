package org.itstep.controller;

import org.itstep.model.entities.Contact;
import org.itstep.model.entities.SocialNetwork;
import org.itstep.model.entities.SocialNetworkLink;
import org.itstep.view.AppException;

public class ContactParser {

    public Contact parseContact(String[] args) throws AppException {
        Contact contact = new Contact();

        for(int i = 0; i < args.length;) {
            if(args[i].charAt(0) != '-')
                throw new AppException("Cannot parse contact");
            String property = args[i].substring(1, args[i].length()).toLowerCase();
            switch(property) {
                case "name":
                    contact.setName(args[(i += 2) - 1]);
                    break;
                case "surname":
                    contact.setSurname(args[(i += 2) - 1]);
                    break;
                case "skype":
                    contact.setSkype(args[(i += 2) - 1]);
                    break;
                default:
                    i = addListProperty(args, contact, i, property);
                    break;
            }
        }

        if(contact.getName() == null)
            throw new AppException("Contact should have name");
        return contact;
    }

    private int addListProperty(String[] args, Contact contact, int i, String property) {
        int j = i + 1;
        for(; j < args.length && args[j].charAt(0) != '-'; j++) {
            switch(property) {
                case "phone":
                    contact.getPhone().add(args[j]);
                    break;
                case "email":
                    contact.getEmail().add(args[j]);
                    break;
                case "socialnetworks":
                    contact.getSocialNetworks().add(
                            SocialNetworkLink.of(SocialNetwork.valueOf(args[j].toUpperCase()), args[++j]));
                    break;
                default:
                    throw new AppException("Cannot parse contact");
            }
        }
        return j;
    }
}
