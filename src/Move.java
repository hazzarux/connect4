public class Move {
	/**
	 * klasse move voor elke move dat een computerspeler kan doen!
	 */
	private int punten;
	private String methode;
	private int kolom;

	public Move(String methode) {
		this.methode = methode;
	}

	public int getPunten() {
		return punten;
	}

	public void setPunten(int punten) {
		this.punten = punten;
	}

	public String getMethode() {
		return methode;
	}

	public void setMethode(String methode) {
		this.methode = methode;
	}

	public int getKolom() {
		return kolom;
	}

	public void setKolom(int kolom) {
		this.kolom = kolom;
	}

}
