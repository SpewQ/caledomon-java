package controller;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Animal;
import model.Battle;
import model.factories.AnimalFactory;
import view.BattleView;
import view.ResultView;
import view.SelectionView;

public class SelectionController {

    private final Stage stage;
    private final SelectionView view;
    private AudioClip sfxButton;

    public SelectionController(Stage stage, SelectionView view) {
        this.stage = stage;
        this.view = view;
        loadAudio();

        // Callback lors de la sélection d'un Calédomon
        this.view.setOnSelectionConfirmed(this::onSelect);
    }

    private void loadAudio() {
        try {
            var actionUrl = getClass().getResource("/sounds/button.mp3");
            if (actionUrl != null) sfxButton = new AudioClip(actionUrl.toExternalForm());
        } catch (Exception e) {
            System.err.println("Impossible de charger les sons : " + e.getMessage());
        }
    }

    private void onSelect(String chosenName) {
        if (sfxButton != null) sfxButton.play();

        Animal player = AnimalFactory.createAnimal(chosenName);

        // IA aléatoire
        String[] list = { "Baobab", "Cagou", "Cerf", "Dawa", "Gecko", "Notou", "Picot", "Roussette", "Tortue", "Ver", "Tricot Raye" };
        String iaName = list[new Random().nextInt(list.length)];
        Animal ia = AnimalFactory.createAnimal(iaName);

        Battle battle = new Battle(player, ia);
        BattleController battleController = new BattleController(battle);
        BattleView battleView = new BattleView(battleController);

        // Attacher le controller à la vue
        battleController.setView(battleView);

        // Callback fin de combat → ResultView
        battleController.setOnBattleEnded(playerWon -> {
            String title = playerWon ? "Victoire !" : "Défaite...";
            String detail = playerWon ? "Bravo, vous avez vaincu votre adversaire." : "Votre Calédomon est K.O.";
            ResultView resultView = new ResultView(title, detail);

            resultView.setOnReturnToMenu(() -> {
                SelectionView newSelection = new SelectionView();
                new SelectionController(stage, newSelection);
                switchView(newSelection);
            });

            switchView(resultView);
        });

        switchView(battleView);

        stage.setTitle("Combat Calédomon");
        stage.setMaximized(true);

        battleController.startBattle();
    }

    /**
     * Change la vue dans le StackPane racine avec fade transition.
     */
    private void switchView(javafx.scene.Parent newView) {
        StackPane root = (StackPane) stage.getScene().getRoot();

        final javafx.scene.Node oldView;
        if (!root.getChildren().isEmpty()) {
            oldView = root.getChildren().get(0);
        } else {
            oldView = null;
        }

        root.getChildren().add(newView);

        // Fade in nouvelle vue
        FadeTransition fadeIn = new FadeTransition(Duration.millis(400), newView);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // Fade out ancienne vue
        if (oldView != null) {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(400), oldView);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> root.getChildren().remove(oldView));
            fadeOut.play();
        }
    }
}
