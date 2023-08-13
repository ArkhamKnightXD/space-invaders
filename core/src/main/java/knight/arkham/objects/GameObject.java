package knight.arkham.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.AssetsHelper;

import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

public abstract class GameObject {
    protected final Rectangle actualBounds;
    protected final World actualWorld;
    protected final Body body;
    protected final Texture sprite;
    protected final Sound collisionSound;

    protected GameObject(Rectangle bounds, World world, String spritePath, String soundPath) {
        actualBounds = bounds;
        actualWorld = world;
        sprite = new Texture(spritePath);
        collisionSound = AssetsHelper.loadSound(soundPath);
        body = createBody();
    }

    protected abstract Body createBody();

    protected Rectangle getDrawBounds() {

        return new Rectangle(
            body.getPosition().x - (actualBounds.width / 2 / PIXELS_PER_METER),
            body.getPosition().y - (actualBounds.height / 2 / PIXELS_PER_METER),
            actualBounds.width / PIXELS_PER_METER,
            actualBounds.height / PIXELS_PER_METER
        );
    }

    public void draw(Batch batch) {

        Rectangle drawBounds = getDrawBounds();

        batch.draw(sprite, drawBounds.x, drawBounds.y, drawBounds.width, drawBounds.height);
    }

    public void dispose() {
        sprite.dispose();
        collisionSound.dispose();
    }
}
