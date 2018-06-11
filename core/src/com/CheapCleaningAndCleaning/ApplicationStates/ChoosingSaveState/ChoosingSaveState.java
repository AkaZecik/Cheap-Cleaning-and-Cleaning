package com.CheapCleaningAndCleaning.ApplicationStates.ChoosingSaveState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStackStateMachine;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.PlayingState;
import com.CheapCleaningAndCleaning.CheapCleaningAndCleaning;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ChoosingSaveState extends AbstractApplicationState {
    private Skin skin;

    private ChoosingSaveState(ApplicationStackStateMachine stateMachine) {
        super(stateMachine);
    }

    public static ChoosingSaveState getInstance() {
        return ChoosingSaveState.InstanceHolder.instance;
    }

    @Override
    public void enter(Game entity) {
        super.enter(entity);
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.getFont("default-font").getData().setScale(2f, 2f);

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

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
//        table.setDebug(true); // DEBUG

        final TextButton save1 = new TextButton("SAVE 1", skin);
        final TextButton save2 = new TextButton("SAVE 2", skin);
        final TextButton save3 = new TextButton("SAVE 3", skin);
        table.add(save1).minWidth(200).fillX().height(100).pad(25);
        table.row();
        table.add(save2).minWidth(200).fillX().height(100).pad(25);
        table.row();
        table.add(save3).minWidth(200).fillX().height(100).pad(25);
        table.pack();

        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stateMachine.transitionToState(PlayingState.getInstance(""));
            }
        };

        save1.addListener(listener);
        save2.addListener(listener);
        save3.addListener(listener);
    }

    @Override
    public void update(Game entity) {
        super.update(entity);
    }

    @Override
    public void exit(Game entity) {
        super.exit(entity);
//        skin.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    private static class InstanceHolder {
        static ChoosingSaveState instance = new ChoosingSaveState(CheapCleaningAndCleaning.getStateMachine());
    }
}
