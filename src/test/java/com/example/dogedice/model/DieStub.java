package com.example.dogedice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DieStub extends Die{
  public DieStub() {
    super(6);
  }

  @Override
  public int roll() {
    return super.roll();
  }

  @Test
  void roll_doesDieReturnCorrectValueWhenRolling() {

    assertEquals(6, super.roll());

  }
}
