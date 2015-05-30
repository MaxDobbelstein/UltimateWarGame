package de.fkg.ultimate.war.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by mkvr on 28.05.15.
 */
public class Player extends Character{
    private final int MOVEMENT_ROWS = 1;
    private final int MOVEMENT_COLS = 8;
    private final int DYING_ROWS = 8;
    private final int DYING_COLS = 4;
    private static final String WALKING = "ogre-move.png";
    private static final String ATTACKING = "ogre-attack-1.png";
    private static final String DYING = "ogre-death.png";
    private Texture walkingTexture;
    private Texture attackingTexture;
    private Texture dyingTexture;


    public Player(int initialX, int initialY){
        this.xPosition = initialX;
        this.yPosition = initialY;
        stateTime = 0f;

        walkingTexture = new Texture(Gdx.files.internal(WALKING));
        attackingTexture = new Texture(Gdx.files.internal(ATTACKING));
        dyingTexture = new Texture(Gdx.files.internal(DYING));

        setAnimation(walkingTexture, MOVEMENT_COLS, MOVEMENT_ROWS);
        movement = Movement.NOT;
    }

    public void die(){
        setAnimation(dyingTexture, DYING_COLS, DYING_ROWS);
    }

    public void attack(){
        setAnimation(attackingTexture,MOVEMENT_COLS, MOVEMENT_ROWS);
    }

    //TODO: remember previous animation to avoid unnecessary setting of a new animation
    public void walk(){
        movement = Movement.NOT;
        setAnimation(walkingTexture, MOVEMENT_COLS, MOVEMENT_ROWS);
    }

    public void strafeLeft(){
        movement = Movement.LEFT;
    }

    public void strafeRight(){
        movement = Movement.RIGHT;
    }
}
