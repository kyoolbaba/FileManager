package com.addressBooks;

import com.addressbook.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadAndWriteIntoJSONFile implements IFileManager {

    @Override
    public List read(String addressBookName) throws FileManagerException {
       try (Reader reader = Files.newBufferedReader(Paths.get(addressBookName));){
           return new Gson().fromJson(reader, new TypeToken<List<Person>>() {
           }.getType());
       }catch(IOException e){
          throw new FileManagerException("File problem",FileManagerException.ExceptionType.FILE_PROBLEM);
       }
    }

    @Override
    public void write(String addressBookName, List addressBookDetails) throws FileManagerException {
        try (  Writer writer =new FileWriter(addressBookName)){
            new Gson().toJson(addressBookDetails, writer);
        }catch(IOException e){
            throw new FileManagerException("File problem",FileManagerException.ExceptionType.FILE_PROBLEM);
        }
    }


}
