package service;

import log.LogHelper;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Provides a mapping between language names and their corresponding language codes.
 * Allows retrieving the language code for a given language name.
 *
 * Supported languages include English, Mandarin, Korean, Japanese, and more.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class TMDBLanguageMapper {

    private static final Logger logger = LogHelper.getLogger(TMDBLanguageMapper.class);

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
            Map.entry("Portuguese", "pt"),
            Map.entry("Russian", "ru"),
            Map.entry("Hindi", "hi"),
            Map.entry("Arabic", "ar"),
            Map.entry("Turkish", "tr"),
            Map.entry("Thai", "th")
    );

    /**
     * Retrieves the language code for a given language name.
     * If the language name is not found, returns "Unknown".
     *
     * @param languageName The name of the language (e.g., "English", "Mandarin").
     * @return The corresponding language code (e.g., "en", "zh"), or "Unknown" if not found.
     */
    public String getCodeByLanguageName(String languageName) {
        String code = LANGUAGE_MAP.getOrDefault(languageName, "Unknown");
        LogHelper.logInfo(logger, "Mapping language name '" + languageName + "' to code: " + code);
        return code;
    }
}