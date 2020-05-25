package com.addressBooks;

public class FileManagerException extends Exception{

    enum ExceptionType{
        CANNOT_CREATE_ADDRESS_BOOK,ADDRESSBOOK_ALREADY_EXISTS,FILE_PROBLEM,ADDRESSBOOK_DOES_NOT_EXIST
    }
    ExceptionType type;
    public FileManagerException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
