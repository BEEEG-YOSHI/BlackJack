import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
    
    private Deck deck;
    
    private ArrayList<Card> player;
    private ArrayList<Card> dealer;
    private boolean dealerReveal;
    public Blackjack(){
        deck = new Deck();
        player = new ArrayList<>();
        dealer = new ArrayList<>();
        Scanner kb = new Scanner(System.in);
        dealerReveal = false;
    }
    
    public static void main(String[] args){
        Blackjack game = new Blackjack();
        game.run();
    }

    private void run(){
        deck.shuffle();
        dealerCard();
        dealerCard();
        playerCard();
        playerCard();

        printDealer();
        printPlayer();

    }

    private void playerCard(){
        player.add(deck.getCard());
    }
    private void dealerCard(){
        dealer.add(deck.getCard());
    }
    public void printPlayer(){
        System.out.print("Player: ");
        for(Card card : player){
            System.out.print(card);
            if(card != player.get(player.size()-1)){
                System.out.print(",  ");
            }
        }
        System.out.println();
    }
    public void printDealer(){
        System.out.print("Dealer: ");
        if(dealerReveal){
        for(Card card : dealer){
            System.out.print(card);
            if(card != dealer.get(dealer.size()-1)){
                System.out.print(",  ");
            }
        }} else {
        System.out.print(dealer.get(0) + ", [X]");
        }
        System.out.println();
    }

    public void printTotal(){

    }

}
