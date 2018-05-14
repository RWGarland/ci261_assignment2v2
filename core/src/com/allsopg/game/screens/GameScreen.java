package com.allsopg.game.screens;


import com.allsopg.game.TBWGame;
import com.allsopg.game.bodies.BonusSprite;
import com.allsopg.game.bodies.PlayerCharacter;
import com.allsopg.game.physics.WorldManager;
import com.allsopg.game.utility.CameraManager;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.allsopg.game.utility.Constants.KATANA_ATLAS_PATH;
import static com.allsopg.game.utility.Constants.KATANA_START;
import static com.allsopg.game.utility.Constants.MEDIUM;
import static com.allsopg.game.utility.Constants.PLAYER_ATLAS_PATH;
import static com.allsopg.game.utility.Constants.SMALL;
import static com.allsopg.game.utility.Constants.START_POSITION;
import static com.allsopg.game.utility.Constants.UMBRELLA_ATLAS_PATH;
import static com.allsopg.game.utility.Constants.UMBRELLA_START;
import static com.allsopg.game.utility.Constants.UNITSCALE;
import static com.allsopg.game.utility.Constants.VIRTUAL_HEIGHT;
import static com.allsopg.game.utility.Constants.VIRTUAL_WIDTH;

/**
 * Created by gerard on 12/02/2017.
 */

public class GameScreen extends ScreenAdapter {
    private TBWGame game;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private PlayerCharacter smif;
    private BonusSprite Umbrella;
    private BonusSprite Katana;
    private HUD gameHUD;
    private CameraManager cameraManager;
    private Rectangle playerRect;
    private Rectangle spriteRect;
    private float frameDelta = 0;
    private float stateTime;
    Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/bitTuneFlux.wav"));

    public GameScreen(TBWGame tbwGame){this.game = tbwGame;
        playMusic();
    }

    @Override
    public void resize(int width, int height) {
        game.camera.setToOrtho(false, VIRTUAL_WIDTH * UNITSCALE, VIRTUAL_HEIGHT * UNITSCALE);
        game.batch.setProjectionMatrix(game.camera.combined);
    }


    @Override
    public void show() {
        super.show();
        tiledMap = game.getAssetManager().get(Constants.BACKGROUND);
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap,UNITSCALE);
        orthogonalTiledMapRenderer.setView(game.camera);
        if(!WorldManager.isInitialised()){WorldManager.initialise(game,tiledMap);}
        //player
        smif = new PlayerCharacter(PLAYER_ATLAS_PATH,SMALL,START_POSITION);
        cameraManager = new CameraManager(game.camera,tiledMap);
        cameraManager.setTarget(smif);
        gameHUD = new HUD(game.batch,smif,game);
        //bonusSprites
        Umbrella = new BonusSprite(UMBRELLA_ATLAS_PATH,MEDIUM,UMBRELLA_START);
        Katana = new BonusSprite(KATANA_ATLAS_PATH,MEDIUM,KATANA_START);

    }

    @Override
    public void render(float delta) {
        frameDelta += delta;
        stateTime +=Gdx.graphics.getDeltaTime();
        //player
        smif.update(frameDelta);
        //bonus sprite
        Umbrella.update(stateTime);
        Katana.update(stateTime);
        gameHUD.update(delta);
        game.batch.setProjectionMatrix(game.camera.combined);
        clearScreen();
        draw();
        WorldManager.getInstance().doPhysicsStep(delta);

    }



    private void draw() {
       orthogonalTiledMapRenderer.setView(game.camera);
       orthogonalTiledMapRenderer.render();
        cameraManager.update();
        game.batch.begin();
        //player
        smif.draw(game.batch);
        //bonus
        Umbrella.draw(game.batch);
        Katana.draw(game.batch);
        game.batch.end();
        gameHUD.stage.draw();
        WorldManager.getInstance().debugRender();
    }
    @Override
    public void dispose(){
        game.dispose();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    //plays the game music in the background
    private void playMusic(){
        gameMusic.play();
        gameMusic.setVolume(0.01f);
    }


}