
public class Speler {
	//TODO score implementeren
	private String naam;
	private Schijf symbol;
	private int aantalAambeelden=0;	
	
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Schijf getSymbol() {
		return symbol;
	}

	public void setSymbol(Schijf symbol) {
		this.symbol = symbol;
	}

	public int getAantalAambeelden() {
		return aantalAambeelden;
	}

	public void setAantalAambeelden(int aantalAambeelden) {
		this.aantalAambeelden = aantalAambeelden;
	}
	
}
