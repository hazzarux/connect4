import java.util.Scanner;

public class Mens extends Speler {
	private SpelBord bord;
	private Rondje symbool = new Rondje();
	static Scanner s = new Scanner(System.in);
	private int nMuurschijf;
	private int aantalMuurschijven = 1;

	public int getnMuurschijf() {
		return nMuurschijf;
	}

	public void setnMuurschijf(int nMuurschijf) {
		this.nMuurschijf = nMuurschijf;
	}

	/**
	 * Menselijke speler constructor
	 * 
	 * @param aantalAambeelden
	 * @param bord
	 * @param naam
	 * @param xWaardeAambeeld
	 */
	public Mens(int aantalAambeelden, SpelBord bord, String naam,
			int xWaardeAambeeld, int nMuurschijf) {
		this.bord = bord;
		super.setNaam(naam);
		super.setSymbol(this.symbool);
		super.setAantalAambeelden(aantalAambeelden);
		super.setxAambeeld(xWaardeAambeeld);
		this.nMuurschijf = nMuurschijf;
	}

	/**
	 * menselijke speler vragen voor een zet. met/zonder aambeeld.
	 */
	public void vraagVoorZet() {
		boolean succesvol = false;
		do {
			System.out.println("In welke kolom wilt u een schijf plaatsen? (1 - "+this.bord.getAantalKolommen()+")");
			String kolom = s.next();
			kolom=kolom.toLowerCase();
			if(kolom.contains("a")){
				String kolomNr = kolom.split("a")[1];
				int kolomInt = Integer.parseInt(kolomNr);
				succesvol=this.bord.zetAambeeld(kolomInt,super.getxAambeeld(),this);
			}else if(kolom.contains("m")){
				String kolomNr = kolom.split("m")[1];
				int kolomInt = Integer.parseInt(kolomNr);
				succesvol = this.bord.zetMuurschijf(kolomInt, this);
			}else{
				int kolomInt = Integer.parseInt(kolom);
				succesvol = this.bord.zetSchijf(kolomInt, this.symbool);
			}
		} while (succesvol != true);

	}

	public int getAantalMuurschijven() {
		return aantalMuurschijven;
	}

	public void setAantalMuurschijven(int aantalMuurschijven) {
		this.aantalMuurschijven = aantalMuurschijven;
	}
}
