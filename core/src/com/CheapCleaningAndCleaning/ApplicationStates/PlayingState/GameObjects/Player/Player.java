package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStackStateMachine;
import com.CheapCleaningAndCleaning.ApplicationStates.MainMenuState.MainMenuState;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.GameObject;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Map.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Player extends GameObject {
    public Map map;
    private ApplicationStackStateMachine stateMachine;
    private float positionX = 0;
    private float positionY = 0;
    private float direction = 0;
    private Texture texture = new Texture(Gdx.files.internal("image/micek.jpeg"));
    private PlayerStates playerState = PlayerStates.STILL;
    private PlayerStates prevPlayerState = PlayerStates.STILL;
    private HashMap<String, String> settings;

    public Player(ApplicationStackStateMachine stateMachine, HashMap<String, String> settings) {
        this.settings = settings;
        if(Boolean.valueOf(settings.get("micek"))) {
            texture = new Texture(Gdx.files.internal("image/micek.jpeg"));
        } else {
            texture = new Texture(Gdx.files.internal("image/player.png"));
        }
        this.stateMachine = stateMachine;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public int getSize() {
        if(settings.get("map").equals("1.json")) {
            return 128;
        } else {
            return 32;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //System.out.println(delta);
        if (playerState != PlayerStates.STILL) {
            switch (playerState) {
                case UP:
                    direction = 0;
                    break;
                case DOWN:
                    direction = (float) 180;
                    break;
                case LEFT:
                    direction = (float) 90;
                    break;
                case RIGHT:
                    direction = (float) 270;
                    break;
            }
            float distance = delta * 2;
            float cap = 0;
            if (prevPlayerState == PlayerStates.STILL) {
                switch (playerState) {
                    case UP:
                        cap = positionY + 1;
                        if (map.map[(int) cap][(int) positionX] == 1) {
                            playerState = PlayerStates.STILL;
                            return;
                        }
                        break;
                    case DOWN:
                        cap = positionY - 1;
                        if (map.map[(int) cap][(int) positionX] == 1) {
                            playerState = PlayerStates.STILL;
                            return;
                        }
                        break;
                    case LEFT:
                        cap = positionX - 1;
                        if (map.map[(int) positionY][(int) cap] == 1) {
                            playerState = PlayerStates.STILL;
                            return;
                        }
                        break;
                    case RIGHT:
                        cap = positionX + 1;
                        if (map.map[(int) positionY][(int) cap] == 1) {
                            playerState = PlayerStates.STILL;
                            return;
                        }
                        break;
                }
                prevPlayerState = playerState;
            } else {
                switch (playerState) {
                    case UP:
                        cap = (long) Math.ceil(positionY);
                        break;
                    case DOWN:
                        cap = (long) Math.floor(positionY);
                        break;
                    case LEFT:
                        cap = (long) Math.floor(positionX);
                        break;
                    case RIGHT:
                        cap = (long) Math.ceil(positionX);
                        break;
                }
            }
            switch (playerState) {
                case UP:
                    if (positionY + distance >= cap) {
                        positionY = cap;
                        playerState = PlayerStates.STILL;
                        prevPlayerState = PlayerStates.STILL;
                        checkPointPosition();
                    } else {
                        positionY = positionY + distance;
                    }
                    break;
                case DOWN:
                    if (positionY - distance <= cap) {
                        positionY = cap;
                        playerState = PlayerStates.STILL;
                        prevPlayerState = PlayerStates.STILL;
                        checkPointPosition();
                    } else {
                        positionY = positionY - distance;
                    }
                    break;
                case LEFT:
                    if (positionX - distance <= cap) {
                        positionX = cap;
                        playerState = PlayerStates.STILL;
                        prevPlayerState = PlayerStates.STILL;
                        checkPointPosition();
                    } else {
                        positionX = positionX - distance;
                    }
                    break;
                case RIGHT:
                    if (positionX + distance >= cap) {
                        positionX = cap;
                        playerState = PlayerStates.STILL;
                        prevPlayerState = PlayerStates.STILL;
                        checkPointPosition();
                    } else {
                        positionX = positionX + distance;
                    }
                    break;
            }
        }
    }

    private void checkPointPosition() {
        if (map.map[(int) positionY][(int) positionX] == 2) {
            map.map[(int) positionY][(int) positionX] = 0;
            map.pointCounter--;
        }
        if (map.map[(int) positionY][(int) positionX] == 3 && map.pointCounter == 0) {
            System.out.println("KONIEC");
            stateMachine.transitionToState(MainMenuState.getInstance());
            //ZROBIC KONIEC
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float size = (float) (positionX - Math.floor(positionX));
        if (size == 0) {
            size = (float) (positionY - Math.floor(positionY));
        }
        size = size * 2;
        if (size > 1) {
            size--;
            size = 1 - size;
        }
        size = (float) Math.sin(size);
        size += 1;
        batch.draw(new TextureRegion(texture), -(getSize() / 2) * size, -(getSize() / 2) * size, getSize() / 2, getSize() / 2, (float) getSize() * size, (float) getSize() * size, 1f, 1f, direction);
        //batch.draw(texture, -(getSize() / 2) * size, -(getSize() / 2) * size, getSize() * size, getSize() * size);
        //BitmapFont font = new BitmapFont();
        //font.draw(batch, "x: " + positionX + ", y: " + positionY, 200, 200);
    }

    public void moveRight() {
        if (playerState == PlayerStates.STILL) {
            playerState = PlayerStates.RIGHT;
        }
    }

    public void moveLeft() {
        if (playerState == PlayerStates.STILL) {
            playerState = PlayerStates.LEFT;
        }
    }

    public void moveUp() {
        if (playerState == PlayerStates.STILL) {
            playerState = PlayerStates.UP;
        }
    }

    public void moveDown() {
        if (playerState == PlayerStates.STILL) {
            playerState = PlayerStates.DOWN;
        }
    }

    public enum PlayerStates {STILL, LEFT, RIGHT, UP, DOWN}
}
