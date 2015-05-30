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
    private final String WALKING = "ogre-move.png";
    private Texture walkingTexture;
    private Character character;
    
    public EnemyOgre(int initialX, int initialY){
        walkingTexture = new Texture(Gdx.files.internal(WALKING));
        character = new Character(initialX, initialY);
        character.setAnimation(walkingTexture, COLS, ROWS);
    }
    
}
