package com.github.perceptron8.datetime.jpa;

import java.time.YearMonth;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converts {@link YearMonth} to {@link Long} and back again.
 */
@Converter(autoApply = false)
public class YearMonthToLongConverter implements AttributeConverter<YearMonth, Long> {
	@Override
	public Long convertToDatabaseColumn(YearMonth attribute) {
		return attribute == null ? null : (long) attribute.getYear() << 32 | (long) attribute.getMonthValue() << 0;
	}

	@Override
	public YearMonth convertToEntityAttribute(Long dbData) {
		return dbData == null ? null : YearMonth.of((int) (dbData >> 32), (int) (dbData >> 0));
	}
}
