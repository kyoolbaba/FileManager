package com.addressBooks;

import java.util.List;

public interface IFileManager {

    public List read(String addressBookName) throws FileManagerException;

    public void write(String addressBookName, List addressBookDetails) throws FileManagerException;
}
