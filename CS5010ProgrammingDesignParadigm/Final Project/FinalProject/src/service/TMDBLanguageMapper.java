package service;

import java.util.Map;

public class TMDBLanguageMapper {

    private static final Map<String, String> LANGUAGE_MAP = Map.ofEntries(
            Map.entry("English", "en"),
            Map.entry("Mandarin", "zh"),
            Map.entry("Cantonese", "yue"),
            Map.entry("Korean", "ko"),
            Map.entry("Japanese", "ja"),
            Map.entry("Spanish", "es"),
            Map.entry("French", "fr"),
            Map.entry("German", "de"),
            Map.entry("Italian", "it"),
            Map.entry("Other", "")
    );

    public String getCodeByLanguageName(String languageName) {
        return LANGUAGE_MAP.getOrDefault(languageName, "Unknown");
    }
}