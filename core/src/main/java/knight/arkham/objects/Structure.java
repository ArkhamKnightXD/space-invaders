package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Structure {
    private final Texture sprite;
    private final Rectangle drawBounds;

    public Structure(Rectangle bounds, World world) {

        Body body = Box2DHelper.createBody(
            new Box2DBody(bounds, 0, world, this)
        );

        drawBounds = Box2DHelper.getDrawBounds(body, bounds);

        sprite = new Texture("images/structure.png");
    }

    public void draw(Batch batch) {

        batch.draw(sprite, drawBounds.x, drawBounds.y, drawBounds.width, drawBounds.height);
    }

    public void dispose (){

        sprite.dispose();
    }
}
