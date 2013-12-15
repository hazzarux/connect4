import java.util.Scanner;

public class Spel {
	static Scanner s = new Scanner(System.in);
	static int aanDeBeurt=1;

	public static void main(String[] args) {
		System.out.println("F. Yigit Ozkan, r0456142");

		// "Vier op een rij" printen
		String welcomeMessage = "__      ___                                                  _ _ \n"
				+ "\\ \\    / (_)                                                (_|_)\n"
				+ " \\ \\  / / _  ___ _ __    ___  _ __     ___  ___ _ __    _ __ _ _ \n"
				+ "  \\ \\/ / | |/ _ \\ '__|  / _ \\| '_ \\   / _ \\/ _ \\ '_ \\  | '__| | |\n"
				+ "   \\  /  | |  __/ |    | (_) | |_) | |  __/  __/ | | | | |  | | |\n"
				+ "    \\/   |_|\\___|_|     \\___/| .__/   \\___|\\___|_| |_| |_|  |_| |\n"
				+ "                             | |                             _/ |\n"
				+ "                             |_|                            |__/ ";
		System.out.println(welcomeMessage);

		boolean playAnotherGame = false;

		// grootte van het spelbord bepalen
		System.out
				.println("Hoe groot moet het spelbord zijn? Dit is het aantal kolommen.");
		int grootteSpelBord = s.nextInt();

		// moeilijkheidsgraad bepalen
		System.out.println("Hoe moeilijk moet de computer zijn?");
		System.out.println("1. makkelijk");
		System.out.println("2. normaal");
		System.out.println("3. moeilijk");
		int moeilijkheidsgraad = s.nextInt();

		// aantal aambeelden bepalen
		System.out.println("Hoeveel aambeelden moet elke speler krijgen?");
		System.out.println("(1 of 2)");
		int aantalAambeelden = s.nextInt();
		
		//waarde voor x (van een aambeeld bepalen)
		System.out.println("Hoeveel schijven moet een aambeeld verwijderen?");
		int xAambeeld = s.nextInt();
		
		
		// naam vragen
		System.out.println("Wat is uw naam?");
		String naam = s.next();

		// bord genereren
		SpelBord bord = new SpelBord(grootteSpelBord);

		// spelers genereren (computerspeler met bepaalde moeilijkheidsgraad)
		Mens human = new Mens(aantalAambeelden, bord, naam, xAambeeld);
		Computer ai = new Computer(moeilijkheidsgraad, aantalAambeelden, bord, xAambeeld);

		do {
			bord.generateSpelbord();
			bord.setGameOver(false);
			bord.printSpelbord();
			human.setAantalAambeelden(aantalAambeelden);
			ai.setAantalAambeelden(aantalAambeelden);
			while (bord.isGameOver() == false) {
				if (aanDeBeurt == 1) {
					human.vraagVoorZet();
					printAlles(bord, human, ai);
					if(bord.check4()!=null){
						Schijf x = bord.check4();
						checkForWinner(x, human, ai, bord);
					}else{
						if(bord.checkVol()){
							System.out.println("Het bord is vol. Het is gelijkspel. Niemand wint!");
							bord.setGameOver(true);
						}else{
							switchPlayer();
						}
						
					}
				} else {
					ai.doeZet();
					printAlles(bord, human, ai);
					if(bord.check4()!=null){
						Schijf x = bord.check4();
						checkForWinner(x, human, ai, bord);
					}else{
						if(bord.checkVol()){
							System.out.println("Het bord is vol. Het is gelijkspel. Niemand wint!");
							bord.setGameOver(true);
						}else{
							switchPlayer();
						}
					}
				}

			}
			playAnotherGame = promptIfPlayerWantsToPlayAgain();
		} while (playAnotherGame == true);

	}

	public static void switchPlayer() {
		if (aanDeBeurt == 1) {
			aanDeBeurt = 2;
		} else {
			aanDeBeurt = 1;
		}
	}

	/**
	 * returns true if player wants to play again, else: false
	 */
	public static boolean promptIfPlayerWantsToPlayAgain() {
		System.out.println("Wilt u opnieuw spelen? (j/n)");
		String yesOrNo = s.next();
		yesOrNo = yesOrNo.toLowerCase();

		if (yesOrNo.equals("j")) {
			return true;
		} else if(yesOrNo.equals("n")) {
			return false;
		}else{
			System.out.println("Ongeldige input!");
			return promptIfPlayerWantsToPlayAgain();
		}

	}
	
	public static void checkForWinner(Schijf winnendeSchijf, Speler speler1, Speler speler2, SpelBord bord){
		if(winnendeSchijf.getCharacter().equals(speler1.getSymbol().toString())){
			speler1.incrementScore();
			System.out.println(speler1.getNaam()+" heeft gewonnen! Zijn/haar score is nu "+speler1.getScore()+"!");
			bord.setGameOver(true);
			
		}else if(winnendeSchijf.getCharacter().equals(speler2.getSymbol().toString())){
			speler2.incrementScore();
			System.out.println(speler2.getNaam()+" heeft gewonnen. Zijn score is nu "+speler2.getScore()+"!");
			bord.setGameOver(true);
		}else{
			System.out.println("error");
			bord.setGameOver(true);
		}
	}
	
	public static void printAlles(SpelBord bord, Speler speler1, Speler speler2) {
		bord.printSpelbord();
		bord.printNamenEnAambeelden(speler1, speler2);
	}
}
