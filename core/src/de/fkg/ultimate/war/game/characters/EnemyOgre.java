/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fkg.ultimate.war.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Random;

/**
 *
 * @author alex
 */
public class EnemyOgre extends Enemy{
    private final int ROWS = 1;
    private final int COLS = 8;
    private final String ASSETNAME = "ogre-move.png";

    
    public EnemyOgre(){
        texture = new Texture(Gdx.files.internal(ASSETNAME));
        width = texture.getWidth()/COLS;
        height = texture.getHeight()/ROWS;
        TextureRegion[][] tmp = TextureRegion.split(texture, width, height);
        frames = new TextureRegion[COLS];

        for (int j = 0; j < COLS; j++)
            frames[j] = tmp[0][j];

        stanceAnimation = new Animation(0.1f, frames);
        stateTime = 0f;
    }
    
    public Sprite getCurrentFrame(float stateTime){
        this.stateTime += stateTime;
        return new Sprite(stanceAnimation.getKeyFrame(this.stateTime, true));
    }
}
