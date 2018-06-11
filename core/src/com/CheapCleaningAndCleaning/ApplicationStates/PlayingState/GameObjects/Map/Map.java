package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Map;

import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.Arrays;

public class Map extends Actor {
    private ArrayList<Texture> textures=new ArrayList<>();
    private Player player;
    public Integer[][] map;

    static class MapParser {
        Integer[][] map;
        int playerX;
        int playerY;
    }

    public Map(Player player) {
        FileHandle folder = Gdx.files.internal("image/tiles");
        int i = 0;
        while (true) {
            FileHandle image = folder.child(i + ".png");
            if (!image.exists()) {
                break;
            }
            textures.add(new Texture(image));
            i++;
        }

        this.player = player;
        this.player.map=this;
        Json json = new Json();
        MapParser mapParser = json.fromJson(MapParser.class, Gdx.files.internal("maps/1.json").readString());
        map = mapParser.map;
        player.setPositionX(mapParser.playerX);
        player.setPositionY(mapParser.playerY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();
        height = height / 1 / player.getSize();
        width = width / 1 / player.getSize();
        for (float i = (float) (-width - 0.5f + (Math.floor(player.getPositionX()) - player.getPositionX())); i < width + 0.5f; i++) {
            for (float j = (float) (-height - 0.5f + (Math.floor(player.getPositionY()) - player.getPositionY())); j < height + 0.5f; j++) {
                int renderX = (int) (Math.ceil(player.getPositionX() + i));
                int renderY = (int) (Math.ceil(player.getPositionY() + j));
                if (renderX < 0 || renderY < 0 || renderY >= map.length || renderX >= map[0].length) {
                    continue;
                }
                int tile = map[renderY][renderX];
                Texture texture = textures.get(tile);
                batch.draw(texture, i * player.getSize(), j * player.getSize(), player.getSize(), player.getSize());
            }
        }
    }
}
