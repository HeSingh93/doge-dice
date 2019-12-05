package com.example.dogedice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  @BeforeEach
  void setUp() {
    Player player = new Player("Carl");
  }

  @Test
  void canPlayerRollDies(Player player){

    int test = player.rollAllDice();

    assertEquals(test, 21);
  }
}