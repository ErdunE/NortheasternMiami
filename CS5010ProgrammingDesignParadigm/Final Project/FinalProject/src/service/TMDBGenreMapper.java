package service;

import java.util.HashMap;
import java.util.Map;

public class TMDBGenreMapper {

    private static final Map<String, Integer> GENRE_MAP = new HashMap<>();

    static {
        GENRE_MAP.put("Action", 28);
        GENRE_MAP.put("Adventure", 12);
        GENRE_MAP.put("Animation", 16);
        GENRE_MAP.put("Comedy", 35);
        GENRE_MAP.put("Crime", 80);
        GENRE_MAP.put("Documentary", 99);
        GENRE_MAP.put("Drama", 18);
        GENRE_MAP.put("Family", 10751);
        GENRE_MAP.put("Fantasy", 14);
        GENRE_MAP.put("History", 36);
        GENRE_MAP.put("Horror", 27);
        GENRE_MAP.put("Music", 10402);
        GENRE_MAP.put("Mystery", 9648);
        GENRE_MAP.put("Romance", 10749);
        GENRE_MAP.put("Science Fiction", 878);
        GENRE_MAP.put("TV Movie", 10770);
        GENRE_MAP.put("Thriller", 53);
        GENRE_MAP.put("War", 10752);
        GENRE_MAP.put("Western", 37);
    }

    public int getGenreIdByName(String genreName) {
        return GENRE_MAP.getOrDefault(genreName, -1);
    }

    public String getGenreNameById(int id) {
        return GENRE_MAP.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(id))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("Unknown");
    }
}