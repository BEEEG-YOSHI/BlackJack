public class Card {

    private int suit;
    private int value;
    private int number;

    public Card(int suit, int value){
        this.suit = suit;
        this.value = value;
        this.number = value;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        if(number < 11){
            return number;
        } else if(number == 14) {
            return 11;
        } else {
            return 10;
        }
    }

    public boolean isAce(){
        if(value == 14){
            return true;
        }
        return false;
    }

    public String toString(){
        String[] suits = {"Hearts","Diamonds","Clubs","Spades"};
        String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        return values[this.value-2] + " of " + suits[this.suit];
    }

    public void setValue(int value){
        number = value;
    }
}
