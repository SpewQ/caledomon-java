package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResultView extends VBox {

    private final Button btnReplay = new Button("Rejouer");
    private final Button btnMenu = new Button("Retour au menu");

    public ResultView(String titleText, String detailText) {
        setSpacing(20);
        setPadding(new Insets(30));
        setAlignment(Pos.CENTER);

        Text title = new Text(titleText);
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");

        Text detail = new Text(detailText);
        detail.setStyle("-fx-font-size: 16px;");

        btnReplay.setPrefWidth(200);
        btnMenu.setPrefWidth(200);

        getChildren().addAll(title, detail, btnReplay, btnMenu);
    }

    public void setOnReplay(Runnable r) {
        btnReplay.setOnAction(e -> r.run());
    }

    public void setOnReturnToMenu(Runnable r) {
        btnMenu.setOnAction(e -> r.run());
    }
}