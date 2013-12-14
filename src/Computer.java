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

	//TODO implement doe zet normaal
	public void doeZetNormaal(){
		
		/*SpelBord bord2 = new SpelBord(this.bord.getAantalKolommen());
		
		bord2.setArray(this.bord.getArray());
		int result=-1;
		for(int i = 0;i<bord2.getAantalKolommen();i++){
			Schijf x = bord2.getSchijf(i);
			bord2.zetSchijf(i, this);
			if(bord2.check4()==this.symbool){
				result=i;
			}
			if(x.getCharacter())
			bord2.zetSchijf(i, x);
		}
		
		if(result!=-1){
			this.bord.zetSchijf(result, this);
		}else{
			this.doeZetMakkelijk();
		}*/
	}
	
	public void doeZetMakkelijk() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(this.bord.getAantalKolommen())+1;
		if(randomInt==this.bord.getAantalKolommen()){
			randomInt--;
		}
		if (this.bord.isJuisteZet(randomInt)) {
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
		}else if(this.moeilijkheidsgraad==2){
			this.doeZetNormaal();
		}
	}

}
