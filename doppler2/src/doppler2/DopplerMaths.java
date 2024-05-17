package doppler2;

public class DopplerMaths {
	
	/*
	 * Klasa sluzaca do obliczania efektu Dopplera
	 */

	private Mediator mediator;
	@SuppressWarnings("unused")
	private long sVel, rVel, sFrq;
	private double factor = 1;
	
	public DopplerMaths() {}
	
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;	
	}
	
	public double dopplerFactor() {
		
		sVel = mediator.getSourceVelocity(); 		//tutaj juz masz wartosci pobierane z JTextField'ów
		rVel = mediator.getReceiverVelocity(); 		//wiec wystarczy, ze napiszesz wzory
		sFrq = mediator.getSoundFrequency();
		
		double sVelDouble = 1;
		double rVelDouble = 1;
		
		sVelDouble = (double) sVel;
		rVelDouble = (double) rVel;
		
		//tutaj zrob obliczenia dopplera
		//i zrzutuj je na double
		
		if( rVelDouble != 0 && sVelDouble != 0 ) {
			factor = sVelDouble/rVelDouble;
		}else if( rVelDouble == 0 && sVelDouble != 0 ){
			factor = 1.5;			
		}else if( sVelDouble == 0 && rVelDouble != 0 ) {
			factor = 0.5;
		}else if(sVelDouble == 0 && rVelDouble == 0 ) {
			factor = 1;
		}
		
		
//		System.out.println("\nPrędkość źródła doppler: " + sVel);
//		System.out.println("\nPrędkość odbiorcy doppler: " + rVel);
//		System.out.println("\nCzęstotliwość dzwięku doppler: " + sFrq);
//		System.out.println("\nWspolczynnik: " + factor + "\n");
		return factor;
	}
	
//	public double returnFactor() {
//		return factor;
//	}
	
}
