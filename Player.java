
public class Player{

	//attributes
	private int ID;
	private int totalNumber;
	private boolean bust;
	private boolean blackJack;
	
	//creates a players hand
	Card[] hand = new Card[11];
	
	//getters and setters
	public boolean isBlackJack() {
		return blackJack;
	}
	public void setBlackJack(boolean blackJack) {
		this.blackJack = blackJack;
	}
	
	public boolean isBust() {
		return bust;
	}
	public void setBust(boolean bust) {
		this.bust = bust;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	
	//constructors
	//initializes player
	public Player(){
		this.ID = 0;
		this.totalNumber = 0;
		this.bust = false;
		this.blackJack = false;
		
		for(int i = 0; i < 11; i++){
			hand[i] = new Card(0);
		}
	}
	
	public Player(int iD, int totalNumber, boolean bust, boolean blackJack) {
		ID = iD;
		this.totalNumber = totalNumber;
		this.bust = bust;
		this.blackJack = blackJack;
		
		for(int i = 0; i < 11; i++){
			hand[i] = new Card(totalNumber);
		}
	}
	
	//j = deck Index
	public void recieveCard(int handIndex, int j){
		hand[handIndex].setValue(j);
		//System.out.println(this.totalNumber);
	}
	
	//gets player's hand length
	public int handLength(){
		int total = 0;
		int counterHand = 0;
		while(hand[counterHand].getValue() != 0){
			total = total + 1;
			counterHand = counterHand + 1;
		}
		return total;
	}
	
	//gets player's total card value
	public int addPlayerCards(int i, Player[] players){
		//sets total value for a player hand
		int totalValue = 0;
		
			for(int j = 0; j < players[i].handLength(); j++){
				totalValue = totalValue + players[i].hand[j].getValue();
			}
			players[i].setTotalNumber(totalValue);
			
			return totalValue;
	}
	
	//determines if a player has an ace
	public void checkAce(int i, Player[] players, int totValue){
		System.out.println("\n");
		for(int j = 0; j < players[i].handLength(); j++){
			//add 10 to an ace by default
			if(players[i].hand[j].getValue() == 1){
				players[i].setTotalNumber(totValue + 10);
				
				//don't add 10 for an ace
				if(players[i].getTotalNumber() > 21){
					players[i].setTotalNumber(totValue);
				}
				
			}
		}
	}
}
