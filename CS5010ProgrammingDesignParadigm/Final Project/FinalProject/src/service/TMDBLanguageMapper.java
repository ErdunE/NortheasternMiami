package service;

import java.util.HashMap;
import java.util.Map;

public class TMDBLanguageMapper {

    private static final Map<String, String> LANGUAGE_MAP = new HashMap<>();

    static {
        LANGUAGE_MAP.put("English", "en");
        LANGUAGE_MAP.put("Mandarin", "zh");
        LANGUAGE_MAP.put("Cantonese", "yue");
        LANGUAGE_MAP.put("Korean", "ko");
        LANGUAGE_MAP.put("Japanese", "ja");
        LANGUAGE_MAP.put("Spanish", "es");
        LANGUAGE_MAP.put("French", "fr");
        LANGUAGE_MAP.put("German", "de");
        LANGUAGE_MAP.put("Italian", "it");
        LANGUAGE_MAP.put("Other", "");
    }

    public String getLanguageCodeByName(String languageName) {
        return LANGUAGE_MAP.getOrDefault(languageName, null);
    }
}