package Assignment;

import processing.core.PApplet;

public class Mesh_Line {
    Visualiser pa;

    int x;              // used to make the circle spin
    int radius = 200;   // radius in pixels of the circle

    float[] lerpedBuffer;
    float lerpedAverage = 0;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float off = 0;

    public Mesh_Line(Visualiser pa) {
        this.pa = pa;

        y = pa.height / 2;
        smoothedY = y;

        lerpedBuffer = new float[2048];
        x = 0;
    }

    public void render() {
        // background(0);
        float halfH = pa.height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < pa.getAudioBuffer().size(); i++) {
            sum += PApplet.abs(pa.getAudioBuffer().get(i));
            lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], pa.getAudioBuffer().get(i), 0.05f);
        }

        average = sum / (float) pa.getAudioBuffer().size();
        smoothedAmplitude = PApplet.lerp(smoothedAmplitude, average, 0.1f);

        pa.background(0);
        pa.colorMode(PApplet.HSB);

        pa.strokeWeight(2);

        for (int i = 0; i < pa.getAudioBuffer().size(); i += 10) {

            float c = PApplet.map(i, 0, pa.getAudioBuffer().size(), 0, 255);
            float f = lerpedBuffer[i] * halfH * 1.0f;

            pa.stroke(c, 255, 255);

            lerpedBuffer[i] = PApplet.lerp(lerpedBuffer[i], pa.getAudioBuffer().get(i), 0.1f);

            // Bands around the sides of the screens
            pa.line(0, i, lerpedBuffer[i] * halfH * 4, i);
            pa.line(pa.width, i, pa.width - (lerpedBuffer[i] * halfH * 4), i);
            pa.line(i, 0, i, lerpedBuffer[i] * halfH * 4);
            pa.line(i, pa.height, i, pa.height - (lerpedBuffer[i] * halfH * 4));
            
            // Center line
            pa.circle(i, halfH + f, 5);
            pa.circle(i, halfH - f, 5);

        } // end for

    }
    
}
