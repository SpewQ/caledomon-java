package view;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Animal;
import model.actions.Action;
import model.actions.BuffAction;
import model.actions.DebuffAction;

/**
 * Classe publique ActionInfoPane pour afficher une fenêtre contenant les informations d'une capacité
 * lorsqu'on survol un bouton de capacité
 */
public class ActionInfoPane extends VBox {

    private final Label nameText = new Label();
    private final Label typeText = new Label();
    private final Label detailsText = new Label();

    private FadeTransition fadeIn;
    private TranslateTransition slideIn;
    private ParallelTransition showTransition;

    private FadeTransition fadeOut;

    /**
     * Constructeur par défaut
     */
    public ActionInfoPane() {
        setSpacing(8);
        setPadding(new Insets(12));
        setMinWidth(220);
        setPrefWidth(260);
        setMaxWidth(300);

        setMouseTransparent(true);

        setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 0, 0, 0.85),
                new CornerRadii(10),
                Insets.EMPTY
        )));

        setBorder(new Border(new BorderStroke(
                Color.GOLD,
                BorderStrokeStyle.SOLID,
                new CornerRadii(10),
                new BorderWidths(2)
        )));

        nameText.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        typeText.setStyle("-fx-font-size: 13px; -fx-text-fill: white;");
        detailsText.setStyle("-fx-font-size: 13px; -fx-text-fill: white;");

        nameText.setWrapText(true);
        typeText.setWrapText(true);
        detailsText.setWrapText(true);

        getChildren().addAll(nameText, typeText, detailsText);

        applyCss();
        layout();

        buildTransitions();
        setVisible(false);
    }

    /**
     * Méthode privée pour faire les animations de transitions fade in / fade out au survol de la capacité
     */
    private void buildTransitions() {
        fadeIn = new FadeTransition(Duration.millis(180), this);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setInterpolator(Interpolator.EASE_OUT);

        slideIn = new TranslateTransition(Duration.millis(180), this);
        slideIn.setFromY(0);
        slideIn.setToY(0);
        slideIn.setInterpolator(Interpolator.EASE_OUT);

        showTransition = new ParallelTransition(slideIn, fadeIn);

        fadeOut = new FadeTransition(Duration.millis(140), this);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setInterpolator(Interpolator.EASE_IN);
        fadeOut.setOnFinished(e -> setVisible(false));
    }

    /**
     * Affiche les infos d'une action et les stages si Buff/Debuff
     */
    public void showFor(Action action, Animal target, double parentWidth, boolean leftSide) {
        nameText.setText(action.getClass().getSimpleName());

        if (action instanceof BuffAction) typeText.setText("Type : Buff");
        else if (action instanceof DebuffAction) typeText.setText("Type : Débuff");
        else typeText.setText("Type : Attaque");

        StringBuilder sb = new StringBuilder();
        if (action.getPower() > 0) sb.append("Puissance : ").append(action.getPower()).append("\n");
        sb.append("Précision : ").append(action.getAccuracy()).append("%\n");

        // Effets spéciaux
        if (action instanceof BuffAction) sb.append("Buffs appliqués sur l'utilisateur\n");
        if (action instanceof DebuffAction) sb.append("Débuffs appliqués à la cible\n");

        detailsText.setText(sb.toString());

        // Position latérale
        double x = leftSide ? 10 : getParent().getLayoutBounds().getWidth() - getPrefWidth() - 10;
        double y = 50;
        setLayoutX(x);
        setLayoutY(y);

        stopAnimations();
        setOpacity(0);
        setVisible(true);
        showTransition.playFromStart();
    }

    /**
     * Méthode publique pour masquer la fenêtre lorsque le curseur n'est pas sur le bouton d'action
     */
    public void hidePane() {
        stopAnimations();
        fadeOut.playFromStart();
    }

    /**
     * Méthode privée pour arrêter les animations lorsque c'est nécessaire
     */
    private void stopAnimations() {
        if (showTransition != null && showTransition.getStatus() == Animation.Status.RUNNING) showTransition.stop();
        if (fadeOut != null && fadeOut.getStatus() == Animation.Status.RUNNING) fadeOut.stop();
    }
}
