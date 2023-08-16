package knight.arkham.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Bullet extends GameObject {

    public Bullet(Rectangle bounds, World world) {
        super(bounds, world, "images/ball.png", "fall.wav");
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBulletBody(
            new Box2DBody(actualBounds, 0.1f, actualWorld, this)
        );
    }

    public void update(){

        body.setLinearVelocity(0, 5 * 6);
    }
}
