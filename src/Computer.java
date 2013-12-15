import java.util.Random;

public class Computer extends Speler {
	
	//TODO aambeeld implementeren
	//TODO verschillende moeilijkheidsgraden implementeren
	private int moeilijkheidsgraad = 0;
	private Schijf symbool = new Kruis();
	private SpelBord bord;
	private String naam = "Computer";

	public Computer(int moeilijkheidsgraad, int aantalAambeelden, SpelBord bord, int xWaardeAambeeld) {
		this.moeilijkheidsgraad = moeilijkheidsgraad;
		this.bord = bord;
		super.setAantalAambeelden(aantalAambeelden);
		super.setSymbol(this.symbool);
		super.setNaam(this.naam);
		super.setxAambeeld(xWaardeAambeeld);
	}

	//TODO implement doe zet normaal
	public void doeZetNormaal(){
		SpelBord bord2 = new SpelBord(this.bord.getAantalKolommen());
		bord2.generateSpelbord();
		Schijf[][] deepCopy = this.bord.deepCopyMatrix(this.bord.getArray());
		bord2.setArray(deepCopy);
		bord2.zetSchijf(3, this.symbool);
		int result = -1;
		for(int i=1;i<=this.bord.getAantalKolommen();i++){
			if(bord2.zetSchijf(i, this.symbool)){
				if(bord2.check4()==this.symbool){
					result=i;
				}
			}
			bord2.setArray(deepCopy);
		}
		
		if(result!=-1){
			this.bord.zetSchijf(result, this.symbool);
		}else{
			this.doeZetMakkelijk();
		}
	}
	
	public void doeZetMakkelijk() {
		Random randomGenerator = new Random();
		boolean succes=false;
		do{
			int randomInt = randomGenerator.nextInt(this.bord.getAantalKolommen())+1;
			succes = this.bord.zetSchijf(randomInt, this.symbool);
		}while(succes!=true);
	}
	
	public void doeZet(){
		if(this.moeilijkheidsgraad==1){
			this.doeZetMakkelijk();
		}else if(this.moeilijkheidsgraad==2){
			this.doeZetNormaal();
		}
	}

}
