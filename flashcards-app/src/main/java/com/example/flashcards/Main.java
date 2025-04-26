package com.example.flashcards;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
public void start(Stage primaryStage) {
    primaryStage.setTitle("Bienvenue sur Flashcards App 📚");

    // Création du titre
    Label welcomeLabel = new Label("Bienvenue !");
    welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

    // Création des boutons
    Button ajouterDeckButton = new Button("Ajouter un deck");
    Button entrainementButton = new Button("S'entraîner");

    // Actions des boutons
    ajouterDeckButton.setOnAction(e -> {
    Stage newWindow = new Stage();
    newWindow.setTitle("Ajouter un nouveau Deck");

    VBox layout = new VBox(10);

    // Label pour expliquer
    Label label = new Label("Entrez le nom du nouveau deck :");

    // Champ de texte
    TextField inputField = new TextField();
    inputField.setPromptText("Nom du deck..."); // Texte d'aide grisé

    Label label2 = new Label("Entrez la question:");

    // Champ de texte
    TextField inputField2 = new TextField();
    inputField2.setPromptText("Question");

    // Bouton pour valider
    Button validerButton = new Button("Valider");

    // Action quand on clique sur "Valider"
    validerButton.setOnAction(event -> {
        String nomDuDeck = inputField.getText();
        System.out.println("Nom du deck saisi : " + nomDuDeck);
        newWindow.close(); // Ferme la fenêtre après
    });

    layout.getChildren().addAll(label,label2, inputField,inputField2, validerButton);

    Scene newScene = new Scene(layout, 400, 300);
    newWindow.setScene(newScene);
    newWindow.show();
});

    entrainementButton.setOnAction(e -> {
        System.out.println("Bouton S'entraîner cliqué !");
    });

    // Organisation verticale
    VBox layout = new VBox(20);
    layout.getChildren().addAll(welcomeLabel, ajouterDeckButton, entrainementButton);
    layout.setStyle("-fx-alignment: center; -fx-padding: 20;"); // Centrage + marges

    Scene introScene = new Scene(layout, 1500, 1500);
    primaryStage.setScene(introScene);
    primaryStage.setFullScreen(true);
    primaryStage.centerOnScreen(); // Centre la fenêtre sur l'écran
    primaryStage.show();
}

    public static void main(String[] args) {
        launch(args);
    }
}

