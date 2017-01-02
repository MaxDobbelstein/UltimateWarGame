package de.fkg.ultimate.war.game.de.fkg.ultimate.war.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.fkg.ultimate.war.game.characters.Player;

/**
 * Created by mkvr on 30.05.15.
 */
public class DesktopInput extends InputAdapter{
    Player player;

    public DesktopInput(Player player){
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.LEFT:
                player.strafeLeft();
                player.walk();
                break;
            case Input.Keys.RIGHT:
                player.strafeRight();
                player.walk();
                break;
            case Input.Keys.UP:
                player.moveUp();
                player.walk();
                break;
            case Input.Keys.DOWN:
                player.moveDown();
                player.walk();
                break;
            case Input.Keys.SPACE:
                player.attack();
                break;
        }
        
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        player.walk();
        return true;
    }
}
