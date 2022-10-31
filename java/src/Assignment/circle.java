package Assignment;

import processing.core.PApplet;

public class circle {
    Visualiser pa;
    float n = 15;

    int c1 = 51;
    int c2 = 204;
    int c3 = 255;


    public circle(Visualiser pa) {
        this.pa = pa;
    }

    public void render()
    {
        pa.colorMode(PApplet.HSB);
        pa.fill(0, 20);
        pa.noStroke();
        pa.rect(0, 0, pa.width, pa.height);
        pa.translate(pa.width / 2, pa.height / 2);

        for (int i = 0; i < pa.getAudioPlayer().bufferSize() - 1; i++) {

            float leftLevel = pa.getAudioPlayer().left.level() * 20;
            pa.ellipse(i, i, leftLevel, leftLevel);
            pa.rotateZ((float) (n * - PApplet.PI / 3 * 0.05));
            
            // c1 keeps cycling through the colors
            // c3 value matches max brightness when yellow is displayed, making yellow more prominent
            pa.fill(c1++%256,255,c3++%256); 
            
        }

        n += 0.008;
    }
}
