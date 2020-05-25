package com.addressBooks;

import java.io.File;
import java.io.IOException;
public class FileManager {

    private final static String SOURCE_LOCATION="src/test/resources/";

    public void createAddressBook(String addressBookName) throws  AddressBookException {
       try{
           if(addressBookName != null&& addressBookName!="") {
               File file = new File(SOURCE_LOCATION+addressBookName+".json");
               if(this.checkFileExistsOrNot(addressBookName)){
                   throw new AddressBookException("file already exists"
                           ,AddressBookException.ExceptionType.ADDRESSBOOK_ALREADY_EXISTS);
               }else {
                   file.createNewFile();
               }
           }else{
               throw new AddressBookException("cannot create file with no input or null value"
                       ,AddressBookException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK);
           }
       }catch(IOException e){
           throw new AddressBookException("cannot create file with no input or null value"
                   ,AddressBookException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK);
       }
    }

    public boolean checkFileExistsOrNot(String addressBookName) throws AddressBookException {
        File file=new File(SOURCE_LOCATION+addressBookName+".json");
      return file.exists();
    }


}
