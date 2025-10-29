package main;

import controller.BattleController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Animal;
import model.Battle;
import model.factories.AnimalFactory;
import view.BattleView;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        // Player = Cagou, IA = Tricot Rayé
        Animal player = AnimalFactory.createAnimal("cagou");
        Animal ia = AnimalFactory.createAnimal("tricot raye");  


        Battle battle = new Battle(player, ia);
        BattleController controller = new BattleController(battle);
        BattleView view = new BattleView(controller);

        controller.setView(view);
        controller.startBattle();

        Scene scene = new Scene(view);
        stage.setTitle("Caledomon RPG");
        stage.setScene(scene);
        stage.sizeToScene(); // auto-adjust
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}