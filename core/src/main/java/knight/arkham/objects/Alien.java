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

    public Alien(int positionX, int positionY, World world, String spritePath, int points) {
        super(
            new Rectangle(
                740 + positionX,
                850 - positionY, 32, 32
            ), world, spritePath, "okay.wav"
        );
        alienPoints = points;
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 0, actualWorld, this)
        );
    }

    public void update() {

        if (setToDestroy && !isDestroyed)
            destroyBrick();
    }

    private void destroyBrick() {

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
