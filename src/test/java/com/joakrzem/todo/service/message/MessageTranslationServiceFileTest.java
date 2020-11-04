package com.joakrzem.todo.service.message;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageTranslationServiceFileTest {

    private final static String MESSAGE_KEY = "testMessage";
    private MessageTranslationService messageTranslationService;

    @BeforeEach
    void setUp() throws BackingStoreException {
        clearPreferences();

        messageTranslationService = new MessageTranslationServiceFile();
    }

    @AfterEach
    void clean() throws BackingStoreException {
        clearPreferences();
    }

    void clearPreferences() throws BackingStoreException {
        Preferences preferences = Preferences.userRoot().node(MessageTranslationServiceFile.class.getName());

        preferences.remove("language");
        preferences.flush();
    }

    @Test
    void getMessage_ShouldReturnRightMessage() {
        //When
        String result = messageTranslationService.getMessage(MESSAGE_KEY);

        //Then
        assertEquals("test", result);

    }

    @Test
    void getMessage_Should_Return_Right_Message() {
        //When
        messageTranslationService.reloadMessages("pl_PL");
        String result = messageTranslationService.getMessage(MESSAGE_KEY);

        //Then
        assertEquals("another test", result);

    }

    @Test
    void getAvailableLanguages_ShouldReturnEn_ENAndPl_PL() {

        //When
        List<String> listOfAvailableLanguages = messageTranslationService.getAvailableLanguages();

        //Then
        assertEquals(2, listOfAvailableLanguages.size());
        assertEquals("en_US", listOfAvailableLanguages.get(0));
        assertEquals("pl_PL", listOfAvailableLanguages.get(1));

    }
}