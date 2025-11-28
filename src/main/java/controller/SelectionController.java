package controller;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
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

    public SelectionController(Stage stage, SelectionView view) {
        this.stage = stage;
        this.view = view;

        // lorsque la vue signale une sélection : create battle
        this.view.setOnSelectionConfirmed(this::onSelect);
    }

    private void onSelect(String chosenName) {
        Animal player = AnimalFactory.createAnimal(chosenName);

        // IA aléatoire
        String[] list = { "Cagou", "Gecko", "Tricot Raye" };
        String iaName = list[new Random().nextInt(list.length)];
        Animal ia = AnimalFactory.createAnimal(iaName);

        Battle battle = new Battle(player, ia);
        BattleController battleController = new BattleController(battle);
        BattleView battleView = new BattleView(battleController);

        // permettre au controller de revenir vers le menu / afficher résultat
        battleController.setView(battleView);
        battleController.setOnBattleEnded(playerWon -> {
            // crée la vue résultat
            String title = playerWon ? "Victoire !" : "Défaite...";
            String detail = playerWon ? "Bravo, vous avez vaincu votre adversaire." : "Votre Calédomon est K.O.";
            ResultView resultView = new ResultView(title, detail);

            // actions boutons
            resultView.setOnReplay(() -> {
                // replay : relancer un combat entre les mêmes créatures
                // pour l'effet, on crée un nouveau Battle avec mêmes animals
                Battle newBattle = new Battle(battle.getJoueur1().copy(), battle.getJoueur2().copy());
                BattleController bc = new BattleController(newBattle);
                BattleView bv = new BattleView(bc);
                bc.setView(bv);
                bc.setOnBattleEnded(this::showResultSceneFromBoolean);
                // transition
                switchSceneWithFade(bv);
                bc.startBattle();
            });

            resultView.setOnReturnToMenu(() -> {
                // revenir au menu de sélection
                SelectionView selectionView = new SelectionView();
                // IMPORTANT : attacher un nouveau controller pour que les callbacks fonctionnent
                new SelectionController(stage, selectionView);
                switchSceneWithFade(selectionView);
            });

            // afficher la scène résultat
            switchSceneWithFade(resultView);
        });

        // faire une transition en fondu vers la scène de combat
        switchSceneWithFade(battleView);

        stage.setTitle("Combat Calédomon");
        stage.sizeToScene();
        stage.centerOnScreen();

        // start the battle (initializes UI)
        battleController.startBattle();
    }

    private void switchSceneWithFade(javafx.scene.Parent root) {
        Scene scene = new Scene(root, 800, 600);
        // appliquer un fade in sur le root après setScene
        stage.setScene(scene);
        FadeTransition ft = new FadeTransition(Duration.millis(400), root);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    private void showResultSceneFromBoolean(Boolean playerWon) {
        onSelectResult(playerWon);
    }

    private void onSelectResult(Boolean playerWon) {
        
    }
}