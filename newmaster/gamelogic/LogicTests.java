package gamelogic;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import playertype.Player;

class LogicTests {

  @Test
  void badDimensions() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    assertEquals(logic.getX(),7);
  }
  
  
  @Test
  void checkWinHorizontal() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.setNumPlayers(1);
    logic.setSpace(2, 2, 1);
    logic.setSpace(3, 2, 1);
    logic.setSpace(4, 2, 1);
    logic.setSpace(5, 2, 1);
    printBoard(logic);
    assertEquals(logic.checkWin(),1);
  }

  @Test
  void checkWinVertical() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.setNumPlayers(1);
    logic.setSpace(2, 2, 1);
    logic.setSpace(2, 3, 1);
    logic.setSpace(2, 4, 1);
    logic.setSpace(2, 5, 1);
    assertEquals(logic.checkWin(),1);
  }

  @Test
  void checkWinDiagonalUp() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.setNumPlayers(1);
    logic.setSpace(2, 5, 1);
    logic.setSpace(3, 4, 1);
    logic.setSpace(4, 3, 1);
    logic.setSpace(5, 2, 1);
    assertEquals(logic.checkWin(),1);
  }

  @Test
  void checkWinDiagonalDown() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.setNumPlayers(1);
    logic.setSpace(0, 0, 1);
    logic.setSpace(1, 1, 1);
    logic.setSpace(2, 2, 1);
    logic.setSpace(3, 3, 1);
    assertEquals(logic.checkWin(),1);
  }

  @Test
  void checkWinFullBoard() {
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
  void checkWinNoWin() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.setNumPlayers(1); 
    logic.setSpace(0, 0, 1);
    logic.setSpace(1, 0, 1);
    logic.setSpace(2, 0, 1);
    assertEquals(logic.checkWin(),0);
  }

  @Test
  void setSpace() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.setSpace(4, 5, 2);
    assertEquals(logic.getGrid()[4][5],2);
  }

  @Test
  void invalidOver() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    assertEquals(logic.isValid(8),false);
  }

  @Test
  void invalidUnder() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    assertEquals(logic.isValid(-1),false);
  }

  @Test
  void isValid() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    assertEquals(logic.isValid(6),true);
  }

  @Test
  void addPlayer() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.addPlayer(new Player("d", 1, "lenny"));
    assertEquals(logic.getPlayerlist()[0].getName(),"lenny");
  }


  @Test
  void addPlayerTooMany() {
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
  void drop() {
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
  void nextTurnFullBoard() {
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
  void getCurrentPlayer() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    logic.addPlayer(new Player("d", 1, "tester"));
    logic.nextTurn(1);
    assertEquals(logic.getCurrentPlayer().getName(),"tester");
    
  }
  

  @Test
  void getCurrentPlayerNone() {
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
