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
  void addModifier_AddValueOfOne_PlayerAddModifier() {
    player.addModifier(new Modifier(1));
    assertEquals(1, player.getModifiers().size());
  }
}