#  Roadmap for Entertainment Recommendation System
***

## ✅ Completed Tasks (Final Project)

### 🎯 Core Design and Patterns
- **Strategy Design Pattern**:  
  Implemented to allow dynamic switching between different recommendation strategies.

- **Factory Design Pattern**:  
  Added a factory to create different recommendation strategies based on user input dynamically.

### 🎬 Recommendation Strategies
- **Popular Recommendations**:  
  Fetches and displays popular movies from the TMDB API.

- **Rating-Based Recommendations**:  
  Provides movie recommendations sorted by ratings.

- **Filter-Based Recommendations**:  
  Added support for filtering movies based on genres, ratings, languages, durations, and certifications.

- **Filter-Based Recommendations**:  
  Allows users to filter movies by:
    - **Genres**
    - **Ratings**
    - **Languages**
    - **Duration**
    - **Release Year**
    - **Rating Level (Certification)**

### 💻 User Interface
- **JavaFX UI**:  
  Developed a user-friendly interface for interacting with the recommendation system.

- **Interactive Filters**:  
  Added a dialog for applying various filters (e.g., genre, rating, language, and more).

- **Movie Details Window**:  
  Detailed view of movies, including:
    - Overview
    - Director
    - Cast
    - Genres
    - Rating
    - Language
    - Duration
    - Revenue
    - Keywords
    - **YouTube Trailer Integration** for watching trailers directly.

- **Hover Effects**:  
  Added hover overlays to display additional movie information dynamically.

### 🔍 Search Functionality
- **Movie Search**:  
  Implemented a search feature that allows users to search for movies by keywords.

### 🧪 Testing
- **JUnit Tests**:  
  Comprehensive tests for the core functionalities, including recommendation strategies, factory creation, and TMDB API integration.

### 🔗 Dependencies
- **TMDB Java SDK**:  
  Utilized `java-sdk-23.0.1` for API communication.

- **JSON Library**:  
  Added `json-20240303.jar` for parsing JSON responses.

---

## 🚀 Planned Enhancements

### 📈 Future Improvements
- **Support for TV Shows and Music**:  
  Extend the recommendation system to include TV shows and music.

- **User Profiles**:  
  Implement user accounts to store and personalize preferences.

- **Offline Mode**:  
  Cache data locally for offline use.

- **Advanced Sorting**:  
  Enhance sorting options (e.g., by director, cast, or revenue).

---

# Timeline (Gantt Chart)

| Task                           | Start Date        | End Date          | Status                                                                                                                                                                                                             |
|--------------------------------|-------------------|-------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Implement Core Design          | October   1, 2024 | October   5, 2024 | ![Completed](https://img.shields.io/badge/Status-Completed-brightgreen)                                                                                                                                            |
| Initial Recommendation         | October   6, 2024 | October  18, 2024 | ![Completed](https://img.shields.io/badge/Status-Completed-brightgreen)                                                                                                                                            |
| Add Factory Design Pattern     | October  19, 2024 | October  30, 2024 | ![Completed](https://img.shields.io/badge/Status-Completed-brightgreen)                                                                                                                                            |
| Add UI Implementation          | November  1, 2024 | November 10, 2024 | ![Completed](https://img.shields.io/badge/Status-Completed-brightgreen)                                                                                                                                            |
| Expand User Preferences        | November 11, 2024 | November 20, 2024 | ![Completed](https://img.shields.io/badge/Status-Completed-brightgreen)                                                                                                                                            |
| Final Testing & UI Update      | November 21, 2024 | December 10, 2024 | ![Completed](https://img.shields.io/badge/Status-Completed-brightgreen)  |
| Project Due                    | December 11, 2024 | -                 | ![Planned](https://img.shields.io/badge/Status-Planned-lightgrey)                                                                                                                                                        |
                                                                                                                                               |