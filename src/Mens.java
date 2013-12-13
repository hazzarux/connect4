import java.util.Scanner;


public class Mens extends Speler{
	private SpelBord bord;
	private Rondje symbool = new Rondje();
	static Scanner s = new Scanner(System.in);
	
	public Mens(int aantalAambeelden, SpelBord bord, String naam){
		this.bord=bord;
		super.setNaam(naam);
		super.setSymbol(this.symbool);
		super.setAantalAambeelden(aantalAambeelden);
	}
	
	public void vraagVoorZet(){
		boolean succesvol = false;
		do{
			System.out.println("In welke kolom wilt u een schijf plaatsen?");	
			int kolom = s.nextInt();
			succesvol = this.bord.zetSchijf(kolom, this);
		}while (succesvol!=true);
		
	}
}
