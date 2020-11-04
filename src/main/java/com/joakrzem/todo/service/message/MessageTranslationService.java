package com.joakrzem.todo.service.message;

import java.util.List;

public interface MessageTranslationService {
    String getMessage(String key);

    boolean reloadMessages(String language);

    List<String> getAvailableLanguages();
}

