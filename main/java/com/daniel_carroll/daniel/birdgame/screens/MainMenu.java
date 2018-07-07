package com.daniel_carroll.daniel.birdgame.screens;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.SystemClock;

import com.daniel_carroll.daniel.birdgame.MainActivity;
import com.daniel_carroll.daniel.birdgame.R;
import com.daniel_carroll.daniel.birdgame.utility.Util;


public class MainMenu extends Level {
    private long startTime;
    private int currentMenu = 0, currentDesiredMenu = 0;
    private boolean pressed = false, displayingMuted = false;

    public MainMenu(Context c) {
        context = c;

        startTime = SystemClock.elapsedRealtime();

        levelType = LevelType.MAINMENU;

        spriteManager.loadSprite(context, R.drawable.menuback,"menuback", 1.0, 1.0);
        spriteManager.loadSprite(context, R.drawable.menubacksettings,"menubacksettings", 1.0, 1.0);
        spriteManager.loadSprite(context, R.drawable.menubacksettings2,"menubacksettings2", 1.0, 1.0);
        spriteManager.loadSprite(context, R.drawable.pressedboxsettings,"pressedboxsettings", 1.0, 1.0);
        spriteManager.loadSprite(context, R.drawable.pressedboxplay,"pressedboxplay", 1.0, 1.0);
        spriteManager.loadSprite(context, R.drawable.pressedboxexit,"pressedboxexit", 1.0, 1.0);
        spriteManager.loadSprite(context, R.drawable.pressedboxback,"pressedboxback", 1.0, 1.0);
        spriteManager.loadSprite(context, R.drawable.jeff,"jeff", 300, 300, false);

        spriteManager.displaySprite("menuback", 10);

        spriteManager.displaySprite("jeff", 20);
        spriteManager.getImages(20).visible = false;
    }

    @Override
    public void update() {
        long deltaTime = SystemClock.elapsedRealtime() - startTime;
        spriteManager.update(getDeltaTime());

        if(Util.isPressed() && !pressed) {
            pressed = true;
        }
        if(!Util.isPressed() && pressed)
        {
            pressed = false;

            //System.out.println("tX: " + Util.gettX() + "tY: " + Util.gettY());

            if(currentMenu == 0)
            {
                if(Util.gettX() > 592 && Util.gettX() < 1983
                        && Util.gettY() > 1101 && Util.gettY() < 1518)
                {

                    levelType = LevelType.BOARD;
                }
                else if(Util.gettX() > 592 && Util.gettX() < 1983
                        && Util.gettY() > 1804 && Util.gettY() < 2232)
                {
                    currentDesiredMenu = 1;

                }
                else if(Util.gettX() > 592 && Util.gettX() < 1983
                        && Util.gettY() > 2515 && Util.gettY() < 2946)
                {
                    System.out.println("Exit");

                }

            }
            else
            {
                if(Util.gettX() > 144 && Util.gettX() < 1524
                        && Util.gettY() > 2809 && Util.gettY() < 3226)
                {

                    currentDesiredMenu = 0;
                }
                else if(Util.gettX() > 250 && Util.gettX() < 693
                        && Util.gettY() > 2112 && Util.gettY() < 2547)
                    Util.muted = !Util.muted;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        spriteManager.draw(canvas);

        if(currentMenu != 0 && currentDesiredMenu == 0)
        {
            spriteManager.removeImage(10);
            spriteManager.displaySprite("menuback", 10);

            currentMenu = 0;
        }
        else if(currentMenu != 1 && currentDesiredMenu == 1)
        {
            spriteManager.removeImage(10);

            if(Util.muted)
                spriteManager.displaySprite("menubacksettings2", 10);
            else
                spriteManager.displaySprite("menubacksettings", 10);

            currentMenu= 1;
        }
        if(currentMenu == 1 && displayingMuted != Util.muted)
        {
            spriteManager.removeImage(10);

            if(Util.muted)
                spriteManager.displaySprite("menubacksettings2", 10);
            else
                spriteManager.displaySprite("menubacksettings", 10);

            displayingMuted = !displayingMuted;
        }



    }

    @Override
    public LevelType desiredLevel() {
        return levelType;
    }


    @Override
    public Level exit() {

        spriteManager.exit();
        return new Board(context);
    }
}
