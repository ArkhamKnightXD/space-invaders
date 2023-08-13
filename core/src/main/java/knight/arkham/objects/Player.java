package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Player extends GameObject {
    public static int score;

    public Player(Rectangle bounds, World world) {
        super(bounds, world, "images/players.png", "drop.wav");
        score = 0;
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 10, actualWorld, this)
        );
    }

    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.D) && body.getLinearVelocity().x <= 10)
            applyLinealImpulse(new Vector2(3, 0));

        else if (Gdx.input.isKeyPressed(Input.Keys.A) && body.getLinearVelocity().x >= -10)
            applyLinealImpulse(new Vector2(-3, 0));

        else if (Gdx.input.isKeyPressed(Input.Keys.W) && body.getLinearVelocity().x >= -10)
            applyLinealImpulse(new Vector2(0, 3));

        else if (Gdx.input.isKeyPressed(Input.Keys.S) && body.getLinearVelocity().x >= -10)
            applyLinealImpulse(new Vector2(0, -3));

    }

    private void applyLinealImpulse(Vector2 impulseDirection) {
        body.applyLinearImpulse(impulseDirection, body.getWorldCenter(), false);
    }

    public void hitByTheAsteroid() {

        collisionSound.play(0.6f);
    }
}
