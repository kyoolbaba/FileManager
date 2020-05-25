package com.addressBooks;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class AddressBookTest {
public static final String SOURCE_LOCATION="src/test/resources/";
    @Test
    public void createAddressBook_whenInitializedWithCreateAddressBook_shouldCreateJsonFileByNameSupplied() {
       try {
           FileManager fileManager = new FileManager();
           fileManager.createAddressBook("Rajnikanth");
           boolean shouldReturnTrue=fileManager.checkFileExistsOrNot("Rajnikanth");
           File file=new File(SOURCE_LOCATION+"Rajnikanth"+".json");
           file.delete();
           Assert.assertTrue(shouldReturnTrue);
       }catch(FileManagerException e){
           e.printStackTrace();
       }
    }

    @Test
    public void createAddressBook_whenAddressBookexistsAlready_shouldthrowException() {
        try {
            FileManager fileManager = new FileManager();
            fileManager.createAddressBook("Milan");
            boolean shouldReturnTrue=fileManager.checkFileExistsOrNot("Milan");
        }catch(FileManagerException e){
            Assert.assertEquals(FileManagerException.ExceptionType.ADDRESSBOOK_ALREADY_EXISTS,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenNullValue_whenCreatingAddressBook_shouldThrowException() {
        try {
            FileManager fileManager = new FileManager();
            fileManager.createAddressBook(null);
        }catch(FileManagerException e){
            Assert.assertEquals(FileManagerException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmptyValue_whenCreatingAddressBook_shouldThrowException() {
        try {
            FileManager fileManager = new FileManager();
            fileManager.createAddressBook(null);
        }catch(FileManagerException e){
            Assert.assertEquals(FileManagerException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenContactInfo_whenSaved_shouldSaveTheEnteredDataInJsonFile() {
    try {
        FileManager fileManager = new FileManager();
        fileManager.createAddressBook("Kamal");
        fileManager.addPerson("Milan","Gowda","HSRLAYOUT","789456","Bangalore",
                "Karnataka","7894561230");

        fileManager.addPerson("Rahul","kumar","BTM","783356","Bangalore",
                "Karnataka","7894561789");
        fileManager.saveChangesInAddressBook();
    } catch (FileManagerException e) {
        e.printStackTrace();
        }
    }

    @Test
    public void givenJsonFile_whenRead_shouldReturnTheDataPresentAndDataAdded()  {
       try {
           FileManager fileManager = new FileManager();
           fileManager.openAddressBook("Kamal");
           fileManager.addPerson("Rakesh", "kumar",
                   "HSRLayout", "12896", "Bangalore", "Karnataka", "4561327891");
           fileManager.addPerson("Rahul", "kumar",
                   "HSRLayout", "12896", "Mangaore", "Goa", "4561327");
           fileManager.saveChangesInAddressBook();
       }catch(FileManagerException e){
            e.printStackTrace();
       }
    }

//    @Test
//    public void givenJsonFile_whenReadAndSaved_shouldSaveInAnotherFileNameGiven()  {
//        try {
//            FileManager fileManager = new FileManager();
//            fileManager.openAddressBook("Kamal");
//            fileManager.addPerson("Rakesh", "kumar",
//                    "HSRLayout", "12896", "Bangalore", "Karnataka", "4561327891");
//            fileManager.addPerson("Rahul", "kumar",
//                    "HSRLayout", "12896", "Mangaore", "Goa", "4561327");
//            fileManager.saveAsChangesInAddressBook("HrithikRoshan");
//        }catch(FileManagerException e){
//            e.printStackTrace();
//        }
//    }

}

