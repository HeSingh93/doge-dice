package com.example.dogedice.controllers;

import com.example.dogedice.model.GameEngine;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class NamePlayersWindowTest {
  private Logger logger = LoggerFactory.getLogger(NamePlayersWindow.class);
  private NamePlayersWindow controller;

  @BeforeEach
  void setUp() {
    controller = new NamePlayersWindow();
    controller.namePlayersBox = new VBox();
    controller.gameEngine = new GameEngine(30, 10, 15, 20, new int[]{6,6}, new int[]{});
  }
}