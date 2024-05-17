package doppler2;

import java.awt.Color;

public class Editor implements Mediator {
	
	/*
	 * Klasa ze wszystkimi wywolywanymi metodami
	 * ktorych uzywa program. Wszystkie operacje
	 * logiczne dzieja sie tutaj
	 */
	
	private BottomPanel bottomPanel;
	private RightPanel rightPanel;
	private CenterPanel centerPanel;
	private DopplerMaths doppler;
	@SuppressWarnings("unused")
	private MenuPanel menuPanel;
	private SimpleSound simpleSound;
//    private BottomPanel bottomPanel;
//    private RightPanel rightPanel;

    public void setBottomPanel(BottomPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }

    public void setRightPanel(RightPanel rightPanel) {
        this.rightPanel = rightPanel;
    }
    
    public void setCenterPanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
    }
    
    public void setDopplerMaths(DopplerMaths doppler) {
    	this.doppler = doppler;
    }
    
    public void setMenuPanel(MenuPanel menuPanel) {
    	this.menuPanel = menuPanel;
    }
    
    public void setSimpleSound(SimpleSound simpleSound) {
    	this.simpleSound = simpleSound;
    }

	@Override
	public void resetEverything() {
		// TODO Auto-generated method stub
		bottomPanel.resetBottomPanel();
		rightPanel.resetRightPanel();
//		simpleSound.deleteTempFiles();
	}

	@Override
	public void startAnimationPressed() {
		// TODO Auto-generated method stub
		rightPanel.startAnimationPressed();
//		doppler.dupa();
	}
	
	@Override
	public void generateWavePressed() {
		// TODO Auto-generated method stub
		bottomPanel.unlockSoundButtons();
		simpleSound.generateEmittedWave(getSoundFrequency());
		simpleSound.generateReceivedSound(getSoundFrequency(), returnDopplerFactor());
		rightPanel.generateWavePressed();
	}

	@Override
	public long getSourceVelocity() {
		// TODO Auto-generated method stub
		long sourceVelocity = rightPanel.getSourceVelocity();
		return sourceVelocity;
	}

	@Override
	public long getReceiverVelocity() {
		// TODO Auto-generated method stub
		long receiverVelocity = rightPanel.getReceiverVelocity();
		return receiverVelocity;
	}

	@Override
	public long getSoundFrequency() {
		// TODO Auto-generated method stub
		long soundFrequency = rightPanel.getSoundFrequency();
		return soundFrequency;
	}

//	@Override
//	public void pokazDopplra() {
//		// TODO Auto-generated method stub
//		doppler.dopplerFactor();
//	}

	@Override
	public void changeAppMode(Color X, Color Y, Color Z) {
		// TODO Auto-generated method stub
		centerPanel.changeMode(X, Y, Z);
		bottomPanel.changeMode(X, Y, Z);
		rightPanel.changeMode(X, Y, Z);
	}

	@Override
	public double returnDopplerFactor() {
		// TODO Auto-generated method stub
		double dopplerFactor = doppler.dopplerFactor();
		return dopplerFactor;
	}

	@Override
	public void saveSound() {
		// TODO Auto-generated method stub
		simpleSound.saveSoundsToFile(getSoundFrequency(), returnDopplerFactor());
	}

	@Override
	public void playEmittedSound() {
		// TODO Auto-generated method stub
//		simpleSound.playEmittedSound();
		byte[] emittedSoundData = simpleSound.generateEmittedWave(getSoundFrequency());
	    simpleSound.playSound(emittedSoundData);
	}

	@Override
	public void playReceivedSound() {
		// TODO Auto-generated method stub
//		simpleSound.playReceivedSound();
		byte[] receivedSoundData = simpleSound.generateReceivedSound(getSoundFrequency(), returnDopplerFactor());
	    simpleSound.playSound(receivedSoundData);
	}

//	@Override
//	public void playSound(byte[] generatedSound) {
//		// TODO Auto-generated method stub
//		
//	}

}
