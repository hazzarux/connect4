import java.util.Random;

public class Computer extends Speler {
	//TODO verschillende moeilijkheidsgraden implementeren
	private int moeilijkheidsgraad = 0;
	private Schijf symbool = new Kruis();
	private SpelBord bord;
	private String naam = "Computer";

	public Computer(int moeilijkheidsgraad, int aantalAambeelden, SpelBord bord) {
		this.moeilijkheidsgraad = moeilijkheidsgraad;
		this.bord = bord;
		super.setAantalAambeelden(aantalAambeelden);
		super.setSymbol(this.symbool);
		super.setNaam(this.naam);
	}

	public void doeZetMakkelijk() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(this.bord.getAantalKolommen());
		if(randomInt==0){
			randomInt=randomInt+1;
		}
		if ((this.bord.isOnField(randomInt))&&(this.bord.isJuisteZet(randomInt))&&(this.bord.getArray()[this.bord.firstEmptyRow(randomInt)][randomInt].isLeeg())) {
			System.out.println("Computer koos kolom "+randomInt);
			this.bord.zetSchijf(randomInt, this);
			return;
		} else {
			this.doeZetMakkelijk();
		}
	}
	
	public void doeZet(){
		if(this.moeilijkheidsgraad==1){
			this.doeZetMakkelijk();
		}
	}

}
