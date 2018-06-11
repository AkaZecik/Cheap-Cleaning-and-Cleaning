package com.CheapCleaningAndCleaning.ApplicationStates.OptionsState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStackStateMachine;
import com.CheapCleaningAndCleaning.ApplicationStates.PreviousState.PreviousState;
import com.CheapCleaningAndCleaning.CheapCleaningAndCleaning;
import com.CheapCleaningAndCleaning.GlobalFunctions;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.io.IOException;
import java.util.HashMap;

public class OptionsState extends AbstractApplicationState {
    HashMap<String, String> settings;
    private Skin skin;

    private OptionsState(ApplicationStackStateMachine stateMachine) {
        super(stateMachine);
    }

    public static OptionsState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter(Game entity) {
        super.enter(entity);
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        try {
            settings = GlobalFunctions.loadSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    stateMachine.revertToPreviousState();
                    return true;
                }

                return false;
            }
        });

        final Slider volume = new Slider(0, 100, 1, false, skin);
        volume.setValue(Float.valueOf(settings.get("volume")));

        ImageTextButton.ImageTextButtonStyle buttonStyle = new ImageTextButton.ImageTextButtonStyle(skin.get(TextButton.TextButtonStyle.class));
        ImageTextButton saveButton = new ImageTextButton("Save settings", buttonStyle);
        ImageTextButton backButton = new ImageTextButton("Back", buttonStyle);

        Label volumeLabel = new Label("Volume: ", skin);
        volumeLabel.setWrap(true);
        Label volumeValue = new Label(String.valueOf(volume.getValue()), skin);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
//        table.setDebug(true); // DEBUG

        table.add(volumeLabel).minWidth(100).fillX().pad(25).colspan(2);
        table.add(volume).minWidth(200).fillX().pad(25).colspan(2);
        table.add(volumeValue).minWidth(50).fillX().pad(25).colspan(2);
        table.row();
        table.add(backButton).minWidth(200).colspan(3).pad(25).center();
        table.add(saveButton).minWidth(200).colspan(3).pad(25).center();
        table.pack();

        volume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                settings.put("volume", String.valueOf(volume.getValue()));
                volumeValue.setText(String.valueOf(volume.getValue()));
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stateMachine.transitionToState(PreviousState.getInstance());
            }
        });

        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    GlobalFunctions.saveSettings(settings);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                stateMachine.transitionToState(PreviousState.getInstance());
            }
        });
    }

    private static class InstanceHolder {
        public static OptionsState instance = new OptionsState(CheapCleaningAndCleaning.getStateMachine());
    }
}
