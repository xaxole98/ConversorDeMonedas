package com.xaxocode.practices.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import com.xaxocode.modules.ApiConnection;

public class FileWrite {

    private FileWrite() {
        throw new IllegalStateException("Utility class");
    }

    public static void setFileWrite() {
        String jsonToWrite = ApiConnection.getData();
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(
                        new File("conversordemonedas\\src\\main\\resources\\json\\datos.json"))))) {
            bufferedWriter.write(jsonToWrite);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
