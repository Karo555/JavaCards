package javacards;

public interface CrazyEightsPlayer {
    void discardCard(int card);
    void drawCard(Card card);
    void passTurn(int turn);
    void collectPayment();
    void receiveHand();
}


//void startGame();
// face-up first card in deck
//    void endGame();
//    void checkForWinner();
//Deck shuffleDeck();
// Card dealCards(int numCards);
//Player getWinner();
