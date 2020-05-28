package com.addressBooks;

public class FileManagerController extends AddressBookController {

    IFileController iFileController;
    public FileManagerController() {
        iFileController =FileManagerControlHandler.createFileManagerController();
    }

    public void createAddressBook(String addressBookName) throws FileManagerException {
       iFileController.createAddressBook(addressBookName);
    }


    public void saveChangesInAddressBook() throws FileManagerException {
    iFileController.saveChangesInAddressBook();
    }

    public void openAddressBook(String addressBookName) throws FileManagerException {
       iFileController.openAddressBook(addressBookName);
    }

    public boolean checkFileExistsOrNot(String addressBookName) throws FileManagerException {
       return iFileController.checkFileExistsOrNot(addressBookName);
    }

    public void saveAsChangesInAddressBook(String newAddressBookName) throws FileManagerException {
        iFileController.saveChangesAs(newAddressBookName);
    }

}
