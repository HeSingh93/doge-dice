package com.example.dogedice.controllers;

import com.example.dogedice.model.Player;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javafx.scene.control.Label;

public class WinnerWindow extends GenericController {
   @FXML
   Label winnerLabel;

   @Override
  public void postInitialization() {
     gameEngine.updateHighScore();
     List<Player> players = gameEngine.getPlayers();
     Collections.sort(players);
     Player player =  players.get(0);
     winnerLabel.setText(player.getName());
   }

  public void menuButtonClicked(MouseEvent mouseEvent) throws IOException {
    gameEngine.resetRounds();
    gameEngine.resetPlayers();
    HelperMethods.replaceScene(
        HelperMethods.mainWindowFXML,
        HelperMethods.mainWindowTitle,
        this
    );
  }

  public void replayButtonClicked(MouseEvent mouseEvent) throws IOException {
    gameEngine.resetRounds();
    gameEngine.resetScores();
    HelperMethods.replaceScene(
        HelperMethods.playWindowFXML,
        HelperMethods.playWindowTitle,
        this
    );
  }

  public void highscoreButtonClicked(MouseEvent mouseEvent) throws IOException {
    gameEngine.resetRounds();
    gameEngine.resetPlayers();
    HelperMethods.replaceScene(
        HelperMethods.highscoreWindowFXML,
        HelperMethods.highscoreWindowTitle,
        this
    );
  }

  public void spinningDogeClicked(MouseEvent mouseEvent)  {
    HelperMethods.spinningDogeClicked(mouseEvent);
  }
}
