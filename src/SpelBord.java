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
	public boolean isJuisteZet(int kolom) {
		if(this.firstEmptyRow(kolom)!=-50){
			if((this.firstEmptyRow(kolom)<=this.AANTAL_RIJEN)&&(this.array[this.firstEmptyRow(kolom)][kolom].isLeeg())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * functie om de eerste lege rij (van onderaan) te zoeken
	 * 
	 * @param kolom
	 * @return eerste lege rij
	 */
	public int firstEmptyRow(int kolom) {
		int i = this.AANTAL_RIJEN - 1;
		while (!this.array[i][kolom].isLeeg()) {
			if(i==0){
				return -50;
			}
			else{
				i--;
			}
			
		}
		return i;
	}
	
	public boolean rijIsOnField(int rij){
		if((rij>=0)&&(rij<this.AANTAL_RIJEN)){
			return true;
		}else{
			return false;
		}
	}
	

	/**
	 * 
	 * @param kolom
	 * @param speler
	 * @return true als schijf geplaatst is
	 */
	public boolean zetSchijf(int kolom, Speler speler) {
		kolom = kolom - 1;
		if ((isOnField(kolom)) && (this.isJuisteZet(kolom))) {
			int rij = this.firstEmptyRow(kolom);
			this.array[rij][kolom] = speler.getSymbol();
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * check if kolom is on field
	 * @param kolom
	 * @return true if kolom is on field
	 */
	public boolean isOnField(int kolom) {
		if ((kolom >= 0) && (kolom < this.AANTAL_KOLOMMEN)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * print de namen, het symbool en het resterende aantal aambeelden van de
	 * spelers
	 * 
	 * @param speler1
	 * @param speler2
	 */
	public void printNamenEnAambeelden(Speler speler1, Speler speler2) {
		System.out.println("----------------------------------------------------------");
		String word;
		String aambeeldenStr;
		Aambeeld aambeeld = new Aambeeld();

		// print voor speler 1
		if (speler1.getAantalAambeelden() == 1) {
			word = "aambeeld";
			aambeeldenStr = aambeeld.toString();
		} else {
			word = "aambeelden";
			aambeeldenStr = aambeeld.toString() + "," + aambeeld.toString();
		}
		System.out.println(speler1.getNaam() + " ("
				+ speler1.getSymbol().toString() + ") ["
				+ speler1.getAantalAambeelden() + " " + word + " ("
				+ aambeeldenStr + ") resterend]. Score: "+speler1.getScore());

		// print voor speler 2
		if (speler2.getAantalAambeelden() == 1) {
			word = "aambeeld";
		} else {
			word = "aambeelden";
		}
		System.out.println(speler2.getNaam() + " ("
				+ speler2.getSymbol().toString() + ") ["
				+ speler2.getAantalAambeelden() + " " + word + " ("
				+ aambeeldenStr + ") resterend]. Score: "+speler2.getScore());
		System.out.println("----------------------------------------------------------");
	}
	
	
	/**
	 * controleren of er 4 op een rij is
	 * @return winnend symbool
	 * 	null als er niemand wint
	 */
	public Schijf check4() {
		// horizontale rijen
		for (int rij = 0; rij < this.AANTAL_RIJEN; rij++) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((!curr.isLeeg()) && curr == this.array[rij][kolom + 1]
						&& curr == this.array[rij][kolom + 2]
						&& curr == this.array[rij][kolom + 3]) {
					return this.array[rij][kolom];
				}
			}
		}
		// verticale kolommen
		for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN; kolom++) {
			for (int rij = 0; rij < this.AANTAL_RIJEN - 3; rij++) {
				Schijf curr = this.array[rij][kolom];
				if ((!curr.isLeeg()) && curr == this.array[rij + 1][kolom]
						&& curr == this.array[rij + 2][kolom]
						&& curr == this.array[rij + 3][kolom])
					return this.array[rij][kolom];
			}
		}
		// diagonaal links beneden naar rechts boven
		for (int rij = 0; rij < this.AANTAL_RIJEN - 3; rij++) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((!curr.isLeeg()) && curr == this.array[rij + 1][kolom + 1]
						&& curr == this.array[rij + 2][kolom + 2]
						&& curr == this.array[rij + 3][kolom + 3])
					return this.array[rij][kolom];
			}
		}
		// diagonaal links boven naar rechts beneden
		for (int rij = this.AANTAL_RIJEN - 1; rij >= 3; rij--) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((!curr.isLeeg())&& curr == this.array[rij - 1][kolom + 1]
						&& curr == this.array[rij - 2][kolom + 2]
						&& curr == this.array[rij - 3][kolom + 3])
					return this.array[rij][kolom];
			}
		}
		return null;
	}
	
	/**
	 * geeft aantal kolommen terug
	 * @return aantal kolommen
	 */
	public int getAantalKolommen() {
		return this.AANTAL_KOLOMMEN;
	}
}
