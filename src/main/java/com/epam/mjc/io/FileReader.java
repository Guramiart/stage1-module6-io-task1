package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> dataMap = new HashMap<>();
        try (java.io.FileReader fileReader = new java.io.FileReader(file)) {
            StringBuilder stringBuilder = new StringBuilder();
            int ch;
            while ((ch = fileReader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            dataMap = getMapFromString(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(dataMap.isEmpty()) {
            return new Profile();
        } else {
            return new Profile(dataMap.get("Name"),
                    Integer.parseInt(dataMap.get("Age")),
                    dataMap.get("Email"),
                    Long.parseLong(dataMap.get("Phone")));
        }
    }

    private Map<String, String> getMapFromString(String str){
        Map<String, String> resultMap = new HashMap<>();
        String[] data = str.split("\r\n");
        for(String el : data) {
            String[] dataArray = el.split(":");
            resultMap.put(dataArray[0].trim(), dataArray[1].trim());
        }
        return resultMap;
    }
}
