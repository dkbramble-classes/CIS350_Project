package playertype;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gamelogic.ConnectLogic;

class PlayerTests {

  @Test
  void testDefaultPlayer() {
    Player player = new Player();
    assertEquals(player.getName(),"DEFAULT");
  }
  
  @Test
  void testCustomPlayer() {
    Player player = new Player("green",1,"Vinny");
    assertEquals(player.getName(),"Vinny");
  }
  
  @Test
  void testCpuPlayer() {
    Player player = new Player("blue",1,"RobotOverlord","m",true);
    assertEquals(player.getCompStatus(),true);
  }
  
  @Test
  void humanPlayerTurnTest() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    Player human = new Player("yellow",1,"Lenny");
    human.playerTurn(logic, 1);
  }
  
  @Test
  void rebelliousHumanTest() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    Player player = new Player();
    assertEquals(player.playerTurn(logic, 420),-1);
  }
  
  @Test
  void inBoundsEasy() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    Player player = new Player("green",1,"cpu","e",true); 
    int column = player.playerTurn(logic, 0);
    assertEquals(column <= logic.getX() && column >= 0, true);
  }
  
  @Test
  void inBoundsMedium() {
    ConnectLogic logic = new ConnectLogic();
    logic.startGame();
    Player player = new Player("green",1,"cpu","m",true); 
    int column = player.playerTurn(logic, 0);
    assertEquals(column <= logic.getX() && column >= 0, true);
  }
  
  

}
