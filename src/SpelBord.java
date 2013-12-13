public class SpelBord {
	private int AANTAL_KOLOMMEN = 0;
	private int AANTAL_RIJEN = 0;
	private Schijf[][] array;
	private boolean gameOver = false;

	/**
	 * Constructor voor het spelbord aantal rijen = aantal kolommen - 1
	 */
	public SpelBord(int aantalKolommen) {
		this.AANTAL_KOLOMMEN = aantalKolommen;
		this.AANTAL_RIJEN = this.AANTAL_KOLOMMEN - 1;
		this.generateSpelbord();
	}

	/**
	 * Genereert een 2-dimensionale array van schijven voor het spelbord
	 * 
	 */
	public void generateSpelbord() {
		array = new Schijf[AANTAL_RIJEN][AANTAL_KOLOMMEN];
		for (int i = 0; i < AANTAL_RIJEN; i++) {
			for (int x = 0; x < AANTAL_KOLOMMEN; x++) {
				Leeg legeSchijf = new Leeg();
				array[i][x] = legeSchijf;
			}
		}
	}

	/**
	 * Print het spelbord af
	 */
	public void printSpelbord() {
		this.printHeader(); // kolomnummers boven spelbord
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < this.AANTAL_RIJEN; i++) {
			output.append("| ");
			for (int x = 0; x < this.AANTAL_KOLOMMEN; x++) {
				output.append(" ");
				output.append(array[i][x].toString());
				if (x >= 9) {
					output.append("  |");
				} else {
					output.append(" |");
				}

			}
			output.append("\n"); // nieuwe lijn na elke rij
		}
		output.deleteCharAt(output.length() - 1); // delete laatste \n
		System.out.println(output);
		this.printHeader(); // kolomnummers onder spelbord
	}

	/**
	 * print header voor het spelbord af (kolomnummers)
	 */
	public void printHeader() {
		StringBuffer output = new StringBuffer();

		for (int i = 1; i <= this.AANTAL_KOLOMMEN; i++) {
			output.append("   ");
			output.append(i);
		}
		System.out.println(output);
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * check of speler een juiste zet doet
	 * 
	 * @param kolom
	 * @return true als de speler een juiste zet doet
	 */
	public boolean juisteZet(int kolom) {
		// TODO check of de kolom vol is
		return true;
	}

	/**
	 * functie om de eerste lege rij (van onderaan) te zoeken
	 * @param kolom
	 * @return eerste lege rij
	 */
	public int firstEmptyRow(int kolom) {
		for (int i = AANTAL_RIJEN-1; i >= 0; i--) {
			if (array[i][kolom].isLeeg()) {
				return i;
			}
		}
		return 1;
	}

	public void zetSchijf(int kolom, Speler speler) {
		kolom = kolom - 1;
		int rij = this.firstEmptyRow(kolom);
		this.array[rij][kolom] = speler.getSymbol();
	}
	/**
	 * print de namen, het symbool en het resterende aantal aambeelden van de spelers
	 * @param speler1
	 * @param speler2
	 */
	public void printNamenEnAambeelden(Speler speler1,Speler speler2){
		// TODO score toevoegen aan output
		System.out.println("------");
		String word;
		String aambeeldenStr;
		Aambeeld aambeeld = new Aambeeld();
		
		//print voor speler 1
		if(speler1.getAantalAambeelden()==1){
			word = "aambeeld";
			aambeeldenStr=aambeeld.toString();
		}else{
			word="aambeelden";
			aambeeldenStr=aambeeld.toString()+","+aambeeld.toString();
		}
		System.out.println(speler1.getNaam()+" ("+speler1.getSymbol().toString()+") ["+speler1.getAantalAambeelden()+" "+word+" ("+aambeeldenStr+") resterend]");
		
		//print voor speler 2
		if(speler2.getAantalAambeelden()==1){
			word="aambeeld";
		}else{
			word="aambeelden";
		}
		System.out.println(speler2.getNaam()+" ("+speler2.getSymbol().toString()+") ["+speler2.getAantalAambeelden()+" "+word+ " ("+aambeeldenStr+") resterend]");
		System.out.println("------");
	}
}
