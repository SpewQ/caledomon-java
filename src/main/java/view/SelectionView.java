/**
 * <p>
 * Classe <strong>SelectionView</strong>.
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
 *   <li>Centraliser la logique associée à SelectionView</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.animals.Baobab;
import model.animals.Cagou;
import model.animals.Cerf;
import model.animals.Dawa;
import model.animals.Gecko;
import model.animals.Notou;
import model.animals.Picot;
import model.animals.Roussette;
import model.animals.Tortue;
import model.animals.TricotRaye;
import model.animals.Ver;

/**
 * Classe publique SelectionView représentant la vue de sélection d'un CalédoMon
 */
public class SelectionView extends VBox {

    // callback quand la sélection est confirmée
    private java.util.function.Consumer<String> onSelected;
    private final CaledomonInfoPane infoPane = new CaledomonInfoPane();

    /**
     * Constructeur par défaut
     */
    public SelectionView() {
        setSpacing(20);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);

        // --- Logo du jeu ---
        try {
            Image logoImg = new Image(getClass().getResourceAsStream("/images/icon.png"));
            ImageView logoView = new ImageView(logoImg);
            logoView.setFitWidth(200); // largeur souhaitée
            logoView.setPreserveRatio(true);
            getChildren().add(logoView); // ajoute le logo en premier
        } catch (Exception e) {
            System.err.println("Logo introuvable : " + e.getMessage());
        }

        Text title = new Text("Choisissez votre Calédomon");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        // Layout principal
        StackPane rootLayer = new StackPane(grid, infoPane);
        rootLayer.setPrefHeight(480);
        StackPane.setAlignment(infoPane, Pos.TOP_LEFT);

        // --- Boutons des Calédomons ---

        Button btnBaobab = createAnimalButton("Baobab", new Baobab(), rootLayer);
        Button btnCagou = createAnimalButton("Cagou", new Cagou(), rootLayer);
        Button btnCerf = createAnimalButton("Cerf", new Cerf(), rootLayer);
        Button btnDawa = createAnimalButton("Dawa", new Dawa(), rootLayer);
        Button btnGecko = createAnimalButton("Gecko", new Gecko(), rootLayer);
        Button btnNotou = createAnimalButton("Notou", new Notou(), rootLayer);
        Button btnPicot = createAnimalButton("Picot", new Picot(), rootLayer);
        Button btnRoussette = createAnimalButton("Roussette", new Roussette(), rootLayer);
        Button btnTortue = createAnimalButton("Tortue", new Tortue(), rootLayer);
        Button btnTricot = createAnimalButton("Tricot Raye", new TricotRaye(), rootLayer);
        Button btnVer = createAnimalButton("Ver", new Ver(), rootLayer);

        btnBaobab.setOnAction(e -> notifySelection("Baobab"));
        btnCagou.setOnAction(e -> notifySelection("Cagou"));
        btnCerf.setOnAction(e -> notifySelection("Cerf"));
        btnDawa.setOnAction(e -> notifySelection("Dawa"));
        btnGecko.setOnAction(e -> notifySelection("Gecko"));
        btnNotou.setOnAction(e -> notifySelection("Notou"));
        btnPicot.setOnAction(e -> notifySelection("Picot"));
        btnRoussette.setOnAction(e -> notifySelection("Roussette"));
        btnTortue.setOnAction(e -> notifySelection("Tortue"));
        btnTricot.setOnAction(e -> notifySelection("Tricot Raye"));
        btnVer.setOnAction(e -> notifySelection("Ver"));

