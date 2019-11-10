package com.example.dogedice.controllers;

import com.example.dogedice.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerSelectionWindow {
  @FXML
  Spinner<Integer> humPlayersSpinner, cpuPlayersSpinner;

  public void confirmButtonClicked(MouseEvent mouseEvent) throws IOException {
    Stage newWindow = new Stage();
    newWindow.setTitle("Name Players");
    FXMLLoader loader = Main.getLoader("fxml/namePlayersWindow.fxml");
    Parent root = loader.load();
    NamePlayersWindow controller = loader.getController();
    Scene scene = new Scene(root);
    newWindow.setScene(scene);
    for (int i = 1; i <= humPlayersSpinner.getValue(); i++) {
      controller.addHumPlayer(i);
    }

    for (int i = 1; i <= cpuPlayersSpinner.getValue(); i++) {
      controller.addCpuPlayer(i);
    }

    Main.hideParentWindow(mouseEvent);
    newWindow.show();
  }

  public void spinningDogeClicked(MouseEvent mouseEvent)  {
    ImageView spinningDoge = (ImageView ) mouseEvent.getSource();
    spinningDoge.setScaleX(spinningDoge.getScaleX() * -1);
  }

  public void backIconClicked(MouseEvent mouseEvent) throws IOException {
    Main.replaceWindow("fxml/mainWindow.fxml", "Main Window", mouseEvent);
  }
}
