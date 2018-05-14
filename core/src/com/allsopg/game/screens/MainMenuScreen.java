package com.allsopg.game.screens;

import com.allsopg.game.TBWGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

import static com.allsopg.game.utility.Constants.BACKGROUND;
import static com.allsopg.game.utility.Constants.VIRTUAL_HEIGHT;
import static com.allsopg.game.utility.Constants.VIRTUAL_WIDTH;


/**
 * Created by gerard on 16/02/2018.
 */

public class MainMenuScreen extends ScreenAdapter {
        private TBWGame game;
        private Texture TitleScreen;

        public MainMenuScreen(TBWGame aGame) {

            this.game = aGame;
            TitleScreen = new Texture(Gdx.files.internal("Menu/Menu.png"));
        }

    @Override
    public void show() {
        game.getAssetManager().load(BACKGROUND, TiledMap.class);
        game.getAssetManager().finishLoading();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.batch.begin();
        game.batch.draw(TitleScreen, 0, 0, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        game.font.draw(game.batch, "Assignment 2 By Edward and Rupert", 10, 60);
       // game.font.draw(game.batch, "Touch screen to start", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
