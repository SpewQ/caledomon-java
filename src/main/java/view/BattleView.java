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
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import model.Animal;
import model.EnvironmentType;
import model.actions.Action;

/**
 * Classe publique BattleView représentant la vue de combat
 */
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

    private final ActionInfoPane actionInfoLeft = new ActionInfoPane();


    private AudioClip sfxHit;

    
    /**
     * Constructeur de BattleView
     * @param controller : Contrôleur de combat
     */
    public BattleView(BattleController controller) {
        this.getStyleClass().add("battle-background");
        this.getStylesheets().add(
            getClass().getResource("/styles/battle.css").toExternalForm()
        );
        this.controller = controller;
        loadAudio();
        buildUI();
        redirectSystemOutToLog(); // 🔗 redirige System.out vers la TextArea
    }

    /**
     * Méthode privée de redirection de System.out vers la TextArea
     */
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

    /**
     * Méthode privée pour charger l'audio d'un CalédoMon touché
     */
    private void loadAudio() {
        try {
            var hitUrl = getClass().getResource("/sounds/hit.mp3");
            if (hitUrl != null) sfxHit = new AudioClip(hitUrl.toExternalForm());

        } catch (Exception e) {
            System.err.println("Impossible de charger les sons : " + e.getMessage());
        }
    }

    /**
     * Méthode privée pour normaliser les noms d'images (remplacer certains caractères par d'autres)
     */
    private String normalizeImageName(String name) {
        return name.toLowerCase()
                .replace(" ", "_")
                .replace("é", "e")
                .replace("è", "e")
                .replace("ê", "e")
                .replace("à", "a")
                .replace("ç", "c");
    }

    /**
     * Méthode privée pour construire l'interface de la vue combat
     */
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
        playerSpriteStack.getChildren().addAll(imgPlayerPlatform, imgPlayer);
        StackPane.setAlignment(imgPlayer, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(imgPlayerPlatform, Pos.BOTTOM_CENTER);

        StackPane iaSpriteStack = new StackPane();
        iaSpriteStack.getChildren().addAll(imgIaPlatform, imgIa);
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

        pbPlayerHp.getStyleClass().add("player-hp-bar");
        pbIaHp.getStyleClass().add("enemy-hp-bar");

        // --- Zone centrale ---
        HBox battleBox = new HBox(50, playerBox, iaBox);
        battleBox.setAlignment(Pos.CENTER);
        battleBox.setPadding(new Insets(20));
        setCenter(battleBox);

        // Pour que les infos latérales flottent au-dessus du combat, on utilise un StackPane
        Pane overlayPane = new Pane();
        overlayPane.setPickOnBounds(false); // permet de cliquer à travers
        overlayPane.getChildren().addAll(actionInfoLeft);

        // Positionner à gauche/droite
        actionInfoLeft.setLayoutX(10);
        actionInfoLeft.setLayoutY(50);

        // Ajouter overlayPane au-dessus du centre
        StackPane centerStack = new StackPane();
        centerStack.getChildren().addAll(battleBox, overlayPane);
        setCenter(centerStack);

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
        for (int i = 0; i < actionButtons.size(); i++) {
            Button b = actionButtons.get(i);
            if (i < moves.size()) {
                Action act = moves.get(i);
                b.setText(controller.actionLabel(act));
                b.setDisable(false);
                int finalI = i;
                b.setOnAction(e -> controller.onPlayerAction(moves.get(finalI)));

                // --- Hover pour infos latérales ---
                b.setOnMouseEntered(e -> {
                    // Si c'est un Buff, target = joueur ; si Débuff, target = ennemi
                    Animal target = null;
                    if (act.isBuff()) target = controller.getPlayer();
                    else if (act.isDebuff()) target = controller.getIa();

                    actionInfoLeft.showFor(act, target, getWidth(), true);
                });
                b.setOnMouseExited(e -> {
                    actionInfoLeft.hidePane();
                });

            } else {
                b.setText("-"); b.setDisable(true); b.setOnAction(null);
                b.setOnMouseEntered(null); b.setOnMouseExited(null);
            }
            b.setOnMousePressed(e -> { b.setScaleX(0.95); b.setScaleY(0.95); });
            b.setOnMouseReleased(e -> { b.setScaleX(1.0); b.setScaleY(1.0); });
        }
    }

    /**
     * Méthode privée pour ajouter un style aux boutons d'action
     */
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

    /**
     * Méthode publique pour affecter les noms de CalédoMon en dessous des sprites
     * @param playerName : nom du CalédoMon dujoueur en chaînes de caractères
     * @param iaName : nom du CalédoMon de l'IA en chaînes de caractères
     */
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

    /**
     * Méthode publique pour affecter les sprites des CalédoMon en fonction de leur nom
     * @param playerName : nom du CalédoMon dujoueur en chaînes de caractères
     * @param iaName : nom du CalédoMon de l'IA en chaînes de caractères
     */
    public void setImages(String playerName, String iaName) {
        try {
            String playerFile = normalizeImageName(playerName);
            String iaFile = normalizeImageName(iaName);

            String pathPlayer = "/images/" + playerFile + ".png";
            String pathIa = "/images/" + iaFile + ".png";

            var isPlayer = getClass().getResourceAsStream(pathPlayer);
            var isIa = getClass().getResourceAsStream(pathIa);

            if (isPlayer != null) {
                imgPlayer.setImage(new Image(isPlayer));
            } else {
                System.err.println("❌ Image joueur introuvable : " + pathPlayer);
            }

            if (isIa != null) {
                imgIa.setImage(new Image(isIa));
            } else {
                System.err.println("❌ Image IA introuvable : " + pathIa);
            }

        } catch (Exception e) {
            System.err.println("Erreur chargement sprites : " + e.getMessage());
        }
    }

    
    /**
     * Méthode publique pour choisir l'environnement
     */
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

    /**
     * Méthode publique d'animation de la barre de vie
     */
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

    /**
     * Méthode publique pour ajouter les messages dans la TextArea
     */
    public void addLog(String message) {
        taLog.appendText(message + "\n");
    }

    /**
     * Méthode publique pour désactiver les boutons d'actions
     */
    public void disableActions() {
        for (Button b : actionButtons) b.setDisable(true);
    }

    /**
     * Méthode publique pour activer les boutons d'actions
     */
    public void enableActions() {
        for (Button b : actionButtons) b.setDisable(false);
    }

    // ---------------------------------------------------------
    // Animations Pokémon
    // ---------------------------------------------------------

    /**
     * Méthode publique pour animer l'effet de paralysie sur le joueur
     */
    public void animateParalysisOnPlayer() {
        animateParalysisEffect(imgPlayer);
    }

    /**
     * Méthode publique pour animer l'effet de paralysie sur l'IA
     */
    public void animateParalysisOnEnemy() {
        animateParalysisEffect(imgIa);
    }

    /**
     * Méthode publique pour animer l'effet de poison sur le joueur
     */
    public void animatePoisonOnPlayer() {
        animatePoisonEffect(imgPlayer);
    }

    /**
     * Méthode publique pour animer l'effet de poison sur l'IA
     */
    public void animatePoisonOnEnemy() {
        animatePoisonEffect(imgIa);
    }

    /**
     * Méthode publique pour animer l'effet de buff sur le joueur
     */
    public void animateBuffOnPlayer() { 
        animateBuff(imgPlayer); 
    }

    /**
     * Méthode publique pour animer l'effet de débuff sur l'ennemi
     */
    public void animateDebuffOnEnemy() { 
        animateDebuff(imgIa); 
    }

    /**
     * Méthode publique pour animer l'effet de buff sur l'IA
     */
    public void animateBuffOnEnemy() { 
        animateBuff(imgIa); 
    }

    /**
     * Méthode publique pour animer l'effet de débuff sur le joueur
     */
    public void animateDebuffOnPlayer() { 
        animateDebuff(imgPlayer); 
    }

    /**
     * Méthode publique qui renvoie un booléen si on lance l'animation ou non (le joueur touche l'ennemi)
     */
    public void animateHitOnEnemyIfAllowed(boolean canAttack) {
        animateHitOnEnemy(canAttack);
    }

    /**
     * Méthode publique qui renvoie un booléen si on lance l'animation ou non (l'ennemi touche le joueur)
     */
    public void animateHitOnPlayerIfAllowed(boolean canAttack) {
        animateHitOnPlayer(canAttack);
    }


    /** 
     * Sprite du joueur avance pour attaquer — annulé si paralysé 
     */
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

    /** 
     * Sprite de l'ennemi avance pour attaquer — annulé si paralysé 
     */
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

    /** 
     * Clignotement lors des dégâts 
     */
    private void flashSprite(ImageView iv) {
        FadeTransition f = new FadeTransition(Duration.seconds(0.1), iv);
        f.setFromValue(1);
        f.setToValue(0.2);
        f.setAutoReverse(true);
        f.setCycleCount(4);
        f.play();
    }

    /** 
     * Animation poison (flash violet) 
     */
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

    /** 
     * Animation paralysie (flash jaune rapide + micro-shake) 
     */
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

    /** 
     * Tremblement de l'écran 
     */
    private void shakeScreen() {
        TranslateTransition shake = new TranslateTransition(Duration.seconds(0.05), this);
        shake.setByX(6);
        shake.setAutoReverse(true);
        shake.setCycleCount(6);
        shake.play();
    }

    /** 
     * Animation : buff appliqué sur soi (aura claire + montée) 
     */
    public void animateBuff(ImageView sprite) {

        // légère montée
        TranslateTransition lift = new TranslateTransition(Duration.seconds(0.25), sprite);
        lift.setByY(-20);
        lift.setAutoReverse(true);
        lift.setCycleCount(2);

        // glow lumineux
        ColorAdjust color = new ColorAdjust();
        sprite.setEffect(color);

        Timeline glow = new Timeline(
            new KeyFrame(Duration.ZERO,
                new KeyValue(color.brightnessProperty(), 0)
            ),
            new KeyFrame(Duration.seconds(0.25),
                new KeyValue(color.brightnessProperty(), 0.8)
            ),
            new KeyFrame(Duration.seconds(0.50),
                new KeyValue(color.brightnessProperty(), 0)
            )
        );

        // jouer ensemble
        ParallelTransition pt = new ParallelTransition(lift, glow);
        pt.setOnFinished(e -> sprite.setEffect(null));
        pt.play();
    }

    /** 
     * Animation : debuff appliqué à l'ennemi (assombrissement + contraction) 
     */
    public void animateDebuff(ImageView sprite) {

        // assombrissement
        ColorAdjust color = new ColorAdjust();
        color.setBrightness(-0.7);
        sprite.setEffect(color);

        // contraction
        ScaleTransition shrink = new ScaleTransition(Duration.seconds(0.25), sprite);
        shrink.setToX(0.85);
        shrink.setToY(0.85);
        shrink.setAutoReverse(true);
        shrink.setCycleCount(2);

        // petit tremblement horizontal
        TranslateTransition shake = new TranslateTransition(Duration.seconds(0.05), sprite);
        shake.setByX(10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);

        ParallelTransition pt = new ParallelTransition(shrink, shake);
        pt.setOnFinished(e -> sprite.setEffect(null));
        pt.play();
    }

}
