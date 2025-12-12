package main;

import controller.SelectionController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.SelectionView;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        // Conteneur racine unique pour toutes les vues
        StackPane root = new StackPane();

        // Scène unique
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sélection du Calédomon");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        stage.setMaximized(true);

        // Empêcher la réduction de la fenêtre (optionnel)
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        // Créer et afficher la vue de sélection
        SelectionView selectionView = new SelectionView();
        root.getChildren().add(selectionView);

        // Attacher le controller
        new SelectionController(stage, selectionView);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
