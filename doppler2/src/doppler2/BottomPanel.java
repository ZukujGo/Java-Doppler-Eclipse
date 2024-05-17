package doppler2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BottomPanel extends JPanel {
	
	/**
	 * Dolny panel.
	 * Przy kazdym z elementow wywolywany jest poczatkowy
	 * kolor z jakim pojawiaja sie po wlaczeniu APP
	 */
	private static final long serialVersionUID = 102;

	private Mediator mediator;
	
    private int bottomPanelWidth = 0;
    private JButton emittedSoundButton;
    private JButton receivedSoundButton;
    private JButton enlargeEmittedImage;
    private JButton enlargeReceivedImage;
    private JPanel bottomLeftPanel, emittedImagePanel, emittedButtonPanel;
    private JPanel leftSeparationPanel, bottomRightPanel, receivedImagePanel, receivedButtonPanel, rightSeparationPanel;
    private JLabel emittedLabel, receivedLabel;
    
    public BottomPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(new Color(66, 66, 66));
        
        //Dolny, lewy panel z obrazkiem fali emitowanej i przyciskami
        bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(new BoxLayout(bottomLeftPanel, BoxLayout.X_AXIS));
        bottomLeftPanel.setBackground(new Color(66, 66, 66));

        // Panel z obrazkiem fali emitowanej
		emittedImagePanel = new JPanel();
		emittedImagePanel.setBackground(new Color(66, 66, 66));
		ImageIcon emittedIcon = new ImageIcon("G:/Java/doppler/sinus_java.png");
		Image emittedWave = emittedIcon.getImage();
		Image scaledEmittedWave = emittedWave.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
		ImageIcon scaledEmittedIcon = new ImageIcon(scaledEmittedWave);
		JLabel emitted = new JLabel(scaledEmittedIcon);
		emittedImagePanel.add(emitted);

        // Panel z przyciskami fali emitowanej
		emittedButtonPanel = new JPanel();
		emittedButtonPanel.setBackground(new Color(66, 66, 66));
		emittedButtonPanel.setLayout(new GridLayout(3, 1));
		emittedSoundButton = new JButton("<html><center>Odtworz<br>dzwiek</center></html>");
		emittedSoundButton.addActionListener( e -> {
			mediator.playEmittedSound();
		});
		emittedSoundButton.setEnabled(false);
		enlargeEmittedImage = new JButton("<html><center>Powieksz<br>wykres</center></html>");
		enlargeEmittedImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showImageInNewWindow(emittedIcon);
            }
        });
		emittedLabel = new JLabel("<html><center>Fala emitowana</center></html>");
		emittedLabel.setForeground(new Color(0, 0, 0));
		emittedButtonPanel.add(emittedLabel);
		emittedButtonPanel.add(emittedSoundButton);
		emittedButtonPanel.add(enlargeEmittedImage);
		
		//Panel separujacy lewy
		leftSeparationPanel = new JPanel();
		leftSeparationPanel.setBackground(new Color(66, 66, 66));

		//Dodanie komponentow do dolnego, lewego panelu
		bottomLeftPanel.add(emittedImagePanel);
		bottomLeftPanel.add(emittedButtonPanel);
		bottomLeftPanel.add(leftSeparationPanel);
		
		//Dolny, prawy panel z obrazkiem fali odbieranej i przyciskami
        bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new BoxLayout(bottomRightPanel, BoxLayout.X_AXIS));
        bottomRightPanel.setBackground(new Color(66, 66, 66));

		//Panel z obrazkiem fali odbieranej
		receivedImagePanel = new JPanel();
		receivedImagePanel.setBackground(new Color(66, 66, 66));
		ImageIcon receivedIcon = new ImageIcon("G:/Java/doppler/cosinus_java.jpg");
		Image receivedWave = receivedIcon.getImage();
		Image scaledReceivedWave = receivedWave.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
		ImageIcon scaledReceivedIcon = new ImageIcon(scaledReceivedWave);
		JLabel received = new JLabel(scaledReceivedIcon);
		receivedImagePanel.add(received);
		
		//Panel z przyciskami fali odbieranej
		receivedButtonPanel = new JPanel();
		receivedButtonPanel.setBackground(new Color(66, 66, 66));
		receivedButtonPanel.setLayout(new GridLayout(3, 1));
		receivedSoundButton = new JButton("<html><center>Odtworz<br>dzwiek</center></html>");
		receivedSoundButton.addActionListener( e -> {
			mediator.playReceivedSound();
		});
		receivedSoundButton.setEnabled(false);
		enlargeReceivedImage = new JButton("<html><center>Powieksz<br>wykres</center></html>");
		enlargeReceivedImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showImageInNewWindow(receivedIcon);
            }
        });
		receivedLabel = new JLabel("<html><center>Fala odbierana</center></html>");
		receivedLabel.setForeground(new Color(0, 0, 0));
		
		receivedButtonPanel.add(receivedLabel);
		receivedButtonPanel.add(receivedSoundButton);
		receivedButtonPanel.add(enlargeReceivedImage);
		
		//Panel separujacy prawy
		rightSeparationPanel = new JPanel();
		rightSeparationPanel.setBackground(new Color(66, 66, 66));
		
		//Dodanie komponentow do dolnego, prawego panelu
		bottomRightPanel.add(receivedImagePanel);
		bottomRightPanel.add(receivedButtonPanel);
		bottomRightPanel.add(rightSeparationPanel);
        
        addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent e) {
        		super.componentResized(e);		 //Jezeli odkomentujemy linijke z "add(Box.createHorizontalStrut(15));" to trzeba bedzie zamienic wartosci 415 na 430 :)
        		bottomPanelWidth = (int) getWidth();
        		emittedButtonPanel.setPreferredSize(new Dimension((bottomPanelWidth - 415)/4, 150));
        		emittedButtonPanel.revalidate();
        		receivedButtonPanel.setPreferredSize(new Dimension((bottomPanelWidth - 415)/4, 150));
        		receivedButtonPanel.revalidate();
        		leftSeparationPanel.setPreferredSize(new Dimension((bottomPanelWidth - 415)/4, 150));
        		leftSeparationPanel.revalidate();
        		rightSeparationPanel.setPreferredSize(new Dimension((bottomPanelWidth - 415)/4, 150));
        		rightSeparationPanel.revalidate();
        	}
        });
        
		add(Box.createHorizontalStrut(15));
		add(bottomLeftPanel);
		add(bottomRightPanel);
