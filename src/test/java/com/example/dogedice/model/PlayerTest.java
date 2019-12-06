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
}