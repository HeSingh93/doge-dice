package com.example.dogedice.model;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
  private Logger logger = LoggerFactory.getLogger(GameEngineTest.class);

  @Test
  void Constructor_InitiatesPlayersWithNoDice_IfGivenEmptyArrayAsStartingDice() {
    GameEngine gameEngine = new GameEngine(
        1,
        1,
        1,
        1,
        new int[0],
        new int[0]);
    gameEngine.addCpuPlayer("");
    List<Die> dice = gameEngine.getPlayer().getDice();
    assertEquals(dice.size(), 0);
  }

  @Test
  void Constructor_InitiatesPlayersWithNoModifiers_IfGivenEmptyArrayAsStartingModifiers() {
    GameEngine gameEngine = new GameEngine(
        1,
        1,
        1,
        1,
        new int[0],
        new int[0]);
    gameEngine.addCpuPlayer("");
    List<Modifier> modifiers = gameEngine.getPlayer().getModifiers();
    assertEquals(modifiers.size(), 0);
  }

  @Test
  void Constructor_InitiatesPlayersCorrectDice_GivenArrayWithSixAndTwenty() {
    GameEngine gameEngine = new GameEngine(
        1,
        1,
        1,
        1,
        new int[]{6, 20},
        new int[0]);
    gameEngine.addHumanPlayer("");
    List<Die> dice = gameEngine.getPlayer().getDice();
    assertEquals(dice.size(), 2);
    assertEquals(dice.get(0).getNumOfSides(), 6);
    assertEquals(dice.get(1).getNumOfSides(), 20);
  }

  @Test
  void Constructor_InitiatesPlayersCorrectModifiers_GivenArrayWithSixAndTwenty() {
    GameEngine gameEngine = new GameEngine(
        1,
        1,
        1,
        1,
        new int[0],
        new int[]{6,20});
    gameEngine.addHumanPlayer("");
    List<Modifier> modifiers = gameEngine.getPlayer().getModifiers();
    assertEquals(modifiers.size(), 2);
    assertEquals(modifiers.get(0).getValue(), 6);
    assertEquals(modifiers.get(1).getValue(), 20);
  }

  @Test
  void getPlayers_PlayerListHasCorrectSize_BeforeAndAfterAddingPlayers() {
    GameEngine gameEngine = new GameEngine(
        1,
        1,
        1,
        1,
        new int[0],
        new int[0]);
    assertEquals(Integer.valueOf(0), gameEngine.getPlayers().size());

    gameEngine.addHumanPlayer("");
    assertEquals(Integer.valueOf(1), gameEngine.getPlayers().size());

    gameEngine.addCpuPlayer("");
    assertEquals(Integer.valueOf(2), gameEngine.getPlayers().size());
  }

  @Test
  void getD6Price_ReturnsThree_WhenD6PriceIsSetToThree() {
    GameEngine gameEngine = new GameEngine(
        1,
        3,
        1,
        1,
        new int[0],
        new int[0]);
    assertEquals(3, gameEngine.getD6Price());
    assertEquals("Cost: 3", gameEngine.getD6PriceAsString());
  }

  @Test
  void getD20Price_ReturnsThree_WhenD20PriceIsSetToThree() {
    GameEngine gameEngine = new GameEngine(
        1,
        1,
        3,
        1,
        new int[0],
        new int[0]);
    assertEquals(3, gameEngine.getD20Price());
    assertEquals("Cost: 3", gameEngine.getD20PriceAsString());
  }

  @Test
  void getModifierPrice_ReturnsThree_WhenModifierPriceIsSetToThree() {
    GameEngine gameEngine = new GameEngine(
        1,
        1,
        1,
        3,
        new int[0],
        new int[0]);
    assertEquals(3, gameEngine.getModifierPrice());
    assertEquals("Cost: 3", gameEngine.getModifierPriceAsString());
  }

  @Test
  void getRoundsLeft_ReturnsCorrectRoundNumber_WhenHasOnePlayerAndTotalRoundsIsFiveAndIncrementPlayerCalledTwoTimes() {
    GameEngine gameEngine = new GameEngine(
        5,
        1,
        1,
        3,
        new int[0],
        new int[0]);
    gameEngine.addHumanPlayer("");
    assertEquals(5, gameEngine.getRoundsLeft());
    assertEquals("5", gameEngine.getRoundsLeftAsString());
    gameEngine.incrementPlayer();
    assertEquals(4, gameEngine.getRoundsLeft());
    assertEquals("4", gameEngine.getRoundsLeftAsString());
  }

  // TODO
  // TODO
  // TODO
  // TODO
  @Test
  void canBuyD6_ReturnsFalse_WhenActivePlayerHasFourPointsAndCostIsFive() {
    GameEngine gameEngine = new GameEngine(
        5,
        5,
        1,
        3,
        new int[]{1,1,1,1},
        new int[0]);
    gameEngine.addHumanPlayer("");
    gameEngine.rollDice();
    assertFalse(
        gameEngine.canBuyD6(),
        "D6 costs 5 points, should return false when player has insufficient points");
  }

  @Test
  void canBuyD20_ReturnsFalse_WhenActivePlayerHasFourPointsAndCostIsFive() {
    GameEngine gameEngine = new GameEngine(
        5,
        1,
        5,
        3,
        new int[]{1,1,1,1},
        new int[0]);
    gameEngine.addHumanPlayer("");
    gameEngine.rollDice();
    assertFalse(
        gameEngine.canBuyD20(),
        "D20 costs 5 points, should return false when player has insufficient points");
  }

  @Test
  void canBuyModifier_ReturnsFalse_WhenActivePlayerHasFourPointsAndCostIsFive() {
    GameEngine gameEngine = new GameEngine(
        5,
        1,
        1,
        5,
        new int[]{1,1,1,1},
        new int[0]);
    gameEngine.addHumanPlayer("");
    gameEngine.rollDice();
    assertFalse(
        gameEngine.canBuyModifier(),
        "Modifier costs 5 points, should return false when player has insufficient points");
  }

  @Test
  void canBuyD6_ReturnsTrue_WhenActivePlayerHasFivePointsAndCostIsFive() {
    GameEngine gameEngine = new GameEngine(
        5,
        5,
        8,
        3,
        new int[]{1,1,1,1,1},
        new int[0]);
    gameEngine.addHumanPlayer("");
    gameEngine.rollDice();
    assertTrue(
        gameEngine.canBuyD6(),
        "D6 costs 5 points, returns true when a player has 5 points"
    );
  }

  @Test
  void canBuyD20_ReturnsTrue_WhenActivePlayerHasFivePointsAndCostIsFive() {
    GameEngine gameEngine = new GameEngine(
        5,
        1,
        5,
        3,
        new int[]{1,1,1,1,1},
        new int[0]);
    gameEngine.addHumanPlayer("");
    gameEngine.rollDice();
    assertTrue(
        gameEngine.canBuyD20(),
        "D20 costs 5 points, returns true when a player has 5 points"
    );
  }

  @Test
  void canBuyModifierOnce_ReturnsTrue_WhenActivePlayerHasFivePointsAndCostIsFive() {
    GameEngine gameEngine = new GameEngine(
        5,
        1,
        1,
        5,
        new int[]{1,1,1,1,1},
        new int[0]);
    gameEngine.addHumanPlayer("");
    gameEngine.rollDice();
    assertTrue(
        gameEngine.canBuyModifier(),
        "Modifier costs 5 points, returns true when a player has 5 points"
    );
  }

  @Test
  void rollDice_ReturnsFive_WhenActivePlayerHasFiveOneSidedDice() {
    GameEngine gameEngine = new GameEngine(
        5,
        1,
        1,
        5,
        new int[]{1,1,1,1,1},
        new int[0]);
    gameEngine.addHumanPlayer("");
    assertEquals(
        5,
        gameEngine.rollDice()
    );
  }

  @Test
  void sumModifiers_ReturnTwenty_WhenActivePlayerHasTwoDiceAndFiveLevelTwoModifiers() {
    GameEngine gameEngine = new GameEngine(
        5,
        1,
        1,
        5,
        new int[]{1,1},
        new int[]{2,2,2,2,2});
        gameEngine.addHumanPlayer("");
        gameEngine.rollDice();

        assertEquals(
            20,
            gameEngine.sumModifiers()
        );
  }

  @Test
  void resetPlayer_getPlayersReturnsZeroSizeList_AfterResettingPlayersWhileHavingNonZeroPlayers() {
    GameEngine gameEngine = new GameEngine(
        5,
        1,
        1,
        5,
        new int[0],
        new int[0]);
    gameEngine.addHumanPlayer("A");
    gameEngine.addHumanPlayer("B");
    gameEngine.addCpuPlayer("RoboDoge#1");
    gameEngine.resetPlayers();
    assertEquals(
        0,
        gameEngine.getPlayers().size()
    );
  }

  @Test
  void resetRounds_ResetsRoundsToOriginalValue_AfterRoundsHasBeenDecremented() {
    GameEngine gameEngine = new GameEngine(
        30,
        1,
        1,
        5,
        new int[0],
        new int[0]);
    gameEngine.addHumanPlayer("A");
    gameEngine.incrementPlayer();
    gameEngine.incrementPlayer();
    gameEngine.incrementPlayer();
    gameEngine.resetRounds();
    assertEquals(
        30,
        gameEngine.getRoundsLeft()
    );
  }

  @Test
  void resetScores_PlayersHaveZeroScore_AfterResettingWithTwoPlayersHavingNonZeroScores() {
    GameEngine gameEngine = new GameEngine(
        30,
        1,
        1,
        5,
        new int[]{1,1,1,1},
        new int[]{2,3,2});
    gameEngine.addHumanPlayer("A");
    gameEngine.rollDice();
    gameEngine.incrementPlayer();
    gameEngine.resetScores();
    assertEquals(
        0,
        gameEngine.getScore()
    );
  }

  @Test
  void resetScores_PlayersHaveOnlyStarterDiceAndModifiers_AfterResettingWithTwoPlayersAndExtraDiceAndModifiers() {
    GameEngine gameEngine = new GameEngine(
        30,
        1,
        1,
        1,
        new int[]{1,1,1,1,1},
        new int[]{1});
    gameEngine.addHumanPlayer("1");
    gameEngine.addHumanPlayer("2");
    gameEngine.rollDice();
    gameEngine.incrementPlayer();
    gameEngine.rollDice();
    gameEngine.buyD6();
    gameEngine.buyD20();
    gameEngine.buyModifier();
    gameEngine.incrementPlayer();
    gameEngine.buyD6();
    gameEngine.buyD20();
    gameEngine.buyModifier();

    gameEngine.resetScores();

    assertEquals(5,
    gameEngine.getPlayer().getDice().size());

    assertEquals(1,
        gameEngine.getPlayer().getModifiers().size());
    //Player has 5 dice and one modifier to start and should be that when resetting.
  }

  // TODO
  @Test
  void buyD6_WhenPlayerBuysD6_RemovePointsReturnTrueAndAddDieToPlayer() {

  }

  @Test
  void buyD20_WhenPlayerBuysD20_RemovePointsReturnTrueAndAddDieToPlayer(){
    GameEngine gameEngine = new GameEngine(
        30,
        1,
        5,
        1,
        new int[]{1,1,1,1,1},
        new int[]{1});
    gameEngine.addHumanPlayer("");
    gameEngine.rollDice();
    //System.out.println("Players score before buying: " + gameEngine.getPlayer().getScore());
    //System.out.println("Players dice before buying: " + gameEngine.getPlayer().getDice().size());
    assertTrue(
        gameEngine.canBuyD20()
    );

    gameEngine.buyD20();
    //System.out.println("Players score after buying: " + gameEngine.getPlayer().getScore());
    //System.out.println("Players dice after buying: " + gameEngine.getPlayer().getDice().size());

    // Should be 6 when a player buys a new die since player is starting with 5 dice.
    assertEquals(6,
        gameEngine.getPlayer().getDice().size());

    // Returns false if points are removed from the player
    assertFalse(
        gameEngine.canBuyD20());

    // Score should be 0 when player has bought a die. (Points was 5 before buying die)
    assertEquals(0,
        gameEngine.getScore());

  }

  @Test
  void buyModifier_WhenPlayerBuysModifier_RemovePointsReturnTrueAndAddModifierToPlayer(){

  }
  // buyD6 subtrahera poäng, returnera true, lägga till tärning till spelare
  // buyD20 subtrahera poäng, returnera true, lägga till tärning till spelare
  // buyModifier subtrahera poäng, returnera true, lägga till tärning till spelare
  // toString
}