package com.example.flashcards;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    // Définition de constantes pour simplifier les changements globaux
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_SPACING = 20;
    private static final int MARGIN = 700;
    private static final int RECTANGLE_WIDTH = 200;
    private static final int RECTANGLE_HEIGHT = 100;
    private static final int PADDING = 10;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bienvenue sur Flashcards App 📚");

        // Création du titre
        Label welcomeLabel = createLabel("Bienvenue !", 24);

        // Création des boutons
        Button ajouterDeckButton = createButton("Ajouter un deck");
        Button entrainementButton = createButton("S'entraîner");

        // Actions des boutons
        ajouterDeckButton.setOnAction(e -> openAddDeckWindow());
        entrainementButton.setOnAction(e -> {
            System.out.println("Bouton S'entraîner cliqué !");
            displayDeck();
        });

        // Organisation verticale
        VBox layout = new VBox(BUTTON_SPACING);
        layout.getChildren().addAll(welcomeLabel, ajouterDeckButton, entrainementButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: " + MARGIN + ";");

        Scene introScene = new Scene(layout, 1500, 1500);
        primaryStage.setScene(introScene);
        primaryStage.setFullScreen(true);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void openAddDeckWindow() {
        Stage newWindow = new Stage();
        newWindow.setTitle("Ajouter un nouveau Deck");

        VBox layout = new VBox(PADDING);

        // Label pour expliquer
        Label label = createLabel("Entrez le nom du nouveau deck :", 14);
        TextField inputField = createTextField("Nom du deck...");
        Label label2 = createLabel("Entrez le nombre de questions:", 14);
        TextField inputField2 = createTextField("Nombre de questions");

        Button validerButton = createButton("Valider");
        validerButton.setOnAction(event -> {
            String nomDuDeck = inputField.getText();
            String nombreQuestionsStr = inputField2.getText();

            if (nomDuDeck.isEmpty() || nombreQuestionsStr.isEmpty()) {
                System.out.println("Veuillez remplir tous les champs.");
                return;
            }

            try {
                int nombreQuestions = Integer.parseInt(nombreQuestionsStr);
                openQuestionsWindow(nomDuDeck, nombreQuestions, nouveauDeck -> {
                    DeckSaver.saveDeck(nouveauDeck);
                    System.out.println("Deck enregistré : " + nomDuDeck);
                    newWindow.close();
                });

            } catch (NumberFormatException e1) {
                System.out.println("Le nombre de questions doit être un entier.");
            }
        });

        layout.getChildren().addAll(label, inputField, label2, inputField2, validerButton);

        Scene newScene = new Scene(layout, 400, 300);
        newWindow.setScene(newScene);
        newWindow.show();
    }

    private void openQuestionsWindow(String nomDuDeck, int nombreQuestions, DeckSaverCallback callback) {
        Stage questionStage = new Stage();
        VBox layout = new VBox(PADDING);

        List<TextField> questionFields = new ArrayList<>();
        List<TextField> reponseFields = new ArrayList<>();

        for (int i = 0; i < nombreQuestions; i++) {
            TextField questionField = createTextField("Question " + (i + 1));
            TextField reponseField = createTextField("Réponse " + (i + 1));

            layout.getChildren().addAll(new Label("Carte " + (i + 1) + ":"), questionField, reponseField);
            questionFields.add(questionField);
            reponseFields.add(reponseField);
        }

        Button enregistrerButton = createButton("Enregistrer");
        enregistrerButton.setOnAction(e -> {
            List<FlashCard> flashcards = new ArrayList<>();
            for (int i = 0; i < nombreQuestions; i++) {
                String question = questionFields.get(i).getText();
                String reponse = reponseFields.get(i).getText();
                flashcards.add(new FlashCard(question, reponse));
            }

            Deck completeDeck = new Deck(nomDuDeck, nombreQuestions, flashcards);
            callback.onSave(completeDeck);
            questionStage.close();
        });

        layout.getChildren().add(enregistrerButton);
        Scene scene = new Scene(layout, 400, 600);
        questionStage.setScene(scene);
        questionStage.setTitle("Ajout des questions");
        questionStage.show();
    }

    public void displayDeck() {
        List<Deck> listDeck = DeckSaver.loadDecks();

        // Création d'une fenêtre principale
        Stage stage = new Stage();
        VBox vbox = new VBox(PADDING); // Conteneur vertical pour les lignes de decks

        // Centrer le contenu de la VBox
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-padding: " + MARGIN + ";");

        HBox hbox = new HBox(PADDING); // Conteneur horizontal pour les decks (pour une ligne de 3)

        int count = 0;
        for (Deck deck : listDeck) {
            StackPane stackPane = createDeckPane(deck);
            hbox.getChildren().add(stackPane);

            count++;
            if (count == 3) {
                vbox.getChildren().add(hbox);
                hbox = new HBox(PADDING);
                count = 0;
            }
        }

        if (!hbox.getChildren().isEmpty()) {
            vbox.getChildren().add(hbox);
        }

        Scene scene = new Scene(vbox, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Liste des Decks");
        stage.setScene(scene);
        stage.show();
    }

    private StackPane createDeckPane(Deck deck) {
        Rectangle rectangle = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setStroke(Color.BLACK);

        Label label = createLabel(deck.getNom(), 16);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, label);
        stackPane.setMinSize(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);

        stackPane.setOnMouseClicked(event -> {
            System.out.println("Deck sélectionné : " + deck.getNom());
        });

        return stackPane;
    }

    private Label createLabel(String text, int fontSize) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: " + fontSize + "px; -fx-font-weight: bold;");
        return label;
    }

    private TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        return textField;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
