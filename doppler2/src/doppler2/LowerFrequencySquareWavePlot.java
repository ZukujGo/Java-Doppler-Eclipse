package doppler2;

import javax.swing.*;
import java.awt.*;

public class LowerFrequencySquareWavePlot extends JPanel {

    private int frequency; // Częstotliwość sygnału
    private int duration;  // Czas trwania sygnału (ilość okresów)
    private Color waveColor; // Kolor sygnału

    private Mediator mediator;

    private long sVel, rVel, sFrq;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Wymiary panelu
        int width = getWidth();
        int height = getHeight();

        // Ustawienia rysowania
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Oś X i Oś Y
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, height / 2, width, height / 2);  // Oś X
        g2d.drawLine(width / 2, 0, width / 2, height);  // Oś Y

        // Rysowanie sygnału prostokątnego
        g2d.setColor(Color.BLUE);
        int amplitude = height / 4;
        int frequency = 5;
        int period = width / frequency;
        for (int x = 0; x < width; x++) {
            int y;
            if ((x / (period / 2)) % 2 == 0) {
                y = height / 2 - amplitude;
            } else {
                y = height / 2;  // Zero level
            }
            g2d.drawLine(x, y, x, y);
        }
    }

    public void showEnlargedPlot() {
        JFrame frame = new JFrame("Enlarged Rectangle Wave Plot");
        LowerFrequencySquareWavePlot enlargedPlot = new LowerFrequencySquareWavePlot();
        frame.add(enlargedPlot);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}

