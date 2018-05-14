package com.allsopg.game.utility;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by gerard on 09/11/2016.
 * Updated 17/02/18
 */

public class Constants {
    //Screen Size
    public static final float VIRTUAL_WIDTH = Gdx.graphics.getWidth();
    public static final float VIRTUAL_HEIGHT = Gdx.graphics.getHeight();
    //World to screen scale
    public static final float TILE_SIZE   = 32;
    public static final float UNITSCALE = 1.0f / TILE_SIZE;
    //Animation Speed
    public static final float FRAME_DURATION = 1.0f / 12.0f; //changed the frame duration to fit the character animation
    public static final float TIME_STEP=1/60f;
    public static final int LEVEL_TIME = 60;

    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;
    //tiled background
    public static final String BACKGROUND = "tileData/BackGround.tmx";
    public static final String PHYSICS_MATERIALS_PATH = "tileData/physicsData.json";

    public static final float DENSITY=.5f;
    public static final float FRICTION=.7f;
    public static final float RESTITUTION=.5f;
    //impulse strength
    public static final float FORCE_X=30f;
    public static final float FORCE_Y=30f;
    //Speed
    public static final float MAX_VELOCITY = 1f;
    public static final float MAX_HEIGHT = 30;
    //player body
    public static int PLAYER_WIDTH= 3;
    public static int PLAYER_HEIGHT=4;
    public static float PLAYER_OFFSET_Y=2.15f;
    public static float PLAYER_OFFSET_X=1.5f;
    //player graphics
    public static final String PLAYER_ATLAS_PATH = "gfx/Character/RunningAni_Assets.atlas";
    public static final Texture MEDIUM = new Texture(Gdx.files.internal("gfx/mediumSize.png"));
    public static final Texture SMALL = new Texture(Gdx.files.internal("gfx/smallSize.png"));
    public static final Texture TINY = new Texture(Gdx.files.internal("gfx/tinySize.png"));
    //player start position
    public static final Vector2 START_POSITION = new Vector2(10,10);
    //bonus start position
    public static final Vector2 UMBRELLA_START = new Vector2(20,15);
    public static final Vector2 KATANA_START = new Vector2(18,15);
    //Bonus sprite
    public static final String KATANA_ATLAS_PATH = "gfx/Katana/katana_assets.atlas";
    public static final String UMBRELLA_ATLAS_PATH = "gfx/Umbrella/UmJump_assets.atlas";
    //Sprite body
    public static float SPRITE_OFFSET_Y=1.5f;
    public static float SPRITE_OFFSET_X=1.5f;
    //Setting collision rectangle measurements
    public static final float COLLISION_RECT_WIDTH = 1;
    public static final float COLLISION_RECT_HEIGHT = 2;
    //sound
   // public static final String SOUND_PATH = "sounds/bitTuneFlux.wav";

}
