package doppler2;

import java.awt.*;
import javax.swing.*;


public class Frame extends JFrame {
	
	/**
	 * Efekt Dopplera
	 * Glowna funkcja
	 */
	
	private static final long serialVersionUID = 100;
	private Mediator mediator;
	private BottomPanel bottomPanel;
	private RightPanel rightPanel;
	private CenterPanel centerPanel;
	private DopplerMaths doppler;
	private MenuPanel menuPanel;
	private SimpleSound simpleSound;
	
	
	public Frame() {
        setTitle("Efekt Dopplera");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 900);
        setMinimumSize(new Dimension(13*700/9, 700));		// Najmniejszy rozmiar okna (przeskalowany poczatkowy rozmiar)
        
        mediator = new Editor();					// Tworzenie ostatecznego mediatora
        
        bottomPanel = new BottomPanel();			// Tworzenie kazdego z paneli GUI
        rightPanel = new RightPanel();
        centerPanel = new CenterPanel();
        menuPanel = new MenuPanel();
        
        doppler = new DopplerMaths();				// By mozliwe byly jakiekolwiek operacje
        simpleSound = new SimpleSound();			// trzeba wywolac kazda z klas
        
        
        bottomPanel.setMediator(mediator);			// Klasy musza dostac finalna instancje mediatora
        rightPanel.setMediator(mediator);			// bo dopiero tutaj, w main, jest ostatecznie wywolywany
        centerPanel.setMediator(mediator);
        menuPanel.setMediator(mediator);
        simpleSound.setMediator(mediator);
        doppler.setMediator(mediator);
        
        if (mediator instanceof Editor) {
            Editor editor = (Editor) mediator;
            editor.setBottomPanel(bottomPanel);  	// Wszystkie klasy, po przekazaniu im finalnie wywolanego
            editor.setRightPanel(rightPanel); 		// mediatora, musza powiazac go z klasa wykonujaca jego metody
            editor.setCenterPanel(centerPanel);
            editor.setDopplerMaths(doppler);
            editor.setSimpleSound(simpleSound);
        }
        
        setLayout(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
        setJMenuBar(menuPanel);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame window = new Frame();
		window.setVisible(true);
	}

	
}
