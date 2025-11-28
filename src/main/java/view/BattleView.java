package view;

import java.util.ArrayList;
import java.util.List;

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
import model.actions.Action;

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

    // boutons dynamiques (remplace btn1..4 statiques)
    private final List<Button> actionButtons = new ArrayList<>();

    private final TextArea taLog = new TextArea();

    public BattleView(BattleController controller) {
        this.controller = controller;
        buildUI();
        // wireActions is not used for static buttons anymore
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

        // --- Boutons (placeholder HBox, les boutons seront ajoutés dynamiquement) ---
        HBox actionBox = new HBox(10);
        actionBox.setAlignment(Pos.CENTER);
        actionBox.setPadding(new Insets(10));
        // crée 4 boutons vides pour respecter la mise en page initiale
        for (int i = 0; i < 4; i++) {
            Button b = new Button("-");
            b.setPrefWidth(160);
            actionButtons.add(b);
            actionBox.getChildren().add(b);
        }
        setBottom(actionBox);

        // --- Zone de log ---
        taLog.setEditable(false);
        taLog.setPrefHeight(120);
        taLog.setWrapText(true);
        setTop(taLog);
    }

    /**
     * Charge les actions (moves) du joueur et met à jour les boutons.
     */
    public void setPlayerActions(List<Action> moves) {
        // on suppose jusqu'à 4 moves ; si moins, on désactive certains boutons
        for (int i = 0; i < actionButtons.size(); i++) {
            Button b = actionButtons.get(i);
            if (i < moves.size()) {
                Action act = moves.get(i);
                // label via controller (plus lisible)
                b.setText(controller.actionLabel(act));
                b.setDisable(false);
                int finalI = i;
                b.setOnAction(e -> controller.onPlayerAction(moves.get(finalI)));
            } else {
                b.setText("-");
                b.setDisable(true);
                b.setOnAction(null);
            }
        }
    }

    public void bindNames(String playerName, String iaName) {
        lblPlayerName.setText(playerName);
        lblIaName.setText(iaName);
    }

    public void setImages(String playerName, String iaName) {
        try {
            String pathPlayer = "/images/" + playerName.toLowerCase() + ".jpg";
            String pathIa = "/images/" + iaName.toLowerCase() + ".jpg";

            if (getClass().getResourceAsStream(pathPlayer) != null) {
                imgPlayer.setImage(new Image(getClass().getResourceAsStream(pathPlayer)));
            } else {
                System.err.println("Image joueur introuvable: " + pathPlayer);
            }

            if (getClass().getResourceAsStream(pathIa) != null) {
                imgIa.setImage(new Image(getClass().getResourceAsStream(pathIa)));
            } else {
                System.err.println("Image IA introuvable: " + pathIa);
            }
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
                    offsetY = 22;
                }
                case SAND -> {
                    fileName = "sand.png";
                    offsetY = 12;
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
            if (getClass().getResourceAsStream(path) != null) {
                Image platformImage = new Image(getClass().getResourceAsStream(path));
                imgPlayerPlatform.setImage(platformImage);
                imgIaPlatform.setImage(platformImage);
                imgPlayerPlatform.setTranslateY(offsetY);
                imgIaPlatform.setTranslateY(offsetY);
            } else {
                System.err.println("Plateforme introuvable: " + path);
            }

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
        for (Button b : actionButtons) b.setDisable(true);
    }
}
