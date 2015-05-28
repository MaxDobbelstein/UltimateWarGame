package de.fkg.ultimate.war.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by mkvr on 28.05.15.
 */
public class Player extends InputAdapter {
    private final int ROWS = 1;
    private final int COLS = 8;
    private final String ASSETNAME = "ogre-move.png";
    private Animation stanceAnimation;
    private Texture texture;
    private TextureRegion[] frames;
    private float stateTime;

    public int xPosition = 300;
    public int yPosition = 100;


    public Player(){
        texture = new Texture(Gdx.files.internal(ASSETNAME));

        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/COLS, texture.getHeight()/ROWS);
        frames = new TextureRegion[COLS];

        for (int j = 0; j < COLS; j++)
            frames[j] = tmp[0][j];

        stanceAnimation = new Animation(0.1f, frames);
        stateTime = 0f;
    }

    public TextureRegion getCurrentFrame(float stateTime){
        this.stateTime += stateTime;
        return stanceAnimation.getKeyFrame(this.stateTime, true);
    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
            xPosition -= 10;
        if(keycode == Input.Keys.RIGHT)
            xPosition += 10;

        return true;

    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


}
