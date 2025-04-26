package com.example.flashcards;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String nom;
    private List<FlashCard> flashcards;
    private List<String> listDeck = new ArrayList<>();

    public Deck(String nom, List<FlashCard> flashcards) {
        this.nom = nom;
        this.flashcards = flashcards;
        listDeck.add(nom);
    }

    public String getNom() {
        return this.nom;
    }

    public List<FlashCard> getFlashcardList() {
        return this.flashcards;
    }

    public void ajouterFlashCard(FlashCard flashCard) {
        flashcards.add(flashCard);
    }

    public void supprimerFlashCard(FlashCard flashCard) {
        flashcards.remove(flashCard);
    }

    public void listDeck(){
        for (String deck : listDeck){
            System.out.println(deck);
        }
    }
}
