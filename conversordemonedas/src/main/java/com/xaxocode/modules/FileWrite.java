package com.xaxocode.modules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileWrite {
    /**
     * We are saying that the FileWrite class is a Utility class through the
     * constructor and an exception that will be met in every file when using throw
     */
    private FileWrite() {
        throw new IllegalStateException("Utility class");
    }

    /*
     * BufferedWriter: This class is used to write characters to an output stream
     * efficiently, which is useful when you need to write large amounts of text.
     * 
     * (BufferedWriter bufferedWriter = ...): Here, we are using the
     * try-with-resources statement to ensure that the BufferedWriter is closed
     * properly after use. This is important to free up resources and ensure that
     * data is written to the file correctly.
     * 
     * new OutputStreamWriter(...): This is used to convert the characters to be
     * written to the file into a stream of bytes. You are connecting the
     * BufferedWriter to the output byte stream.
     * 
     * new FileOutputStream(new File("data.json")): Here, a byte output stream is
     * created pointing to the file "data.json". This provides the location and name
     * of the file to which the content will be written.
     * 
     * bufferedWriter.write(responsebody): Finally, we use the BufferedWriter to
     * write the content stored in the responsebody variable to the "data.json"
     * file. This line effectively saves the content obtained from the API to the
     * file.
     * 
     * In short, this block of code is responsible for writing the content of the
     * responsebody to a file called "data.json" using a character stream and a byte
     * stream to ensure that the write is done efficiently and correctly
     */

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

    public static void main(String[] args) {
        setFileWrite();
    }

}
