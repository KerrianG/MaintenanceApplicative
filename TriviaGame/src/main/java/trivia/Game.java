package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// REFACTOR ME
public class Game implements IGame {
   ArrayList<Player> players = new ArrayList<>();
   int[] places = new int[6];
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   private final Map<String, QuestionDeck> questionDecks = new HashMap<>();
   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public Game() {
      questionDecks.put("Pop", new QuestionDeck());
      questionDecks.put("Science", new QuestionDeck());
      questionDecks.put("Sports", new QuestionDeck());
      questionDecks.put("Rock", new QuestionDeck());

      for (int i = 0; i < 50; i++) {
         questionDecks.get("Pop").addQuestion(new Question("Pop", "Pop Question " + i));
         questionDecks.get("Science").addQuestion(new Question("Science", "Science Question " + i));
         questionDecks.get("Sports").addQuestion(new Question("Sports", "Sports Question " + i));
         questionDecks.get("Rock").addQuestion(new Question("Rock", "Rock Question " + i));
      }
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));
      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      Player currentPlayer = players.get(this.currentPlayer);
      System.out.println(currentPlayer.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer.isInPenaltyBox()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
            movePlayer(currentPlayer,roll);
            System.out.println("The category is " + currentCategory());
            askQuestion();
         } else {
            System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {
         movePlayer(currentPlayer, roll);
         System.out.println("The category is " + currentCategory());
         askQuestion();
      }

   }

   private void movePlayer(Player currentPlayer, int roll) {
        int newPlace = currentPlayer.getPlace() + roll;
        if (newPlace > 12) newPlace = newPlace - 12;
        currentPlayer.setPlace(newPlace);
        System.out.println(currentPlayer.getName() + "'s new location is " + newPlace);
   }

   private void askQuestion() {
      String category = currentCategory();
        System.out.println(questionDecks.get(category).drawQuestion().getText());
   }


   private String currentCategory() {
      switch (players.get(currentPlayer).getPlace()) {
         case 1:
         case 5:
         case 9:
            return "Pop";
         case 2:
         case 6:
         case 10:
            return "Science";
         case 3:
         case 7:
         case 11:
            return "Sports";
         default:
            return "Rock";
      }
   }

   public boolean handleCorrectAnswer() {
      Player currentPlayer = players.get(this.currentPlayer);
      if (currentPlayer.isInPenaltyBox()) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was corrent!!!!");
            currentPlayer.addGoldCoin();
            System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getPurse() + " Gold Coins.");
            boolean winner = didPlayerWin();
            nextPlayer();
            return winner;
         } else {
            nextPlayer();
            return true;
         }
      } else {
         System.out.println("Answer was corrent!!!!");
         currentPlayer.addGoldCoin();
         System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getPurse() + " Gold Coins.");
         boolean winner = didPlayerWin();
         nextPlayer();
         return winner;
      }
   }

   private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
   }

   public boolean wrongAnswer() {
      Player currentPlayer = players.get(this.currentPlayer);
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer.getName() + " was sent to the penalty box");
      currentPlayer.setInPenaltyBox(true);
      nextPlayer();
      return true;
   }


   private boolean didPlayerWin() {
      return players.get(currentPlayer).getPurse() != 6;
   }
}
