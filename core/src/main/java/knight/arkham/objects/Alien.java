package knight.arkham.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;
import knight.arkham.scenes.Hud;

public class Alien extends GameObject {
    private boolean isDestroyed;
    private boolean setToDestroy;
    private final int alienPoints;
    private float stateTimer;
    private float velocityX;

    public Alien(int positionX, int positionY, World world, String spritePath, int points) {
        super(
            new Rectangle(
                720 + positionX,
                850 - positionY, 32, 32
            ), world, spritePath, "okay.wav"
        );
        alienPoints = points;

        velocityX = 2;
        body.setLinearVelocity(velocityX,0);
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 1, actualWorld, this)
        );
    }

    public void update(float deltaTime) {

        if (setToDestroy && !isDestroyed)
            destroyAlien();

        stateTimer += deltaTime;

        if (stateTimer > 2.8f){

            if (stateTimer < 3.5f)
                body.setLinearVelocity(0,-1);
        }
        if (stateTimer > 3.5f){
            body.setLinearVelocity(-2,0);

            stateTimer = -3.6f;
        }
    }

    private void destroyAlien() {

        actualWorld.destroyBody(body);
        isDestroyed = true;
    }

    @Override
    public void draw(Batch batch) {

        if (!isDestroyed)
            super.draw(batch);
    }

    public void hitByTheBullet() {
        setToDestroy = true;

        Hud.addScore(alienPoints);

        collisionSound.play(0.6f);
    }
}
