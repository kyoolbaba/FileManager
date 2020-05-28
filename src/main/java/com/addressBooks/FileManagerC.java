package com.addressBooks;

import com.addressbook.Person;
import java.io.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileManagerC extends AddressBookController implements IFileController {
    private final static String SOURCE_LOCATION="src/test/resources/";
    public  String addressBookFile;
    IFileManager readOrWriteFile;
    FileType fileType;

    public void createAddressBook(String addressBookName) throws FileManagerException {
        addressBookFile=SOURCE_LOCATION+addressBookName;
        try{
            addressBookFile =SOURCE_LOCATION+addressBookName;
            if(addressBookName != null&& addressBookName!="") {
                File file = new File(addressBookFile);
                if(this.checkFileExistsOrNot(addressBookFile)){
                    throw new FileManagerException("file already exists"
                            , FileManagerException.ExceptionType.ADDRESSBOOK_ALREADY_EXISTS);
                }else {
                    file.createNewFile();
                    AddressBookController addressBookController =new AddressBookController();
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
    public boolean checkFileExistsOrNot(String addressBookName) throws FileManagerException {
        File file=new File(addressBookFile);
        return file.exists();
    }

    public void saveChangesInAddressBook() throws FileManagerException {
        this.checkFileType();
        List<Person>  listOfContacts = this.getListOfContacts();
        readOrWriteFile.write(addressBookFile, listOfContacts);
    }

    private void checkFileType() throws FileManagerException {
        Predicate<String> jsonFile= Pattern.compile(".json$",Pattern.CASE_INSENSITIVE).asPredicate();
        Predicate<String> csvFile= Pattern.compile(".csv$",Pattern.CASE_INSENSITIVE).asPredicate();
        if(jsonFile.test(addressBookFile))
          fileType=FileType.JSON_FILE;
        if(csvFile.test(addressBookFile))
            fileType=FileType.CSV_FILE;
        readOrWriteFile= FileFactory.createObject(fileType);
    }

    @Override
    public void openAddressBook(String addressBookName) throws FileManagerException {
        addressBookFile=SOURCE_LOCATION+addressBookName;
            if(addressBookName!=null&&addressBookName!=""){
                if(this.checkFileExistsOrNot(addressBookName)){
                    addressBookFile=SOURCE_LOCATION+addressBookName;
                }else{
                    throw new FileManagerException("file does not exists"
                            , FileManagerException.ExceptionType.ADDRESSBOOK_DOES_NOT_EXIST);
                }
            }else{
                throw new FileManagerException("cannot create file with no input or null value"
                        , FileManagerException.ExceptionType.CANNOT_CREATE_ADDRESS_BOOK);
            }
            this.checkFileType();
        List listOfContacts=readOrWriteFile.read(addressBookFile);
        AddressBookController addressBookController = new AddressBookController(listOfContacts);
    }

    @Override
    public void saveChangesAs(String newAddressBookName) throws FileManagerException {
        try{
            addressBookFile=SOURCE_LOCATION+newAddressBookName+".json";
            File file=new File(addressBookFile);
            if(newAddressBookName != null&& newAddressBookName!="") {
                if(this.checkFileExistsOrNot(addressBookFile)){
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
