package com.allsopg.game.bodies;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by Rupert on 14/05/2018.
 */

/**
 *
 */
public class worldContactListener implements ContactListener{
    @Override
    public void beginContact(Contact contact) {

//        Fixture firstBody, secondBody;
//
//        if(contact.getFixtureA().getUserData() == "player"){
//            firstBody = contact.getFixtureA();
//            secondBody = contact.getFixtureB();
//            Gdx.app.log("player","");
//        }else if (contact.getFixtureA().getUserData() == "sprite"){
//            firstBody = contact.getFixtureB();
//            secondBody = contact.getFixtureA();
//            Gdx.app.log("sprite", "");
//        }



        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "player" || fixB.getUserData() == "player") {
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
           Fixture object = player == fixA ? fixB : fixA;

            if(object.getUserData()!= null && AnimatedSprite.class.isAssignableFrom(object.getUserData().getClass()));
            ((AnimatedSprite) object.getUserData()).onPlayerHit();
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
