package com.mossonthetree.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileHandler {
    public void save(String fileName, String content) {
        try (FileOutputStream outStream = new FileOutputStream(fileName)) {
            outStream.write(content.getBytes());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String load(String fileName) {
        try (FileInputStream inStream = new FileInputStream(fileName)) {
            byte[] buffer = inStream.readAllBytes();
            return new String(buffer);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }

    public boolean delete(String fileName) {
        File file = new File(fileName);
        return file.delete();
    }
}
