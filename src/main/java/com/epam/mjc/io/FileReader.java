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
        if (dataMap.isEmpty()) {
            return new Profile();
        } else {
            String name = dataMap.get("Name");
            String email = dataMap.get("Email");
            Integer age;
            try {
                age = Integer.parseInt(dataMap.get("Age"));
            } catch (NumberFormatException e) {
                age = null;
            }
            Long phone;
            try {
                phone = Long.parseLong(dataMap.get("Phone"));
            } catch (NumberFormatException e) {
                phone = null;
            }
            return new Profile(name, age, email, phone);
        }
    }

    private Map<String, String> getMapFromString(String str) {
        Map<String, String> resultMap = new HashMap<>();
        String[] data = str.split("\r\n");
        for (String el : data) {
            String[] dataArray = el.split(":");
            if (dataArray.length == 2) {
                resultMap.put(dataArray[0].trim(), dataArray[1].trim());
            } else {
                resultMap.put(dataArray[0].trim(), null);
            }
        }
        return resultMap;
    }

}
