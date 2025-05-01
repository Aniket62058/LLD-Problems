package strategies.WinningStrategy;

import exceptions.GameDrawnException;
import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{
    private int symbolsAdded;
    private int dimension;
    private List<HashMap<Character, Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCount = new ArrayList<>();
    HashMap<Character, Integer> topLeftDiagonalSymbolCount = new HashMap<>();
    HashMap<Character, Integer> bottomLeftDiagonalSymbolCount = new HashMap<>();
    HashMap<Character, Integer> cornersSymbolCount = new HashMap<>();

    public boolean isCellTopLeftDiagonal(int row, int col){
        return row == col;
    }

    public boolean isCellBottomLeftDiagonal(int row, int col){
        return (row + col) == dimension-1;
    }

    public boolean isCornerCell(int row, int col){
        if(row == 0 || row == dimension-1){
            return col == 0 || col == dimension - 1;
        }
        return false;
    }

    public OrderOneWinningStrategy(int dimension){
        this.symbolsAdded=0;
        this.dimension = dimension;
        for(int i=0; i<dimension; i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }


    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;
        Player lastMovePlayer = lastMove.getPlayer();
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        if(checkForRowWins(row, col, symbol))
            return lastMovePlayer;
        else if (checkForColumnWins(row, col, symbol))
            return lastMovePlayer;
        else if (checkForDiagonalWins(row, col, symbol))
            return lastMovePlayer;
        else if (checkForCornerWins(row, col, symbol))
            return lastMovePlayer;

        if(symbolsAdded == (dimension*dimension)){
            board.printBoard();
//            throw new GameDrawnException("Game is drawn as cells are cells are full");
        }
        return null;
    }

    private boolean checkForDiagonalWins(int row, int col, char symbol){
        if(isCellTopLeftDiagonal(row, col)){
            if(!topLeftDiagonalSymbolCount.containsKey(symbol)){
                topLeftDiagonalSymbolCount.put(symbol, 0);
            }
            topLeftDiagonalSymbolCount.put(
                    symbol,
                    topLeftDiagonalSymbolCount.get(symbol) + 1
            );
            // Winning by same symbol across top left diagonal
            if(topLeftDiagonalSymbolCount.get(symbol) == dimension)
                return true;
        }

        if(isCellBottomLeftDiagonal(row, col)){
            if(!bottomLeftDiagonalSymbolCount.containsKey(symbol)){
                bottomLeftDiagonalSymbolCount.put(symbol, 0);
            }
            bottomLeftDiagonalSymbolCount.put(
                    symbol,
                    bottomLeftDiagonalSymbolCount.get(symbol) + 1
            );
            // Winning by same symbol across bottom left diagonal
            return bottomLeftDiagonalSymbolCount.get(symbol) == dimension;
        }
        return false;
    }

    private boolean checkForCornerWins(int row, int col, char symbol){
        if(isCornerCell(row, col)){
            if(!cornersSymbolCount.containsKey(symbol)){
                cornersSymbolCount.put(symbol, 0);
            }
            cornersSymbolCount.put(
                    symbol,
                    cornersSymbolCount.get(symbol) + 1
            );
            // Winning by same symbol across all corners
            return cornersSymbolCount.get(symbol) == dimension;
        }
        return false;
    }

    private boolean checkForRowWins(int row, int col, char symbol){
        if(!rowSymbolCount.get(row).containsKey(symbol)){
            rowSymbolCount.get(row).put(symbol, 0);
        }
        rowSymbolCount.get(row).put(
                symbol,
                rowSymbolCount.get(row).get(symbol) + 1
        );
        // Winning by same symbol across a column
        return rowSymbolCount.get(row).get(symbol) == dimension;
    }

    private boolean checkForColumnWins(int row, int col, char symbol){
        if(!colSymbolCount.get(col).containsKey(symbol)){
            colSymbolCount.get(col).put(symbol, 0);
        }
        colSymbolCount.get(col).put(
                symbol,
                colSymbolCount.get(col).get(symbol) + 1
        );
        // Winning by same symbol across a column
        return colSymbolCount.get(col).get(symbol) == dimension;
    }
}
