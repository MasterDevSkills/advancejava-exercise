package com.masterdevskills.cha3;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class ReflectionUtil {

	public static <E> E findFieldValue(Object pool, Class<E> fieldType) {

		return Stream.of(pool.getClass().getDeclaredFields())
						.filter(field -> fieldType.isAssignableFrom(field.getType()))
						.findAny()
						.map(field -> setAccessibleAndCast(pool, fieldType, field))
						.orElseThrow(() -> new IllegalArgumentException("Field of type " + fieldType + " not found"));
	}

	private static <E> E setAccessibleAndCast(Object pool, Class<E> fieldType, Field field) {
		field.setAccessible(true);
		try {
			return fieldType.cast(field.get(pool));
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}
}