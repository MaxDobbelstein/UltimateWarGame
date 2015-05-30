package de.fkg.ultimate.war.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by mkvr on 28.05.15.
 */
public class Player extends InputAdapter {
    private final int ROWS = 1;
    private final int COLS = 8;
    private static final String WALKING = "ogre-move.png";
    private static final String ATTACKING = "ogre-attack-1.png";
    private static final String DYING = "ogre-death.png";

    private Animation animation;
    private Texture walkingTexture;
    private Texture attackingTexture;
    private Texture dyingTexture;
    private TextureRegion[] frames;
    private float stateTime;
    private Movement movement;

    public int xPosition = 300;
    public int yPosition = 100;
    public int width;
    public int height;

    public Player(){
        walkingTexture = new Texture(Gdx.files.internal(WALKING));
        attackingTexture = new Texture(Gdx.files.internal(ATTACKING));
        dyingTexture = new Texture(Gdx.files.internal(DYING));
        width = walkingTexture.getWidth()/COLS;
        height = walkingTexture.getHeight()/ROWS;

        setAnimation(walkingTexture, COLS, ROWS);
        stateTime = 0f;
        movement = Movement.NOT;
    }

    public Sprite getCurrentFrame(float stateTime){
        movement();
        this.stateTime += stateTime;
        return new Sprite(animation.getKeyFrame(this.stateTime, true));
    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
            movement = Movement.LEFT;
        if(keycode == Input.Keys.RIGHT)
            movement = Movement.RIGHT;
        if(keycode == Input.Keys.SPACE)
            setAnimation(attackingTexture, COLS, ROWS);

        return true;

    }

    @Override
    public boolean keyUp(int keycode) {
        movement = Movement.NOT;
        if(keycode == Input.Keys.SPACE)
            setAnimation(walkingTexture, COLS, ROWS);
        return true;
    }

    private void movement(){
        if(movement == Movement.LEFT)
            xPosition -= 4;
        if(movement == Movement.RIGHT)
            xPosition += 4;
    }

    private void setAnimation(Texture currentTexture, int cols, int rows){
        TextureRegion[][] tmp = TextureRegion.split(currentTexture, width, height);
        frames = new TextureRegion[rows*cols];

        int animIndex = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                frames[animIndex++] = tmp[i][j];
        
        animation = new Animation(0.1f, frames);
    }
    
    public void die(){
        setAnimation(walkingTexture, 4, 8);
    }

}
