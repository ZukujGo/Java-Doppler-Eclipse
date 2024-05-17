package doppler2;

import java.awt.*;

public interface Mediator {
	
	/*
	 * Interfejs klasy wykonujacej wszystkie metody
	 */

	void resetEverything();
	void startAnimationPressed();
	void generateWavePressed();
	long getSourceVelocity();
	long getReceiverVelocity();
	long getSoundFrequency();
//	void pokazDopplra();
	void changeAppMode(Color X, Color Y, Color Z);
	double returnDopplerFactor();
	void saveSound();
	void playEmittedSound();
	void playReceivedSound();
//	void playSound(byte[] generatedSound);
	
}
