package de.fkg.ultimate.war.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import de.fkg.ultimate.war.game.characters.*;
import de.fkg.ultimate.war.game.characters.Character;
import de.fkg.ultimate.war.game.de.fkg.ultimate.war.game.input.DesktopInput;
import java.util.Random;

/**
 * Created by mkvr on 28.05.15.
 */
public class MainGameScreen implements Screen {
    private SpriteBatch batch;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Player player;
    private int scrollTrack = 0;
    
    private final Array<Enemy> activeEnemies;

    // bullet pool.
    private final Pool<Enemy> enemyPool = new Pool<Enemy>() {
        @Override
        protected Enemy newObject() {
            return new Enemy();
        }
    };


    public MainGameScreen(){
        batch = new SpriteBatch();
        player = new Player(100, 300);
        activeEnemies = new Array<Enemy>();
        Gdx.input.setInputProcessor(new DesktopInput(player));
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
        Sprite playerSprite = player.getCurrentFrame(delta);
        detectCollision();

        batch.begin();
        batch.draw(playerSprite, player.xPosition, player.yPosition);
        drawEnemies(delta);
        batch.end();
        camera.translate(0,1);
        scrollTrack++;
        
        Random rnd = new Random();
        if(rnd.nextInt(100) == 1)
            spawnEnemy();
    }
    
    private void spawnEnemy(){
        Enemy mob = enemyPool.obtain();
        Random r = new Random();
        Character character = new EnemyOgre(r.nextInt(Gdx.graphics.getWidth())-64, Gdx.graphics.getHeight()-64);
        mob.init(character);
        activeEnemies.add(mob);
    }
    
    private void drawEnemies(float delta){
        for(Enemy enemy : activeEnemies){
            Sprite enemySprite = enemy.character.getCurrentFrame(delta);
            enemySprite.flip(false, true);
            enemy.character.yPosition -= 2;
            batch.draw(enemySprite, enemy.character.xPosition, enemy.character.xPosition);
        }
    }

    private void detectCollision(){
        int objectLayerId = 2;
        MapLayer collisionObjectLayer = tiledMap.getLayers().get(objectLayerId);
        MapObjects objects = collisionObjectLayer.getObjects();
        Rectangle playerRectangle = new Rectangle(player.xPosition, player.yPosition + scrollTrack, player.width, player.height);
        // there are several other types, Rectangle is probably the most common one
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle, playerRectangle)) {
                player.yPosition--;
                if(player.yPosition == 0)
                    player.die();
            }
        }
        detectEnemyCollision();
    }

    private void detectEnemyCollision(){
        Rectangle playerRectangle = new Rectangle(player.xPosition, player.yPosition + scrollTrack, player.width, player.height);
        for(Enemy enemy : activeEnemies){
            Rectangle enemyRectangle = new Rectangle(enemy.character.xPosition, enemy.character.yPosition + scrollTrack,
                    enemy.character.width, enemy.character.height);
            if (Intersector.overlaps(enemyRectangle, playerRectangle)) {
                player.die();
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
