/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artec.apps.facebook.utils;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Translator {

    public enum Language {
        EN, ES
    }

    public static final String NAME_TEMPLATE = "name_%s_%s.properties";
    public static final String DESCRIPTION_TEMPLATE = "description_%s_%s.properties";

    private final Properties translations = new Properties();

    public Translator(Language from, Language to, String TEMPLATE) {
        String translationFile = String.format(TEMPLATE, from, to);
        try {
            URL url = new URL("https://imstudio.tv/apps/fb/poststats/prop/"+translationFile);
            translations.load(url.openStream());
        } catch (final IOException e) {
            throw new RuntimeException("Could not read: " + translationFile, e);
        }
    }

    public String[] translate(String text) {
        String[] source = normalizeText(text);
        List<String> translation = new ArrayList<>();
        for (String sourceWord : source) {
            translation.add(translateWord(sourceWord));
        }
        return translation.toArray(new String[source.length]);
    }

    private String translateWord(String sourceWord) {
        Object value = translations.get(sourceWord);
        String translatedWord;
        if (value != null) {
            translatedWord = String.valueOf(value);
        }
        else {
            // if no translation is found, add the source word with a question mark
            translatedWord = sourceWord + "?";
        }
        return translatedWord;
    }

    private String[] normalizeText(String text) {
        String alphaText = text.replaceAll("[^A-Za-z]-", " ");
        return alphaText.split("\\s+");
    }

   
}