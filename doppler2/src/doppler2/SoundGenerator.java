package doppler2;

import javax.sound.sampled.*;
import java.io.*;

public class SoundGenerator {
    public static void main(String[] args) {
        // Parametry dźwięku
        float frequency = 20; // Częstotliwość w Hz
        float modifier = 1; // Modyfikator częstotliwości
        int durationMs = 5000; // Czas trwania dźwięku w milisekundach
        float sampleRate = 44100; // Częstotliwość próbkowania

        // Tworzenie bufora dźwięku
        byte[] buf = new byte[2 * durationMs * (int) (sampleRate / 1000)];
        for (int i = 0; i < buf.length; i += 2) {
            double angle = i / (sampleRate / (frequency * modifier)) * 2.0 * Math.PI;
            short sample = (short) (Math.sin(angle) * Short.MAX_VALUE);
            buf[i] = (byte) (sample & 0xFF);
            buf[i + 1] = (byte) ((sample >> 8) & 0xFF);
        }

        // Zapisanie bufora dźwięku do pliku WAV na dysku
        File soundFile = new File("generated_sound.wav");
        try {
            // Tworzenie strumienia do zapisu danych do pliku
            FileOutputStream fileOutputStream = new FileOutputStream(soundFile);

            // Tworzenie obiektu AudioFormat
            AudioFormat audioFormat = new AudioFormat(sampleRate, 16, 1, true, false);

            // Tworzenie strumienia audio
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
            AudioInputStream audioInputStream = new AudioInputStream(byteArrayInputStream, audioFormat, buf.length / 2);

            // Zapisywanie danych do pliku
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, fileOutputStream);
            fileOutputStream.close(); // Zamykanie strumienia

            System.out.println("Plik dźwiękowy został zapisany na dysku: " + soundFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Usunięcie pliku po zakończeniu działania programu
//        soundFile.deleteOnExit();
    }
}
