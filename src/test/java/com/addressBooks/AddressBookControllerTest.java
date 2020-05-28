package com.addressBooks;

import com.addressbook.Person;
import com.addressbook.SearchUpdateAndDeleteBy;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class AddressBookControllerTest {
public static final String SOURCE_LOCATION="src/test/resources/";
    @Test
    public void createAddressBook_whenInitializedWithCreateAddressBook_shouldCreateJsonFileByNameSupplied() {
       try {
           FileManagerController fileManagerController = new FileManagerController();
           fileManagerController.createAddressBook("Rajnikanth.json");
           boolean shouldReturnTrue= fileManagerController.checkFileExistsOrNot("Rajnikanth.json");
           File file=new File(SOURCE_LOCATION+"Rajnikanth.json");
           file.delete();
           Assert.assertTrue(shouldReturnTrue);
       }catch(FileManagerException e){
           e.printStackTrace();
       }
    }

    @Test
    public void createAddressBook_whenAddressBookexistsAlready_shouldthrowException() {
        try {
            FileManagerController fileManagerController = new FileManagerController();
            fileManagerController.createAddressBook("Milan.json");
        }catch(FileManagerException e){
            Assert.assertEquals(FileManagerException.ExceptionType.ADDRESSBOOK_ALREADY_EXISTS,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenNullValue_whenCreatingAddressBook_shouldThrowException() {
        try {
            FileManagerController fileManagerController = new FileManagerController();
            fileManagerController.createAddressBook(null);
        }catch(FileManagerException e){
            Assert.assertEquals(FileManagerException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenEmptyValue_whenCreatingAddressBook_shouldThrowException() {
        try {
            FileManagerController fileManagerController = new FileManagerController();
            fileManagerController.createAddressBook("");
        }catch(FileManagerException e){
            Assert.assertEquals(FileManagerException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenContactInfo_whenSaved_shouldSaveTheEnteredDataInJsonFile() {
    try {
        FileManagerController fileManagerController = new FileManagerController();
        fileManagerController.createAddressBook("RajniKamal.json");
        fileManagerController.addPerson("Milan","Gowda","HSRLAYOUT","789456","Bangalore",
                "Karnataka","7894561230");

        fileManagerController.addPerson("Rahul","kumar","BTM","783356","Bangalore",
                "Karnataka","7894561789");
        fileManagerController.saveChangesInAddressBook();
        Assert.assertEquals(2, fileManagerController.getNoOfRecordsInTheAddressBook());
    } catch (FileManagerException e) {
        e.printStackTrace();
        }
    }

    @Test
    public void givenJsonFile_whenRead_shouldReturnTheDataPresentAndDataAdded()  {
       try {
           FileManagerController fileManagerController = new FileManagerController();
           fileManagerController.openAddressBook("RajniKamal.json");
           fileManagerController.addPerson("Rakesh", "kumar",
                   "HSRLayout", "12896", "Bangalore", "Karnataka", "4561327891");
           fileManagerController.addPerson("Rahul", "kumar",
                   "HSRLayout", "12896", "Mangaore", "Goa", "4561327");
           fileManagerController.saveChangesInAddressBook();
           Assert.assertEquals(4, fileManagerController.getNoOfRecordsInTheAddressBook());
           File file=new File(SOURCE_LOCATION+"RajniKamal.json");
           file.delete();
       }catch(FileManagerException e){
            e.printStackTrace();
       }
    }

    @Test
    public void givenJsonFile_whenReadAndSaved_shouldSaveInAnotherFileNameGiven()  {
        try {
            FileManagerController fileManagerController = new FileManagerController();
            fileManagerController.openAddressBook("KamallHassan.json");
            fileManagerController.addPerson("Rakesh", "kumar",
                    "HSRLayout", "12896", "Bangalore", "Karnataka", "4561327891");
            fileManagerController.addPerson("Rahul", "kumar",
                    "HSRLayout", "12896", "Mangaore", "Goa", "4561327");
            fileManagerController.saveAsChangesInAddressBook("HrithikRoshan.json");
            Assert.assertEquals(6, fileManagerController.getNoOfRecordsInTheAddressBook());
            File file=new File(SOURCE_LOCATION+"HrithikRoshan.json");
            file.delete();
        }catch(FileManagerException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenUpdatedCity_shouldReturnUpdatedCity() {
       try{
           FileManagerController fileManagerController = new FileManagerController();
        fileManagerController.openAddressBook("KamallHassan.json");
        fileManagerController.updateContact(SearchUpdateAndDeleteBy.UPDATE_CITY,"Milan","London");
        Person[] personDetails= fileManagerController.searchPersonDetails("Milan").toArray(new Person[0]);
        Assert.assertEquals("London",personDetails[0].getCity());
           fileManagerController.updateContact(SearchUpdateAndDeleteBy.UPDATE_CITY,"Milan","Bangalore");
           fileManagerController.saveChangesInAddressBook();
    }catch(FileManagerException e){
           e.printStackTrace();
       }
    }

    @Test
    public void givenAddressBook_whenUpdatedState_shouldReturnUpdatedState() {
        try{
            FileManagerController fileManagerController = new FileManagerController();
            fileManagerController.openAddressBook("KamallHassan.json");
            fileManagerController.updateContact(SearchUpdateAndDeleteBy.UPDATE_STATE,"Milan","Telangana");
            Person[] personDetails= fileManagerController.searchPersonDetails("Milan").toArray(new Person[0]);
            Assert.assertEquals("Telangana",personDetails[0].getState());
            fileManagerController.updateContact(SearchUpdateAndDeleteBy.UPDATE_STATE,"Milan","Karnataka");
            fileManagerController.saveChangesInAddressBook();
        }catch(FileManagerException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenUpdatedPhoneNumber_shouldReturnUpdatedPhoneNumber() {
        try{
            FileManagerController fileManagerController = new FileManagerController();
            fileManagerController.openAddressBook("KamallHassan.json");
            fileManagerController.updateContact(SearchUpdateAndDeleteBy.UPDATE_PHONENUMBER,"Milan","123123123");
            Person[] personDetails= fileManagerController.searchPersonDetails("Milan").toArray(new Person[0]);
            Assert.assertEquals("123123123",personDetails[0].getPhoneNumber());
            fileManagerController.updateContact(SearchUpdateAndDeleteBy.UPDATE_PHONENUMBER,"Milan","7777777777");
            fileManagerController.saveChangesInAddressBook();
        }catch(FileManagerException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenUpdatedPinCode_shouldReturnUpdatedPinCode() {
        try{
            FileManagerController fileManagerController = new FileManagerController();
            fileManagerController.openAddressBook("KamallHassan.json");
            fileManagerController.updateContact(SearchUpdateAndDeleteBy.UPDATE_PINCODE,"Milan","000000");
            Person[] personDetails= fileManagerController.searchPersonDetails("Milan").toArray(new Person[0]);
            Assert.assertEquals("000000",personDetails[0].getPincode());
            fileManagerController.updateContact(SearchUpdateAndDeleteBy.UPDATE_PINCODE,"Milan","123456");
        }catch(FileManagerException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_whenUpdatedAddress_shouldReturnUpdatedAddress() {
        try{
            FileManagerController fileManagerController = new FileManagerController();
            fileManagerController.openAddressBook("KamallHassan.json");
            fileManagerController.updateContact(SearchUpdateAndDeleteBy.UPDATE_ADDRESS,"Milan","Malleshwaram");
            Person[] personDetails= fileManagerController.searchPersonDetails("Milan").toArray(new Person[0]);
            Assert.assertEquals("Malleshwaram",personDetails[0].getAddress());
            fileManagerController.updateContact(SearchUpdateAndDeleteBy.UPDATE_PINCODE,"Milan","HSRLAYOUT");
            fileManagerController.saveChangesInAddressBook();
        }catch(FileManagerException e){
            e.printStackTrace();
        }
    }

}

