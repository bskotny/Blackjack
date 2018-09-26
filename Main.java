import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Deck deck = new Deck();					//creates an instance of a deck
		deck.buildDeck();
		deck.Shuffle();
		System.out.println("\n");

		//creates an array for both players and dealer
		Player[] players = new Player[7];
		Dealer[] dealer = new Dealer[1];
		
		//creates a few players with default values
		for(int i = 0; i < 2; i++){
			players[i] = new Player();
		}
		
		
		//creates a dealer
		for(int i = 0; i < 1; i++){
			dealer[i] = new Dealer();
		}
		
			
		int deckIndex = 0;
		//gets individual card of the players
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				Card playerCard = deck.Deal();
				deckIndex++;
				players[i].hand[j].setValue(playerCard.getValue());
				players[i].setID(i);
				System.out.println("Player " + players[i].getID() + " : " + players[i].hand[j].getValue());
			}
			System.out.println("\n");
		}
		
		
		//gets individual card of the dealer
		for(int i = 0; i < 1; i++){
			for(int j = 0; j < 2; j++){
				Card dealerCard = deck.Deal();
				deckIndex++;
				dealer[i].dealerHand[j].setValue(dealerCard.getValue());
			}
		}
		
		//sets total value for a player hand
		int totalValue = 0;
		for(int i = 0; i < 2; i++){
			totalValue = players[i].addPlayerCards(i, players);
		}
		
		//sets total value for the dealer hand
		totalValue = 0;
		for(int i = 0; i < 1; i++){
			totalValue = dealer[i].addDealerCards(i, dealer);
		}
		
		//check if player has an ace and a 10
		//if so set for blackjack
		for(int i = 0; i < 2; i++){
			if((players[i].hand[0].getValue() == 1 && players[i].hand[1].getValue() == 10) || (players[i].hand[0].getValue() == 10 && players[i].hand[1].getValue() == 1)){
				System.out.println("Player " + i + " has black Jack!");
				players[i].setTotalNumber(21);;
				players[i].setBlackJack(true);
			}
		}

		
		boolean under21 = true;
		
		//print out player total
		for(int i = 0; i < 2; i++){
			System.out.println("Player " + i + " total value " + players[i].getTotalNumber());
		}
		
		//player action test
		int handIndex = 2;
		for(int i = 0; i < 2; i++){
		Scanner reader = new Scanner (System.in);
		
		if(players[i].isBlackJack() == true){	
		}
		else{
		System.out.println("(1) Hit or (2) Stand: for player " + i + " (" + players[i].hand[0].getValue() + "," + players[i].hand[1].getValue() + ")");
		String answer = reader.nextLine();
			if(answer.equals("1")){
				players[i].recieveCard(handIndex,deck.cards[deckIndex].getValue());
				
				//check to see if an ace is reset to 1
				int tempTotal = players[i].addPlayerCards(i, players);
				
				//check for ace
				players[i].checkAce(i, players, tempTotal);
				
				//increase deck index
				deckIndex++;
				
				//prints out individual cards of the player
				for(int j = 0; j < players[i].handLength(); j++){
					System.out.println("Player " + players[i].getID() + " : " + players[i].hand[j].getValue());
				}
				
				handIndex = players[i].handLength();
				
				//prints out payers[i] total value
				System.out.println("Player total value" + i + " : " + players[i].getTotalNumber());
				
				
					if(players[i].getTotalNumber() > 21){
						players[i].setBust(true);
						System.out.println("Player " + players[i].getID() + " loses!");
					}
					else if(players[i].getTotalNumber() == 21){
						System.out.println("Player " + players[i].getID() + " has blackjack!");
						players[i].setBlackJack(true);
					}		
					else
					{
						//under 21
						while(under21){
						System.out.println("\n");	
						System.out.println("(1) Hit again or (2) Stand?");
						String answer2 = reader.nextLine();
						
						
						if(answer2.equals("1")){
							System.out.println("Hit again");
							players[i].recieveCard(handIndex,deck.cards[deckIndex].getValue());
							deckIndex++;
							handIndex = players[i].handLength();
							
							//prints out individual cards of the player
							for(int j = 0; j < players[i].handLength(); j++){
								System.out.println("Player " + players[i].getID() + " : " + players[i].hand[j].getValue());
							}
							
							//sets total value for a player hand
							totalValue = 0;
							
								for(int j = 0; j < players[i].handLength(); j++){
									totalValue = totalValue + players[i].hand[j].getValue();
								}
								players[i].setTotalNumber(totalValue);
								totalValue = 0; 
								System.out.println("Players " + i + " total value: " + players[i].getTotalNumber());
								
								//checks for players total value
								if(players[i].getTotalNumber() > 21){
									System.out.println("You lose!");
									players[i].setBust(true);
									under21 = false;
								}
								else if(players[i].getTotalNumber() == 21){
									System.out.println("You have Black Jack!");
									under21 = false;
								}
								else if(players[i].getTotalNumber() < 21){
									System.out.println("You are under 21!");
									under21 = true;
								}
						}
						else if(answer2.equals("2")){
							//player stands
							
							//check to see if an ace is reset to 1
							int tempStandTotal = players[i].addPlayerCards(i, players);
							
							//check for ace
							players[i].checkAce(i, players, tempStandTotal);

							under21 = false;
						}
						}
					}
			}
			else if(answer.equals("2")){
				//check to see if an ace is reset to 1
				int tempStandTotal = players[i].addPlayerCards(i, players);
				
				//check for ace
				players[i].checkAce(i, players, tempStandTotal);
			}
			else{
				//keeps asking for the current player's move
				i--;
				System.out.println("invalid move");
			}
			//reseting values
			handIndex = 2;
			under21 = true;
		}
		}
		
		//dealer action
		int dealerHandIndex = 2;
		boolean over17 = false;
		
		//compare cards with dealer
		
		while(!over17){
		if(dealer[0].getDealerTotalNumber() <= 17){
			dealer[0].dealerRecieveCard(dealerHandIndex, deck.cards[deckIndex].getValue());
			
			
			//check to see if an ace is reset to 1
			int dealerTempTotal = dealer[0].addDealerCards(0, dealer);
			
			//check for ace
			dealer[0].dealerCheckAce(0, dealer, dealerTempTotal);
			
			//increase index of deck by 1
			deckIndex++;
			
			dealerHandIndex++;
			dealer[0].addDealerCards(0, dealer);
			if(dealer[0].getDealerTotalNumber() > 17){
				over17 = true;
				
				//check for dealer over 21
				if(dealer[0].getDealerTotalNumber() > 21){
					dealer[0].setDealerBust(true);
				}
			}
		}
		//dealer is over 17
		else{
			over17 = true;
		}
		}

		//compare cards with dealer
		System.out.println("Dealer total value:  " + dealer[0].getDealerTotalNumber());
		
		//print out player total
		for(int i = 0; i < 2; i++){
			System.out.println("Player " + i + " total value " + players[i].getTotalNumber());
		}
		
		System.out.println("\n");
		
		for(int i = 0; i < 2; i++){
			if((players[i].isBust() == true) && (dealer[0].isDealerBust() == true)){
				System.out.println("Player " + i + " loses!");
			}
			else if((players[i].getTotalNumber() <= 21) && (dealer[0].isDealerBust() == true)){
				System.out.println("Player " + i + " wins!");
			}
			else if((players[i].isBust() == true) && (dealer[0].getDealerTotalNumber() <= 21)){
				System.out.println("Player " + i + " loses!");
			}
			else if(players[i].getTotalNumber() > dealer[0].getDealerTotalNumber()){
				System.out.println("Player " + i + " wins!");
			}
			else if(players[i].getTotalNumber() < dealer[0].getDealerTotalNumber()){
				System.out.println("Player " + i + " loses!");
			}
			else if((players[i].getTotalNumber() == 21) && (dealer[0].getDealerTotalNumber() == 21)){
				System.out.println("Player " + i + " and Dealer have blackjack!");
			}
			else if(players[i].getTotalNumber() == dealer[0].getDealerTotalNumber()){
				System.out.println("Player " + i + " and dealer have the same value!");
			}
				
		}
	}

}
