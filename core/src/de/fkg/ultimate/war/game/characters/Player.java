package de.fkg.ultimate.war.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by mkvr on 28.05.15.
 */
public class Player extends InputAdapter {
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
    private Character character;

    public Player(){
        walkingTexture = new Texture(Gdx.files.internal(WALKING));
        attackingTexture = new Texture(Gdx.files.internal(ATTACKING));
        dyingTexture = new Texture(Gdx.files.internal(DYING));

        character = new Character(100, 300);
        character.setAnimation(walkingTexture, MOVEMENT_COLS, MOVEMENT_ROWS);
        character.movement = Movement.NOT;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
            character.movement = Movement.LEFT;
        if(keycode == Input.Keys.RIGHT)
            character.movement = Movement.RIGHT;
        if(keycode == Input.Keys.SPACE)
            character.setAnimation(attackingTexture, MOVEMENT_COLS, MOVEMENT_ROWS);

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        character.movement = Movement.NOT;
        if(keycode == Input.Keys.SPACE)
            character.setAnimation(walkingTexture, MOVEMENT_COLS, MOVEMENT_ROWS);
        return true;
    }

    public void die(){
        character.setAnimation(dyingTexture, DYING_COLS, DYING_ROWS);
    }
}
