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
       }catch(AddressBookException e){
           e.printStackTrace();
       }
    }

    @Test
    public void createAddressBook_whenAddressBookexistsAlready_shouldthrowException() {
        try {
            FileManager fileManager = new FileManager();
            fileManager.createAddressBook("Milan");
            boolean shouldReturnTrue=fileManager.checkFileExistsOrNot("Milan");
        }catch(AddressBookException e){
            Assert.assertEquals(AddressBookException.ExceptionType.ADDRESSBOOK_ALREADY_EXISTS,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void createAddressBook_whenGivenNullValue_shouldThrowException() {
        try {
            FileManager fileManager = new FileManager();
            fileManager.createAddressBook(null);
        }catch(AddressBookException e){
            Assert.assertEquals(AddressBookException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void createAddressBook_whenGivenEmptyValue_shouldThrowException() {
        try {
            FileManager fileManager = new FileManager();
            fileManager.createAddressBook(null);
        }catch(AddressBookException e){
            Assert.assertEquals(AddressBookException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK,e.type);
            e.printStackTrace();
        }
    }

}

