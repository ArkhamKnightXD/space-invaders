package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;
import knight.arkham.scenes.Hud;

import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

public class Player extends GameObject {
    public static int score;
    public static int livesQuantity;
    private float velocityX;
    private final Array<Bullet> bullets;
    private float bulletSpawnTime;

    public Player(Rectangle bounds, World world) {
        super(bounds, world, "images/players.png", "drop.wav");
        score = 0;
        livesQuantity = 0;
        bullets = new Array<>();
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 5, actualWorld, this)
        );
    }

    public void update(float deltaTime) {

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            velocityX = 1.5f;

        else if (Gdx.input.isKeyPressed(Input.Keys.A))
            velocityX = -1.5f;

        body.setLinearVelocity(velocityX * 10, 0);

        velocityX = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            shootBullet(deltaTime);
    }

    public void drawBullets(Batch batch) {

        for (Bullet bullet : bullets){
            bullet.update();
            bullet.draw(batch);
        }
    }

    private void shootBullet(float deltaTime) {

        bulletSpawnTime += deltaTime;

        if (bulletSpawnTime > 1) {

            bullets.add(new Bullet(getPixelPosition(), actualWorld));

            bulletSpawnTime = 0;
        }
    }

    public Vector2 getPixelPosition() {
        return new Vector2(body.getPosition().x * PIXELS_PER_METER, body.getPosition().y * PIXELS_PER_METER);
    }

    public void hitByAttack() {

        collisionSound.play(0.6f);

        Hud.takeAvailableHealth();
    }
}
