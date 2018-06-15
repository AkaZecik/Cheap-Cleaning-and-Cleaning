package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStackStateMachine;
import com.CheapCleaningAndCleaning.ApplicationStates.MainMenuState.MainMenuState;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic.BPMcalc;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic.BeatChecker;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic.SongDatabase;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.BPMhud.BPMhud;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Map.Map;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player.Player;
import com.CheapCleaningAndCleaning.CheapCleaningAndCleaning;
import com.CheapCleaningAndCleaning.GlobalFunctions;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class PlayingState extends AbstractApplicationState {
    private Player player;
    private String song;
    private Music music;
    private BeatChecker currentBeat;
    private Map map;
    private HashMap<String, String> settings;

    private PlayingState(ApplicationStackStateMachine stateMachine, String song) {
        super(stateMachine);
        this.song = song;
    }

    public static PlayingState getInstance(String song) {
        return new PlayingState(CheapCleaningAndCleaning.getStateMachine(), song);
    }

    @Override
    public void enter(Game entity) {
        super.enter(entity);

        try {
            settings = GlobalFunctions.loadSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.getCamera().position.x = 0;
        stage.getCamera().position.y = 0;
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    stateMachine.transitionToState(MainMenuState.getInstance());
                    return true;
                }

                return false;
            }
        });

        String name = settings.get("song");
//        System.out.println(settings);
        SongDatabase sd = null;

        try {
            sd = new SongDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        double BPM = -1.0;

        try {
            BPM = sd.find(name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SongDatabase.SongNotFoundException e) {
            Path path = Paths.get("music/" + name);
            try {
                BPM = new BPMcalc(AudioSystem.getAudioInputStream(path.toFile()), 131072).bpm();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            }
//            try {
//                sd.add(name, BPM);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            } catch (ClassNotFoundException e1) {
//                e1.printStackTrace();
//            }
        }

        player = new Player(stateMachine);
        player.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (currentBeat.isPermitted()) {
                    switch (keycode) {
                        case Input.Keys.UP:
                            player.moveUp();
                            return true;
                        case Input.Keys.RIGHT:
                            player.moveRight();
                            return true;
                        case Input.Keys.DOWN:
                            player.moveDown();
                            return true;
                        case Input.Keys.LEFT:
                            player.moveLeft();
                            return true;
                    }
                }
                return false;
            }
        });

        currentBeat = new BeatChecker(BPM);
        music = Gdx.audio.newMusic(Gdx.files.internal("music/" + name));
        music.setLooping(true);
        music.setVolume(Float.valueOf(settings.get("volume")) / 100);
        music.play();
        currentBeat.start();

        stage.addActor(player);
        stage.setKeyboardFocus(player);

        map = new Map(player);
        stage.addActor(map);
        BPMhud bPMhud = new BPMhud(currentBeat, BPM);
        stage.addActor(bPMhud);

        map.setZIndex(0);
    }

    @Override
    public void update(Game entity) {
        super.update(entity);
    }

    @Override
    public void exit(Game entity) {
        currentBeat.interrupt();
        music.dispose();
        super.exit(entity);
    }

    @Override
    public void render() {
        super.render();
        //currentBeat.render(stage.getBatch());
    }
}
