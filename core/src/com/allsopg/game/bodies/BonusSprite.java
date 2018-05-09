package com.allsopg.game.bodies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rupert on 09/05/2018.
 */

public class BonusSprite extends AnimatedSprite {

    protected Animation animation;
    protected Animation.PlayMode playmode;
    private TextureAtlas atlas;
    private Rectangle collisionRectangle;


    public BonusSprite(String atlasString, Texture t, Vector2 pos) {
        super(atlasString, t, pos);
        this.setPosition(pos.x,pos.y);
    }


}
