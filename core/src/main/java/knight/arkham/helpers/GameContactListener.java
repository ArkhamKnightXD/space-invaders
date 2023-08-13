package knight.arkham.helpers;

import com.badlogic.gdx.physics.box2d.*;
import knight.arkham.objects.Brick;
import knight.arkham.objects.Player;

import static knight.arkham.helpers.Constants.*;

public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int collisionDefinition = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (collisionDefinition) {

            case PLAYER_BIT | BRICK_BIT:

                if (fixtureA.getFilterData().categoryBits == PLAYER_BIT){
                    ((Player) fixtureB.getUserData()).hitByTheAsteroid();
                }

                else{
                    ((Player) fixtureA.getUserData()).hitByTheAsteroid();
                }
                break;

            case BULLET_BIT | BRICK_BIT:

                if (fixtureA.getFilterData().categoryBits == PLAYER_BIT){
                    ((Brick) fixtureB.getUserData()).hitByTheBullet();

                }
                else{
                    ((Brick) fixtureA.getUserData()).hitByTheBullet();
                }
                break;

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
