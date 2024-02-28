import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
    
    private Deck deck;
    Scanner kb;
    private ArrayList<Card> player;
    private ArrayList<Card> dealer;
    private boolean dealerReveal;
    public Blackjack(){
        deck = new Deck();
        player = new ArrayList<>();
        dealer = new ArrayList<>();
        kb = new Scanner(System.in);
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

        printAll();

        playerTurn();

    }

    public void printAll(){
        printDealer();
        printPlayer();
        printTotals();
        System.out.println();
    }

    public void playerTurn(){
        System.out.println("Would you like to [Hit] or [Stand]?");

        if(getCommand().equals("Stand")){
            dealerTurn();
        } else {
            playerCard();
            System.out.println("Player drew a " + player.getLast());
            System.out.println();
            aceCheck(player);
            printAll();
            if(getPlayerTotal() > 21){
                System.out.println("The Player has busted!");
                System.out.println("You Lose!");
            } else {
                playerTurn();
            }
        }
    }

    public void dealerTurn(){
        dealerReveal = true;
        printAll();
        if(getDealerTotal() > 21){
            System.out.println("The Dealer has busted!");
            System.out.println("You Win!");
        } else if (getDealerTotal() < 17){
            dealerCard();
            System.out.println("Dealer drew a " + dealer.getLast());
            System.out.println();
            aceCheck(dealer);
            printAll();

            dealerTurn();
        } else {
            winCheck();
        }
    }

    public void winCheck(){
        if(getDealerTotal() > getPlayerTotal()){
            System.out.println("Dealer Wins!");
            System.out.println("Better luck next time!");
        } else {
            System.out.println("Player Wins!");
            System.out.println("Congratulations!");
        }
    }

    public void aceCheck(ArrayList<Card> array){
        int numOfAces = 0;
        for(Card card : array){
            if(card.getValue() == 11){
                numOfAces++;
            }
        }
        if(getTotal(array) > 21 || numOfAces > 0){
            for(Card card : array){
                if(card.getValue() == 11){
                    card.setValue(1);
                    break;
                }
            }
            if(numOfAces > 0){
                aceCheck(array);
            }
        }
    }

    public String getCommand(){
        String command = kb.nextLine();
        switch(command) {
            case "hit":
                return "Hit";
            case "stand":
                return "Stand";
            case "Hit":
                return "Hit";
            case "Stand":
                return "Stand";
            default:
                System.out.println("Invalid command, please try again.");
                return getCommand();
        }
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

    public void printTotals(){
        if(dealerReveal){
            int DTotal = 0;
            for(Card card: dealer){
                DTotal += card.getValue();
            }
            System.out.print("DTotal: " + DTotal);
        } else {
            System.out.print("DTotal: " + dealer.get(0).getValue() + " + " + "[X]");
        }
        int PTotal = 0;
        for(Card card: player){
            PTotal += card.getValue();
        }
        System.out.println("       PTotal: " + PTotal);
    }

    public int getTotal(ArrayList<Card> array){
        if(array.equals(player)){
            return getPlayerTotal();
        } else {
            return getDealerTotal();
        }
    }

    public int getPlayerTotal(){
        int PTotal = 0;
        for(Card card: player){
            PTotal += card.getValue();
        }
        return PTotal;
    }
    public int getDealerTotal(){
        int DTotal = 0;
        for(Card card: dealer){
            DTotal += card.getValue();
        }
        return DTotal;
    }
}


