/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fkg.ultimate.war.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 *
 * @author alex
 */
public class Enemy implements Pool.Poolable {
    public boolean alive;
    public Character character;
    /**
     * Bullet constructor. Just initialize variables.
     */
    public Enemy() {
        this.alive = false;
    }

    /**
     * Initialize the bullet. Call this method after getting a bullet from the pool.
     */
    public void init(Character character) {
        this.character = character;
        alive = true;
    }

    /**
     * Callback method when the object is freed. It is automatically called by Pool.free()
     * Must reset every meaningful field of this bullet.
     */
    @Override
    public void reset() {
        alive = false;
    }

    /**
     * Method called each frame, which updates the bullet.
     */
    public void update (float delta) {

        // update bullet position
        character.xPosition += 1*delta*60;
        character.yPosition -= -2*delta*60;

        // if bullet is out of screen, set it to dead
        // if (isOutOfScreen()) alive = false;
    }
    
}