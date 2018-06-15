package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic;

import com.badlogic.gdx.Gdx;

import java.io.*;
import java.util.HashMap;

public class SongDatabase {
    HashMap<String, Double> fileMap;

    public SongDatabase() throws IOException, ClassNotFoundException {
//        File toRead = new File("music/SongsBPM");
//        FileInputStream fis = new FileInputStream(toRead);
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        fileMap = ((HashMap<String, Double>) ois.readObject());
//
//        ois.close();
//        fis.close();

        InputStream fis = Gdx.files.internal("music/SongsBPM").read();
        //File toRead = new File("music/SongsBPM");
        //FileInputStream fis = new FileInputStream(toRead);
        ObjectInputStream ois = new ObjectInputStream(fis);
        fileMap = ((HashMap<String, Double>) ois.readObject());

        ois.close();
        fis.close();
    }

    public Double find(String name) throws IOException, NullPointerException, ClassNotFoundException {
        if (name == null) {
            throw new NullPointerException();
        }
        if (fileMap.get(name) == null) {
            throw new SongNotFoundException();
        } else {
            return fileMap.get(name);
        }
    }

    public void update() throws IOException {
//        File toWrite = new File("music/SongsBPM");
//        FileOutputStream fos = new FileOutputStream(toWrite);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//        oos.writeObject(fileMap);
//        oos.flush();
//        oos.close();
//        fos.close();

        OutputStream fos = Gdx.files.internal("music/SongsBPM").write(true, 0);
        //FileOutputStream fos = new FileOutputStream(toWrite);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(fileMap);
        oos.flush();
        oos.close();
        fos.close();
    }

    public Double add(String name, Double BPM) throws IOException, ClassNotFoundException {
        fileMap.put(name, BPM);
        update();
        return BPM;
    }

    public Double delete(String name) throws IOException, ClassNotFoundException {
        Double val = fileMap.remove(name);
        update();
        return val;
    }

    public class SongNotFoundException extends RuntimeException {
    }
}

