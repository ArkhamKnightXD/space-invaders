package knight.arkham.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Structure extends GameObject {
    private final Rectangle drawBounds;
    private int hitCounter;
    private boolean setToDestroy;
    private boolean isDestroyed;

    public Structure(Rectangle bounds, World world) {
        super(bounds, world, "images/structure.png", "break.ogg");

        drawBounds = getDrawBounds();
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 0, actualWorld, this)
        );
    }

    public void update(){
        if (setToDestroy && !isDestroyed)
            destroyBody();
    }

    private void destroyBody() {

        actualWorld.destroyBody(body);
        isDestroyed = true;
    }


    @Override
    public void draw(Batch batch) {

        if (!isDestroyed)
            batch.draw(sprite, drawBounds.x, drawBounds.y, drawBounds.width, drawBounds.height);
    }

    public void hitByTheBullet() {

        hitCounter++;

        actionSound.play();

        if (hitCounter == 5)
            setToDestroy = true;
    }
}
