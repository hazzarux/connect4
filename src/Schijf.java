public class Schijf {
	private String character;

	public String toString() {
		return this.character;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	/**
	 * 
	 * @return true als de schijf een lege schijf is
	 */
	public boolean isLeeg() {
		Leeg lege = new Leeg();
		if (this.getCharacter().equals(lege.getCharacter())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return if schijf is computer (kruisje)
	 */
	public boolean isComputer() {
		Kruis x = new Kruis();
		if (this.getCharacter().equals(x.getCharacter())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return if schijf is human schijf (rondje)
	 */
	public boolean isHuman() {
		Rondje x = new Rondje();
		if (this.getCharacter().equals(x.getCharacter())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isMuurschijf() {
		Muurschijf x = new Muurschijf();
		if (this.getCharacter().equals(x.getCharacter())) {
			return true;
		} else {
			return false;
		}
	}

}
