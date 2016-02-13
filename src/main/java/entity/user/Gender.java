package entity.user;

/**
 * Created by guilherme on 10/02/16.
 * Gender of User
 */
public enum Gender {
    MALE("M"), FEMALE("F");

    private String gender;

    Gender(String enumValue) {
        gender = enumValue;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return gender;
    }
}