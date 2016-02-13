package util;

import entity.user.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by guilherme on 12/02/16.
 */
@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.equals(Gender.MALE)? "M" : "F";
    }

    @Override
    public Gender convertToEntityAttribute(String gender) {
        return gender.equals("M")? Gender.MALE : Gender.FEMALE;
    }
}
