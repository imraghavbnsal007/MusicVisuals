package ie.tudublin;

//all other importing files
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class waterRipple extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    BeatDetect beat;
    FFT fft;

    final int initradius = 30;
    final int endradius = 600;

    int rad = initradius;
    float cx = width/2;
    float cy = height/2;

    public void settings() {
        size(800, 800);
        // fullScreen();
        smooth();
    }

    public void setup() {
        
    }

    public void draw() {
        background(0);
        colorMode(HSB);
        rad = rad + 2;
        float c = map(rad, initradius, endradius, 255, 0);
        
        fill(255,255,c);
        ellipse(cx, cy, rad, rad);
    }
}