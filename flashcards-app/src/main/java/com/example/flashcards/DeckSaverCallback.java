package com.example.flashcards;

 // Interface callback pour sauvegarder le deck après la saisie des questions
 interface DeckSaverCallback {
    void onSave(Deck nouveauDeck);
}
