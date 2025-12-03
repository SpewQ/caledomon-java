package view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import controller.BattleController;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
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

    private AudioClip sfxHit;

    public BattleView(BattleController controller) {
        this.controller = controller;
        loadAudio();
        buildUI();
        redirectSystemOutToLog(); // 🔗 redirige System.out vers la TextArea
    }

    // ---------------------------------------------------------
    // Redirection de System.out vers la TextArea
    // ---------------------------------------------------------
    private void redirectSystemOutToLog() {
        PrintStream ps = new PrintStream(new OutputStream() {
            private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            @Override
            public void write(int b) throws IOException {
                if (b == '\n') {
                    // on convertit les octets accumulés en String UTF-8
                    String line = buffer.toString(StandardCharsets.UTF_8);
                    buffer.reset();
                    Platform.runLater(() -> taLog.appendText(line + "\n"));
                } else {
                    buffer.write(b);
                }
            }
        }, true, StandardCharsets.UTF_8);

        System.setOut(ps);
    }

    private void loadAudio() {
        try {
            var hitUrl = getClass().getResource("/sounds/hit.mp3");
            if (hitUrl != null) sfxHit = new AudioClip(hitUrl.toExternalForm());

        } catch (Exception e) {
            System.err.println("Impossible de charger les sons : " + e.getMessage());
        }
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

        imgIaPlatform.setFitWidth(170);
        imgIaPlatform.setFitHeight(55);
        imgIaPlatform.setPreserveRatio(false);

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
        imgIa.setTranslateY(0);

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
        taLog.prefHeightProperty().bind(widthProperty().multiply(0.20));
        taLog.setWrapText(true);
        setTop(taLog);

        // --- Images joueur/IA responsives ---
        imgPlayer.fitHeightProperty().bind(heightProperty().multiply(0.28));
        imgIa.fitHeightProperty().bind(heightProperty().multiply(0.28));

        imgPlayerPlatform.fitWidthProperty().bind(widthProperty().multiply(0.18));
        imgPlayerPlatform.fitHeightProperty().bind(heightProperty().multiply(0.08));

        imgIaPlatform.fitWidthProperty().bind(widthProperty().multiply(0.18));
        imgIaPlatform.fitHeightProperty().bind(heightProperty().multiply(0.08));

        // --- PV bars responsives ---
        pbPlayerHp.prefWidthProperty().bind(widthProperty().multiply(0.20));
        pbPlayerHp.prefHeightProperty().bind(widthProperty().multiply(0.02));
        lblPlayerHp.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", heightProperty().multiply(0.04).asString(), ";")
        );

        pbIaHp.prefWidthProperty().bind(widthProperty().multiply(0.20));
        pbIaHp.prefHeightProperty().bind(widthProperty().multiply(0.02));
        lblIaHp.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", heightProperty().multiply(0.04).asString(), ";")
        );

        // --- Boutons responsifs ---
        for (Button b : actionButtons) {
            b.prefWidthProperty().bind(widthProperty().multiply(0.20));
            b.prefHeightProperty().bind(heightProperty().multiply(0.08));
        }
        setBottom(actionBox);
        styleActionButtons();
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
            b.setOnMousePressed(e -> b.setScaleX(0.95));
            b.setOnMousePressed(e -> b.setScaleY(0.95));
            b.setOnMouseReleased(e -> {
                b.setScaleX(1.0);
                b.setScaleY(1.0);
            });
        }
    }

    private void styleActionButtons() {
        for (Button b : actionButtons) {
            b.setStyle("""
                -fx-background-color: linear-gradient(#00cfff, #007f5f);
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                -fx-background-radius: 15;
                -fx-border-radius: 15;
                -fx-border-color: #ffd700;
                -fx-border-width: 2;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 4,0,0,2);
            """);

            // Hover effect
            b.setOnMouseEntered(e -> b.setStyle("""
                -fx-background-color: linear-gradient(#00e5ff, #00a370);
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                -fx-background-radius: 15;
                -fx-border-radius: 15;
                -fx-border-color: #ffd700;
                -fx-border-width: 2;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 6,0,0,3);
            """));
            b.setOnMouseExited(e -> styleActionButtons()); // revenir au style normal
        }
    }


    public void bindNames(String playerName, String iaName) {

        lblPlayerName.setText(playerName);
        lblPlayerName.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", heightProperty().multiply(0.04).asString(), ";")
        );

        lblIaName.setText(iaName);
        lblIaName.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", heightProperty().multiply(0.04).asString(), ";")
        );

    }

    public void setImages(String playerName, String iaName) {
        try {
            String pathPlayer = "/images/" + playerName.toLowerCase() + ".png";
            String pathIa = "/images/" + iaName.toLowerCase() + ".png";

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

    // ---------------------------------------------------------
    // PV bar animation
    // ---------------------------------------------------------
    public void refreshHp(int playerPv, int iaPv, int playerMax, int iaMax) {
            lblPlayerHp.setText(playerPv + " / " + playerMax);
            lblIaHp.setText(iaPv + " / " + iaMax);
            animateBar(pbPlayerHp, (double) playerPv / playerMax);
            animateBar(pbIaHp, (double) iaPv / iaMax);
        }


        private void animateBar(ProgressBar bar, double target) {
            Timeline tl = new Timeline(
                new KeyFrame(Duration.seconds(0.4),
                new KeyValue(bar.progressProperty(), target, Interpolator.EASE_BOTH))
            );
            tl.play();
    }

    public void addLog(String message) {
        taLog.appendText(message + "\n");
    }

    public void disableActions() {
        for (Button b : actionButtons) b.setDisable(true);
    }

    public void enableActions() {
        for (Button b : actionButtons) b.setDisable(false);
    }

    // ---------------------------------------------------------
    // Animations Pokémon
    // ---------------------------------------------------------

    public void animateParalysisOnPlayer() {
        animateParalysisEffect(imgPlayer);
    }
    public void animateParalysisOnEnemy() {
        animateParalysisEffect(imgIa);
    }
    public void animatePoisonOnPlayer() {
        animatePoisonEffect(imgPlayer);
    }
    public void animatePoisonOnEnemy() {
        animatePoisonEffect(imgIa);
    }
    public void animateHitOnEnemyIfAllowed(boolean canAttack) {
        animateHitOnEnemy(canAttack);
    }
    public void animateHitOnPlayerIfAllowed(boolean canAttack) {
        animateHitOnPlayer(canAttack);
    }


    /** Sprite du joueur avance pour attaquer — annulé si paralysé */
    public void animateHitOnEnemy(boolean canAttack) {
        if (!canAttack) {
            // Effet visuel de paralysie
            animateParalysisEffect(imgPlayer);
            return;
        }

        TranslateTransition t = new TranslateTransition(Duration.seconds(0.15), imgPlayer);
        t.setByX(40);
        t.setAutoReverse(true);
        t.setCycleCount(2);
        t.play();
        if (sfxHit != null) sfxHit.play();

        flashSprite(imgIa);
        shakeScreen();
    }

    /** Sprite de l'ennemi avance pour attaquer — annulé si paralysé */
    public void animateHitOnPlayer(boolean canAttack) {
        if (!canAttack) {
            animateParalysisEffect(imgIa);
            return;
        }

        TranslateTransition t = new TranslateTransition(Duration.seconds(0.15), imgIa);
        t.setByX(-40);
        t.setAutoReverse(true);
        t.setCycleCount(2);
        t.play();
        if (sfxHit != null) sfxHit.play();

        flashSprite(imgPlayer);
        shakeScreen();
    }

    /** Clignotement lors des dégâts */
    private void flashSprite(ImageView iv) {
        FadeTransition f = new FadeTransition(Duration.seconds(0.1), iv);
        f.setFromValue(1);
        f.setToValue(0.2);
        f.setAutoReverse(true);
        f.setCycleCount(4);
        f.play();
    }

    /** Animation poison (flash violet) */
    public void animatePoisonEffect(ImageView iv) {
        ColorAdjust purple = new ColorAdjust();
        purple.setHue(0.6); // violet
        iv.setEffect(purple);

        FadeTransition f = new FadeTransition(Duration.seconds(0.12), iv);
        f.setFromValue(1);
        f.setToValue(0.3);
        f.setCycleCount(4);
        f.setAutoReverse(true);
        f.play();

        f.setOnFinished(e -> iv.setEffect(null)); // reviens à l'état normal
    }

    /** Animation paralysie (flash jaune rapide + micro-shake) */
    public void animateParalysisEffect(ImageView iv) {
        ColorAdjust yellow = new ColorAdjust();
        yellow.setHue(-0.25); // jaune
        iv.setEffect(yellow);

        FadeTransition f = new FadeTransition(Duration.seconds(0.08), iv);
        f.setFromValue(1);
        f.setToValue(0.3);
        f.setCycleCount(6);
        f.setAutoReverse(true);

        // mini-tremblement ajouté
        TranslateTransition shake = new TranslateTransition(Duration.seconds(0.06), iv);
        shake.setByX(4);
        shake.setAutoReverse(true);
        shake.setCycleCount(6);

        f.play();
        shake.play();

        f.setOnFinished(e -> iv.setEffect(null));
    }

    /** Tremblement de l'écran */
    private void shakeScreen() {
        TranslateTransition shake = new TranslateTransition(Duration.seconds(0.05), this);
        shake.setByX(6);
        shake.setAutoReverse(true);
        shake.setCycleCount(6);
        shake.play();
    }
}
