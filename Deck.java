import java.util.Random;

public class Deck {

	//attribute
	private int counter = 0;
	
	Card[] cards = new Card[52];				//creates the array of cards for the deck
	Random randomGenerator = new Random();

	//creates the original deck
	public void buildDeck(){
	//these sections build the deck
		int cardValue = 1;
		for(int index = 0; index < 52; index++){					//gives each card its value
			for(int j = 0; j < 4; j++){
				if(cardValue > 10){
					cards[index] = new Card(10);
				}
				else{
				cards[index] = new Card(cardValue);
				}
				index = index + 1;
			}
			index = index - 1;					//accounts for skipping
			cardValue = cardValue + 1;
		}
	}
	
	//shuffles the deck
	public void Shuffle(){
		System.out.println("\n");	
		for(int i = 0; i < 52; i++){
			int randomInt = randomGenerator.nextInt(52);
				
			//swaps the cards
			int temp = cards[i].getValue();
			cards[i].setValue(cards[randomInt].getValue());
			cards[randomInt].setValue(temp);
		}
		
		for(int i = 0; i < 52; i++){
			System.out.println("Index = " + i + " Value is " + cards[i].getValue());
		}
		
	}
	
	//a player or dealer draws a card
	public Card Deal(){
			counter++;
			return cards[counter - 1];
	}

}
