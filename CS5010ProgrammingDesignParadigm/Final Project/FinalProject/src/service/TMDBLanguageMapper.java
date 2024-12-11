package service;

import log.LogHelper;

import java.util.Map;
import java.util.logging.Logger;


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

    public String getCodeByLanguageName(String languageName) {
        String code = LANGUAGE_MAP.getOrDefault(languageName, "Unknown");
        LogHelper.logInfo(logger, "Mapping language name '" + languageName + "' to code: " + code);
        return code;
    }
}