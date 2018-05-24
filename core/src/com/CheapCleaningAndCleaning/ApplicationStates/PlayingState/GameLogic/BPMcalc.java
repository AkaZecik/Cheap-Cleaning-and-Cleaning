package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic;

import com.CheapCleaningAndCleaning.libraries.jwave.Transform;
import com.CheapCleaningAndCleaning.libraries.jwave.transforms.FastWaveletTransform;
import com.CheapCleaningAndCleaning.libraries.jwave.transforms.wavelets.Wavelet;
import com.CheapCleaningAndCleaning.libraries.jwave.transforms.wavelets.daubechies.Daubechies4;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.event.ChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BPMcalc {

    AudioInputStream audioInputStream;

    Wavelet wavelet = new Daubechies4();

    int windowFrames;
    int windowsToProcess = 0;
    double _bpm = -1;
    double[] instantBpm = new double[0];

    BPMcalc(AudioInputStream ais, int wf) {
        audioInputStream = ais;
        windowFrames = wf;

        if ((windowFrames < 0) || !((windowFrames & (windowFrames - 1)) == 0))
            throw new RuntimeException(); //windowsFrames must be power of 2

        if (windowsToProcess <= 0) {
            windowsToProcess = (int) (audioInputStream.getFrameLength() / windowFrames);
        }
    }

    public static double[] append(double[] array, double value) {
        double[] result = Arrays.copyOf(array, array.length + 1);
        result[result.length - 1] = value;
        return result;
    }

    public static double[] correlate(double[] array) {
        int n = array.length;
        double[] correlation = new double[n];
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (k + i < n) correlation[k] = correlation[k] + array[i] * array[k + i];
            }
        }
        return correlation;
    }

    public static double mean(double[] array) {
        int n = array.length;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        return sum / n;
    }

    public static double[] sub(double[] array, double value) {
        int n = array.length;
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = array[i] - value;
        }
        return res;
    }

    public static double median(double[] array) {
        double[] copy = array.clone();
        Arrays.sort(copy);
        double median;
        if (copy.length % 2 == 0)
            median = (copy[copy.length / 2] + copy[copy.length / 2 - 1]) / 2;
        else
            median = copy[copy.length / 2];
        return median;
    }

    public static double[] abs(double[] array) {
        int n = array.length;
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = Math.abs(array[i]);
        }
        return res;
    }

    public static double[] undersample(double[] array, int pace) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) if (i % pace == 0) list.add(array[i]);
        return list.stream().mapToDouble(d -> d).toArray();
    }

    public static double[] someFunction(int[] array) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) if (i % 2 == 0) list.add((double) array[i]);
        return list.stream().mapToDouble(d -> d).toArray();
    }

    public static double[] addarray(double[] array1, double[] array2) {
        int n = array1.length;
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = array1[i] + array2[i];
        }
        return res;
    }


    public long[] readSamples() throws IOException {
        long value = 0L;

        //
//        System.out.println(audioInputStream.available());
        //

        long[] res = new long[3];

        int bufferPointer = 0;
        int bytesRead = 0;
        byte[] buffer = new byte[6];
        int bytesPerSample = (int) Math.ceil(audioInputStream.getFormat().getSampleSizeInBits() / 8);
        for (int b = 0; b < 2 * bytesPerSample; b++) {
            if (b == bytesPerSample) {
                res[0] = value;
                value = 0;
            }
            if (bufferPointer == bytesRead) {
                int read = audioInputStream.read(buffer, 0, 6);
                if (read == -1) throw new RuntimeException();
                bytesRead = read;
                bufferPointer = 0;
            }


//            System.out.println(bytesPerSample);

            int byteValue = buffer[bufferPointer];
            if (b < bytesPerSample - 1 || bytesPerSample == 1)
                byteValue = byteValue & 0xFF;
            value = value + (byteValue << ((b % 3) * 8));

            bufferPointer = bufferPointer + 1;


        }


        res[1] = value;
        return res;
    }


    int readFrames(int[] sampleBuffer, int offset, int numFramesToRead) throws IOException {
        int index = offset;
        int frameCounter = 0;
        for (int f = 0; f < numFramesToRead; f++) {
            if (frameCounter == audioInputStream.getFrameLength()) return f;

            long[] samples = readSamples();
            sampleBuffer[index] = (int) samples[0];
            index = index + 1;
            sampleBuffer[index] = (int) samples[1];
            index = index + 1;

            frameCounter = frameCounter + 1;
        }

        return numFramesToRead;
    }

    private int detectPeak(double[] data) {
        double max = Double.MIN_VALUE;

        for (double x : data) {
            if (Math.abs(x) > max) max = Math.abs(x);
        }
        int location = -1;
        int i = 0;
        while (i < data.length && location == -1) {
            if (data[i] == max) {
                location = i;
            }
            i = i + 1;
        }
        i = 0;
        while (i < data.length && location == -1) {
            if (data[i] == -max) {
                location = i;
            }
            i = i + 1;
        }
        return location;
    }

    private void computeWindowBpm(double[] data) {

        double[] aC = null;
        double[] dC = null;
        double[] dCSum = null;
        int dCMinLength = 0;
        int levels = 8;
        int maxDecimation = (int) Math.pow(2, levels - 1);
        int minIndex = (int) (((double) 60) / 220 * ((double) audioInputStream.getFormat().getSampleRate()) / maxDecimation);
        int maxIndex = (int) (((double) 60) / 40 * ((double) audioInputStream.getFormat().getSampleRate()) / maxDecimation);


        for (int loop = 0; loop < levels; loop++) {

            Transform transform = new Transform(new FastWaveletTransform(wavelet));
            if (loop == 0) {
                double[][] coefficients = transform.decompose(data);
                int l = coefficients.length - 1;
                aC = Arrays.copyOfRange(coefficients[1], 0, coefficients[1].length / 2);
                dC = Arrays.copyOfRange(coefficients[1], coefficients[l].length / 2, coefficients[1].length);
                dCMinLength = ((int) (dC.length / maxDecimation)) + 1;
            } else {
                double[][] coefficients = transform.decompose(aC);
                int l = coefficients.length - 1;
                aC = Arrays.copyOfRange(coefficients[1], 0, coefficients[1].length / 2);
                dC = Arrays.copyOfRange(coefficients[1], coefficients[l].length / 2, coefficients[1].length);
            }

            int pace = (int) Math.pow(2, (levels - loop - 1));

            dC = abs(undersample(dC, pace));
            dC = sub(dC, mean(dC));

            if (dCSum == null) {
                dCSum = Arrays.copyOfRange(dC, 0, dCMinLength);
            } else {
                dCSum = addarray(Arrays.copyOfRange(dC, 0, Math.min(dCMinLength, dC.length)), dCSum);
            }
        }

        aC = abs(aC);
        aC = sub(aC, mean(aC));
        dCSum = addarray(Arrays.copyOfRange(aC, 0, Math.min(dCMinLength, dC.length)), dCSum);

        double[] correlated = correlate(dCSum);
        double[] correlatedTmp = Arrays.copyOfRange(correlated, minIndex, maxIndex);

        int location = detectPeak(correlatedTmp);

        int realLocation = minIndex + location;
        double windowBpm = ((double) 60) / realLocation * (((double) audioInputStream.getFormat().getSampleRate()) / maxDecimation);
        instantBpm = append(instantBpm, windowBpm);
    }

    double bpm() throws IOException {
        if (_bpm == -1) {
            for (int currentWindow = 0; currentWindow < windowsToProcess; currentWindow++) {
                int[] buffer = new int[windowFrames * audioInputStream.getFormat().getChannels()];
                int framesRead = readFrames(buffer, 0, windowFrames);
                double[] leftChannelSamples = someFunction(buffer); //chyba dobre tlumaczenie
                computeWindowBpm(leftChannelSamples);
            }
            _bpm = median(instantBpm);
        }
        return _bpm;
    }

    public static void main(String... args) {
        Path path =Paths.get("core/assets/music/test2.wav");
        File file= path.toFile();
        try {
            System.out.println(new BPMcalc(AudioSystem.getAudioInputStream(file),131072).bpm());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(path);
            System.out.println(file);
        }
    }
}
