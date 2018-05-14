package com.allsopg.game.Sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by Rupert on 09/05/2018.
 */

public class SoundLink {
    private IntMap<Sound> soundKeys;

    public SoundLink(){
        soundKeys = new IntMap<Sound>();

        Sound waterDropSnd = Gdx.audio.newSound(Gdx.files.internal("sounds/Water.wav"));
        Sound bounceSnd = Gdx.audio.newSound(Gdx.files.internal("sounds/Bounce.wav"));
        Sound swordSnd = Gdx.audio.newSound(Gdx.files.internal("sounds/Sword.wav"));


        soundKeys.put(1,waterDropSnd);
        soundKeys.put(2,bounceSnd);
        soundKeys.put(3,swordSnd);
    }

    public boolean play(int keyCode){
        Sound sound = soundKeys.get(keyCode);
        if(sound != null) {
            sound.play();
            return true;
        }
        return false;
    }

    public void dispose(){
        for(Sound sound : soundKeys.values()){
            sound.dispose();
        }
    }
}
