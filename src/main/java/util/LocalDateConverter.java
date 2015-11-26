package util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by guilherme on 25/11/15.
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        Instant instant = Instant.from(localDate);
        return Date.from(instant);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        Instant instant = date.toInstant();
        return LocalDate.from(instant);
    }
}
