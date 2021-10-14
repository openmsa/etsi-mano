package com.ubiqube.etsi.mano.mapper;

import java.util.EnumSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ManoLenientStringToEnum implements ConverterFactory<String, Enum> {

	private static final Logger LOG = LoggerFactory.getLogger(ManoLenientStringToEnum.class);

	@Override
	public <T extends Enum> Converter<String, T> getConverter(final Class<T> targetType) {
		return new StringToEnum(getEnumType(targetType));
	}

	private static Class<?> getEnumType(final Class<?> targetType) {
		Class<?> enumType = targetType;
		while (enumType != null && !enumType.isEnum()) {
			enumType = enumType.getSuperclass();
		}
		Assert.notNull(enumType, () -> "The target type " + targetType.getName() + " does not refer to an enum");
		return enumType;
	}

	private static class StringToEnum<T extends Enum> implements Converter<String, T> {

		private final Class<T> enumType;

		StringToEnum(final Class<T> enumType) {
			this.enumType = enumType;
		}

		@Override
		@Nullable
		public T convert(final String source) {
			if (source.isEmpty()) {
				// It's an empty enum identifier: reset the enum value to null.
				return null;
			}
			try {
				return (T) Enum.valueOf(this.enumType, source);
			} catch (final Exception ex) {
				LOG.trace("Enum problem", ex);
				return findEnum(source);
			}
		}

		private T findEnum(final String value) {
			final String name = getCanonicalName(value);
			for (final T candidate : (Set<T>) EnumSet.allOf(this.enumType)) {
				final String candidateName = getCanonicalName(candidate.name());
				if (name.equals(candidateName)) {
					return candidate;
				}
			}
			throw new IllegalArgumentException("No enum constant " + this.enumType.getCanonicalName() + "." + value);
		}

		private static String getCanonicalName(final String name) {
			final StringBuilder canonicalName = new StringBuilder(name.length());
			name.chars().filter(Character::isLetterOrDigit).map(Character::toLowerCase)
					.forEach(c -> canonicalName.append((char) c));
			return canonicalName.toString();
		}

	}

}
