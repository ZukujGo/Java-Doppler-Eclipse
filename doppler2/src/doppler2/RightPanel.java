package doppler2;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class RightPanel extends JPanel {

    /**
     * Prawy panel
     * Przy kazdym z elementow wywolywany jest poczatkowy
     * kolor z jakim pojawiaja sie po wlaczeniu APP
     */
    private static final long serialVersionUID = 103;

    private Mediator mediator;

    private int rightPanelHeight = 0;
    private int rightPanelWidth = 100;
    private JPanel rightTopPanel, rightBottomPanel;

    private JTextField soundFrequency, sourceVelocity, receiverVelocity;
    private JLabel sVelInfo, rVelInfo, frequencyInfo;
    private NumericFilter numericFilter;

    private JButton downloadFile, generateWave, startAnimation, resetEverything;

    public RightPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(66, 66, 66));


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                rightPanelHeight = (int) getHeight();
                rightBottomPanel.setPreferredSize(new Dimension(rightPanelWidth, rightPanelHeight / 2));
                rightBottomPanel.revalidate();
//                rightTopPanel.setPreferredSize(new Dimension(100, rightPanelHeight / 2)); //po odkomentowaniu zmienia sie szerokosc????
//                rightTopPanel.revalidate();
            }
        });

        rightTopPanel = new JPanel();
        rightTopPanel.setLayout(new BoxLayout(rightTopPanel, BoxLayout.Y_AXIS));
        rightTopPanel.setBackground(new Color(66, 66, 66));

        sVelInfo = new JLabel("Podaj predkosc zrodla:");
        sourceVelocity = new JTextField();
        sourceVelocity.setPreferredSize(new Dimension(100, 20));
        rVelInfo = new JLabel("Podaj predkosc odbiorcy:");
        receiverVelocity = new JTextField();
        receiverVelocity.setPreferredSize(new Dimension(100, 20));
        frequencyInfo = new JLabel("Podaj czestotliwosc fali:");
        soundFrequency = new JTextField();
        soundFrequency.setPreferredSize(new Dimension(100, 20));

        numericFilter = new NumericFilter();
        ((AbstractDocument) sourceVelocity.getDocument()).setDocumentFilter(numericFilter);
        ((AbstractDocument) receiverVelocity.getDocument()).setDocumentFilter(numericFilter);
        ((AbstractDocument) soundFrequency.getDocument()).setDocumentFilter(numericFilter);

        rightTopPanel.add(Box.createVerticalStrut(15));
        rightTopPanel.add(sVelInfo);
        rightTopPanel.add(sourceVelocity);
        rightTopPanel.add(Box.createVerticalStrut(30));
        rightTopPanel.add(rVelInfo);
        rightTopPanel.add(receiverVelocity);
        rightTopPanel.add(Box.createVerticalStrut(30));
        rightTopPanel.add(frequencyInfo);
        rightTopPanel.add(soundFrequency);
        rightTopPanel.add(Box.createVerticalStrut(30));

        sourceVelocity.setText("0");
        receiverVelocity.setText("0");
        soundFrequency.setText("0");

        add(rightTopPanel);

        rightBottomPanel = new JPanel();
        rightBottomPanel.setLayout(new BoxLayout(rightBottomPanel, BoxLayout.Y_AXIS));
        rightBottomPanel.setBackground(new Color(66, 66, 66));
        rightBottomPanel.add(Box.createVerticalStrut(15));

        downloadFile = new JButton("<html><center>Pobierz<br>dźwięk</center></html>");
        downloadFile.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
//        downloadFile.setPreferredSize(new Dimension(100, 40));
        downloadFile.addActionListener(e -> {
            mediator.saveSound();
        });
        downloadFile.setEnabled(false);
        rightBottomPanel.add(downloadFile);
        rightBottomPanel.add(Box.createVerticalStrut(15));

        generateWave = new JButton("<html><center>Generuj<br>dźwięk</center></html>");
        generateWave.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
//        generateWave.setPreferredSize(new Dimension(100, 40));
        generateWave.setEnabled(false);
        generateWave.addActionListener(e -> {
            mediator.generateWavePressed();
        });
        rightBottomPanel.add(generateWave);
        rightBottomPanel.add(Box.createVerticalStrut(15));

        startAnimation = new JButton("<html><center>Rozpocznij<br>animacje</center></html>");
        startAnimation.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
//        startAnimation.setPreferredSize(new Dimension(100, 40));
        startAnimation.addActionListener(e -> {
            mediator.startAnimationPressed();
            mediator.getSourceVelocity();
            mediator.getReceiverVelocity();
            mediator.getSoundFrequency();
//            mediator.pokazDopplra();
        });
        rightBottomPanel.add(startAnimation);
        rightBottomPanel.add(Box.createVerticalStrut(15));

        resetEverything = new JButton("<html><center>Resetuj<br>wszystko</center></html>");
        resetEverything.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
