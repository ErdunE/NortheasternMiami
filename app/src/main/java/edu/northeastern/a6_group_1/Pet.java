package edu.northeastern.a6_group_1;

public class Pet {
    private String name;
    private String type;
    private String breed;
    private String imageUrl;

    public Pet(String name, String type, String breed, String imageUrl) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
