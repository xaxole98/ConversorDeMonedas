package com.xaxocode.practices.file;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileRead {

    private FileRead() {
        throw new IllegalStateException("Utility class");
    }

    public static String getFileRead() {
        String line;
        String jsonLine = "";
        try (FileReader file = new FileReader("conversordemonedas\\src\\main\\resources\\json\\datos.json");) {
            BufferedReader reader = new BufferedReader(file);
            StringBuilder bld = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                bld.append(line);
            }
            jsonLine = bld.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonLine;
    }
}
