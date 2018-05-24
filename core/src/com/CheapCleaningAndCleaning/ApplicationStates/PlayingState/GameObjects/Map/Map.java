package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Map;

import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Map extends Actor {
    private Texture texture = new Texture(Gdx.files.internal("image/tile.png"));
    private Player player;

    public Map(Player player) {
        this.player = player;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();
        height = height / 1 / player.getSize();
        width = width / 1 / player.getSize();
        for (float i = (float) (-width - 0.5f + (Math.floor(player.getPositionX()) - player.getPositionX())); i <= width + 0.5f; i++) {
            for (float j = (float) (-height - 0.5f + (Math.floor(player.getPositionY()) - player.getPositionY())); j <= height + 0.5f; j++) {
                batch.draw(texture, i * player.getSize(), j * player.getSize(), player.getSize(), player.getSize());
            }
        }
    }
}
