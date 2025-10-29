package view;

import controller.BattleController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BattleView extends BorderPane {

    private final BattleController controller;

    private final Label lblPlayerName = new Label("-");
    private final ProgressBar pbPlayerHp = new ProgressBar(1.0);
    private final Label lblPlayerHp = new Label("0/0");

    private final Label lblIaName = new Label("-");
    private final ProgressBar pbIaHp = new ProgressBar(1.0);
    private final Label lblIaHp = new Label("0/0");

    private final Button btnAttack  = new Button("Attaquer");
    private final Button btnDefend  = new Button("Défendre");
    private final Button btnHeal    = new Button("Soigner");
    private final Button btnSpecial = new Button("Spéciale");

    private final TextArea messages = new TextArea();

    public BattleView(BattleController controller) {
        this.controller = controller;
        buildUI();
        wireActions();
    }

    private void buildUI() {
        setPadding(new Insets(10));

        VBox left = new VBox(8, lblPlayerName, pbPlayerHp, lblPlayerHp);
        left.setAlignment(Pos.CENTER_LEFT);
        left.setPadding(new Insets(10));

        VBox right = new VBox(8, lblIaName, pbIaHp, lblIaHp);
        right.setAlignment(Pos.CENTER_RIGHT);
        right.setPadding(new Insets(10));

        HBox buttons = new HBox(10, btnAttack, btnDefend, btnHeal, btnSpecial);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10));

        messages.setEditable(false);
        messages.setWrapText(true);
        messages.setPrefRowCount(10);

        VBox center = new VBox(10, buttons, messages);
        center.setPadding(new Insets(10));

        setLeft(left);
        setRight(right);
        setCenter(center);
    }

    private void wireActions() {
        btnAttack.setOnAction(e -> controller.onPlayerAction("attaquer"));
        btnDefend.setOnAction(e -> controller.onPlayerAction("defendre"));
        btnHeal.setOnAction(e -> controller.onPlayerAction("soigner"));
        btnSpecial.setOnAction(e -> controller.onPlayerAction("speciale"));
    }

    public void bindNames(String playerName, String iaName) {
        lblPlayerName.setText(playerName);
        lblIaName.setText(iaName);
    }

    public void refreshHp(int playerPv, int iaPv, int playerMax, int iaMax) {
        double pRatio = Math.max(0, Math.min(1.0, (double) playerPv / Math.max(1, playerMax)));
        double iRatio = Math.max(0, Math.min(1.0, (double) iaPv / Math.max(1, iaMax)));
        pbPlayerHp.setProgress(pRatio);
        pbIaHp.setProgress(iRatio);
        lblPlayerHp.setText(playerPv + " / " + playerMax);
        lblIaHp.setText(iaPv + " / " + iaMax);
    }

    public void appendMessage(String msg) {
        messages.appendText(msg + "\n");
    }

    public void disableActions() {
        btnAttack.setDisable(true);
        btnDefend.setDisable(true);
        btnHeal.setDisable(true);
        btnSpecial.setDisable(true);
    }
}