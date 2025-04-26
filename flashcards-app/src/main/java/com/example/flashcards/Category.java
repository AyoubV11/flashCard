package com.example.flashcards;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Category {
    private String nom;
    private List<Deck> listeDeck = new ArrayList<>();
    private List<FlashCard> liste = new ArrayList<>();


    public Category(String nom){
        this.nom=nom;
    }

    public void ajoutListCategorie(Deck f){
        listeDeck.add(f);
    }

    public int nbDeckCategory(){
        return this.listeDeck.size();
    }
}
