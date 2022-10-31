package Assignment;

import processing.core.PApplet;
import ie.tudublin.*;

public class Visualiser extends Visual {
    // Class Objects
    circle circle_obj;
    MusicViz musicViz_obj;
    Oscilloscope oscilloscope_obj;
    LuYu luyu_obj;
    circle_effect circle_effect_obj;
    Mesh_Line mesh_line_obj;
    waterRipple waterRipple_obj;

    public void settings() {
        // size(1366, 768, P3D);
        size(displayWidth, displayHeight);
        fullScreen(P3D, SPAN);
        smooth();
    }

    int mode = 6;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            } else {
                getAudioPlayer().rewind();
                getAudioPlayer().play();
            }
        }
    }

    public void setup() {
        startMinim();
        loadAudio("[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3");
        getAudioPlayer().play();

        // Object Intialisation
        circle_obj = new circle(this);
        musicViz_obj = new MusicViz(this);
        oscilloscope_obj = new Oscilloscope(this);
        luyu_obj = new LuYu(this);
        circle_effect_obj = new circle_effect(this);
        mesh_line_obj = new Mesh_Line(this);
        waterRipple_obj = new waterRipple(this);
    }

    public void draw() {

        switch(mode)
        {
            case 0:
            {
                circle_obj.render();
                break;
            }

            case 1:
            {
                musicViz_obj.render();
                break;
            }

            case 2:
            {
                oscilloscope_obj.render();
                break;
            }

            case 3:
            {
                luyu_obj.render();
                break;
            }

            case 4:
            {
                circle_effect_obj.render();
                break;
            }

            case 5:
            {
                mesh_line_obj.render();
                break;
            }

            case 6:
            {
                waterRipple_obj.render();
                break;
            }
            
            case 7:
            {
                // cs.render();
                break;
            }
        }
        
    }

}
