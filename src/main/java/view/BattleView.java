package view;

import controller.BattleController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.EnvironmentType;

public class BattleView extends BorderPane {

    private final BattleController controller;

    private final Label lblPlayerName = new Label("-");
    private final ProgressBar pbPlayerHp = new ProgressBar(1.0);
    private final Label lblPlayerHp = new Label("0 / 0");

    private final Label lblIaName = new Label("-");
    private final ProgressBar pbIaHp = new ProgressBar(1.0);
    private final Label lblIaHp = new Label("0 / 0");

    private final ImageView imgPlayer = new ImageView();
    private final ImageView imgIa = new ImageView();

    // plateformes
    private final ImageView imgPlayerPlatform = new ImageView();
    private final ImageView imgIaPlatform = new ImageView();

    private final Button btn1 = new Button("Coup de Bec");
    private final Button btn2 = new Button("Cri d'Alerte");
    private final Button btn3 = new Button("Saut de Brousse");
    private final Button btn4 = new Button("Danse du Sol");

    private final TextArea taLog = new TextArea();

    public BattleView(BattleController controller) {
        this.controller = controller;
        buildUI();
        wireActions();
    }

    private void buildUI() {
        // --- Images setup (animaux) ---
        imgPlayer.setFitHeight(120);
        imgPlayer.setPreserveRatio(true);

        imgIa.setFitHeight(120);
        imgIa.setPreserveRatio(true);

        // --- Plateformes ---
        imgPlayerPlatform.setFitWidth(170);
        imgPlayerPlatform.setFitHeight(55);
        imgPlayerPlatform.setPreserveRatio(false);

        imgIaPlatform.setFitWidth(130);
        imgIaPlatform.setFitHeight(50);
        imgIaPlatform.setPreserveRatio(true);

        // --- Stack sol + animal (plateforme devant) ---
        StackPane playerSpriteStack = new StackPane();
        playerSpriteStack.getChildren().addAll(imgPlayer, imgPlayerPlatform);
        StackPane.setAlignment(imgPlayer, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(imgPlayerPlatform, Pos.BOTTOM_CENTER);

        StackPane iaSpriteStack = new StackPane();
        iaSpriteStack.getChildren().addAll(imgIa, imgIaPlatform);
        StackPane.setAlignment(imgIa, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(imgIaPlatform, Pos.BOTTOM_CENTER);

        // Position de base des sprites
        imgPlayer.setTranslateY(0);
        imgIa.setTranslateY(-10);

        // --- Joueur ---
        VBox playerBox = new VBox(5, playerSpriteStack, lblPlayerName, pbPlayerHp, lblPlayerHp);
        playerBox.setAlignment(Pos.CENTER);

        // --- IA ---
        VBox iaBox = new VBox(5, iaSpriteStack, lblIaName, pbIaHp, lblIaHp);
        iaBox.setAlignment(Pos.CENTER);

        // --- Zone centrale ---
        HBox battleBox = new HBox(50, playerBox, iaBox);
        battleBox.setAlignment(Pos.CENTER);
        battleBox.setPadding(new Insets(20));
        setCenter(battleBox);

        // --- Boutons ---
        HBox actionBox = new HBox(10, btn1, btn2, btn3, btn4);
        actionBox.setAlignment(Pos.CENTER);
        actionBox.setPadding(new Insets(10));
        setBottom(actionBox);

        // --- Zone de log ---
        taLog.setEditable(false);
        taLog.setPrefHeight(120);
        taLog.setWrapText(true);
        setTop(taLog);
    }

    private void wireActions() {
        btn1.setOnAction(e -> controller.onPlayerAction("coup"));
        btn2.setOnAction(e -> controller.onPlayerAction("cri"));
        btn3.setOnAction(e -> controller.onPlayerAction("saut"));
        btn4.setOnAction(e -> controller.onPlayerAction("danse"));
    }

    public void bindNames(String playerName, String iaName) {
        lblPlayerName.setText(playerName);
        lblIaName.setText(iaName);
    }

    public void setImages(String playerName, String iaName) {
        try {
            String pathPlayer = "/images/" + playerName.toLowerCase() + ".jpg";
            String pathIa = "/images/" + iaName.toLowerCase() + ".jpg";

            imgPlayer.setImage(new Image(getClass().getResourceAsStream(pathPlayer)));
            imgIa.setImage(new Image(getClass().getResourceAsStream(pathIa)));
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des images : " + e.getMessage());
        }
    }

    // Choisir le sol selon l'environnement du combat
    public void setEnvironment(EnvironmentType env) {
        try {
            String fileName;
            int offsetY; // offset vertical plateforme

            switch (env) {
                case GRASS -> {
                    fileName = "grass.png";
                    offsetY = 22;   // ça marchait déjà bien
                }
                case SAND -> {
                    fileName = "sand.png";
                    offsetY = 12;   // un peu plus haut pour toucher les pattes
                }
                case ROCK -> {
                    fileName = "rock.png";
                    offsetY = 12;
                }
                case WATER -> {
                    fileName = "water.png";
                    offsetY = 12;
                }
                case CAVE -> {
                    fileName = "cave.png";
                    offsetY = 12;
                }
                default -> {
                    fileName = "grass.png";
                    offsetY = 22;
                }
            }

            String path = "/images/" + fileName;
            Image platformImage = new Image(getClass().getResourceAsStream(path));

            imgPlayerPlatform.setImage(platformImage);
            imgIaPlatform.setImage(platformImage);

            // on applique l’offset pour les deux plateformes
            imgPlayerPlatform.setTranslateY(offsetY);
            imgIaPlatform.setTranslateY(offsetY);

        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de la plateforme : " + e.getMessage());
        }
    }

    public void refreshHp(int playerPv, int iaPv, int playerMax, int iaMax) {
        double pRatio = Math.max(0, Math.min(1.0, (double) playerPv / Math.max(1, playerMax)));
        double iRatio = Math.max(0, Math.min(1.0, (double) iaPv / Math.max(1, iaMax)));
        pbPlayerHp.setProgress(pRatio);
        pbIaHp.setProgress(iRatio);
        lblPlayerHp.setText(playerPv + " / " + playerMax);
        lblIaHp.setText(iaPv + " / " + iaMax);
    }

    public void addLog(String message) {
        taLog.appendText(message + "\n");
    }

    public void disableActions() {
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
    }
}
