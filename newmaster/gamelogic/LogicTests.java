package gamelogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import playertype.Player;

class LogicTests {

  
  @Test
  public void checkEveryHorizontal() {
    ConnectLogic logic = new ConnectLogic();
    logic.setNumPlayers(4);
    logic.updateGridSize();
    logic.startGame();
    
    boolean result = true;
    for(int x = 0; x < logic.getX() - 3; x++) {
      for(int y = 0; y < logic.getY(); y++) {
        logic.setSpace(x, y, 1);
        logic.setSpace(x+1, y, 1);
        logic.setSpace(x+2, y, 1);
        logic.setSpace(x+3, y, 1);
        if(logic.checkWin() != 1) {
          fail();
        }
        logic.startGame();
      }
    }
    
    assertEquals(result,true);
  }
  
  
  @Test
  public void checkEveryVertical() {
    ConnectLogic logic = new ConnectLogic();
    logic.setNumPlayers(1);
    logic.startGame();
    
    boolean result = true;
    for(int x = 0; x < logic.getX(); x++) {
      for(int y = 0; y < logic.getY() - 3; y++) {
        logic.setSpace(x, y, 1);
        logic.setSpace(x, y+1, 1);
        logic.setSpace(x, y+2, 1);
        logic.setSpace(x, y+3, 1);
        if(logic.checkWin() != 1) {
          fail();
        }
        logic.startGame();
      }
    }
    
    assertEquals(result,true);
  }
  
  
  @Test
  public void checkEveryDiagUp() {
    ConnectLogic logic = new ConnectLogic();
    logic.setNumPlayers(1);
    logic.startGame();
    
    boolean result = true;
    for(int x = 0; x < logic.getX() - 3; x++) {
      for(int y = 0; y < logic.getY() - 3; y++) {
        logic.setSpace(x, y+3, 1);
        logic.setSpace(x+1, y+2, 1);
        logic.setSpace(x+2, y+1, 1);
        logic.setSpace(x+3, y, 1);
        if(logic.checkWin() != 1) {
          fail();
        }
        logic.startGame();
      }
    }
    
    assertEquals(result,true);
  }
  
  
  @Test
  public void checkEveryDiagDown() {
    ConnectLogic logic = new ConnectLogic();
    logic.setNumPlayers(1);
    logic.startGame();
    
    boolean result = true;
    for(int x = 0; x < logic.getX() - 3; x++) {
      for(int y = 0; y < logic.getY() - 3; y++) {
        logic.setSpace(x, y, 1);
        logic.setSpace(x+1, y+1, 1);
        logic.setSpace(x+2, y+2, 1);
        logic.setSpace(x+3, y+3, 1);
        if(logic.checkWin() != 1) {
          fail();
        }
        logic.startGame();
      }
    }
    
    assertEquals(result,true);
  }

  @Test
  public void checkWinFullBoard() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.setNumPlayers(1);
    for (int i = 0;i < 6;i++) {
      for (int j = 0;j < 7;j++) {
        logic.setSpace(j, i, 1);
      }
    }

    assertEquals(logic.checkWin(),1);

  }


  @Test
  public void checkWinNoWin() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.setNumPlayers(1); 
    logic.setSpace(0, 0, 1);
    logic.setSpace(1, 0, 1);
    logic.setSpace(2, 0, 1);
    assertEquals(logic.checkWin(),0);
  }

  @Test
  public void setSpace() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.setSpace(4, 5, 2);
    assertEquals(logic.getGrid()[4][5],2);
  }

  @Test
  public void invalidOver() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    assertEquals(logic.isValid(8),false);
  }

  @Test
  public void invalidUnder() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    assertEquals(logic.isValid(-1),false);
  }

  @Test
  public void isValid() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    assertEquals(logic.isValid(6),true);
  }

  @Test
  public void addPlayer() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.addPlayer(new Player("d", 1, "lenny"));
    assertEquals(logic.getPlayerlist()[0].getName(),"lenny");
  }


  @Test
  public void addPlayerTooMany() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();

    //add 5 players
    logic.addPlayer(new Player("d", 1, "tester1"));
    logic.addPlayer(new Player());
    logic.addPlayer(new Player("d", 3, "tester3"));
    logic.addPlayer(new Player("d", 4, "tester4"));
    logic.addPlayer(new Player("d", 5, "tester5"));

    assertEquals(logic.getPlayerlist().length,4);
  }

  @Test
  public void drop() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.addPlayer(new Player());
    logic.nextTurn(1);
    logic.drop(2);
    logic.nextTurn(2);
    logic.drop(2);
    assertEquals(logic.getGrid()[2][5],1);
  }

  @Test 
  public void nextTurnFullBoard() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.addPlayer(new Player("d", 2, "tester"));
    for (int i = 0;i < 6;i++) {
      for (int j = 0;j < 7;j++) {
        logic.setSpace(j, i, 1);
      }
    }
    logic.nextTurn(2);
    assertEquals(1, 1);
  }

  @Test
  public void getCurrentPlayer() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.addPlayer(new Player("d", 1, "tester"));
    logic.nextTurn(1);
    assertEquals(logic.getCurrentPlayer().getName(),"tester");
    
  }
  

  @Test
  public void getCurrentPlayerNone() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.nextTurn(2);
    assertEquals(logic.getCurrentPlayer().getName(), "DEFAULT");
  }
 

  public static void printBoard(ConnectLogic logic) {             
    System.out.println("--------------");
    for (int i = 0;i < logic.getY();i++) {
      for (int j = 0;j < logic.getX();j++) {
        System.out.print(logic.getGrid()[j][i] + " ");
      }
      System.out.println();
    }

    System.out.println("--------------");

  }

}
