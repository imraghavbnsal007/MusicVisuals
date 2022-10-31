package Assignment;

import processing.core.PApplet;

public class LuYu {
    Visualiser pa;

    int n = 0;

    public LuYu(Visualiser pa) {
        this.pa = pa;
    }

    public void render() {
        
        pa.background(0);
        pa.colorMode(PApplet.RGB);
        pa.noStroke();

        pa.translate(pa.width / 2, pa.height / 2);

        for (int i = 0; i < pa.getAudioPlayer().bufferSize() - 1; i++) {
            pa.rotateX((float) (n * - PApplet.PI / 4 * 0.05));
            
            pa.fill(255, 20, 147); // dark pink

            float leftLevel = pa.getAudioPlayer().left.level() * 100;
            pa.ellipse(i, i, leftLevel, leftLevel);

            pa.rotateZ((float) (n * - PApplet.PI / 5 * 0.05)); //3 -> 1
           
            pa.fill(124, 252, 0);  // lawn green

            float rightLevel = pa.getAudioPlayer().right.level() * 100;
            pa.rect(i, i, rightLevel, rightLevel);
        }
        n++;
    }
    
}
