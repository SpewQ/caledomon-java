/**
 * <p>
 * Classe <strong>MainApp</strong>.
 * </p>
 *
 * <p>
 * Cette classe fait partie du cœur applicatif du projet et joue un rôle précis
 * dans l'architecture globale (MVC). Elle encapsule un comportement métier,
 * une logique de contrôle ou un composant d'interface selon son package.
 * </p>
 *
 * <p>
 * Les responsabilités principales de cette classe sont :
 * </p>
 * <ul>
 *   <li>Centraliser la logique associée à MainApp</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package main;

import controller.SelectionController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.SelectionView;

/**
 * Classe publique MainApp pour exécuter le jeu CalédoMon.
 */
public class MainApp extends Application {

    @Override
    /**
     * Méthode publique start pour démarrer le jeu.
     * @param stage : stage récupéré
     */
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
