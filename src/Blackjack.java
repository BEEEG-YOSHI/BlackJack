import java.util.ArrayList;
import java.util.InputMismatchException;
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
    }
    
    public static void main(String[] args){
        Blackjack game = new Blackjack();
        game.run();
    }

    private void run(){
        initGame();
    }

    public void initGame(){
        deck.iniDeck();
        player.clear();
        dealer.clear();
        dealerReveal = false;
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
        String command = getCommand();
        if(command.equals("Stand")){
            printAll();
            dealerTurn();
        } else if (command.equals("Hit")){
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
        } else {
            System.out.println("Invalid command, please try again");
            playerTurn();
        }
    }

    public void dealerTurn(){
        dealerReveal = true;
        if(getDealerTotal() < 17){
            printAll();
        }
        if(getDealerTotal() > 21){
            System.out.println("The Dealer has busted!");
            System.out.println("You Win!");
        } else if (getDealerTotal() < 17){
            dealerCard();
            try{
                Thread.sleep(1500);
            } catch (InterruptedException e){}
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
        playAgain();
    }

    public void playAgain(){
        System.out.println();
        System.out.println();
        System.out.println("Want to play again?");
        String command = getCommand();
        if(command.equals("Yes")){
            System.out.println();
            System.out.println();
            initGame();
        } else if(command.equals("No")){
            System.out.println("Thanks for playing!");
        } else {
            System.out.println("Invalid command, please try again");
            playAgain();
        }
    }

    public void aceCheck(ArrayList<Card> array){
        while(getTotal(array) > 21){
            for(Card card : array){
                if(card.getValue() == 11){
                    card.setValue(1);
                    break;
                }
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
            case "Yes":
                return "Yes";
            case "yes":
                return "Yes";
            case "Y":
                return "Yes";
            case "y":
                return "Yes";
            case "No":
                return "No";
            case "no":
                return "No";
            case "N":
                return "No";
            case "n":
                return "No";
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


