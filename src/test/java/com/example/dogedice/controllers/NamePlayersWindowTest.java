package com.example.dogedice.controllers;

import com.example.dogedice.model.GameEngine;
import com.example.dogedice.model.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NamePlayersWindowTest extends ApplicationTest {
  private Logger logger = LoggerFactory.getLogger(NamePlayersWindow.class);
  private NamePlayersWindow controller;
  private GameEngine gameEngine;

  @Mock VBox namePlayersBoxMock;
  @Mock ObservableList<Node> namePlayersListMock;
  @Mock MouseEvent mouseEventMock;


  @Override
  public void start(Stage stage) throws IOException {
    MockitoAnnotations.initMocks(this);

    gameEngine = new GameEngine(
        30,
        10,
        15,
        20,
        new int[]{6,6},
        new int[]{});
    gameEngine.setNumberOfPlayers(2,2);

    FXMLLoader loader = HelperMethods.getLoader(HelperMethods.namePlayersWindowFXML);
    Parent root = loader.load();
    Scene scene = new Scene(root);

    controller = loader.getController();
    controller.namePlayersBox = namePlayersBoxMock;
    controller.gameEngine = gameEngine;
    controller.stage = stage;
    controller.setStage(stage);
    stage.setScene(scene);

    when(namePlayersBoxMock.getChildren())
        .thenReturn(namePlayersListMock);
  }

  @Test
  void postInitialization_ControllerCreatesTwoHumanTextFields_WhenGameEngineIsSetToTwoHumansAndTwoCPUs() {
    controller.postInitialization();
    assertEquals(2, controller.getHumans().size(), "Expected two text fields for human players.");
  }

  @Test
  void postInitialization_ControllerCreatesTwoCpuTextFields_WhenGameEngineIsSetToTwoHumansAndTwoCPUs() {
    controller.postInitialization();
    assertEquals(2, controller.getCpus().size(), "Expected two text fields for CPU players");
  }

  @Test
  void confirmButtonClicked_GameEngineCreatesTwoHumanAndTwoCpuPlayers_WhenControllerHasTwoTextFieldsForEach() {
    controller.postInitialization();

    interact( () -> {
      try {
        controller.confirmButtonClicked(mouseEventMock);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    List<Player> players = gameEngine.getPlayers();
    List<Player> cpus = players
        .stream()
        .filter(Player::isBot)
        .collect(Collectors.toList());
    List<Player> humans = players
        .stream()
        .filter(x -> !x.isBot())
        .collect(Collectors.toList());
    assertEquals(4, players.size(), "Expected four players");
    assertEquals(2, humans.size(), "Expected two human players");
    assertEquals(2, cpus.size(), "Expected two CPU players");

  }
}