package com.example.dogedice.model;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
  }

  @Test
  void canBuyD20_ReturnsFalse_WhenActivePlayerHasFourPointsAndCostIsFive() {
  }

  @Test
  void canBuyModifier_ReturnsFalse_WhenActivePlayerHasFourPointsAndCostIsFive() {
  }

  @Test
  void canBuyD6_ReturnsTrue_WhenActivePlayerHasFivePointsAndCostIsFive() {
  }

  @Test
  void canBuyD20_ReturnsTrue_WhenActivePlayerHasFivePointsAndCostIsFive() {
  }

  @Test
  void canBuyModifierOnce_ReturnsTrue_WhenActivePlayerHasFivePointsAndCostIsFive() {
  }

  @Test
  void rollDice_ReturnsFive_WhenActivePlayerHasFiveOneSidedDice() {
  }

  @Test
  void sumModifiers_ReturnTen_WhenActivePlayerHasTwoDiceAndFiveLevelTwoModifiers() {
  }

  @Test
  void resetPlayer_getPlayersReturnsZeroSizeList_AfterResettingPlayersWhileHavingNonZeroPlayers() {
  }

  @Test
  void resetRounds_ResetsRoundsToOriginalValue_AfterRoundsHasBeenDecremented() {
  }

  @Test
  void resetScores_PlayersHaveZeroScore_AfterResettingWithTwoPlayersHavingNonZeroScores() {
  }

  @Test
  void resetScores_PlayersHaveOnlyStarterDiceAndModifiers_AfterResettingWithTwoPlayersAndExtraDiceAndModifiers() {
  }

  // TODO
  // buyD6
  // buyD20
  // buyModifier
  // toString
}