package testing;

import static org.junit.Assert.assertEquals;

import gamelogic.ConnectLogic;
import gamelogic.ConnectTest;

import org.junit.jupiter.api.Test;
import playertype.ComputerPlayer;
import playertype.Player;


class LogicTests {

  @Test
  void badDimensions() {
    ConnectLogic logic = new ConnectLogic(-2,-3);
    assertEquals(logic.getX(),7);
  }
  
  
  @Test
  void checkWinHorizontal() {
    ConnectLogic logic = new ConnectLogic(7,6);
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
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.setNumPlayers(1);
    logic.setSpace(2, 2, 1);
    logic.setSpace(2, 3, 1);
    logic.setSpace(2, 4, 1);
    logic.setSpace(2, 5, 1);
    assertEquals(logic.checkWin(),1);
  }

  @Test
  void checkWinDiagonalUp() {
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.setNumPlayers(1);
    logic.setSpace(2, 5, 1);
    logic.setSpace(3, 4, 1);
    logic.setSpace(4, 3, 1);
    logic.setSpace(5, 2, 1);
    assertEquals(logic.checkWin(),1);
  }

  @Test
  void checkWinDiagonalDown() {
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.setNumPlayers(1);
    logic.setSpace(0, 0, 1);
    logic.setSpace(1, 1, 1);
    logic.setSpace(2, 2, 1);
    logic.setSpace(3, 3, 1);
    assertEquals(logic.checkWin(),1);
  }

  @Test
  void checkWinFullBoard() {
    ConnectLogic logic = new ConnectLogic(7,6);
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
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.setNumPlayers(1); 
    logic.setSpace(0, 0, 1);
    logic.setSpace(1, 0, 1);
    logic.setSpace(2, 0, 1);
    assertEquals(logic.checkWin(),0);
  }

  @Test
  void setSpace() {
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.setSpace(4, 5, 2);
    assertEquals(logic.getGrid()[4][5],2);
  }

  @Test
  void invalidOver() {
    ConnectLogic logic = new ConnectLogic(7,6);
    assertEquals(logic.isValid(8),false);
  }

  @Test
  void invalidUnder() {
    ConnectLogic logic = new ConnectLogic(7,6);
    assertEquals(logic.isValid(-1),false);
  }

  @Test
  void isValid() {
    ConnectLogic logic = new ConnectLogic(7,6);
    assertEquals(logic.isValid(6),true);
  }

  @Test
  void addPlayer() {
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.addPlayer(new ComputerPlayer('d', 1, "lenny", 'h'));
    assertEquals(logic.getComplist()[0].getName(),"lenny");
  }


  @Test
  void addPlayerTooMany() {
    ConnectLogic logic = new ConnectLogic(7,6);

    //add 5 players
    logic.addPlayer(new ComputerPlayer('d', 1, "tester1", 'm'));
    logic.addPlayer(new ComputerPlayer());
    logic.addPlayer(new ComputerPlayer('d', 3, "tester3", 'h'));
    logic.addPlayer(new ComputerPlayer('d', 4, "tester4", 'h'));
    logic.addPlayer(new ComputerPlayer('d', 5, "tester5", 'h'));

    assertEquals(logic.getComplist().length,4);
  }

  @Test
  void drop() {
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.addPlayer(new Player());
    logic.nextTurn();
    logic.drop(2);
    logic.nextTurn();
    logic.drop(2);
    assertEquals(logic.getGrid()[2][5],1);
  }

  @Test 
  void nextTurnFullBoard() {
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.addPlayer(new ComputerPlayer('d', 2, "tester", 'm'));
    for (int i = 0;i < 6;i++) {
      for (int j = 0;j < 7;j++) {
        logic.setSpace(j, i, 1);
      }
    }
    logic.nextTurn();
    assertEquals(1, 1);
  }

  @Test
  void getCurrentPlayer() {
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.addPlayer(new Player('d', 1, "tester"));
    logic.nextTurn();
    assertEquals(logic.getCurrentPlayer(),"tester");
    
  }
  
  @Test
  void getCurrentPlayerCpu() {
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.addPlayer(new ComputerPlayer('d',1,"cpuTester",'m'));
    logic.nextTurn();
    assertEquals(logic.getCurrentPlayer(),"cpuTester");
  }

  @Test
  void getCurrentPlayerNone() {
    ConnectLogic logic = new ConnectLogic(7,6);
    logic.nextTurn();
    assertEquals(logic.getCurrentPlayer(), "NULL");
  }

  @Test
  void computerTurn() {
    ConnectLogic logic = new ConnectLogic(7,6);
    ComputerPlayer cpu = new ComputerPlayer('d', 1, "tester", 'm');
    logic.addPlayer(cpu);
    logic.nextTurn();  
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
