package com.example.flashcards;

public class FlashCard {
    private String question;
    private String reponse;

    public FlashCard(String question, String reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    public String getReponse() {
        return this.reponse;
    }

    public String getQuestion() {
        return this.question;
    }
}
