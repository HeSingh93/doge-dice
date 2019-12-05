package com.example.dogedice.model;

public class DieStub extends Die {


  public DieStub(int numOfSides) {
    super(numOfSides);

  }

  @Override
  public int roll() {
    return super.roll();
  }

  @Override
  public int getNumOfSides() {
    return super.getNumOfSides();
  }
}
