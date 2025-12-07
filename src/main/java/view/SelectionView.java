package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SelectionView extends VBox {

    // callback when selection confirmed
    private java.util.function.Consumer<String> onSelected;

    public SelectionView() {
        setSpacing(20);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Text title = new Text("Choisissez votre Calédomon");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // --- Boutons des Calédomons ---

        Button btnBaobab = createAnimalButton("Baobab");
        Button btnCagou = createAnimalButton("Cagou");
        Button btnCerf = createAnimalButton("Cerf");
        Button btnDawa = createAnimalButton("Dawa");
        Button btnGecko = createAnimalButton("Gecko");
        Button btnNotou = createAnimalButton("Notou");
        Button btnPicot = createAnimalButton("Picot");
        Button btnRoussette = createAnimalButton("Roussette");
        Button btnTortue = createAnimalButton("Tortue");
        Button btnTricot = createAnimalButton("Tricot Raye");
        Button btnVer = createAnimalButton("Ver");

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

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

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

        getChildren().addAll(title, grid);
    }

    private Button createAnimalButton(String name) {
        Button b = new Button(name);
    
        // --- Image du Calédomon ---
        try {
            Image img = new Image(getClass().getResourceAsStream("/images/" + name.toLowerCase() + ".png"));
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
            -fx-padding: 10 20 10 20;
        """));
        b.setOnMouseExited(e -> b.setStyle(baseStyle));

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


    public void setOnSelectionConfirmed(java.util.function.Consumer<String> callback) {
        this.onSelected = callback;
    }

    private void notifySelection(String name) {
        System.out.println("[DEBUG] Calédomon sélectionné : " + name);
        if (onSelected != null) {
            onSelected.accept(name);
        } else {
            System.out.println("[DEBUG] Aucun callback enregistré !");
        }
    }  

}
