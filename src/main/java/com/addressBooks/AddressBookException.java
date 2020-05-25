package com.addressBooks;

public class AddressBookException extends Exception{

    enum ExceptionType{
        CANNOT_CREATE_ADDRESS_BOOK,ADDRESSBOOK_ALREADY_EXISTS
    }
    ExceptionType type;
    public AddressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
