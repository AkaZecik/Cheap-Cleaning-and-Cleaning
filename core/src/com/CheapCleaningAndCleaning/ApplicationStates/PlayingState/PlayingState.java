package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class PlayingState extends AbstractApplicationState {
    Player player = new Player();

    private PlayingState() {

    }

    public static PlayingState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter(Game entity) {
        super.enter(entity);
        player = new Player();
        player.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
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

                return false;
            }
        });

        stage.addActor(player);
        stage.setKeyboardFocus(player);
    }

    @Override
    public void update(Game entity) {
        super.update(entity);
    }

    @Override
    public void exit(Game entity) {
        super.exit(entity);
    }

    @Override
    public void render() {
        super.render();
    }

    private static class InstanceHolder {
        static PlayingState instance = new PlayingState();
    }
}
