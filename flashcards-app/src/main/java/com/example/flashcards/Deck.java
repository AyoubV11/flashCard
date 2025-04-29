package com.example.flashcards;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String nom;
    private int nombreQuestions;
    private List<FlashCard> flashcards;

    public Deck(String nom, int nombreQuestions) {
        this.nom = nom;
        this.nombreQuestions = nombreQuestions;
        this.flashcards = new ArrayList<>();
    }

    public Deck(String nom, int nombreQuestions, List<FlashCard> flashcards) {
        this.nom = nom;
        this.nombreQuestions = nombreQuestions;
        this.flashcards = flashcards;
    }

    public String getNom() {
        return nom;
    }

    public int getNombreQuestions() {
        return nombreQuestions;
    }

    public List<FlashCard> getFlashcards() {
        return flashcards;
    }

    public void ajouterFlashCard(FlashCard flashCard) {
        flashcards.add(flashCard);
    }

    public void supprimerFlashCard(FlashCard flashCard) {
        flashcards.remove(flashCard);
    }
}
