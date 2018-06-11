package com.CheapCleaningAndCleaning.ApplicationStates.OptionsState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStackStateMachine;
import com.CheapCleaningAndCleaning.ApplicationStates.PreviousState.PreviousState;
import com.CheapCleaningAndCleaning.CheapCleaningAndCleaning;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class OptionsState extends AbstractApplicationState {
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
        volume.setValue(100);
        ImageTextButton.ImageTextButtonStyle buttonStyle = new ImageTextButton.ImageTextButtonStyle(skin.get(TextButton.TextButtonStyle.class));
        ImageTextButton saveButton = new ImageTextButton("Save settings", buttonStyle);

        Label volumeLabel = new Label("Volume: ", skin);
        volumeLabel.setWrap(true);
        Label volumeValue = new Label(String.valueOf(volume.getValue()), skin);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
//        table.setDebug(true); // DEBUG

        table.add(volumeLabel).minWidth(100).fillX().pad(25);
        table.add(volume).minWidth(200).fillX().pad(25);
        table.add(volumeValue).minWidth(50).fillX().pad(25);
        table.row();
        table.add(saveButton).minWidth(200).colspan(3).pad(25).center();
        table.pack();

        volume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                volumeValue.setText(String.valueOf(volume.getValue()));
            }
        });

        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stateMachine.transitionToState(PreviousState.getInstance());
            }
        });
    }

    private static class InstanceHolder {
        public static OptionsState instance = new OptionsState(CheapCleaningAndCleaning.getStateMachine());
    }
}
