package com.CheapCleaningAndCleaning.ApplicationStates.MainMenuState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStackStateMachine;
import com.CheapCleaningAndCleaning.ApplicationStates.ChoosingDifficultyState.ChoosingDifficultyState;
import com.CheapCleaningAndCleaning.ApplicationStates.ExitingState.ExitingState;
import com.CheapCleaningAndCleaning.ApplicationStates.OptionsState.OptionsState;
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

public class MainMenuState extends AbstractApplicationState {
    private Skin skin;

    private MainMenuState(ApplicationStackStateMachine stateMachine) {
        super(stateMachine);
    }

    public static MainMenuState getInstance() {
        return InstanceHolder.instance;
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

        final TextButton play = new TextButton("PLAY", skin);
        final TextButton options = new TextButton("OPTIONS", skin);
        final TextButton exit = new TextButton("EXIT", skin);
        table.add(play).minWidth(200).fillX().height(100).pad(25);
        table.row();
        table.add(options).minWidth(200).fillX().height(100).pad(25);
        table.row();
        table.add(exit).minWidth(200).fillX().height(100).pad(25);
        table.pack();

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stateMachine.transitionToState(ChoosingDifficultyState.getInstance());
            }
        });

        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stateMachine.transitionToState(OptionsState.getInstance());
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stateMachine.transitionToState(ExitingState.getInstance());
            }
        });
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
        static MainMenuState instance = new MainMenuState(CheapCleaningAndCleaning.getStateMachine());
    }
}