//        resetEverything.setPreferredSize(new Dimension(100, 40));
        resetEverything.addActionListener(e -> {
            mediator.resetEverything();
        });
        rightBottomPanel.add(resetEverything);
        rightBottomPanel.add(Box.createVerticalStrut(15));

        add(rightBottomPanel);

        downloadFile.setBackground(new Color(106, 106, 106));
        generateWave.setBackground(new Color(106, 106, 106));
        startAnimation.setBackground(new Color(106, 106, 106));
        resetEverything.setBackground(new Color(106, 106, 106));

        downloadFile.setForeground(new Color(0, 0, 0));
        generateWave.setForeground(new Color(0, 0, 0));
        startAnimation.setForeground(new Color(0, 0, 0));
        resetEverything.setForeground(new Color(0, 0, 0));

        sVelInfo.setForeground(new Color(0, 0, 0));
        rVelInfo.setForeground(new Color(0, 0, 0));
        frequencyInfo.setForeground(new Color(0, 0, 0));

        sourceVelocity.setBackground(new Color(106, 106, 106));
        receiverVelocity.setBackground(new Color(106, 106, 106));
        soundFrequency.setBackground(new Color(106, 106, 106));

        sourceVelocity.setForeground(new Color(0, 0, 0));
        receiverVelocity.setForeground(new Color(0, 0, 0));
        soundFrequency.setForeground(new Color(0, 0, 0));
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void resetRightPanel() {
        downloadFile.setEnabled(false);
        generateWave.setEnabled(false);
        downloadFile.setEnabled(false);
        sourceVelocity.setText("0");
        receiverVelocity.setText("0");
        soundFrequency.setText("0");
    }

    public void startAnimationPressed() {
        generateWave.setEnabled(true);
    }

    public void generateWavePressed() {
        downloadFile.setEnabled(true);
    }

    public double getSourceVelocity() {
        String text = sourceVelocity.getText();
        double value = Double.parseDouble(text);
//        System.out.println("Prędkość źródła: " + value);
        return value;
    }

    public double getReceiverVelocity() {
        String text = receiverVelocity.getText();
        double value = Double.parseDouble(text);
//        System.out.println("Prędkość odbiorcy: " + value);
        return value;
    }

    public double getSoundFrequency() {
        String text = soundFrequency.getText();
        double value = Double.parseDouble(text);
//        System.out.println("Częstotliwość dzwięku: " + value);
        return value;
    }

    public void changeMode(Color X, Color Y, Color Z) {
        this.setBackground(Y);

        rightTopPanel.setBackground(Y);
        rightBottomPanel.setBackground(Y);

        downloadFile.setBackground(Z);
        generateWave.setBackground(Z);
        startAnimation.setBackground(Z);
        resetEverything.setBackground(Z);

        downloadFile.setForeground(X);
        generateWave.setForeground(X);
        startAnimation.setForeground(X);
        resetEverything.setForeground(X);

        sVelInfo.setForeground(X);
        rVelInfo.setForeground(X);
        frequencyInfo.setForeground(X);

        sourceVelocity.setBackground(Z);
        receiverVelocity.setBackground(Z);
        soundFrequency.setBackground(Z);

        sourceVelocity.setForeground(X);
        receiverVelocity.setForeground(X);
        soundFrequency.setForeground(X);
    }

    class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            StringBuilder sb = new StringBuilder(string);
            for (int i = sb.length() - 1; i >= 0; i--) {
                char c = sb.charAt(i);
                if (!Character.isDigit(c) && c != '.') {
                    sb.deleteCharAt(i);
                }
            }
            if (containsMoreThanOneDot(fb.getDocument().getText(0, fb.getDocument().getLength()) + sb.toString())) {
                return;
            }
            super.insertString(fb, offset, sb.toString(), attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text != null) {
                StringBuilder sb = new StringBuilder(text);
                for (int i = sb.length() - 1; i >= 0; i--) {
                    char c = sb.charAt(i);
                    if (!Character.isDigit(c) && c != '.') {
                        sb.deleteCharAt(i);
                    }
                }
                if (containsMoreThanOneDot(fb.getDocument().getText(0, fb.getDocument().getLength()) + sb.toString())) {
                    return;
                }
                text = sb.toString();
            }
            super.replace(fb, offset, length, text, attrs);
        }

        private boolean containsMoreThanOneDot(String text) {
            int dotCount = 0;
            for (char c : text.toCharArray()) {
                if (c == '.') {
                    dotCount++;
                }
                if (dotCount > 1) {
                    return true;
                }
            }
            return false;
        }
    }
}
