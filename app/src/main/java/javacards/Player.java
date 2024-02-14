package javacards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Represents a player in the game.
 * A player has a hand of cards.
 */

public class Player implements CrazyEightsPlayer {
    /**
     * Player's name.
     */
    private final String name;
    /**
     * Player's position on the table.
     */
    public final int[] position;
    /**
     * Is the player controlled by AI.
     */
    private final Boolean AI;
    /**
     * Player's hand.
     */
    private static List<Card> hand;


    /**
     * Constructor for the Player class.
     *
     * @param name The player's name.
     * @param pos  The player's position on the table.
     * @param AI   Is the player controlled by AI.
     */
    public Player(String name, int[] pos, Boolean AI) {
        this.name = name;
        this.position = pos;
        this.AI = AI;
        this.hand = new ArrayList<>();
        receiveHand();
    }

    /**
     * Deals a hand of 7 cards to the player.
     */
    public void receiveHand(){
        for(int i=0;i<7;i++){
            hand.add(App.deck.dealCard());
        }
    }



    /**
     * Gets the player's hand.
     *
     * @return The player's hand.
     */
    public static List<Card> getHand() {
        return hand;
    }

    /**
     * Adds a new card to the player's hand.
     *
     * @param card The card to add.
     */
    @Override
    public void drawCard(Card card) {
        Animation.move(card, this.position);
        hand.add(card);
        System.out.println("Added card: " + card);
    }

    /**
     * Removes a card from the player's hand and adds it to the stack.
     *
     * @param card Index of the card to remove.
     */

    @Override
    public void discardCard(int card) {
        Animation.move(hand.get(card), new int[]{0, 0});
        App.stack.add(hand.remove(card));
        System.out.println("Removed card: " + App.stack.cards.get(App.stack.cards.size() - 1));
    }

    /**
     * Plays a card from the player's hand.
     * If the player has a card that matches the top card of the discard pile, they play it.
     * Otherwise, they draw a card from the deck.
     *
     * @param discardPile The discard pile.
     * @param currentCard The current card.
     */

    public void playCard(DiscardPile discardPile, Card currentCard){
        Random random= new Random();
        for(int i=0;i<hand.size();i++){
            if(hand.get(i).getRank().equals(discardPile.getTopCard().getRank()) || hand.get(i).getSuit().equals(discardPile.getTopCard().getSuit())){
                discardCard(i);
                DiscardPile.setTopCard(hand.get(i));
                return;
            }
        }
        drawCard(App.deck.dealCard());
    }

    /**
     * Displays the player's hand.
     */
    public void displayHand() {
        for (Card card : hand) {
            Animation.flip(card);
            System.out.println(card);
        }
    }

    @Override
    public void passTurn(int turn) {
        System.out.println("Player " + this.name + " passed turn " + turn);
    }

    @Override
    public void collectPayment() {
    }
}
