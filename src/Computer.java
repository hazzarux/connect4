public class Computer extends Speler{
	private int moeilijkheidsgraad=0;
	private Schijf symbool = new Kruis();
	private SpelBord bord;
	private String naam = "Computer";
	
	public Computer(int moeilijkheidsgraad,int aantalAambeelden, SpelBord bord){
		this.moeilijkheidsgraad=moeilijkheidsgraad;
		this.bord=bord;
		super.setAantalAambeelden(aantalAambeelden);
		super.setSymbol(this.symbool);
		super.setNaam(this.naam);
	}
	
	
	
}
