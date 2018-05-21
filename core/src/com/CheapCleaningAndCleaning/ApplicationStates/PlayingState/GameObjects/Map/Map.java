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
        for (float i = player.getPositionX()-100.5f; i <= player.getPositionX()+99.5; i++) {
            for (float j = -player.getPositionY()-100.5f; j <= player.getPositionY()+99.5; j++) {
                batch.draw(texture, i * 32, j * 32, 32, 32);
            }
        }
    }
}
