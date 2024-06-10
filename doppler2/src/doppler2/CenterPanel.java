package doppler2;

import java.awt.*;
import javax.swing.*;

public class CenterPanel extends JPanel {
	
	/**
	 * Panel centralny
	 */
	private static final long serialVersionUID = 101;
	private Mediator mediator;
	private JPanel centerTopPanel, centerLeftPanel;
	private JPanel animationPanel;
	
	public CenterPanel() {
		setLayout(new BorderLayout());
		
		centerTopPanel = new JPanel();
		centerTopPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 15));
		centerTopPanel.setBackground(new Color(66, 66, 66));
		
		add(centerTopPanel, BorderLayout.NORTH);
		
		centerLeftPanel = new JPanel();
		centerLeftPanel.setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
		centerLeftPanel.setBackground(new Color(66, 66, 66));
		
		add(centerLeftPanel, BorderLayout.WEST);
		
	}
	
	public void setMediator(Mediator mediator) {	
		this.mediator = mediator;
	}
	
	public void changeMode(Color X, Color Y, Color Z) {
//		this.setBackground(X);
		centerTopPanel.setBackground(Y);
		centerLeftPanel.setBackground(Y);
	}

}