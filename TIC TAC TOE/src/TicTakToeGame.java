import controller.GameController;
import models.*;
import strategies.BotPlayingStrategy.BotPlayingStrategyFactory;

import java.util.*;

public class TicTakToeGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("Please enter the dimension of the game");
        int dimension = sc.nextInt();

        System.out.println("Will there be any bot in the game ? : Y/N");
        String isBotPresent = sc.next();

        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension -1;

        if(isBotPresent.equals("Y")) {
            iteratorNumber = dimension - 2;
        }

        for(int i=0; i<iteratorNumber; i++){
            // TODO: validate unique symbol
            System.out.println("What is the name of the player number : " + (i+1));
            String playerName = sc.next();

            System.out.println("What is the character symbol of the player number : " + (i+1));
            String characterSymbol=sc.next();

            players.add(new Player(playerName, new Symbol(characterSymbol.charAt(0)), PlayerType.HUMAN));
        }

        if(isBotPresent.equals("Y")){
            System.out.println("What is the name of the BOT");
            String playerName = sc.next();

            System.out.println("What is the character symbol of the BOT");
            String characterSymbol=sc.next();

            // TODO : Take use input for bot difficulty level and create the object accordingly

            Bot bot = new Bot(playerName,
                    new Symbol(characterSymbol.charAt(0)),
                    BotDifficultyLevel.EASY,
                    BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel.EASY));
            players.add(bot);
        }

        // randomizes the players in the list
        Collections.shuffle(players);

        Game game = gameController.createGame(dimension, players);
        int playerIndex = 0;

        while (game.getGameState().equals(GameState.IN_PROGRESS)){
            System.out.println("Current board status");
            gameController.displayBoard(game);
            System.out.println("It's " + players.get(playerIndex).getName() + "'s turn!!");
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
            Player winner = gameController.checkWinner(game, movePlayed);
            // TODO : undo
            // TODO : write logic for giving each player option to play
            if(winner != null){
                gameController.displayBoard(game);
                System.out.println("Game has ended, result was : ");
                System.out.println("Winner is : " + winner.getName());
                break;
            }
            playerIndex++;
            playerIndex = playerIndex % players.size();
        }


    }
}
