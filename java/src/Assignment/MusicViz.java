package Assignment;

import processing.core.*;

public class MusicViz {
    Visualiser pa;

    int r = 200;
    float rad = 0;    
    
    public MusicViz(Visualiser pa) {
        this.pa = pa;
    }

    public void render() {
        pa.background(0);
        pa.colorMode(PApplet.HSB);
        
        pa.getBeatDetect().detect(pa.getAudioPlayer().mix);
        

        pa.noStroke();
        pa.rect(0, 0, pa.width, pa.height);
        pa.translate(pa.width / 2, pa.height / 2);
        pa.noFill();
        
        // Center beat circle
        pa.fill(255);
        if (pa.getBeatDetect().isOnset())
            rad = (float) (rad * 0.8);
        else
            rad = 70;
        pa.ellipse(0, 0, 2 * rad, 2 * rad);
        pa.stroke(-1, 50);
        int bsize = pa.getAudioPlayer().bufferSize();

        // RGB Lines
        for (int i = 0; i < bsize - 1; i += 5) {
            pa.stroke(PApplet.map(i, 0, bsize - 1, 0, 255), 255, 255);
            float x = (r) * PApplet.cos(i * 2 * PApplet.PI / bsize);
            float y = (r) * PApplet.sin(i * 2 * PApplet.PI / bsize);
            float x2 = (r + pa.getAudioPlayer().left.get(i) * 100) * PApplet.cos(i * 2 * PApplet.PI / bsize);
            float y2 = (r + pa.getAudioPlayer().left.get(i) * 100) * PApplet.sin(i * 2 * PApplet.PI / bsize);
            pa.line(x, y, x2, y2);
        }

        // Dots on the lines
        pa.beginShape();
        pa.noFill();
        pa.stroke(-1, 50);
        for (int i = 0; i < bsize; i += 30) {
            float x2 = (r + pa.getAudioPlayer().left.get(i) * 100) * PApplet.cos(i * 2 * PApplet.PI / bsize);
            float y2 = (r + pa.getAudioPlayer().left.get(i) * 100) * PApplet.sin(i * 2 * PApplet.PI / bsize);
            pa.vertex(x2, y2);
            pa.pushStyle();
            pa.stroke(-1);
            pa.strokeWeight(2);
            pa.point(x2, y2);
            pa.popStyle();
        }
        pa.endShape();

    }
    
}
