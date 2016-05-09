package zdr;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by MuratovaO on 09.05.2016.
 */
@Convert
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return localDate == null ? null : Date.valueOf(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return date == null ? null : date.toLocalDate();
    }
}
