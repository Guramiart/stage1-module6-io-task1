package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        final String DELIMITER = ":";
        String[] data = {};
        try (java.io.FileReader fileReader = new java.io.FileReader(file)) {
            StringBuilder stringBuilder = new StringBuilder();
            int ch;
            while ((ch = fileReader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            data = stringBuilder.toString().split("\r");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Profile(
                (data[0].split(DELIMITER))[1].trim(),
                Integer.parseInt((data[1].split(DELIMITER))[1].trim()),
                (data[2].split(DELIMITER))[1].trim(),
                Long.parseLong((data[3].split(DELIMITER))[1].trim()));
    }
}
