package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic;

import java.io.*;
import java.util.HashMap;

public class SongDatabase {
    public static Double find(String name) throws IOException, NullPointerException, ClassNotFoundException {
        if (name == null) throw new NullPointerException();

        File toRead = new File("music/SongsBPM");
        FileInputStream fis = new FileInputStream(toRead);
        ObjectInputStream ois = new ObjectInputStream(fis);

        HashMap<String, Double> fileMap = ((HashMap<String, Double>) ois.readObject());
        ois.close();
        fis.close();

        return fileMap.getOrDefault(name, -1.0);
    }

    public static Double add(String name, Double BPM) throws IOException, ClassNotFoundException {
        File toRead = new File("music/SongsBPM");
        FileInputStream fis = new FileInputStream(toRead);
        ObjectInputStream ois = new ObjectInputStream(fis);

        HashMap<String, Double> fileMap = ((HashMap<String, Double>) ois.readObject());
        ois.close();
        fis.close();

        fileMap.put(name, BPM);

        File toWrite = new File("music/SongsBPM");
        FileOutputStream fos = new FileOutputStream(toWrite);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(fileMap);
        oos.flush();
        oos.close();
        fos.close();

        return BPM;
    }

    public static Double delete(String name) throws IOException, ClassNotFoundException {
        File toRead = new File("music/SongsBPM");
        FileInputStream fis = new FileInputStream(toRead);
        ObjectInputStream ois = new ObjectInputStream(fis);

        HashMap<String, Double> fileMap = ((HashMap<String, Double>) ois.readObject());
        ois.close();
        fis.close();

        Double val = fileMap.remove(name);

        File toWrite = new File("music/SongsBPM");
        FileOutputStream fos = new FileOutputStream(toWrite);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(fileMap);
        oos.flush();
        oos.close();
        fos.close();

        return val;
    }
}
