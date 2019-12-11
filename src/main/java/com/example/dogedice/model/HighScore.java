package com.example.dogedice.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

class HighScore {
  private final Gson gson;
  private List<Player> players;
  private final int playersToSave;
  Path highScoreFile = Paths.get("highscores.json");


  HighScore(int playersToSave) {
    this.gson = new Gson();
    this.players = new ArrayList<>();
    this.playersToSave = playersToSave;

    try {
      String json = new String(Files.readAllBytes(highScoreFile));
      Type listType = new TypeToken<ArrayList<Player>>() {}.getType();
      List<Player> playersFromFile = gson.fromJson(json, listType);
      players.addAll(playersFromFile);
    } catch (Exception e) {
      System.out.println("Something went wrong when trying to load json.");
    }
    while (players.size() < playersToSave) {
      players.add(new Player("Super Doge"));
    }
  }

  void addPlayers(List<Player> newPlayers) {
    List<Player> newHumanPlayers = newPlayers
        .stream()
        .filter(x -> !x.isBot())
        .collect(Collectors.toList());
    players.addAll(newHumanPlayers);
    Collections.sort(players);
    players = players.subList(0, playersToSave);
  }

  List<Player> getPlayers() {
    return players;
  }

  void writeJSON() {
    String json = gson.toJson(players);
    try {
      FileWriter fw = new FileWriter(highScoreFile.toFile());
      fw.write(json);
      fw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
