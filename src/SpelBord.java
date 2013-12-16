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
	 * maakt een deep copy van de 2D array omdat java, by default, shallow copy
	 * doet!!
	 * 
	 * @param input
	 * @return result(2D array van schijven)
	 */
	public Schijf[][] deepCopyMatrix(Schijf[][] input) {
		if (input == null)
			return null;
		Schijf[][] result = new Schijf[input.length][];
		for (int r = 0; r < input.length; r++) {
			result[r] = input[r].clone();
		}
		return result;
	}

	public void setArray(Schijf[][] x) {
		this.array = x.clone();
	}

	public Schijf[][] getArray() {
		return this.array;
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
		output.deleteCharAt(output.length() - 1); // delete laatste \n, anders
													// ziet het er niet zo
													// elegant uit!
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

	/**
	 * checkt of de game gedaan is
	 * 
	 * @return true als het gedaan is
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * zet de gamestate op true/false
	 * 
	 * @param gameOver
	 *            true/false
	 */
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
		if ((this.kolomIsOnField(kolom)) && (!this.kolomIsVol(kolom))) {
			int rij = this.firstEmptyRow(kolom);
			if ((this.rijIsOnField(rij) && (this.array[rij][kolom].isLeeg()))) {
				return true;
			} else {
				return false;
			}
		} else {
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
		for (int i = this.AANTAL_RIJEN - 1; i >= 0; i--) {
			if (this.array[i][kolom].isLeeg()) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param kolom
	 * @return true if kolom is vol
	 */
	public boolean kolomIsVol(int kolom) {
		if (this.firstEmptyRow(kolom) == -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param rij
	 * @return true if rij is on field (valid)
	 */
	public boolean rijIsOnField(int rij) {
		if ((rij >= 0) && (rij < this.AANTAL_RIJEN)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param kolom
	 * @return true if kolom is on field (valid)
	 */
	public boolean kolomIsOnField(int kolom) {
		if ((kolom >= 0) && (kolom < this.AANTAL_KOLOMMEN)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * plaatst een schijf in een kolom
	 * 
	 * @param kolom
	 * @param schijf
	 * @return
	 */
	public boolean zetSchijf(int kolom, Schijf schijf) {
		kolom = kolom - 1;
		if ((this.kolomIsOnField(kolom)) && (this.isJuisteZet(kolom))) {
			int rij = this.firstEmptyRow(kolom);
			this.array[rij][kolom] = schijf;
			return true;
		} else {
			return false;
		}
	}

	public boolean zetMuurschijf(int kolom, Mens mens) {
		if (mens.getAantalMuurschijven() == 0) {
			System.out.println("Sorry, geen muurschijven meer.");
			return false;
		}
		kolom = kolom - 1;
		Muurschijf x = new Muurschijf();
		if ((this.kolomIsOnField(kolom)) && (this.isJuisteZet(kolom))) {
			int rij = this.firstEmptyRow(kolom);
			this.array[rij][kolom] = x;
			mens.setAantalMuurschijven(mens.getAantalMuurschijven() - 1);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * plaatst een aambeeld in een kolom
	 * 
	 * @param kolom
	 * @param xWaarde
	 * @param speler
	 * @return true als aambeeld geplaatst is
	 */
	public boolean zetAambeeld(int kolom, int xWaarde, Speler speler) {
		if (speler.getAantalAambeelden() == 0) {
			System.out.println("Geen aambeelden meer, sorry!");
			return false;
		}
		speler.setAantalAambeelden(speler.getAantalAambeelden() - 1);
		kolom = kolom - 1;
		Aambeeld aam = new Aambeeld();
		Leeg lege = new Leeg();
		if (this.kolomIsOnField(kolom)) {
			int i = 1;
			while (i < xWaarde) {
				int top = this.getTop(kolom);
				if (top == this.getAantalRijen()) {
					top--;
				}
				this.array[top][kolom] = lege;
				i++;
			}
			int top = this.getTop(kolom);
			// System.out.println(top);
			while (top >= this.getAantalRijen()) {
				top--;
			}
			this.array[top][kolom] = aam;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * geeft de "top" van een kolom terug.
	 * 
	 * @param kolom
	 * @return first empty row + 1
	 */
	public int getTop(int kolom) {
		int firstEmpty = this.firstEmptyRow(kolom);
		firstEmpty++;
		return firstEmpty;
	}

	/**
	 * print de namen, het symbool en het resterende aantal aambeelden van de
	 * spelers
	 * 
	 * @param speler1
	 * @param speler2
	 */
	public void printNamenEnAambeelden(Mens speler1, Speler speler2) {
		System.out
				.println("----------------------------------------------------------");
		String word;

		// print voor speler 1
		if (speler1.getAantalAambeelden() == 1) {
			word = "aambeeld";
		} else if (speler1.getAantalAambeelden() == 2) {
			word = "aambeelden";
		} else {
			word = "aambeelden";
		}
		String muurschijfString;
		if (speler1.getAantalMuurschijven() != 0) {
			muurschijfString = ", 1 muurschijf resterend";
		} else {
			muurschijfString = "";
		}
		System.out.println(speler1.getNaam() + " ("
				+ speler1.getSymbol().toString() + ") ["
				+ speler1.getAantalAambeelden() + " " + word + " resterend"
				+ muurschijfString + "]. Score: " + speler1.getScore());

		// print voor speler 2
		if (speler2.getAantalAambeelden() == 1) {
			word = "aambeeld";
		} else if (speler1.getAantalAambeelden() == 2) {
			word = "aambeelden";
		} else {
			word = "aambeelden";
		}
		System.out.println(speler2.getNaam() + " ("
				+ speler2.getSymbol().toString() + ") ["
				+ speler2.getAantalAambeelden() + " " + word
				+ " resterend]. Score: " + speler2.getScore());
		System.out
				.println("----------------------------------------------------------");
	}

	/**
	 * check of de computer 3 op een rij heeft
	 * 
	 * @return true als dit het geval is
	 */
	public boolean check3Computer() {
		// horizontale rijen
		for (int rij = 0; rij < this.AANTAL_RIJEN; rij++) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isComputer()) && curr == this.array[rij][kolom + 1]
						&& curr == this.array[rij][kolom + 2]) {
					return true;
				}
			}
		}
		// verticale kolommen
		for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN; kolom++) {
			for (int rij = 0; rij < this.AANTAL_RIJEN - 3; rij++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isComputer()) && curr == this.array[rij + 1][kolom]
						&& curr == this.array[rij + 2][kolom]) {
					return true;
				}
			}
		}
		// diagonaal links beneden naar rechts boven
		for (int rij = 0; rij < this.AANTAL_RIJEN - 3; rij++) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isComputer())
						&& curr == this.array[rij + 1][kolom + 1]
						&& curr == this.array[rij + 2][kolom + 2]) {
					return true;
				}
			}
		}
		// diagonaal links boven naar rechts beneden
		for (int rij = this.AANTAL_RIJEN - 1; rij >= 3; rij--) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isComputer())
						&& curr == this.array[rij - 1][kolom + 1]
						&& curr == this.array[rij - 2][kolom + 2])
					return true;
			}
		}
		return false;
	}

	/**
	 * check of de computer 2 op een rij heeft
	 * 
	 * @return true als dit het geval is
	 */
	public boolean check2Computer() {
		// horizontale rijen
		for (int rij = 0; rij < this.AANTAL_RIJEN; rij++) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isComputer()) && curr == this.array[rij][kolom + 1]) {
					return true;
				}
			}
		}
		// verticale kolommen
		for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN; kolom++) {
			for (int rij = 0; rij < this.AANTAL_RIJEN - 3; rij++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isComputer()) && curr == this.array[rij + 1][kolom]) {
					return true;
				}
			}
		}
		// diagonaal links beneden naar rechts boven
		for (int rij = 0; rij < this.AANTAL_RIJEN - 3; rij++) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isComputer())
						&& curr == this.array[rij + 1][kolom + 1]) {
					return true;
				}
			}
		}
		// diagonaal links boven naar rechts beneden
		for (int rij = this.AANTAL_RIJEN - 1; rij >= 3; rij--) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isComputer())
						&& curr == this.array[rij - 1][kolom + 1])
					return true;
			}
		}
		return false;
	}

	/**
	 * check of de menselijke speler 3 op een rij heeft
	 * 
	 * @return true als dit het geval is
	 */
	public boolean check3Human() {
		// horizontale rijen
		for (int rij = 0; rij < this.AANTAL_RIJEN; rij++) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isHuman()) && curr == this.array[rij][kolom + 1]
						&& curr == this.array[rij][kolom + 2]) {
					return true;
				}
			}
		}
		// verticale kolommen
		for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN; kolom++) {
			for (int rij = 0; rij < this.AANTAL_RIJEN - 3; rij++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isHuman()) && curr == this.array[rij + 1][kolom]
						&& curr == this.array[rij + 2][kolom]) {
					return true;
				}
			}
		}
		// diagonaal links beneden naar rechts boven
		for (int rij = 0; rij < this.AANTAL_RIJEN - 3; rij++) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isHuman()) && curr == this.array[rij + 1][kolom + 1]
						&& curr == this.array[rij + 2][kolom + 2]) {
					return true;
				}
			}
		}
		// diagonaal links boven naar rechts beneden
		for (int rij = this.AANTAL_RIJEN - 1; rij >= 3; rij--) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isHuman()) && curr == this.array[rij - 1][kolom + 1]
						&& curr == this.array[rij - 2][kolom + 2])
					return true;
			}
		}
		return false;
	}

	/**
	 * checkt of de menselijke speler 2 op een rij heeft
	 * 
	 * @return true als dit het geval is!
	 */
	public boolean check2Human() {
		// horizontale rijen
		for (int rij = 0; rij < this.AANTAL_RIJEN; rij++) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isHuman()) && curr == this.array[rij][kolom + 1]) {
					return true;
				}
			}
		}
		// verticale kolommen
		for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN; kolom++) {
			for (int rij = 0; rij < this.AANTAL_RIJEN - 3; rij++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isHuman()) && curr == this.array[rij + 1][kolom]) {
					return true;
				}
			}
		}
		// diagonaal links beneden naar rechts boven
		for (int rij = 0; rij < this.AANTAL_RIJEN - 3; rij++) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isHuman()) && curr == this.array[rij + 1][kolom + 1]) {
					return true;
				}
			}
		}
		// diagonaal links boven naar rechts beneden
		for (int rij = this.AANTAL_RIJEN - 1; rij >= 3; rij--) {
			for (int kolom = 0; kolom < this.AANTAL_KOLOMMEN - 3; kolom++) {
				Schijf curr = this.array[rij][kolom];
				if ((curr.isHuman()) && curr == this.array[rij - 1][kolom + 1])
					return true;
			}
		}
		return false;
	}

	/**
	 * controleren of er 4 op een rij is
	 * 
	 * @return winnend symbool null als er niemand wint
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
				if ((!curr.isLeeg()) && curr == this.array[rij - 1][kolom + 1]
						&& curr == this.array[rij - 2][kolom + 2]
						&& curr == this.array[rij - 3][kolom + 3])
					return this.array[rij][kolom];
			}
		}
		return null;
	}

	/**
	 * bord = vol -----> game over.
	 * 
	 * @return true if bord = vol
	 */
	public boolean checkVol() {
		int count = 0;
		for (int i = 0; i < this.AANTAL_RIJEN; i++) {
			for (int x = 0; x < this.AANTAL_KOLOMMEN; x++) {
				if (this.array[i][x].isLeeg()) {
					count++;
				}
			}
		}

		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void checkMuurschijf(Mens mens) {
		int counter = 0;
		int rij = 0;
		int kolom = 0;
		for (int i = 0; i < AANTAL_RIJEN; i++) {
			for (int x = 0; x < AANTAL_KOLOMMEN; x++) {
				if (array[i][x].isMuurschijf()) {
					// System.out.println("*********************** muurschijf gevonden");
					// System.out.println("rij " + i + ", kolom " + x);
					rij = i;
					kolom = x;
					for (int n = 1; n <= mens.getnMuurschijf(); n++) {
						// System.out.println("nMuurschijf: "+
						// mens.getnMuurschijf());
						// System.out.println("n: " + n);
						if (this.rijIsOnField(rij - n)) {
							if (!this.array[rij - n][kolom].isLeeg()) {
								// System.out.println("rij " + (rij - n)+
								// ", kolom " + kolom+ " is niet leeg.");
								counter++;
							}
						}
					}
				}
			}
		}

		if (counter == mens.getnMuurschijf()) {
			Leeg lege = new Leeg();
			int bovensteRij = rij - 1;
			this.array[rij][kolom] = this.array[bovensteRij][kolom];
			for (int n = 1; n <= mens.getnMuurschijf(); n++) {
				if (this.rijIsOnField(rij - n - 1)) {
					this.array[rij - n][kolom] = this.array[rij - n - 1][kolom];
				}

			}
			this.array[rij - mens.getnMuurschijf()][kolom] = lege;
		}
	}

	/**
	 * geeft aantal kolommen terug
	 * 
	 * @return aantal kolommen
	 */
	public int getAantalKolommen() {
		return this.AANTAL_KOLOMMEN;
	}

	/**
	 * geeft aantal rijen terug
	 * 
	 * @return aantal rijen
	 */
	public int getAantalRijen() {
		return this.AANTAL_RIJEN;
	}

}
