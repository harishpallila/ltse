package com.ltse;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ValidatorUtil {
	public static final String datePattern = "MM/d/yyyy HH:mm:ss";
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
	
	private final static Optional<Long> longEmpty = Optional.empty();
	private final static Optional<Double> doubleEmpty = Optional.empty();
	private final static Optional<Timestamp> timestampEmpty = Optional.empty();
	private final static Optional<String> stringEmpty = Optional.empty();
	
	private static boolean isValidText(final String input) {
		return null == input || "".equals(input.strip()) || "null".equalsIgnoreCase(input.strip()) ? false : true;
	}
	
	private static Optional<Double> getNumber(final String input) {
		if (!isValidText(input))
			return doubleEmpty;
		
		try {
			return Optional.ofNullable(Double.parseDouble(input));
		} catch (Exception e) {
			return doubleEmpty;
		}
	}
	
	public static Optional<String> getString(final String input) {
		return isValidText(input) ? Optional.ofNullable(input.trim()) : stringEmpty;		
	}
	
	public static Optional<Long> getLong(final String input) {
		Optional<Double> result = getNumber(input);
		return result.isPresent() ? Optional.ofNullable(result.get().longValue()) : longEmpty;
	}

	public static Optional<Double> getDouble(final String input) {
		return getNumber(input);
	}

	public static Optional<Timestamp> getTimestamp(final String input) {
		if (!isValidText(input))
			return timestampEmpty;

		try {
			final LocalDateTime localDateTime = LocalDateTime.from(dateFormatter.parse(input));
			return Optional.ofNullable(Timestamp.valueOf(localDateTime));
		} catch (Exception e) {
			return timestampEmpty;
		}
	}
}
