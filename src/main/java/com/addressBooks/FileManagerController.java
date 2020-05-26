package com.addressBooks;

import com.addressbook.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileManagerController extends AddressBook implements IFileManagerController{
    private final static String SOURCE_LOCATION="src/test/resources/";
    public String addressBookFile;

    public void createAddressBook(String addressBookName) throws FileManagerException {
        try{
            addressBookFile =SOURCE_LOCATION+addressBookName+".json";
            if(addressBookName != null&& addressBookName!="") {
                File file = new File(addressBookFile);
                if(this.checkFileExistsOrNot(addressBookName)){
                    throw new FileManagerException("file already exists"
                            , FileManagerException.ExceptionType.ADDRESSBOOK_ALREADY_EXISTS);
                }else {
                    AddressBook addressBook=new AddressBook();
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
        try (  Writer writer =new FileWriter(addressBookFile)){
            List<Person>  listOfContacts = this.getListOfContacts();
            new Gson().toJson(listOfContacts, writer);
        }catch(IOException e){
            throw new FileManagerException("File problem",FileManagerException.ExceptionType.FILE_PROBLEM);
        }

    }

    public void openAddressBook(String addressBookName) throws FileManagerException {
        try{
            addressBookFile=SOURCE_LOCATION+addressBookName+".json";
            if(addressBookName!=null&&addressBookName!=""){
                if(this.checkFileExistsOrNot(addressBookName)){
                    Reader reader = Files.newBufferedReader(Paths.get(addressBookFile));
                    List<Person> listOfContacts=new Gson().fromJson(reader,new TypeToken<List<Person>>(){}.getType());
                    AddressBook addressBook = new AddressBook(listOfContacts);
                    reader.close();
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

    @Override
    public void saveChangesAs(String newAddressBookName) throws FileManagerException {
        try{
            addressBookFile=SOURCE_LOCATION+newAddressBookName+".json";
            File file=new File(addressBookFile);
            if(newAddressBookName != null&& newAddressBookName!="") {
                if(this.checkFileExistsOrNot(newAddressBookName)){
                    throw new FileManagerException("file already exists"
                            , FileManagerException.ExceptionType.ADDRESSBOOK_ALREADY_EXISTS);
                }else {
                    file.createNewFile();
                    this.saveChangesInAddressBook();
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
