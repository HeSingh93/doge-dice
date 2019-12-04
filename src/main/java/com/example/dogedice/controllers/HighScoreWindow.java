package com.example.dogedice.controllers;

import com.example.dogedice.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class HighScoreWindow extends GenericController {

  @FXML
  Label firstPlace, secondPlace, thirdPlace;

  @Override
  public void postInitialization () {
    List<Player> highScore = gameEngine.getHighScore();
    firstPlace.setText(highScore.get(0).getName());
    secondPlace.setText(highScore.get(1).getName());
    thirdPlace.setText(highScore.get(2).getName());
  }

  public void spinningDogeClicked(MouseEvent mouseEvent)  {
    HelperMethods.spinningDogeClicked(mouseEvent);
  }

  public void backIconClicked(MouseEvent mouseEvent) throws IOException {
    HelperMethods.replaceScene(
        HelperMethods.mainWindowFXML,
        HelperMethods.mainWindowTitle,
        mouseEvent,
        this
    );
  }
}
