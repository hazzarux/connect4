import java.util.Scanner;

public class Mens extends Speler {
	private SpelBord bord;
	private Rondje symbool = new Rondje();
	static Scanner s = new Scanner(System.in);
	/**
	 * Menselijke speler constructor
	 * @param aantalAambeelden
	 * @param bord
	 * @param naam
	 * @param xWaardeAambeeld
	 */
	public Mens(int aantalAambeelden, SpelBord bord, String naam, int xWaardeAambeeld) {
		this.bord = bord;
		super.setNaam(naam);
		super.setSymbol(this.symbool);
		super.setAantalAambeelden(aantalAambeelden);
		super.setxAambeeld(xWaardeAambeeld);
	}
	/**
	 * menselijke speler vragen voor een zet. met/zonder aambeeld.
	 */
	public void vraagVoorZet() {
		boolean succesvol = false;
		do {
			System.out.println("In welke kolom wilt u een schijf plaatsen? (0 - "+this.bord.getAantalKolommen()+")");
			String kolom = s.next();
			kolom=kolom.toLowerCase();
			if(kolom.contains("a")){
				String kolomNr = kolom.split("a")[1];
				int kolomInt = Integer.parseInt(kolomNr);
				succesvol=this.bord.zetAambeeld(kolomInt,super.getxAambeeld(),this);
			}else{
				int kolomInt = Integer.parseInt(kolom);
				succesvol = this.bord.zetSchijf(kolomInt, this.symbool);
			}
			
		} while (succesvol != true);

	}
}
