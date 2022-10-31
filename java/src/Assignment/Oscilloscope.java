package Assignment;

import processing.core.PApplet;

public class Oscilloscope {
    Visualiser pa;

    float gain = 200;
    int tbase = 1024;
    float[] myBuffer;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float off = 0;

    public Oscilloscope(Visualiser pa) {
        this.pa = pa;

        myBuffer = new float[pa.getAudioPlayer().bufferSize()];

        y = pa.height / 2;
        smoothedY = y;

        lerpedBuffer = new float[pa.width];
    }

    public void render() {
        pa.colorMode(PApplet.HSB);
        // colorMode(RGB);
        // pa.background(0);
        pa.fill(0, 60);
        pa.noStroke();
        pa.rect(0, 0, pa.width, pa.height);

        pa.stroke(255);
        pa.noFill();
        pa.strokeWeight(4);

        float average = 0;
        float sum = 0;
        off += 1;

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < pa.getAudioBuffer().size() ; i ++)
        {
            sum += PApplet.abs(pa.getAudioBuffer().get(i));
            myBuffer[i] = PApplet.lerp(myBuffer[i], pa.getAudioBuffer().get(i), 0.05f);
        }
        average = sum / (float) pa.getAudioBuffer().size();

        smoothedAmplitude = PApplet.lerp(smoothedAmplitude, average, 0.1f);
        
        float cx = pa.width / 2;
        float cy = pa.height / 2;

        float r = PApplet.map(smoothedAmplitude, 0, 0.5f, 100, 2000);
        float r1 = PApplet.map(smoothedAmplitude, 0, 0.5f, 150, 2500);

        float c = PApplet.map(smoothedAmplitude, 0, 0.5f, 0, 255);
        pa.stroke(c, 255, 255);
        pa.circle(cx, cy, r);
        pa.circle(cx,cy, r1);

        // ellipse(width / 2, height / 2, 240, 240);

        pa.translate(0, pa.height / 2);
        pa.stroke(255);
        pa.strokeWeight(2);

        // draw the output waveforms, so there's something to look at
        // first grab a stationary copy
        for (int i = 0; i < pa.getAudioPlayer().bufferSize(); ++i) {
            myBuffer[i] = pa.getAudioPlayer().left.get(i);
        }
        // find trigger point as largest +ve slope in first 1/4 of buffer
        int offset = 0;
        float maxdx = 0;
        for (int i = 0; i < myBuffer.length / 4; ++i) {
            float dx = myBuffer[i + 1] - myBuffer[i];
            if (dx > maxdx) {
                offset = i;
                maxdx = dx;
            }
        }
        // plot out that waveform
        int mylen = PApplet.min(tbase, myBuffer.length - offset);
        for (int i = 0; i < mylen - 1; i++) {
            pa.fill((pa.frameCount + i / 2) % 360, 80, 100);

            // map x from 0 to tbase to the whole width
            float x1 = PApplet.map(i, 0, tbase, 0, pa.width);
            float x2 = PApplet.map(i + 1, 0, tbase, 0, pa.width);
            float x3 = PApplet.map(i * 2 + 10, 0, tbase, 0, pa.width);
            float x4 = PApplet.map(i * 2 + 12, 0, tbase, 0, pa.width);

            pa.line(x1, 100 - myBuffer[i + offset] * gain, x2, 100 - myBuffer[i + 1 + offset] * gain);

            pa.line(x3, 100 - myBuffer[i + offset] * gain, x4, 100 - myBuffer[i + 1 + offset] * gain);

        }
    }

    
}
