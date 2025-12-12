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

public class CaledomonInfoPane extends VBox {

    private final Label nameText = new Label();
    private final Label statsText = new Label();
    private final Label typeText = new Label();

    // réutilisables pour éviter la création multiple
    private FadeTransition fadeIn;
    private TranslateTransition slideIn;
    private ParallelTransition showTransition;

    private FadeTransition fadeOut;
    

    public CaledomonInfoPane() {

        setSpacing(6);
        setPadding(new Insets(10));

        // Largeur correcte pour afficher le fond + contenu
        setMinWidth(220);
        setPrefWidth(260);
        setMaxWidth(320);

        // IMPORTANT : ne pas casser le layout !
        setMouseTransparent(true);

        // Fond visible
        setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 0, 0, 0.80),
                new CornerRadii(10),
                Insets.EMPTY
        )));

        // Bordure visible
        setBorder(new Border(new BorderStroke(
                Color.GOLD,
                BorderStrokeStyle.SOLID,
                new CornerRadii(10),
                new BorderWidths(2)
        )));

        nameText.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: white;");
        typeText.setStyle("-fx-font-size: 13px; -fx-text-fill: white;");
        statsText.setStyle("-fx-font-size: 13px; -fx-text-fill: white;");

        nameText.setWrapText(true);
        typeText.setWrapText(true);
        statsText.setWrapText(true);

        // Désactive les ... 
        nameText.setEllipsisString(null);
        typeText.setEllipsisString(null);
        statsText.setEllipsisString(null);

        getChildren().addAll(nameText, typeText, statsText);

        applyCss();
        layout();

        buildTransitions();

        setVisible(false);
    }

    private void buildTransitions() {
        fadeIn = new FadeTransition(Duration.millis(180), this);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setInterpolator(Interpolator.EASE_OUT);

        slideIn = new TranslateTransition(Duration.millis(180), this);
        slideIn.setFromY(12);
        slideIn.setToY(0);
        slideIn.setInterpolator(Interpolator.EASE_OUT);

        showTransition = new ParallelTransition(slideIn, fadeIn);

        fadeOut = new FadeTransition(Duration.millis(140), this);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setInterpolator(Interpolator.EASE_IN);
        fadeOut.setOnFinished(e -> {
            setVisible(false);
            // reset translate to initial (so next show starts from below)
            setTranslateY(12);
        });
    }

    /**
     * Remplit le panneau et l'affiche à la position (x,y) dans le parent.
     * x,y doivent être en coordonnées du parent (layout coordinates).
     */
    public void showForAt(Animal a, double x, double y, double parentWidth, double parentHeight) {
        // remplir textes
        nameText.setText(a.getNom());
        typeText.setText("Type : " + a.getType());
        statsText.setText(
            "PV : " + a.getMaxPv()
            + "   Atk : " + a.getAttaque()
            + "   Def : " + a.getDefense()
            + "   Vit : " + a.getVitesse()
        );

        applyCss();
        layout();

        double paneW = getWidth();
        double paneH = getHeight();

        // Centrer sous le point x (x is left of button). We'll try to center under the button by caller computing desired x.
        double targetX = x;
        double targetY = y;

        // clamp so pane stays inside parent padding (8px)
        double margin = 8;
        if (paneW <= 0) paneW = prefWidth(-1); // fallback
        if (paneH <= 0) paneH = prefHeight(paneW);

        if (targetX + paneW + margin > parentWidth) {
            targetX = Math.max(margin, parentWidth - paneW - margin);
        }
        if (targetX < margin) targetX = margin;

        if (targetY + paneH + margin > parentHeight) {
            // if not enough space below, try to appear above
            double above = y - paneH - 8;
            if (above >= margin) targetY = above;
            else targetY = Math.max(margin, parentHeight - paneH - margin);
        }

        // place and animate
        setLayoutX(targetX);
        setLayoutY(targetY);

        // stop any current transitions so animations don't se chevauchent
        stopAnimations();

        setOpacity(0.0);
        setTranslateY(12);
        setVisible(true);
        showTransition.playFromStart();
    }

    public void hidePane() {
        // stop show transition if running
        stopAnimations();
        fadeOut.playFromStart();
    }

    private void stopAnimations() {
        if (showTransition != null && showTransition.getStatus() == Animation.Status.RUNNING) {
            showTransition.stop();
        }
        if (fadeOut != null && fadeOut.getStatus() == Animation.Status.RUNNING) {
            fadeOut.stop();
        }
    }
}
