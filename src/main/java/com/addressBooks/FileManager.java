package com.addressBooks;

import com.addressbook.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileManager extends AddressBook{

    IFileManagerController iFileManagerController;
    public FileManager() {
        iFileManagerController=FileManagerControlHandler.createFileManagerController();
    }

    public void createAddressBook(String addressBookName) throws FileManagerException {
       iFileManagerController.createAddressBook(addressBookName);
    }


    public void saveChangesInAddressBook() throws FileManagerException {
    iFileManagerController.saveChangesInAddressBook();
    }

    public void openAddressBook(String addressBookName) throws FileManagerException {
       iFileManagerController.openAddressBook(addressBookName);
    }

    public boolean checkFileExistsOrNot(String addressBookName) throws FileManagerException {
       return iFileManagerController.checkFileExistsOrNot(addressBookName);
    }

    public void saveAsChangesInAddressBook(String newAddressBookName) throws FileManagerException {
        iFileManagerController.saveChangesAs(newAddressBookName);
    }
}
