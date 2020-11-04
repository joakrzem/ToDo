package com.joakrzem.todo.service.message;

import com.joakrzem.todo.utils.FileHelper;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class MessageTranslationServiceFile implements MessageTranslationService {
    private static final String LANGUAGE_KEY = "language";
    private static final String LANGUAGE_FILE_PATH = "languages/%s.yml";
    private static final String LANGUAGE_FOLDER_NAME = "languages";

    private final Yaml yaml = new Yaml();
    private final Preferences preferences = Preferences.userRoot().node(this.getClass().getName());

    private Map<String, String> translationMessages;

    public MessageTranslationServiceFile() {
        String language = preferences.get(LANGUAGE_KEY, String.format(LANGUAGE_FILE_PATH, "en_US"));

        reloadMessages(language);
    }

    @Override
    public String getMessage(String key) {
        return translationMessages.get(key);
    }

    @Override
    public boolean reloadMessages(String language) {
        String fileName = language;

        if (!language.contains("languages/")) {
            fileName = String.format(LANGUAGE_FILE_PATH, language);
        }

        String languageFile = FileHelper.readFile(fileName);

        if (languageFile == null) {
            return false;
        }

        translationMessages = yaml.load(languageFile);
        preferences.put(LANGUAGE_KEY, fileName);

        return true;
    }

    @Override
    public List<String> getAvailableLanguages() {
        return FileHelper.getFileNamesInDirectory(LANGUAGE_FOLDER_NAME).stream()
                .map(fileName -> fileName.replace(".yml", ""))
                .collect(Collectors.toList());
    }


}
