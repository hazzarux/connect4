import java.util.Random;

public class Computer extends Speler {
	
	private int moeilijkheidsgraad = 0;
	private Schijf symbool = new Kruis();
	private SpelBord bord;
	private String naam = "Computer";
	/**
	 * constructor voor computerspeler
	 * @param moeilijkheidsgraad
	 * @param aantalAambeelden
	 * @param bord
	 * @param xWaardeAambeeld
	 */
	public Computer(int moeilijkheidsgraad, int aantalAambeelden, SpelBord bord, int xWaardeAambeeld) {
		this.moeilijkheidsgraad = moeilijkheidsgraad;
		this.bord = bord;
		super.setAantalAambeelden(aantalAambeelden);
		super.setSymbol(this.symbool);
		super.setNaam(this.naam);
		super.setxAambeeld(xWaardeAambeeld);
	}
	/**
	 * moeilijke zet van de computerspeler
	 */
	public void doeZetMoeilijk(){
		SpelBord bord2 = new SpelBord(this.bord.getAantalKolommen());
		bord2.generateSpelbord();
		Schijf[][] deepCopy = this.bord.deepCopyMatrix(this.bord.getArray());
		bord2.setArray(deepCopy);
		
		Move hoogstePuntenMove = new Move("");
		
		for(int i=1;i<=this.bord.getAantalKolommen();i++){
			int punten=0;
			Move x = new Move("schijf");
			if(bord2.zetSchijf(i, this.symbool)){
				punten = calculatePunten(bord2);
			}
			x.setKolom(i);
			if(punten>hoogstePuntenMove.getPunten()){
				hoogstePuntenMove=x;
			}
			bord2.setArray(deepCopy);
		}
		
		bord2.setArray(deepCopy);
		if(this.getAantalAambeelden()>0){
			for(int i=1;i<=this.bord.getAantalKolommen();i++){
				int originalAambeelden = this.getAantalAambeelden();
				int punten = 0;
				Move x = new Move("aambeeld");
				if(bord2.zetAambeeld(i, this.getxAambeeld(), this)){
					punten=calculatePunten(bord2);
				}
				x.setKolom(i);
				if(punten>hoogstePuntenMove.getPunten()){
					hoogstePuntenMove=x;
				}
				bord2.setArray(deepCopy);
				this.setAantalAambeelden(originalAambeelden);
			}
			
		}
		
		if(hoogstePuntenMove.getMethode().equals("schijf")){
			System.out.println("Computer koos kolom "+hoogstePuntenMove.getKolom());
			this.bord.zetSchijf(hoogstePuntenMove.getKolom(), this.symbool);
		}else if(hoogstePuntenMove.getMethode().equals("aambeeld")){
			System.out.println("Computer koos om een aambeeld te zetten op kolom "+hoogstePuntenMove.getKolom());
			this.bord.zetAambeeld(hoogstePuntenMove.getKolom(), this.getxAambeeld(), this);
		}else{
			//System.out.println("Computer doet een normale zet.");
			this.doeZetNormaal();
		}
		
	}
	
	/**
	 * berekent punten voor 3e moeilijkheidsgraad van computerspeler
	 * @param bord
	 * @return aantal punten van een bepaalde zet in een bepaald bord
	 */
	public int calculatePunten(SpelBord bord){
		int punten=0;
		if (bord.check4()==this.symbool){
			punten+=150;
		}
		if(bord.check3Computer()){
			punten+=10;
		}
		if(bord.check2Computer()){
			punten+=1;
		}
		if(bord.check3Human()){
			punten-=100;
		}
		if(bord.check2Human()){
			punten-=10;
		}
		return punten;
	}
	/**
	 * normale zet van een computerspeler 
	 */
	public void doeZetNormaal(){
		SpelBord bord2 = new SpelBord(this.bord.getAantalKolommen());
		bord2.generateSpelbord();
		Schijf[][] deepCopy = this.bord.deepCopyMatrix(this.bord.getArray());
		bord2.setArray(deepCopy);
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
			System.out.println("Computer koos kolom "+result+".");
		}else{
			this.doeZetMakkelijk();
		}
	}
	/**
	 * makkelijke zet van een computerspeler
	 */
	public void doeZetMakkelijk() {
		Random randomGenerator = new Random();
		boolean succes=false;
		do{
			int randomInt = randomGenerator.nextInt(this.bord.getAantalKolommen())+1;
			succes = this.bord.zetSchijf(randomInt, this.symbool);
			if(succes==true){
				System.out.println("Computer koos kolom "+randomInt+".");
			}
		}while(succes!=true);
	}
	/**
	 * main functie om een zet te doen (computerspeler)
	 */
	public void doeZet(){
		if(this.moeilijkheidsgraad==1){
			this.doeZetMakkelijk();
		}else if(this.moeilijkheidsgraad==2){
			this.doeZetNormaal();
		}else if(this.moeilijkheidsgraad==3){
			this.doeZetMoeilijk();
		}
	}

}
