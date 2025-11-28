package main;

import controller.SelectionController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.SelectionView;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        SelectionView selectionView = new SelectionView();
        new SelectionController(stage, selectionView);

        Scene selectionScene = new Scene(selectionView, 800, 500);
        stage.setTitle("Sélection du Calédomon");
        stage.setScene(selectionScene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}