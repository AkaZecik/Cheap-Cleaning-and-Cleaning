package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Map;

import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Map extends Actor {
    private Texture texture = new Texture(Gdx.files.internal("image/tile.png"));
    private Player player;

    public Map(Player player){
        this.player=player;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (float i = (float) (-10.5f+(Math.floor(player.getPositionX())-player.getPositionX())); i <= +10.5f; i++) {
            for (float j = (float) (-10.5f+(Math.floor(player.getPositionY())-player.getPositionY())); j <= +10.5f; j++) {
                batch.draw(texture, i * 32, j * 32, 32, 32);
            }
        }
    }
}
