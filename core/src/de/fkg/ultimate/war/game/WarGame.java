package de.fkg.ultimate.war.game;

import com.badlogic.gdx.Game;
import de.fkg.ultimate.war.game.screens.MainGameScreen;

public class WarGame extends Game {

	
	@Override
	public void create () {
		this.setScreen(new MainGameScreen());
	}

	@Override
	public void render () {
		super.render();
	}
}
