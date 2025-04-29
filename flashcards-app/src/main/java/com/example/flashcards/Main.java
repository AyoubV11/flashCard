package com.example.flashcards;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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

            // Champ de texte pour le nom du deck
            TextField inputField = new TextField();
            inputField.setPromptText("Nom du deck...");

            Label label2 = new Label("Entrez le nombre de questions:");

            // Champ de texte pour le nombre de questions
            TextField inputField2 = new TextField();
            inputField2.setPromptText("Nombre de questions");

            // Bouton pour valider
            Button validerButton = new Button("Valider");

            validerButton.setOnAction(event -> {
                String nomDuDeck = inputField.getText();
                String nombreQuestionsStr = inputField2.getText();

                if (nomDuDeck.isEmpty() || nombreQuestionsStr.isEmpty()) {
                    System.out.println("Veuillez remplir tous les champs.");
                    return;
                }

                try {
                    int nombreQuestions = Integer.parseInt(nombreQuestionsStr);

                    // Ouvrir une nouvelle fenêtre pour entrer les questions et réponses
                    openQuestionsWindow(nomDuDeck,nombreQuestions,nouveauDeck -> {
                        // Sauvegarder le deck après l'ajout des flashcards
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
        });

        entrainementButton.setOnAction(e -> {
            System.out.println("Bouton S'entraîner cliqué !");
        });

        // Organisation verticale
        VBox layout = new VBox(20);
        layout.getChildren().addAll(welcomeLabel, ajouterDeckButton, entrainementButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Scene introScene = new Scene(layout, 1500, 1500);
        primaryStage.setScene(introScene);
        primaryStage.setFullScreen(true);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void openQuestionsWindow(String nomDuDeck, int nombreQuestions, DeckSaverCallback callback) {
        // Création d'une nouvelle fenêtre
        Stage questionStage = new Stage();
        VBox layout = new VBox(10);
    
        List<TextField> questionFields = new ArrayList<>();
        List<TextField> reponseFields = new ArrayList<>();
    
        for (int i = 0; i < nombreQuestions; i++) {
            TextField questionField = new TextField();
            questionField.setPromptText("Question " + (i + 1));
            TextField reponseField = new TextField();
            reponseField.setPromptText("Réponse " + (i + 1));
    
            layout.getChildren().addAll(new Label("Carte " + (i + 1) + ":"), questionField, reponseField);
            questionFields.add(questionField);
            reponseFields.add(reponseField);
        }
    
        Button enregistrerButton = new Button("Enregistrer");
    
        enregistrerButton.setOnAction(e -> {
            List<FlashCard> flashcards = new ArrayList<>();
            for (int i = 0; i < nombreQuestions; i++) {
                String question = questionFields.get(i).getText();
                String reponse = reponseFields.get(i).getText();
                flashcards.add(new FlashCard(question, reponse));
            }
    
            Deck completeDeck = new Deck(nomDuDeck,nombreQuestions, flashcards);
            callback.onSave(completeDeck);
            questionStage.close();
        });
    
        layout.getChildren().add(enregistrerButton);
        Scene scene = new Scene(layout, 400, 600);
        questionStage.setScene(scene);
        questionStage.setTitle("Ajout des questions");
        questionStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
