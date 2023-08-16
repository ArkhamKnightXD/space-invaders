package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;
import knight.arkham.scenes.Hud;

import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

public class Player extends GameObject {
    public static int score;
    public static int livesQuantity;
    private float velocityX;

    public Player(Rectangle bounds, World world) {
        super(bounds, world, "images/players.png", "drop.wav");
        score = 0;
        livesQuantity = 0;
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 5, actualWorld, this)
        );
    }

    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            velocityX = 1.5f;

        else if (Gdx.input.isKeyPressed(Input.Keys.A))
            velocityX = -1.5f;

        body.setLinearVelocity(velocityX * 10, 0);

        velocityX = 0;
    }

    public Vector2 getPixelPosition() {
        return new Vector2(body.getPosition().x * PIXELS_PER_METER, body.getPosition().y * PIXELS_PER_METER);
    }

    public void hitByAttack() {

        collisionSound.play(0.6f);

        Hud.takeAvailableHealth();
    }
}
