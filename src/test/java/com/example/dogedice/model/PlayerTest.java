package com.example.dogedice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  Player player;

  @BeforeEach
  void setUp() {
    player = new Player("Carl");
  }

  @Test
  void addModifier_ModifierListHasSizeOne_AfterAddingOneModifier() {
    player.addModifier(new Modifier(1));
    assertEquals(1, player.getModifiers().size());
  }

  @Test
  void removePoints_returnsFalse_IfPlayerHasInsufficientPoints() {
    boolean remove = player.removePoints(1);
    assertFalse(remove, "Failed to remove Points");
  }

  @Test
  void addDie_DiceListHasSizeTen_AfterAddingTenDice() {
    for (int i = 1; i <= 10; i++) {
      player.addDie(new Die(6));
    }
    assertEquals(10, player.getDice().size());
  }

  @Test
  void addDie_DiceListHasTwentySidedDie_AfterAddingTwentySidedDie() {
    player.addDie(new Die(20));
    assertEquals(20, player.getDice().get(0).getNumOfSides());
  }

  @Test
  void rollAllDice_SuccesfullyRollAllDice_AfterAddingModifier() {
    player.addModifier(new Modifier(2));
    player.addDie(new DieStub());

    player.rollAllDice();
    player.sumAllModifiers();

    assertEquals(8, player.getScore());
  }

  @Test
  void rollAllDice_RollAllDiceAndRemoveScore_AfterAddingModifier() {
    player.addModifier(new Modifier(2));
    player.addDie(new DieStub());

    player.rollAllDice();
    player.sumAllModifiers();
    player.removePoints(8);

    assertEquals(0, player.getScore());
  }

  @Test
  void sumAllModifiers_ModifierListHasTwo_AfterSumAllModifiers() {
    player.addModifier(new Modifier(2));
    player.addDie(new Die(6));

    assertEquals(2, player.sumAllModifiers());
  }

  @Test
  void resetScore_ResetScore_AfterRollAllDice() {
    player.addModifier(new Modifier(2));
    player.addDie(new DieStub());

    player.rollAllDice();
    player.getScore();
    player.resetScore();

    assertEquals(0, player.getScore());
  }

  @Test
  void compareTo_comparePlayers_afterComparingPlayerScore() {
    PlayerStub testPlayer = new PlayerStub();
    PlayerStub testPlayer2 = new PlayerStub();

    testPlayer.addDie(new DieStub());
    testPlayer.addModifier(new Modifier(2));
    testPlayer.rollAllDice();
    testPlayer.sumAllModifiers();
    System.out.println("player 1 score: " + testPlayer);

    testPlayer2.addDie(new DieStub());
    testPlayer2.addModifier(new Modifier(3));
    testPlayer2.rollAllDice();
    testPlayer2.sumAllModifiers();
    System.out.println("player 2 score: " + testPlayer2);

    assertEquals(1, testPlayer2.compareTo(testPlayer));
  }
}