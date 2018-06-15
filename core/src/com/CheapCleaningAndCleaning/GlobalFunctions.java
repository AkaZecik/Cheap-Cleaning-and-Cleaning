package com.CheapCleaningAndCleaning;

import java.io.*;
import java.util.HashMap;

public abstract class GlobalFunctions {
    public static HashMap<String, String> loadSettings() throws IOException {
        File toRead = new File("settings");
        HashMap<String, String> settings = null;

        if (toRead.createNewFile()) {
            settings = new HashMap<>();
            settings.put("volume", "100");
            settings.put("song", "test.mp3");
            saveSettings(settings);
        } else {
            try (FileInputStream fis = new FileInputStream(toRead)) {
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    settings = (HashMap<String, String>) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return settings;
    }

    public static void saveSettings(HashMap<String, String> settings) throws IOException {
        File toWrite = new File("settings");

        try (FileOutputStream fos = new FileOutputStream(toWrite)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(settings);
                oos.flush();
            }
        }
    }
}
