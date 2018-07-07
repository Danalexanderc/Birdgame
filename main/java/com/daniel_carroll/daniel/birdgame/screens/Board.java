package com.daniel_carroll.daniel.birdgame.screens;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.Toast;

import com.daniel_carroll.daniel.birdgame.CharacterSprite;
import com.daniel_carroll.daniel.birdgame.MainActivity;
import com.daniel_carroll.daniel.birdgame.R;
import com.daniel_carroll.daniel.birdgame.utility.Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

public class Board extends Level {

    private boolean pressed = false;

    public Board(Context c) {
        context = c;

        levelType = LevelType.BOARD;
    }

    @Override
    public void update() {

        if(Util.isPressed() && !pressed) {
            pressed = true;

            if(Util.gettY() > Util.screenHeight/2)
            {
                try {
                    FileOutputStream fileout=context.openFileOutput("test1.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                    outputWriter.write("Hello there!");
                    outputWriter.close();

                    //display file saved message
                    System.out.println("File Saved!");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                    FileInputStream fileIn=context.openFileInput("test1.txt");
                    InputStreamReader InputRead= new InputStreamReader(fileIn);

                    char[] inputBuffer= new char[100];
                    String s="";
                    int charRead;

                    while ((charRead=InputRead.read(inputBuffer))>0) {
                        // char to string conversion
                        String readstring=String.copyValueOf(inputBuffer,0,charRead);
                        s +=readstring;
                    }
                    InputRead.close();
                    System.out.println(s);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        if(!Util.isPressed() && pressed)
        {
            pressed = false;
        }




    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(200,100,100));
    }

    @Override
    public LevelType desiredLevel() {
        return levelType;
    }


    @Override
    public Level exit() {
        return null;
    }
}
