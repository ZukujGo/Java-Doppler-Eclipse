package doppler2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DopplerEffectAnimation extends JPanel implements ActionListener {
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 400;
    private static final int SOURCE_RADIUS = 20;
    private static final int RECEIVER_RADIUS = 20;
    private static final double SOUND_SPEED = 340.0; // Prędkość fali dźwiękowej w m/s
    private static final double TIME_STEP = 0.01; // Krok czasu w sekundach (10 ms)
    private static final double RADIUS_INCREMENT = SOUND_SPEED * TIME_STEP; // Przyrost promienia fali w jednym kroku czasu
    private static final int MAX_WAVE_COUNT = 12; // Maksymalna liczba fal

    private Timer timer;
    private double sourcePositionX = 50; // Początkowa pozycja źródła dźwięku
    private double receiverPositionX = PANEL_WIDTH - 50; // Początkowa pozycja odbiorcy
    private List<Wave> waves = new ArrayList<>(); // Lista fal dźwiękowych
    private long lastWaveSentTime = System.currentTimeMillis(); // Czas ostatniego wysłania fali
    private boolean isNewWaveReady = true; // Flaga oznaczająca gotowość do wysłania nowej fali
    private boolean isTouching = false; // Flaga oznaczająca dotknięcie się źródła dźwięku i odbiornika
    private boolean isAnimationRunning = false; // Flaga określająca, czy animacja jest uruchomiona

//    private int sourceSpeed = 2; // Prędkość źródła dźwięku (domyślna)
//    private int receiverSpeed = 0; // Prędkość odbiorcy (na początku 0)

    private Mediator mediator;

    private double sVel, rVel, sFrq;
    
//    private boolean check = true;    
    
    private int castedSourcePosX;
    private int castedReceiverPosX;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public DopplerEffectAnimation() {
        setPreferredSize(new Dimension(getWidth(), getHeight()));
//        receiverPositionX = getWidth() - 50;
        timer = new Timer((int) (TIME_STEP * 1000), this); // Timer z częstotliwością odświeżania 10 milisekund
    }

//    public void setSourceSpeed(int newSpeed) {
//        this.sourceSpeed = newSpeed;
//    }
//
//    public void setReceiverSpeed(int newSpeed) {
//        this.receiverSpeed = newSpeed;
//    }

    public void startAnimation() {
        if (!isAnimationRunning) {
            timer.start();
            receiverPositionX = getWidth() - 50;
            isAnimationRunning = true;
        }
    }

    public void stopAnimation() {
        timer.stop();
        isAnimationRunning = false;
    }

    public void resetAnimation() {
        timer.stop();
        isAnimationRunning = false;
        waves.clear();
        sourcePositionX = 50;
        receiverPositionX = getWidth() - 50;
        isNewWaveReady = true;
        isTouching = false;
//        check = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
//        receiverPositionX = getWidth() - 50;
        
//        if(check == true) {
//        	receiverPositionX = getWidth() - 50;
//        	check = false;
//        }
        
        castedSourcePosX = (int) sourcePositionX;
        castedReceiverPosX = (int) receiverPositionX;

        // Rysowanie źródła dźwięku
        g.setColor(Color.RED);
        g.fillOval(castedSourcePosX - SOURCE_RADIUS, getHeight() / 2 - SOURCE_RADIUS, 2 * SOURCE_RADIUS, 2 * SOURCE_RADIUS);

        // Rysowanie odbiorcy
        g.setColor(Color.BLUE);
        g.fillOval(castedReceiverPosX - RECEIVER_RADIUS, getHeight() / 2 - RECEIVER_RADIUS, 2 * RECEIVER_RADIUS, 2 * RECEIVER_RADIUS);

        // Rysowanie fal dźwiękowych
        for (Wave wave : waves) {
            int radius = (int) Math.round(wave.radius);
            int xCenter = (int) Math.round(wave.originX);

            g.setColor(wave.color);
            g.drawOval(xCenter - radius, getHeight() / 2 - radius, 2 * radius, 2 * radius);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Aktualny czas
        long currentTime = System.currentTimeMillis();

        sVel = mediator.getSourceVelocity()/100; 		// Tutaj już masz wartości pobierane z JTextField'ów
        rVel = mediator.getReceiverVelocity()/100; 		// Wystarczy, że napiszesz wzory
        sFrq = mediator.getSoundFrequency();
        
//        receiverPositionX = getWidth() - 50;
        
//        System.out.println("Prędkość odbiorcy: " + RADIUS_INCREMENT);

//        sourceSpeed = (int) sVel;
//        receiverSpeed = (int) rVel;

        // Sprawdzenie, czy minęły co najmniej 0.1 sekundy od ostatniego wysłania fali
        if (currentTime - lastWaveSentTime >= 100) {
            isNewWaveReady = true; // Ustawienie flagi na gotowość do wysłania nowej fali
        }

        // Jeśli flaga jest ustawiona, wyślij nową falę
        if (isNewWaveReady) {
            waves.add(new Wave(sourcePositionX, 0)); // Dodanie nowej fali do listy
            lastWaveSentTime = currentTime; // Aktualizacja czasu ostatniego wysłania fali
            isNewWaveReady = false; // Zresetowanie flagi
        }

        // Aktualizacja promieni fal
        for (int i = 0; i < waves.size(); i++) {
            Wave wave = waves.get(i);
            wave.radius += RADIUS_INCREMENT;
            wave.originX += (sVel / SOUND_SPEED) * RADIUS_INCREMENT;

            if (wave.radius > getWidth()) {
                waves.remove(i); // Usunięcie fali, jeśli promień przekracza szerokość panelu
                i--; // Zmniejszenie indeksu, aby nie pominąć kolejnego elementu po usunięciu
            }
        }

        // Przesunięcie źródła dźwięku w prawo
        sourcePositionX += sVel;
        if (sourcePositionX >= getWidth() - SOURCE_RADIUS) {
            sourcePositionX = 50; // Powrót źródła dźwięku na początkową pozycję
        }

        // Przesunięcie odbiorcy w lewo
        if (!isTouching) {
            receiverPositionX -= rVel;
            if (receiverPositionX <= RECEIVER_RADIUS) {
                receiverPositionX = RECEIVER_RADIUS;
            }
            // Sprawdzenie, czy źródło dźwięku dotknęło odbiornika
            if (Math.abs(sourcePositionX - receiverPositionX) <= SOURCE_RADIUS + RECEIVER_RADIUS) {
                isTouching = true; // Ustawienie flagi dotknięcia
                stopAnimation(); // Zatrzymanie animacji
            }
        }

        repaint(); // Odświeżenie panelu
    }

    private static class Wave {
        double originX; // Pozycja początkowa fali
        double radius; // Promień fali
        Color color;

        public Wave(double originX, double radius) {
            this.originX = originX;
            this.radius = radius;
            this.color = Color.BLACK;
        }
    }
}

