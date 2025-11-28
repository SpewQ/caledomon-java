package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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

        Button btnCagou = createAnimalButton("Cagou");
        Button btnGecko = createAnimalButton("Gecko");
        Button btnTricot = createAnimalButton("Tricot Raye");

        btnCagou.setOnAction(e -> notifySelection("Cagou"));
        btnGecko.setOnAction(e -> notifySelection("Gecko"));
        btnTricot.setOnAction(e -> notifySelection("Tricot Raye"));

        HBox box = new HBox(20, btnCagou, btnGecko, btnTricot);
        box.setAlignment(Pos.CENTER);

        getChildren().addAll(title, box);
    }

    private Button createAnimalButton(String name) {
        Button b = new Button(name);
        try {
            Image img = new Image(getClass().getResourceAsStream("/images/" + name.toLowerCase() + ".jpg"));
            ImageView iv = new ImageView(img);
            iv.setFitHeight(80);
            iv.setPreserveRatio(true);
            b.setGraphic(iv);
        } catch (Exception ignored) {
            // si image manquante on garde juste le texte
        }
        b.setStyle("-fx-font-size: 14px; -fx-padding: 10;");
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
