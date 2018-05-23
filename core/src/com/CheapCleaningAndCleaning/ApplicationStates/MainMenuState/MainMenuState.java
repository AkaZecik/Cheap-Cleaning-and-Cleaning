package com.CheapCleaningAndCleaning.ApplicationStates.MainMenuState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.ExitingState.ExitingState;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.PlayingState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenuState extends AbstractApplicationState {
    Skin skin;

    private MainMenuState() {

    }

    public static MainMenuState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter(Game entity) {
        super.enter(entity);
        skin = new Skin();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);
        skin.add("default", font);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
//        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.setDebug(true); // DEBUG

        final TextButton play = new TextButton("PLAY", skin);
        final TextButton options = new TextButton("OPTIONS", skin);
        final TextButton exit = new TextButton("EXIT", skin);
        table.add(play).width(200).height(100).pad(25);
        table.row();
        table.add(options).width(200).height(100).pad(25);
        table.row();
        table.add(exit).width(200).height(100).pad(25);

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                nextState = PlayingState.getInstance();
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                nextState = ExitingState.getInstance();
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
    }

    @Override
    public void render() {
        super.render();
    }

    private static class InstanceHolder {
        static MainMenuState instance = new MainMenuState();
    }
}
