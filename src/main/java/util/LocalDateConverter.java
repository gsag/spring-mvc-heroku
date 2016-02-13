package util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/*
 * Created by guilherme on 25/11/15.
 */
@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate entityValue) {
        return (entityValue != null) ? Date.valueOf(entityValue) : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
        return (databaseValue != null) ? databaseValue.toLocalDate() : null;
    }
}
