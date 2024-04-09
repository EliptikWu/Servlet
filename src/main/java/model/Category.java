package model;

import java.util.Arrays;

public enum Category {

    Female(1),

    Male( 2),

    Unisex(3);


    private final int value;

    Category(int value) {
        this.value = value;
    }

    public static Category fromName(int value){
        return Arrays.stream(Category.values()).filter(e->e.value==value).findFirst().orElseThrow();
    }
}
