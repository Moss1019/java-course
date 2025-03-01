package com.mossonthetree;

import java.io.FileOutputStream;
import java.util.UUID;

public class JsonSerializer {
    public void write(JsonSerializable object) {
        String fileName = UUID.randomUUID().toString() + ".json";

        String json = object.toJson();

        try (FileOutputStream fOut = new FileOutputStream(fileName)) {
            fOut.write(json.getBytes());
        } catch (Exception ex) {
            System.out.println("Your file didn't write");
        }
    }
}
