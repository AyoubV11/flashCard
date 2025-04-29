package com.example.flashcards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DeckSaver {
    private static final String FILE_PATH = "decks.json";

    /**
     * Sauvegarde un deck en l'ajoutant à la liste existante.
     */
    public static boolean saveDeck(Deck newDeck) {
        List<Deck> decks = loadDecks(); // Charge les anciens decks

        decks.add(newDeck); // Ajoute le nouveau

        try (Writer writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(decks, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Charge tous les decks depuis le fichier JSON.
     */
    public static List<Deck> loadDecks() {
        List<Deck> decks = new ArrayList<>();
        Gson gson = new Gson();

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Deck>>() {}.getType();
            decks = gson.fromJson(reader, listType);

            if (decks == null) {
                decks = new ArrayList<>();
            }

        } catch (FileNotFoundException e) {
            // Pas de fichier = pas de deck existant
        } catch (IOException e) {
            e.printStackTrace();
        }

        return decks;
    }
}
