import java.util.Scanner;

public class Spel {
	@SuppressWarnings("resource")
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("F. Yigit Ozkan, r0456142");
		
		
		// "Vier op een rij" printen
		String welcomeMessage = "__      ___                                                  _ _ \n" + 
				"\\ \\    / (_)                                                (_|_)\n" + 
				" \\ \\  / / _  ___ _ __    ___  _ __     ___  ___ _ __    _ __ _ _ \n" + 
				"  \\ \\/ / | |/ _ \\ '__|  / _ \\| '_ \\   / _ \\/ _ \\ '_ \\  | '__| | |\n" + 
				"   \\  /  | |  __/ |    | (_) | |_) | |  __/  __/ | | | | |  | | |\n" + 
				"    \\/   |_|\\___|_|     \\___/| .__/   \\___|\\___|_| |_| |_|  |_| |\n" + 
				"                             | |                             _/ |\n" + 
				"                             |_|                            |__/ ";
		System.out.println(welcomeMessage);
		
		
		
		boolean playAnotherGame = false;
		
		// TODO: playanothergame implementeren
		
		
		//grootte van het spelbord bepalen
		System.out.println("Hoe groot moet het spelbord zijn? Dit is het aantal kolommen.");
		int grootteSpelBord = s.nextInt();
		
		//moeilijkheidsgraad bepalen
		System.out.println("Hoe moeilijk moet de computer zijn?");
		System.out.println("1. makkelijk");
		System.out.println("2. normaal");
		System.out.println("3. moeilijk");
		int moeilijkheidsgraad = s.nextInt();
		
		//aantal aambeelden bepalen
		System.out.println("Hoeveel aambeelden moet elke speler krijgen?");
		System.out.println("(1 of 2)");
		int aantalAambeelden = s.nextInt();
		
		//naam vragen
		System.out.println("Wat is uw naam?");
		String naam = s.next();
		
		//bord genereren
		SpelBord bord = new SpelBord(grootteSpelBord);
		bord.printSpelbord();
		
		//spelers genereren (computerspeler met bepaalde moeilijkheidsgraad)
		Mens human = new Mens(aantalAambeelden,bord,naam);
		Computer ai = new Computer(moeilijkheidsgraad,aantalAambeelden,bord);
		
		do{
			while(bord.isGameOver()==false){
				human.vraagVoorZet();
				printAlles(bord, human, ai);
			}
			playAnotherGame=promptIfPlayerWantsToPlayAgain();
		}while(playAnotherGame==true);
		
		
	}

	/**
	 * returns true if player wants to play again else: false
	 */
	public static boolean promptIfPlayerWantsToPlayAgain() {
		System.out.println("Wilt u opnieuw spelen?");
		String yesOrNo = s.nextLine();
		yesOrNo = yesOrNo.toLowerCase();

		if (yesOrNo.equals("y")) {
			return true;
		} else {
			return false;
		}

	}
	
	
	public static void printAlles(SpelBord bord, Speler speler1, Speler speler2){
		bord.printSpelbord();
		bord.printNamenEnAambeelden(speler1, speler2);
	}
}