        title.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", heightProperty().multiply(0.06).asString(), ";")
        );

        btnBaobab.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnCagou.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnCerf.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnDawa.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnGecko.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnNotou.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnPicot.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnRoussette.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnTortue.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnTricot.prefWidthProperty().bind(widthProperty().multiply(0.25));
        btnVer.prefWidthProperty().bind(widthProperty().multiply(0.25));

        Button[] buttons = {
            btnBaobab, btnCagou, btnCerf, btnDawa, btnGecko, btnNotou,
            btnPicot, btnRoussette, btnTortue, btnTricot, btnVer
        };

        // 3 boutons par ligne
        int columns = 3;
        for (int i = 0; i < buttons.length; i++) {
            int row = i / columns;
            int col = i % columns;
            grid.add(buttons[i], col, row);
        }
        

        getChildren().addAll(title, rootLayer);
    }

    /**
     * Méthode privée pour créer un bouton d'un CalédoMon
     * @param name : nom du CalédoMon en chaîne de caractères
     * @param animalData : instance du CalédoMon récupérée
     * @param rootLayer : Layout du menu de sélection sous StackPane
     */
    private Button createAnimalButton(String name, model.Animal animalData, StackPane rootLayer) {
        Button b = new Button(name);
    
        // --- Image du Calédomon ---
        try {
            String fileName = name.toLowerCase().replace(" ", "_") + ".png";
            Image img = new Image(getClass().getResourceAsStream("/images/" + fileName));
            ImageView iv = new ImageView(img);
            iv.setFitHeight(80);
            iv.setPreserveRatio(true);
            b.setGraphic(iv);
            b.setContentDisplay(javafx.scene.control.ContentDisplay.TOP); // image au-dessus du texte
        } catch (Exception ignored) {
            // si image manquante on garde juste le texte
        }

        // --- Style Pokémon / Nouvelle-Calédonie ---
        String baseStyle = """
            -fx-background-color: linear-gradient(#00cfff, #007f5f);
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-background-radius: 15;
            -fx-border-radius: 15;
            -fx-border-color: #ffd700;
            -fx-border-width: 2;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 4,0,0,2);
            -fx-padding: 10 20 10 20;
        """;
        b.setStyle(baseStyle);

        // --- Hover effect ---
        b.setOnMouseEntered(e -> {
            // Récupère les bounds du bouton en coordonnées de scène
            Bounds btnBounds = b.localToScene(b.getBoundsInLocal());

            // Convertit en coordonnées locales du parent (rootLayer)
            javafx.geometry.Point2D localPoint = rootLayer.sceneToLocal(btnBounds.getMinX(), btnBounds.getMaxY());

            // Centre horizontalement le panneau sous le bouton
            double candidateX = localPoint.getX() + (btnBounds.getWidth() / 2.0) - (infoPane.getPrefWidth() / 2.0);
            double candidateY = localPoint.getY() + 6; // petit margin de 6px sous le bouton

            // Clamp pour rester à l’intérieur de rootLayer
            double parentW = rootLayer.getWidth();
            double parentH = rootLayer.getHeight();

            double paneW = infoPane.getPrefWidth();
            double paneH = infoPane.getPrefHeight();

            if (candidateX < 8) candidateX = 8;
            if (candidateX + paneW + 8 > parentW) candidateX = parentW - paneW - 8;

            if (candidateY + paneH + 8 > parentH) {
                // Si pas assez de place en bas, on place au-dessus du bouton
                candidateY = localPoint.getY() - paneH - 6;
                if (candidateY < 8) candidateY = 8;
            }

            infoPane.showForAt(animalData, candidateX, candidateY, parentW, parentH);
        });


        b.setOnMouseExited(e -> {
            infoPane.hidePane();
        });


        // --- Click animation ---
        b.setOnMousePressed(e -> {
            b.setScaleX(0.95);
            b.setScaleY(0.95);
        });
        b.setOnMouseReleased(e -> {
            b.setScaleX(1.0);
            b.setScaleY(1.0);
        });

        return b;
    }

    /**
     * Méthode publique pour faire un callback à la sélection du CalédoMon
     */
    public void setOnSelectionConfirmed(java.util.function.Consumer<String> callback) {
        this.onSelected = callback;
    }

    /**
     * Méthode privée pour notifier l'application de la sélection d'un CalédoMon
     * @param name : nom du CalédoMon en chaîne de caractères
     */
    private void notifySelection(String name) {
        System.out.println("[DEBUG] Calédomon sélectionné : " + name);
        if (onSelected != null) {
            onSelected.accept(name);
        } else {
            System.out.println("[DEBUG] Aucun callback enregistré !");
        }
    }  

}
