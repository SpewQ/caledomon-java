package main;

import controller.SelectionController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.SelectionView;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        SelectionView selectionView = new SelectionView();
        Image icon = new Image(getClass().getResourceAsStream("/images/icon.png"));

        new SelectionController(stage, selectionView);

        Scene selectionScene = new Scene(selectionView, 1280, 720);
        stage.setTitle("Sélection du Calédomon");
        stage.getIcons().add(icon);
        stage.setScene(selectionScene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}