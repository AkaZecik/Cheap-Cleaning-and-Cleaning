package com.CheapCleaningAndCleaning.ApplicationStates.ChoosingDifficultyState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStackStateMachine;
import com.CheapCleaningAndCleaning.ApplicationStates.ChoosingSaveState.ChoosingSaveState;
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

public class ChoosingDifficultyState extends AbstractApplicationState {
    private Skin skin;

    private ChoosingDifficultyState(ApplicationStackStateMachine stateMachine) {
        super(stateMachine);
    }

    public static ChoosingDifficultyState getInstance() {
        return ChoosingDifficultyState.InstanceHolder.instance;
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

        final TextButton easy = new TextButton("EASY", skin);
        final TextButton medium = new TextButton("MEDIUM", skin);
        final TextButton hard = new TextButton("TCS", skin);
        table.add(easy).minWidth(200).fillX().height(100).pad(25);
        table.row();
        table.add(medium).minWidth(200).fillX().height(100).pad(25);
        table.row();
        table.add(hard).minWidth(200).fillX().height(100).pad(25);
        table.pack();

        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stateMachine.transitionToState(ChoosingSaveState.getInstance());
            }
        };

        easy.addListener(listener);
        medium.addListener(listener);
        hard.addListener(listener);
    }

    @Override
    public void update(Game entity) {
        super.update(entity);
    }

    @Override
    public void exit(Game entity) {
        super.exit(entity);
        skin.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    private static class InstanceHolder {
        static ChoosingDifficultyState instance = new ChoosingDifficultyState(CheapCleaningAndCleaning.getStateMachine());
    }
}
