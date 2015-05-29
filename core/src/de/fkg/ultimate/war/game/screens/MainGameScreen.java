package de.fkg.ultimate.war.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import de.fkg.ultimate.war.game.WarGame;
import de.fkg.ultimate.war.game.characters.Player;
import org.w3c.dom.css.Rect;

/**
 * Created by mkvr on 28.05.15.
 */
public class MainGameScreen implements Screen {
    private SpriteBatch batch;
    private Texture img;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private WarGame warGame;
    private Player player;

    public MainGameScreen(final WarGame warGame){
        this.warGame = warGame;
        batch = new SpriteBatch();
        player = new Player();
        Gdx.input.setInputProcessor(player);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        tiledMap = new TmxMapLoader().load("debug_level_one.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        detectCollision();

        batch.begin();
        batch.draw(player.getCurrentFrame(delta), player.xPosition, player.yPosition);
        batch.end();
        camera.translate(0,1);
    }

    private void detectCollision(){
        int objectLayerId = 2;
        TiledMapTileLayer collisionObjectLayer = (TiledMapTileLayer)tiledMap.getLayers().get(objectLayerId);
        MapObjects objects = collisionObjectLayer.getObjects();
        Rectangle playerRectangle = new Rectangle(player.xPosition, player.yPosition, player.width, player.height);
        // there are several other types, Rectangle is probably the most common one
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle, playerRectangle)) {
                player.xPosition += 10;
            }
        }
    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
