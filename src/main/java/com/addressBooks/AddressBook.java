package com.addressBooks;

import com.addressbook.AddressBookBuilderFactory;
import com.addressbook.IAddressBookBuilder;
import com.addressbook.Person;
import com.addressbook.SearchUpdateAndDeleteBy;

import java.util.List;

public class AddressBook {


    IAddressBookBuilder iAddressBookBuilder;

    public AddressBook() {
        iAddressBookBuilder =AddressBookBuilderFactory.createAddressBookControlller();
    }

    public AddressBook(List<Person> list) {
        iAddressBookBuilder =AddressBookBuilderFactory.accessAddressBookControlller(list);
    }

    public void addPerson(String firstName, String lastName, String address, String pincode, String city,
                          String state, String phoneNumber){
        iAddressBookBuilder.addPerson(firstName, lastName, address,pincode,city,state,phoneNumber);
    }

    public String printAll(SearchUpdateAndDeleteBy search){
        return iAddressBookBuilder.printAll(search);
    }

    public int getNoOfRecordsInTheAddressBook(){
        return iAddressBookBuilder.getNoOfRecordsInTheAddressBook().size();
    }

    public List<Person> getListOfContacts(){
        return iAddressBookBuilder.getNoOfRecordsInTheAddressBook();
    }

    public void deleteContact(String firstName){
        iAddressBookBuilder.deleteContact(firstName);
    }

    public void updateContact(SearchUpdateAndDeleteBy updateBy,String firstName,String modify){
        iAddressBookBuilder.updateContact(updateBy,firstName,modify);
    }

    public List<Person> searchPersonDetails(String firstName){
        return iAddressBookBuilder.searchPersonDetails(firstName);
    }

}
