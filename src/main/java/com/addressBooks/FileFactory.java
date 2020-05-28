package com.addressBooks;

import java.util.List;

public class FileFactory {
    public static IFileManager createObject(FileType fileType) throws FileManagerException {
        if(fileType.equals(FileType.JSON_FILE))
        return  new ReadAndWriteIntoJSONFile();
        if(fileType.equals(FileType.CSV_FILE))
        return new ReadAndWriteInCSVFile();
        throw new FileManagerException("Invalid extension",FileManagerException.ExceptionType.INVALID_EXTENSION);
    }
}
