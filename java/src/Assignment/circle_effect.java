package Assignment;

import processing.core.PApplet;

public class circle_effect {
    Visualiser pa;

    float n4 = 0;
    float n6 = 0;

    public circle_effect(Visualiser pa) {
        this.pa = pa;
    }

    public void render() {
        pa.colorMode(PApplet.RGB);

        pa.fill(0, 50);
        pa.noStroke();
        pa.rect(0, 0, pa.width, pa.height);
        pa.translate(pa.width / 2, pa.height / 2);

        for (int i = 0; i < pa.getAudioPlayer().bufferSize() - 1; i++) {

            float angle1 = PApplet.sin(i + n4) * 10;
            float angle2 = PApplet.sin(i + n6) * 300;

            float x = PApplet.sin(PApplet.radians(i)) * (angle2 + 30);
            float y = PApplet.cos(PApplet.radians(i)) * (angle2 + 30);

            float x3 = PApplet.sin(PApplet.radians(i)) * (500 / angle1);
            float y3 = PApplet.cos(PApplet.radians(i)) * (500 / angle1);

            pa.fill(255, 255, 0);  // yellow color

            pa.ellipse(x, y, pa.getAudioPlayer().left.get(i) * 10, pa.getAudioPlayer().left.get(i) * 10);

            pa.fill(255);  // white color
            pa.rect(x, y, pa.getAudioPlayer().right.get(i) * 10, pa.getAudioPlayer().left.get(i) * 10);  //10 -> 20

            pa.fill(255, 69, 0);   // orange color

            pa.rect(x, y, pa.getAudioPlayer().right.get(i) * 10, pa.getAudioPlayer().left.get(i) * 10);

            pa.fill(255, 255, 255);    // white color
            pa.rect(x3, y3, pa.getAudioPlayer().right.get(i) * 10, pa.getAudioPlayer().right.get(i) * 20);

        }

        n4 += 0.008;
        n6 += 0.04;
    }
    
}
