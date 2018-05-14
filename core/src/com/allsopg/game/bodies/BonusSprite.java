package com.allsopg.game.bodies;

import com.allsopg.game.physics.WorldManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.allsopg.game.utility.Constants.DENSITY;
import static com.allsopg.game.utility.Constants.FRICTION;
import static com.allsopg.game.utility.Constants.RESTITUTION;
import static com.allsopg.game.utility.Constants.SPRITE_OFFSET_X;
import static com.allsopg.game.utility.Constants.SPRITE_OFFSET_Y;

/**
 * Created by Rupert on 09/05/2018.
 */

public class BonusSprite extends AnimatedSprite {

    protected Animation animation;
    protected Animation.PlayMode playmode;
    private Body spriteBody;
    private TextureAtlas atlas;
    private int score;
   // private SoundLink soundLink;

    public BonusSprite(String atlasString, Texture t, Vector2 pos) {
        super(atlasString, t, pos);
        this.setPosition(pos.x,pos.y);
        buildBody();
        playmode = Animation.PlayMode.NORMAL;
       // soundLink = new SoundLink();
    }

    //builds bounding box for sprite
    public void buildBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(),getY());
    //sets the sprite body
        spriteBody = WorldManager.getInstance().getWorld().createBody(bodyDef);
        spriteBody.setUserData("sprite");
        spriteBody.setFixedRotation(true);
        spriteBody.createFixture(getFixtureDef(DENSITY,FRICTION,RESTITUTION));
    }
    //gets the position of the sprites to react with the player
    public void update(float statetime) {
        super.update(statetime);
        this.setPosition(spriteBody.getPosition().x-SPRITE_OFFSET_X,spriteBody.getPosition().y-SPRITE_OFFSET_Y);
    }

    //sets parameters for the bounding box
    private FixtureDef getFixtureDef(float density, float friction, float restitution) {
        PolygonShape shape = new PolygonShape();
        //the box is the same as player to stop confusion while in development
        shape.setAsBox((getWidth()/2)-.90f,getHeight()/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution=restitution;
        return fixtureDef;
    }




    //in collision function add the soundLink.play();
    //This should work...
}
