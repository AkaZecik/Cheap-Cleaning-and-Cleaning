package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.MainMenuState.MainMenuState;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic.BPMcalc;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic.BeatChecker;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic.SongDatabase;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Map.Map;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player.Player;
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

public class PlayingState extends AbstractApplicationState {
    private Player player;
    private Music music;
    private BeatChecker currentBeat;
    private Map map;

    private PlayingState() {

    }

    public static PlayingState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter(Game entity) {
        super.enter(entity);
        stage.getCamera().position.x = 0;
        stage.getCamera().position.y = 0;
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    nextState = MainMenuState.getInstance();
                    return true;
                }

                return false;
            }
        });
        String name = "test.mp3";
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
        }
        Path path = Paths.get("core/assets/music/" + name);
        if (BPM == -1.0) {
            try {
                BPM = new BPMcalc(AudioSystem.getAudioInputStream(path.toFile()), 131072).bpm();
                sd.add(name, BPM);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        currentBeat = new BeatChecker(BPM);
        currentBeat.start();
        player = new Player();
        player.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (currentBeat.IsPermitted()) {
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

        music = Gdx.audio.newMusic(Gdx.files.internal("music/" + name));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

        stage.addActor(player);
        stage.setKeyboardFocus(player);

        map = new Map(player);
        stage.addActor(map);

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
        currentBeat.render(stage.getBatch());
    }

    private static class InstanceHolder {
        static PlayingState instance = new PlayingState();
    }
}
