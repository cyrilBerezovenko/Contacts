package org.itstep.view;

public class View {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String message) {
        System.err.println("Error : " + message);
    }

    public void printUsage() {
        System.out.println("Usage :");
        System.out.println("list - print all contacts");
        System.out.println("exit - exit program");
        System.out.println("load [fileName] - load contacts from json file");
        System.out.println("Add contact :");
        System.out.println("add -[property] [value]...");
        System.out.println("Find contact by matching entered properties:");
        System.out.println("find -[property] [value]...");
        System.out.println("Properties : name, surname, skype");
        System.out.println("List properties : phone, email, socialNetworks");
    }
}
