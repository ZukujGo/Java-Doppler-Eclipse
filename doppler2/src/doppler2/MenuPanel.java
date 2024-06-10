package doppler2;

import java.awt.*;
import javax.swing.*;

public class MenuPanel extends JMenuBar {
	
	/**
	 * Menu
	 */
	private static final long serialVersionUID = 1L;

	private Mediator mediator;
	
	private JMenu projectInfo;
	private JMenuItem authorInfo, progressInfo, contributionInfo;
	
	private JMenu colorChooser;
	private JMenuItem darkMode, lightMode, rainbowMode;
	
	private Color primary1 = new Color(0, 0, 0);
	private Color secondary1 = new Color(86, 86, 86);
	private Color tertiary1 = new Color(126, 126, 126);
	
	private Color primary2 = new Color(55, 55, 55);
	private Color secondary2 = new Color(220, 220, 220);
	private Color tertiary2 = new Color(255, 255, 255);
	
	private Color primary3 = new Color(255, 0, 0);
	private Color secondary3 = new Color(0, 255, 0);
	private Color tertiary3 = new Color(0, 0, 255);
	
	public MenuPanel() {
		
		projectInfo = new JMenu("Informacje o projekcie");
		
        authorInfo = new JMenuItem("Informacje o autorze");
        contributionInfo = new JMenuItem("Wkład");
        progressInfo = new JMenuItem("Postęp");
        
        authorInfo.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Autorzy: Juliusz Nowak & Barnaba Smycz\n" +
                    "Projekt Java: 'Efekt Dopplera'",
                    "Informacje o autorze",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        contributionInfo.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "GUI: Juliusz Nowak\n" +
                    "Logika: Juliusz Nowak\n"+
                    "Animacja: Barnaba Smycz\n"+
                    "Wykresy: Barnaba Smycz\n"+
                    "Dzwiek: Juliusz Nowak\n",
                    "Informacje o wkładzie",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        progressInfo.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Ostatnia edycja GUI: 25.03.2024\n" +
                    "Ostatnia edycja wejść użytkownika: 25.03.2024\n",
                    "Postęp",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        projectInfo.add(authorInfo);
        projectInfo.add(contributionInfo);
//        projectInfo.add(progressInfo);
                
        colorChooser = new JMenu("Motyw aplikacji");
		
        darkMode = new JMenuItem("Ciemny motyw");
        lightMode = new JMenuItem("Jasny motyw");
        rainbowMode = new JMenuItem("Kolorowy motyw");

        darkMode.addActionListener(e -> {
        	mediator.changeAppMode(primary1, secondary1, tertiary1);
        });
        
        lightMode.addActionListener(e -> {
        	mediator.changeAppMode(primary2, secondary2, tertiary2);
        });
        
        rainbowMode.addActionListener(e -> {
        	mediator.changeAppMode(primary3, secondary3, tertiary3);
        });
        
        colorChooser.add(darkMode);
        colorChooser.add(lightMode);
        colorChooser.add(rainbowMode);
        
        add(projectInfo);
        add(colorChooser);
	}
	
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;	
	}

}
