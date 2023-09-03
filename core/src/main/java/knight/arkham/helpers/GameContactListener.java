package knight.arkham.helpers;

import com.badlogic.gdx.physics.box2d.*;
import knight.arkham.objects.Alien;
import knight.arkham.objects.Bullet;
import knight.arkham.objects.Player;
import knight.arkham.objects.Structure;

import static knight.arkham.helpers.Constants.*;

public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int collisionDefinition = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (collisionDefinition) {


            case BULLET_BIT | ALIEN_BIT:

                if (fixtureA.getFilterData().categoryBits == ALIEN_BIT){
                    ((Alien) fixtureA.getUserData()).hitByTheBullet();
                    ((Bullet) fixtureB.getUserData()).hitTheAlien();
                }
                else{
                    ((Alien) fixtureB.getUserData()).hitByTheBullet();
                    ((Bullet) fixtureA.getUserData()).hitTheAlien();
                }
                break;

            case BULLET_BIT | STRUCTURE_BIT:

                if (fixtureA.getFilterData().categoryBits == STRUCTURE_BIT){
                    ((Structure) fixtureA.getUserData()).hitByTheBullet();
                    ((Bullet) fixtureB.getUserData()).hitTheAlien();
                }
                else{
                    ((Structure) fixtureB.getUserData()).hitByTheBullet();
                    ((Bullet) fixtureA.getUserData()).hitTheAlien();
                }
                break;

            case BULLET_BIT | PLAYER_BIT:

                if (fixtureA.getFilterData().categoryBits == PLAYER_BIT){
                    ((Player) fixtureA.getUserData()).hitByTheBullet();
                    ((Bullet) fixtureB.getUserData()).hitTheAlien();
                }
                else{
                    ((Player) fixtureB.getUserData()).hitByTheBullet();
                    ((Bullet) fixtureA.getUserData()).hitTheAlien();
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
