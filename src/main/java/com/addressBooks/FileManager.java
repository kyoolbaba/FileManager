package com.addressBooks;

import com.addressbook.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileManager extends AddressBook{

    private final static String SOURCE_LOCATION="src/test/resources/";
    Writer writer;

    public void createAddressBook(String addressBookName) throws FileManagerException {
       try{
           if(addressBookName != null&& addressBookName!="") {
               File file = new File(SOURCE_LOCATION+addressBookName+".json");
               if(this.checkFileExistsOrNot(addressBookName)){
                   throw new FileManagerException("file already exists"
                           , FileManagerException.ExceptionType.ADDRESSBOOK_ALREADY_EXISTS);
               }else {
                   AddressBook addressBook=new AddressBook();
                   writer =new FileWriter(SOURCE_LOCATION+addressBookName+".json");
                   file.createNewFile();
               }
           }else{
               throw new FileManagerException("cannot create file with no input or null value"
                       , FileManagerException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK);
           }
       }catch(IOException e){
           throw new FileManagerException("cannot create file with no input or null value"
                   , FileManagerException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK);
       }
    }

    public boolean checkFileExistsOrNot(String addressBookName) throws FileManagerException {
        File file=new File(SOURCE_LOCATION+addressBookName+".json");
      return file.exists();
    }


    public void saveChangesInAddressBook() throws FileManagerException {
      try {
          List<Person> listOfContacts = this.getListOfContacts();
          new Gson().toJson(listOfContacts, writer);
          writer.close();
      }catch(IOException e){
          throw new FileManagerException("File problem",FileManagerException.ExceptionType.FILE_PROBLEM);
      }

    }

    public void openAddressBook(String addressBookName) throws FileManagerException {
        try{
        if(addressBookName!=null&&addressBookName!=""){
           if(this.checkFileExistsOrNot(addressBookName)){
               Reader reader = Files.newBufferedReader(Paths.get(SOURCE_LOCATION+addressBookName+".json"));
                List<Person> listOfContacts=new Gson().fromJson(reader,new TypeToken<List<Person>>(){}.getType());
               AddressBook addressBook = new AddressBook(listOfContacts);
                reader.close();
               writer =new FileWriter(SOURCE_LOCATION+addressBookName+".json");

           }else{
               throw new FileManagerException("file does not exists"
                       , FileManagerException.ExceptionType.ADDRESSBOOK_DOES_NOT_EXIST);
           }
        }else{
            throw new FileManagerException("cannot create file with no input or null value"
                    , FileManagerException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK);
        }
        }catch(IOException e){
            throw new FileManagerException("cannot create file with no input or null value"
                    , FileManagerException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK);
        }
    }

}