//		add(Box.createHorizontalStrut(15));		//chodzi o te linijke. wspomniane w poprzednim komentarzu(line 130)
		
        emittedSoundButton.setBackground(new Color(106, 106, 106));
        receivedSoundButton.setBackground(new Color(106, 106, 106));
        enlargeEmittedImage.setBackground(new Color(106, 106, 106));
        enlargeReceivedImage.setBackground(new Color(106, 106, 106));
        
        emittedSoundButton.setForeground(new Color(0, 0, 0));
        receivedSoundButton.setForeground(new Color(0, 0, 0));
        enlargeEmittedImage.setForeground(new Color(0, 0, 0));
        enlargeReceivedImage.setForeground(new Color(0, 0, 0));
    }
    
    private void showImageInNewWindow(ImageIcon icon) {
        JFrame frame = new JFrame();
        frame.setSize(1500, 1350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public void setMediator(Mediator mediator) {
    	this.mediator = mediator;
    }
    
    
    public void unlockSoundButtons() {
    	receivedSoundButton.setEnabled(true);
    	emittedSoundButton.setEnabled(true);
    }
    
    public void resetBottomPanel() {
    	emittedSoundButton.setEnabled(false);
    	receivedSoundButton.setEnabled(false);
    }
    
    public void changeMode(Color X, Color Y, Color Z) {
    	this.setBackground(Y);
    	bottomLeftPanel.setBackground(Y);
    	emittedImagePanel.setBackground(Y);
    	emittedButtonPanel.setBackground(Y);
    	leftSeparationPanel.setBackground(Y);
    	bottomRightPanel.setBackground(Y);
    	receivedImagePanel.setBackground(Y);
    	receivedButtonPanel.setBackground(Y);
    	rightSeparationPanel.setBackground(Y);
    	
        emittedSoundButton.setBackground(Z);
        receivedSoundButton.setBackground(Z);
        enlargeEmittedImage.setBackground(Z);
        enlargeReceivedImage.setBackground(Z);
        
        emittedSoundButton.setForeground(X);
        receivedSoundButton.setForeground(X);
        enlargeEmittedImage.setForeground(X);
        enlargeReceivedImage.setForeground(X);
        
        emittedLabel.setForeground(X);
        receivedLabel.setForeground(X);
    }
    
}
