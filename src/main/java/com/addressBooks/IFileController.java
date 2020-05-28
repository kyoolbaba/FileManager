package com.addressBooks;

public interface IFileController {

    public void createAddressBook(String addressBookName) throws FileManagerException;

    public void saveChangesInAddressBook() throws FileManagerException;

    public void openAddressBook(String addressBookName) throws FileManagerException;

    public boolean checkFileExistsOrNot(String addressBookName) throws FileManagerException;

    public void saveChangesAs(String newAddressBookName) throws FileManagerException;
}
