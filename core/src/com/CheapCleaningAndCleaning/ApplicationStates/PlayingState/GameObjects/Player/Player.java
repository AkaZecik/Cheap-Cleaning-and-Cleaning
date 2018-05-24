package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player;

import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Player extends GameObject {
    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    private float positionX = 0;
    private float positionY = 0;
    private Texture texture = new Texture(Gdx.files.internal("image/firstplayer.png"));

    public enum PlayerStates {STILL, LEFT, RIGHT, UP, DOWN}

    private PlayerStates playerState = PlayerStates.STILL;

    private PlayerStates prevPlayerState = PlayerStates.STILL;

    @Override
    public void act(float delta) {
        super.act(delta);
        //System.out.println(delta);
        if (playerState != PlayerStates.STILL) {
            float distance = delta * 2;
            float cap = 0;
            if (prevPlayerState == PlayerStates.STILL) {
                switch (playerState) {
                    case UP:
                        cap = positionY + 1;
                        break;
                    case DOWN:
                        cap = positionY - 1;
                        break;
                    case LEFT:
                        cap = positionX - 1;
                        break;
                    case RIGHT:
                        cap = positionX + 1;
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
                    } else {
                        positionY = positionY + distance;
                    }
                    break;
                case DOWN:
                    if (positionY - distance <= cap) {
                        positionY = cap;
                        playerState = PlayerStates.STILL;
                        prevPlayerState = PlayerStates.STILL;
                    } else {
                        positionY = positionY - distance;
                    }
                    break;
                case LEFT:
                    if (positionX - distance <= cap) {
                        positionX = cap;
                        playerState = PlayerStates.STILL;
                        prevPlayerState = PlayerStates.STILL;
                    } else {
                        positionX = positionX - distance;
                    }
                    break;
                case RIGHT:
                    if (positionX + distance >= cap) {
                        positionX = cap;
                        playerState = PlayerStates.STILL;
                        prevPlayerState = PlayerStates.STILL;
                    } else {
                        positionX = positionX + distance;
                    }
                    break;
            }
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
        batch.draw(texture, -16 * size, -16 * size, 32 * size, 32 * size);
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
}
