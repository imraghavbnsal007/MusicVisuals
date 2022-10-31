package Assignment;

import processing.core.PApplet;

public class waterRipple {
    Visualiser pa;

    public waterRipple(Visualiser pa) {
        this.pa = pa;

        cols = pa.width;
        rows = pa.height;

        current = new float[cols][rows];
        previous = new float[cols][rows];
    }

    int cols = 200;
    int rows = 200;

    float[][] current;
    float[][] previous;

    float dampening = (float) 0.93;

    public void render() {
        pa.getBeatDetect().detect(pa.getAudioBuffer());
        pa.background(0);
        pa.loadPixels();
        if (pa.getBeatDetect().isOnset()) {
            int x = 200 + (int) pa.random((float) cols - 400);
            int y = 100 + (int) pa.random((float) rows - 200);
            current[x][y] = 255;
        }
        for (int i = 1; i < cols - 1; i++) {
            for (int j = 1; j < rows - 1; j++) {
                
                current[i][j] = (previous[i - 1][j] +
                        previous[i + 1][j] +
                        previous[i][j - 1] +
                        previous[i][j + 1]) / 2 -
                        current[i][j];

                current[i][j] = current[i][j] * dampening; // Gradually decreases the color value by 0.93%
                int index = i + j * cols;
                pa.pixels[index] = pa.color(current[i][j] * 255);
            } // end inner for loop
        } // end outer for loop
        pa.updatePixels();

        float[][] temp = previous;
        previous = current;
        current = temp;
    }
}
