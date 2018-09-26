
public class Dealer{
	
	//attribute
	private int dealerTotalNumber;
	private boolean dealerBust;
	
	//an array for the dealer hand
	Card[] dealerHand = new Card[11];
	
	//getters and setters
	public boolean isDealerBust() {
		return dealerBust;
	}

	public void setDealerBust(boolean dealerBust) {
		this.dealerBust = dealerBust;
	}

	public int getDealerTotalNumber() {
		return dealerTotalNumber;
	}

	public void setDealerTotalNumber(int dealerTotalNumber) {
		this.dealerTotalNumber = dealerTotalNumber;
	}

	//initializes variables, constructors
	public Dealer(){
		this.dealerTotalNumber = 0;
		this.dealerBust = false;
		
		for(int i = 0; i < 11; i++){
			dealerHand[i] = new Card(0);
		}
	}
	
	public Dealer(int dealerTotalNumber, boolean dealerBust) {
		this.dealerTotalNumber = dealerTotalNumber;
		this.dealerBust = dealerBust;
		
		for(int i = 0; i < 11; i++){
			dealerHand[i] = new Card(dealerTotalNumber);
		}
	}
	
	//gets dealer's total card value
	public int addDealerCards(int i, Dealer[] dealer){
		//sets total value for a player hand
		int totalValue = 0;
		
			for(int j = 0; j < dealer[i].dealerHandLength(); j++){
				totalValue = totalValue + dealer[i].dealerHand[j].getValue();
			}
			dealer[i].setDealerTotalNumber(totalValue);
			return totalValue;
	}
	
	//dealer draws a card
	public void dealerRecieveCard(int dealerHandIndex, int m){
		dealerHand[dealerHandIndex].setValue(m);
	}
	
	//gets dealer hand length
	public int dealerHandLength(){
		int total = 0;
		int counterHand = 0;
		while(dealerHand[counterHand].getValue() != 0){
			total = total + 1;
			counterHand = counterHand + 1;
		}
		return total;
	}
	
	//determines if dealer has an ace
	public void dealerCheckAce(int i, Dealer[] dealer, int totValue){
		System.out.println("\n");
		for(int j = 0; j < dealer[i].dealerHandLength(); j++){
			//add 10 to an ace by default
			if(dealer[i].dealerHand[j].getValue() == 1){
				dealer[i].setDealerTotalNumber(totValue + 10);

				//System.out.println("Dealer " + i + " total value " + dealer[i].getDealerTotalNumber());
				
				//don't add 10 for an ace
				if(dealer[i].getDealerTotalNumber() > 21){
					//System.out.println("Total value: " + totValue);
					dealer[i].setDealerTotalNumber(totValue);
					//System.out.println("Dealer " + i + " after -10 " + dealer[i].getDealerTotalNumber());
				}
				
			}
		}
	}
	
}
