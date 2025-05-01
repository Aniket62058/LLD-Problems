package strategies.BotPlayingStrategy;

import models.*;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move makeMove(Player player, Board board) {
        int boardSize = board.getSize();
        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    board.getBoard().get(i).get(j).setPlayer(player);
                    board.getBoard().get(i).get(j).setCellState(CellState.FILLED);
                    return new Move(new Cell(i, j), player);
                }
            }
        }

        return null;
    }
}
