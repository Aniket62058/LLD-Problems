package models;

import exceptions.InvalidMoveException;

import java.util.Scanner;

public class Player {
    private static int idCounter=0;
    private int id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.id = idCounter++;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Move makeMove(Board board){
        System.out.println(this.getName() + ", Please enter the row for the move");
        int row = scanner.nextInt();
        System.out.println(this.getName() + ", Please enter the column for the move");
        int col = scanner.nextInt();

        //TODO: Validate the move and throw exception or MSG
        if(row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize() ){
            throw new InvalidMoveException("Row and col for the should be between more than 0 and less then board's size");
        }

        board.getBoard().get(row).get(col).setPlayer(this);
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        return new Move(new Cell(row, col, this), this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
