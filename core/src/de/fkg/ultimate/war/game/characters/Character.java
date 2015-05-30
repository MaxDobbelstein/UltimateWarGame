package de.fkg.ultimate.war.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by mkvr on 30.05.15.
 */
public abstract class Character {
    public int width;
    public int height;
    public int xPosition;
    public int yPosition;
    public Movement movement;

    private TextureRegion[] frames;
    private Animation animation;
    protected float stateTime;

    public void setAnimation(Texture currentTexture, int cols, int rows) {
        width = currentTexture.getWidth() / cols;
        height = currentTexture.getHeight() / rows;

        TextureRegion[][] tmp = TextureRegion.split(currentTexture, width, height);
        frames = new TextureRegion[rows * cols];

        int animIndex = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                frames[animIndex++] = tmp[i][j];

        animation = new Animation(0.1f, frames);
    }

    public Sprite getCurrentFrame(float stateTime) {
        movement();
        this.stateTime += stateTime;
        return new Sprite(animation.getKeyFrame(this.stateTime, true));
    }

    private void movement() {
        if (movement == Movement.LEFT)
            xPosition -= 4;
        if (movement == Movement.RIGHT)
            xPosition += 4;
    }
}
