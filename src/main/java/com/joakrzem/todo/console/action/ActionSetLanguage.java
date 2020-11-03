package com.joakrzem.todo.console.action;

import com.joakrzem.todo.console.ConsoleAppUtils;
import com.joakrzem.todo.service.message.MessageTranslationService;

import java.util.List;

public class ActionSetLanguage implements Action {
    private final MessageTranslationService messageTranslationService;
    private final ConsoleAppUtils consoleAppUtils;

    public ActionSetLanguage(MessageTranslationService messageTranslationService) {
        this.messageTranslationService = messageTranslationService;
        this.consoleAppUtils = new ConsoleAppUtils(messageTranslationService);
    }

    @Override
    public String description() {
        return messageTranslationService.getMessage("actionSetLanguageDescription");
    }

    @Override
    public void execute() {
        List<String> availableLanguages = messageTranslationService.getAvailableLanguages();

        for (int i = 1; i <= availableLanguages.size(); i++) {
            System.out.println(i + ". " + availableLanguages.get(i - 1));
        }

        int choice = consoleAppUtils.getIntFromConsole("enter language choice");
        messageTranslationService.reloadMessages(availableLanguages.get(choice - 1));
    }
}
