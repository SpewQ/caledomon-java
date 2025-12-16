/**
 * <p>
 * Classe <strong>ResultView</strong>.
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
 *   <li>Centraliser la logique associée à ResultView</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Classe publique ResultView qui permet d'afficher la vue du résultat du combat (Victoire ou défaite)
 */
public class ResultView extends VBox {

    private final Button btnMenu = new Button("Retour au menu");

    /**
     * Méthode privée pour appliquer un style sur le bouton Retour au menu
     */
    private void styleMenuButton() {
        String baseStyle = """
            -fx-background-color: linear-gradient(#00cfff, #007f5f);
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-background-radius: 15;
            -fx-border-radius: 15;
            -fx-border-color: #ffd700;
            -fx-border-width: 2;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 4,0,0,2);
        """;

        btnMenu.setStyle(baseStyle);

        // Hover effect
        btnMenu.setOnMouseEntered(e -> btnMenu.setStyle("""
            -fx-background-color: linear-gradient(#00e5ff, #00a370);
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-background-radius: 15;
            -fx-border-radius: 15;
            -fx-border-color: #ffd700;
            -fx-border-width: 2;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 6,0,0,3);
        """));

        btnMenu.setOnMouseExited(e -> btnMenu.setStyle(baseStyle));

        // Click animation
        btnMenu.setOnMousePressed(e -> {
            btnMenu.setScaleX(0.95);
            btnMenu.setScaleY(0.95);
        });
        btnMenu.setOnMouseReleased(e -> {
            btnMenu.setScaleX(1.0);
            btnMenu.setScaleY(1.0);
        });
    }

    /**
     * Constructeur de ResultView avec comme paramètres :
     * @param titleText : texte Victoire ou Défaite en chaîne de caractères
     * @param detailText : texte du détail de la victoire ou défaite en chaîne de caractères
     */
    public ResultView(String titleText, String detailText) {
        setSpacing(20);
        setPadding(new Insets(30));
        setAlignment(Pos.CENTER);

        Text title = new Text(titleText);
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
        title.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", heightProperty().multiply(0.04).asString(), ";")
        );

        Text detail = new Text(detailText);
        detail.setStyle("-fx-font-size: 16px;");
        detail.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", heightProperty().multiply(0.04).asString(), ";")
        );

        // Largeur flexible : entre 120 et 300 px, proportionnelle à la fenêtre
        btnMenu.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnMenu.setMinWidth(120);
        btnMenu.setMaxWidth(300);

        // Hauteur flexible : entre 40 et 80 px
        btnMenu.prefHeightProperty().bind(heightProperty().multiply(0.08));
        btnMenu.setMinHeight(40);
        btnMenu.setMaxHeight(80);

        btnMenu.setWrapText(true);
        btnMenu.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        styleMenuButton();
        getChildren().addAll(title, detail, btnMenu);
    }

    /**
     * Méthode publique pour régler le retour au menu
     */
    public void setOnReturnToMenu(Runnable r) {
        btnMenu.setOnAction(e -> r.run());
    }
}