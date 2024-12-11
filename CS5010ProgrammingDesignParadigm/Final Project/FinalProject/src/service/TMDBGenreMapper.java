package service;

import log.LogHelper;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Provides utility methods for mapping genre names to their corresponding IDs
 * and vice versa using a predefined genre map from TMDB.
 *
 * This class helps to translate between genre names and TMDB genre IDs.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class TMDBGenreMapper {

    private static final Logger logger = LogHelper.getLogger(TMDBGenreMapper.class);

    private static final Map<String, Integer> GENRE_MAP = Map.ofEntries(
            Map.entry("Action", 28),
            Map.entry("Adventure", 12),
            Map.entry("Animation", 16),
            Map.entry("Comedy", 35),
            Map.entry("Crime", 80),
            Map.entry("Documentary", 99),
            Map.entry("Drama", 18),
            Map.entry("Family", 10751),
            Map.entry("Fantasy", 14),
            Map.entry("History", 36),
            Map.entry("Horror", 27),
            Map.entry("Music", 10402),
            Map.entry("Mystery", 9648),
            Map.entry("Romance", 10749),
            Map.entry("Science Fiction", 878),
            Map.entry("TV Movie", 10770),
            Map.entry("Thriller", 53),
            Map.entry("War", 10752),
            Map.entry("Western", 37)
    );

    /**
     * Gets the genre ID for a given genre name.
     *
     * @param genreName The name of the genre (e.g., "Action", "Comedy").
     * @return The corresponding genre ID, or -1 if the genre name is not found.
     */
    public int getIdByGenreName(String genreName) {
        int id = GENRE_MAP.getOrDefault(genreName, -1);
        LogHelper.logInfo(logger, "Mapping genre name '" + genreName + "' to ID: " + id);
        return id;
    }

    /**
     * Gets the genre name for a given genre ID.
     *
     * @param id The genre ID (e.g., 28, 35).
     * @return The corresponding genre name, or "Unknown" if the ID is not found.
     */
    public String getNameByGenreId(int id) {
        String genreName = GENRE_MAP.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == id)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("Unknown");
        LogHelper.logInfo(logger, "Mapping genre ID '" + id + "' to name: " + genreName);
        return genreName;
    }
}