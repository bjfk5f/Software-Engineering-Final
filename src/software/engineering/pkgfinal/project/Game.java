/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.pkgfinal.project;

import javafx.concurrent.Task;
import javafx.concurrent.Worker;
/**
 *
 * @author jebro
 */
public class Game implements TilePress{
    private Player playerOne;
    private Player playerTwo;
    
    private Board board;
    
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;
    private int turn = PLAYER_TWO;
    
    public static final String COMPUTER_NAME = "computer";
    
    public Game(Player playerOne, Player playerTwo){
        
    }
    
    public Game(String nameOfPlayerOne, String nameOfPlayerTwo){
        playerOne = createPlayerFromString(nameOfPlayerOne, true);
        playerTwo = createPlayerFromString(nameOfPlayerTwo, false);
    }
    
    private Player createPlayerFromString(String name, boolean top){

        if(name == null || name.equals(COMPUTER_NAME)){
            return new ComputerPlayer(board, top);
        }
        
        return new HumanPlayer(board, top, name);
    }
    
    public void start(){
        board = new Board(this, playerOne, playerTwo);
        playerOne.setBoard(board);
        playerTwo.setBoard((board));
        changeTurns();
    }
    
    private void changeTurns()
    {
        Player nextPlayer;
        if(turn == PLAYER_ONE){
            turn = PLAYER_TWO;
            nextPlayer = playerTwo;
        }else{
            turn = PLAYER_ONE;
            nextPlayer = playerOne;
        }
        
       /* Task<Boolean> startTurn = new Task<Boolean>(){
            @Override
            protected Boolean call() throws Exception {*/
                if(nextPlayer.startTurn()){
                    changeTurns();
                }
                /*return true;
            }
        };
      
        Thread thread = new Thread(startTurn);
        thread.setDaemon(true);
        thread.start();*/
    }
        

    @Override
    public void onTilePressed(int row, int column) {
        if(turn == PLAYER_ONE){
            if(playerOne.handleTilePress(row, column))
               changeTurns();
        } else {
            if(playerTwo.handleTilePress(row, column))
                changeTurns();
        }
    }
    
    
}
