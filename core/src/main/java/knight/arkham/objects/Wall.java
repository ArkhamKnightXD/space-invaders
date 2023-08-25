package knight.arkham.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Wall {

    public Wall(Rectangle bounds, World world) {
        Box2DHelper.createBody(
            new Box2DBody(bounds, 0, world, this)
        );
    }
}
