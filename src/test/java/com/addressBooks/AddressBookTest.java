package com.addressBooks;

import com.addressbook.Person;
import com.addressbook.SearchUpdateAndDeleteBy;
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
            fileManager.createAddressBook("");
        }catch(FileManagerException e){
            Assert.assertEquals(FileManagerException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenContactInfo_whenSaved_shouldSaveTheEnteredDataInJsonFile() {
    try {
        FileManager fileManager = new FileManager();
        fileManager.createAddressBook("RajniKamal");
        fileManager.addPerson("Milan","Gowda","HSRLAYOUT","789456","Bangalore",
                "Karnataka","7894561230");

        fileManager.addPerson("Rahul","kumar","BTM","783356","Bangalore",
                "Karnataka","7894561789");
        fileManager.saveChangesInAddressBook();
        Assert.assertEquals(2,fileManager.getNoOfRecordsInTheAddressBook());
    } catch (FileManagerException e) {
        e.printStackTrace();
        }
    }

    @Test
    public void givenJsonFile_whenRead_shouldReturnTheDataPresentAndDataAdded()  {
       try {
           FileManager fileManager = new FileManager();
           fileManager.openAddressBook("RajniKamal");
           fileManager.addPerson("Rakesh", "kumar",
                   "HSRLayout", "12896", "Bangalore", "Karnataka", "4561327891");
           fileManager.addPerson("Rahul", "kumar",
                   "HSRLayout", "12896", "Mangaore", "Goa", "4561327");
           fileManager.saveChangesInAddressBook();
           Assert.assertEquals(4,fileManager.getNoOfRecordsInTheAddressBook());
           File file=new File(SOURCE_LOCATION+"RajniKamal"+".json");
           file.delete();
       }catch(FileManagerException e){
            e.printStackTrace();
       }
    }

    @Test
    public void givenJsonFile_whenReadAndSaved_shouldSaveInAnotherFileNameGiven()  {
        try {
            FileManager fileManager = new FileManager();
            fileManager.openAddressBook("KamallHassan");
            fileManager.addPerson("Rakesh", "kumar",
                    "HSRLayout", "12896", "Bangalore", "Karnataka", "4561327891");
            fileManager.addPerson("Rahul", "kumar",
                    "HSRLayout", "12896", "Mangaore", "Goa", "4561327");
            fileManager.saveAsChangesInAddressBook("HrithikRoshan");
            Assert.assertEquals(6,fileManager.getNoOfRecordsInTheAddressBook());
            File file=new File(SOURCE_LOCATION+"HrithikRoshan"+".json");
            file.delete();
        }catch(FileManagerException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenUpdatedCity_shouldReturnUpdatedCity() {
       try{
           FileManager fileManager = new FileManager();
        fileManager.openAddressBook("KamallHassan");
        fileManager.updateContact(SearchUpdateAndDeleteBy.UPDATE_CITY,"Milan","London");
        Person[] personDetails=fileManager.searchPersonDetails("Milan").toArray(new Person[0]);
        Assert.assertEquals("London",personDetails[0].getCity());
           fileManager.updateContact(SearchUpdateAndDeleteBy.UPDATE_CITY,"Milan","Bangalore");
           fileManager.saveChangesInAddressBook();
    }catch(FileManagerException e){
           e.printStackTrace();
       }
    }

    @Test
    public void givenAddressBook_whenUpdatedState_shouldReturnUpdatedState() {
        try{
            FileManager fileManager = new FileManager();
            fileManager.openAddressBook("KamallHassan");
            fileManager.updateContact(SearchUpdateAndDeleteBy.UPDATE_STATE,"Milan","Telangana");
            Person[] personDetails=fileManager.searchPersonDetails("Milan").toArray(new Person[0]);
            Assert.assertEquals("Telangana",personDetails[0].getState());
            fileManager.updateContact(SearchUpdateAndDeleteBy.UPDATE_STATE,"Milan","Karnataka");
            fileManager.saveChangesInAddressBook();
        }catch(FileManagerException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenUpdatedPhoneNumber_shouldReturnUpdatedPhoneNumber() {
        try{
            FileManager fileManager = new FileManager();
            fileManager.openAddressBook("KamallHassan");
            fileManager.updateContact(SearchUpdateAndDeleteBy.UPDATE_PHONENUMBER,"Milan","123123123");
            Person[] personDetails=fileManager.searchPersonDetails("Milan").toArray(new Person[0]);
            Assert.assertEquals("123123123",personDetails[0].getPhoneNumber());
            fileManager.updateContact(SearchUpdateAndDeleteBy.UPDATE_PHONENUMBER,"Milan","7777777777");
            fileManager.saveChangesInAddressBook();
        }catch(FileManagerException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenUpdatedPinCode_shouldReturnUpdatedPinCode() {
        try{
            FileManager fileManager = new FileManager();
            fileManager.openAddressBook("KamallHassan");
            fileManager.updateContact(SearchUpdateAndDeleteBy.UPDATE_PINCODE,"Milan","000000");
            Person[] personDetails=fileManager.searchPersonDetails("Milan").toArray(new Person[0]);
            Assert.assertEquals("000000",personDetails[0].getPincode());
            fileManager.updateContact(SearchUpdateAndDeleteBy.UPDATE_PINCODE,"Milan","123456");

        }catch(FileManagerException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenUpdatedAddress_shouldReturnUpdatedAddress() {
        try{
            FileManager fileManager = new FileManager();
            fileManager.openAddressBook("KamallHassan");
            fileManager.updateContact(SearchUpdateAndDeleteBy.UPDATE_ADDRESS,"Milan","Malleshwaram");
            Person[] personDetails=fileManager.searchPersonDetails("Milan").toArray(new Person[0]);
            Assert.assertEquals("Malleshwaram",personDetails[0].getAddress());
            fileManager.updateContact(SearchUpdateAndDeleteBy.UPDATE_PINCODE,"Milan","HSRLAYOUT");
            fileManager.saveChangesInAddressBook();
        }catch(FileManagerException e){
            e.printStackTrace();
        }
    }

}

