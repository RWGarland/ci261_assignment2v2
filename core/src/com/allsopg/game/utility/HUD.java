package com.allsopg.game.utility;

import com.allsopg.game.TBWGame;
import com.allsopg.game.bodies.PlayerCharacter;
import com.allsopg.game.screens.EndScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD implements Disposable {

    public Stage stage;
    private Viewport viewport;
    //structural elements for UI
    Table tableData;
    Table tableControls;
    // Navigation widgets
    private Button leftBtn,rightBtn,upBtn,downBtn;
    private PlayerCharacter playerCharacter;
    private TBWGame game;

    //score && time tracking variables
    private Integer worldTimer;
    private float timeCount;
    private static Integer score;
    private static Integer armour;
    private static Integer attack;
    private boolean timeUp;

    //Scene2D Widgets
    private Label countdownLabel, timeLabel, linkLabel, armourLinkLabel, attackLinkLabel;
    private static Label scoreLabel, armourLabel, attackLabel;

    public HUD(SpriteBatch sb, PlayerCharacter playerCharacter, TBWGame tbwGame) {
        this.playerCharacter = playerCharacter;
        this.game = tbwGame;
        //define tracking variables
        worldTimer = Constants.LEVEL_TIME;
        timeCount = 0;
        score = 0;
        armour = 0;
        attack = 0;
        //new camera used to setup the HUD viewport seperate from the main Game Camera
        //define stage using that viewport and games spritebatch
        viewport = new FitViewport(Constants.VIRTUAL_WIDTH,
                Constants.VIRTUAL_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        tableData = new Table();
        tableData.bottom();
        tableData.setFillParent(true);
        tableControls = new Table();
        tableControls.top();
        tableControls.setFillParent(true);

        createScoreAndTimer();
        createNavButtons();
        stage.addActor(tableData);
        stage.addActor(tableControls);
        Gdx.input.setInputProcessor(stage);
    }

    private void createScoreAndTimer(){
        countdownLabel = new Label(String.format("%03d", worldTimer),
                new Label.LabelStyle(new BitmapFont(), Color.RED));
        scoreLabel = new Label(String.format("%03d", score),
                new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        armourLabel = new Label(String.format("%03d", armour),
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        attackLabel = new Label(String.format("%03d", attack),
                new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        timeLabel = new Label("COUNTDOWN:",
                new Label.LabelStyle(new BitmapFont(), Color.RED));
        linkLabel = new Label("POINTS: ",
                new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        armourLinkLabel = new Label("ARMOUR: ",
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        attackLinkLabel = new Label("ATTACK: ",
                new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        //labels added to table using padding and expandX
        tableData.add(linkLabel).padBottom(5).padLeft(120);
        tableData.add(scoreLabel).expandX().padBottom(5);
        tableData.add(armourLinkLabel).padBottom(5).padLeft(100);
        tableData.add(armourLabel).expandX().padBottom(5);
        tableData.add(attackLinkLabel).padBottom(5).padLeft(80);
        tableData.add(attackLabel).expandX().padBottom(5);
        tableData.add(timeLabel).padBottom(5).padRight(20);
        tableData.add(countdownLabel).expandX().padBottom(5);

    }

    private void createNavButtons(){
        Texture actorUpBtn =
                new Texture(Gdx.files.internal("buttons/up.png"));
        Texture actorLeftBtn =
                new Texture(Gdx.files.internal("buttons/left.png"));
        Texture actorRightBtn =
                new Texture(Gdx.files.internal("buttons/right.png"));

        Button.ButtonStyle buttonStyleUp = new Button.ButtonStyle();
        buttonStyleUp.up =
                new TextureRegionDrawable(new TextureRegion(actorUpBtn));
        upBtn = new Button( buttonStyleUp );

        Button.ButtonStyle buttonStyleLeft = new Button.ButtonStyle();
        buttonStyleLeft.up =
                new TextureRegionDrawable(new TextureRegion(actorLeftBtn));
        leftBtn = new Button( buttonStyleLeft );

        Button.ButtonStyle buttonStyleRight = new Button.ButtonStyle();
        buttonStyleRight.up =
                new TextureRegionDrawable(new TextureRegion(actorRightBtn));
        rightBtn = new Button( buttonStyleRight );

        //add buttons
        tableControls.add(upBtn).padLeft(50);
        tableControls.add(downBtn).expandX(); // even though this line of code is not necessary anymore as we have removed the down button, we have kept this line in to help with the location of the other buttons on screen.
        tableControls.add(leftBtn).expandX().padLeft(300);
        tableControls.add(rightBtn).expandX().padRight(25);
        //add listeners to the buttons
        addButtonListeners();
    }

    private void addButtonListeners(){
        //up
        upBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerCharacter.move(CurrentDirection.UP);
            }
        });
        //left
        leftBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerCharacter.move(CurrentDirection.LEFT);
            }
        });
        //right
        rightBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerCharacter.move(CurrentDirection.RIGHT);
            }
        });
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            if (worldTimer > 0) {
                worldTimer--;
            } else {
                timeUp = true;
                GameData.getInstance().setScore(score);
                GameData.getInstance().setTime(worldTimer);
                game.setScreen(new EndScreen());
            }
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    public static void addArmour(int value) {
        armour += value;
        armourLabel.setText(String.format("%06d", armour));
    }

    public static void addAttack(int value) {
        attack += value;
        attackLabel.setText(String.format("%06d", attack));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public boolean isTimeUp() {
        return timeUp;
    }


    public static Label getScoreLabel() {
        return scoreLabel;
    }

    public static Integer getScore() {
        return score;
    }

    public static Label getArmourLabel(){
        return armourLabel;
    }

    public static Integer getArmour(){
        return armour;
    }

    public static Label getAttackLabel(){
        return attackLabel;
    }

    public static Integer getAttack(){
        return attack;
    }
}