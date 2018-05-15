package com.allsopg.game.bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import static com.allsopg.game.utility.Constants.COLLISION_RECT_HEIGHT;
import static com.allsopg.game.utility.Constants.COLLISION_RECT_WIDTH;
import static com.allsopg.game.utility.Constants.FRAME_DURATION;
import static com.allsopg.game.utility.Constants.PLAYER_HEIGHT;
import static com.allsopg.game.utility.Constants.PLAYER_WIDTH;

/**
 * Created by gerard on 09/11/2016.
 * updated 02/03/18
 */

public abstract class AnimatedSprite extends Sprite {
    protected Animation animation;
    protected Animation.PlayMode playmode;
    protected Fixture fixture;
    private TextureAtlas atlas;
    //to put in the collision rectangle
    private Rectangle collisionRectangle;

    public AnimatedSprite(String atlasString, Texture t, Vector2 pos){
        super(t,PLAYER_WIDTH,PLAYER_HEIGHT);
        this.setX(pos.x);
        this.setY(pos.y);
        playmode = Animation.PlayMode.NORMAL;
        initAtlas(atlasString);
        createCollisionRect();
    }

    private void createCollisionRect(){
        this.collisionRectangle =  new Rectangle(this.getX(), this.getY(), COLLISION_RECT_WIDTH, COLLISION_RECT_HEIGHT);
    }

    public void updateCollisionData() {
        collisionRectangle.setX(this.getX());
        collisionRectangle.setY(this.getY());
    }

    private Rectangle getCollisionRectangle() {return collisionRectangle;}

    public void update(float animationTime){
        this.setRegion((TextureRegion) animation.getKeyFrame(animationTime));
        updateCollisionData();
    }

    private void initAtlas(String atlasString){
        atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        //load animations
        Array<TextureAtlas.AtlasRegion> regions = new
                Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        regions.sort(new RegionComparator());
        //changed to a loop to keep animations animating
        animation = new Animation(FRAME_DURATION,regions, Animation.PlayMode.LOOP);
    }

    private static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion> {
        @Override
        public int compare(TextureAtlas.AtlasRegion region1, TextureAtlas.AtlasRegion region2) {
            return region1.name.compareTo(region2.name);
        }
    }

    public void onPlayerHit(){
        Gdx.app.log("play hit","sprite");
    }

}