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
import javafx.scene.layout.VBox;


public class BattleView extends BorderPane {

    private final BattleController controller;

    private final Label lblPlayerName = new Label("-");
    private final ProgressBar pbPlayerHp = new ProgressBar(1.0);
    private final Label lblPlayerHp = new Label("0/0");

    private final Label lblIaName = new Label("-");
    private final ProgressBar pbIaHp = new ProgressBar(1.0);
    private final Label lblIaHp = new Label("0/0");

    private final ImageView imgPlayer = new ImageView();
    private final ImageView imgIa = new ImageView();

    // Noms des boutons inspirés Pokémon (pour le Cagou)
    private final Button btn1 = new Button("Coup de Bec");
    private final Button btn2 = new Button("Cri dAlerte");
    private final Button btn3 = new Button("Saut de Brousse");
    private final Button btn4 = new Button("Danse du Sol");

    private final TextArea taLog = new TextArea();

    public BattleView(BattleController controller) {
        this.controller = controller;
        buildUI();
        wireActions();
    }

    private void buildUI() {
        // --- Images setup ---
        imgPlayer.setFitHeight(120);
        imgPlayer.setPreserveRatio(true);
        imgIa.setFitHeight(120);
        imgIa.setPreserveRatio(true);

        // --- Joueur ---
        VBox playerBox = new VBox(5, imgPlayer, lblPlayerName, pbPlayerHp, lblPlayerHp);
        playerBox.setAlignment(Pos.CENTER);

        // --- IA ---
        VBox iaBox = new VBox(5, imgIa, lblIaName, pbIaHp, lblIaHp);
        iaBox.setAlignment(Pos.CENTER);

        // --- Zone centrale ---
        HBox battleBox = new HBox(50, playerBox, iaBox);
        battleBox.setAlignment(Pos.CENTER);
        setCenter(battleBox);

        // --- Boutons d’action ---
        HBox actionBox = new HBox(10, btn1, btn2, btn3, btn4);
        actionBox.setAlignment(Pos.CENTER);
        actionBox.setPadding(new Insets(10));
        setBottom(actionBox);

        // --- Zone de log ---
        taLog.setEditable(false);
        taLog.setPrefHeight(120);
        setTop(taLog);
    }

    private void wireActions() {
        // on transmet des clés simples au contrôleur
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