package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.Asteroid;
import knight.arkham.helpers.AssetsHelper;
import knight.arkham.helpers.GameContactListener;
import knight.arkham.objects.Alien;
import knight.arkham.objects.Player;
import knight.arkham.objects.Structure;
import knight.arkham.scenes.Hud;
import knight.arkham.scenes.PauseMenu;

public class GameScreen extends ScreenAdapter {
    private final Asteroid game;
    private final OrthographicCamera camera;
    public SpriteBatch batch;
    private final Hud hud;
    private final PauseMenu pauseMenu;
    private final World world;
    private final Box2DDebugRenderer debugRenderer;
    private final Player player;
    private final Structure structure;
    private final Structure structure2;
    private final Structure structure3;
    private final Structure structure4;
    private final Array<Alien> aliens;
    private final Sound winSound;
    public static boolean isGamePaused;

    public GameScreen() {

        game = Asteroid.INSTANCE;

        camera = game.camera;

        batch = new SpriteBatch();

        world = new World(new Vector2(0, 0), true);

        GameContactListener contactListener = new GameContactListener();

        world.setContactListener(contactListener);
        debugRenderer = new Box2DDebugRenderer();

        player = new Player(new Rectangle(1000, 350, 32, 32), world);

        structure = new Structure(new Rectangle(650, 450, 48, 32), world);
        structure2 = new Structure(new Rectangle(900, 450, 48, 32), world);
        structure3 = new Structure(new Rectangle(1150, 450, 48, 32), world);
        structure4 = new Structure(new Rectangle(1400, 450, 48, 32), world);

        aliens = createAliens();

        winSound = AssetsHelper.loadSound("win.wav");

        hud = new Hud();
        pauseMenu = new PauseMenu();

        isGamePaused = false;
    }

    private Array<Alien> createAliens() {
        int positionX;
        int positionY = 0;
        int alienPoints = 8;
        String spritePath;

        Array<Alien> temporalAliens = new Array<>();

        for (int i = 0; i < 5; i++) {

            positionX = 0;

            if (i == 0)
                spritePath = "images/blue-alien.png";
            else if (i >= 3)
                spritePath = "images/green-alien.png";
            else
                spritePath = "images/red-alien.png";


            for (int j = 0; j < 11; j++) {

                temporalAliens.add(new Alien(positionX, positionY, world, spritePath, alienPoints));
                positionX += 60;
            }

            alienPoints--;
            positionY += 40;
        }

        return temporalAliens;
    }


    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    private void update(float deltaTime) {

        world.step(1 / 60f, 6, 2);

        player.update(deltaTime);

        for (Alien alien : aliens)
            alien.update(deltaTime);
    }

    @Override
    public void render(float deltaTime) {

        ScreenUtils.clear(0, 0, 0, 0);

        if (!isGamePaused) {
            update(deltaTime);
            draw();
        } else {

            pauseMenu.stage.act();
            pauseMenu.stage.draw();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F1))
            isGamePaused = !isGamePaused;
    }

    private void draw() {

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        player.draw(batch);

        structure.draw(batch);
        structure2.draw(batch);
        structure3.draw(batch);
        structure4.draw(batch);

        for (Alien alien : aliens)
            alien.draw(batch);

        batch.end();

        hud.stage.draw();

//        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void hide() {

        dispose();
    }

    @Override
    public void dispose() {

        player.dispose();
        hud.dispose();
        pauseMenu.dispose();
        winSound.dispose();
        world.dispose();
        batch.dispose();
        debugRenderer.dispose();
        structure.dispose();

        for (Alien alien : aliens)
            alien.dispose();
    }
}
