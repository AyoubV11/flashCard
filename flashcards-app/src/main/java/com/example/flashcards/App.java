package com.example.flashcards;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main(String[] args)
    {
        // Liste de flashcards
        List<FlashCard> flashList = new ArrayList<>();

        FlashCard flash1 = new FlashCard("2 + 2 =", "4");
        FlashCard flash2 = new FlashCard("2 x 2 =", "4");
        FlashCard flash3 = new FlashCard("7 + 3 =", "10");
        FlashCard flash4 = new FlashCard("1 + 3 =", "4");
        FlashCard flash5 = new FlashCard("8 + 2 =", "10");

        // Ajout manuel dans la liste
        flashList.add(flash1);
        flashList.add(flash2);
        flashList.add(flash3);
        flashList.add(flash4);
        flashList.add(flash5);

        // Création du deck
        Deck deck = new Deck("CALCUL", flashList);

        // Affichage des questions du deck
        System.out.println("Deck: " + deck.getNom());
        for (FlashCard flashcard : deck.getFlashcardList()) {
            System.out.println("Question: " + flashcard.getQuestion());
            System.out.println("Réponse: " + flashcard.getReponse());
            System.out.println();
        }
    }
}
