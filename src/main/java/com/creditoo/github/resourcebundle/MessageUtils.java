package com.creditoo.github.resourcebundle;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Class for resolve messages of all languages
 */
@Component
@Scope("singleton")
public class MessageUtils {
	private static final String MESSAGES = "messages";
	private static Locale systemLocale = new Locale(System.getProperty("user.language"));

	public static String getMessage(String key) {
		try {
			ResourceBundle resource = ResourceBundle.getBundle(MESSAGES, systemLocale);
			return resource.getString(key);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public static String getMessage(final String key, final Object... params) {
		try {
			ResourceBundle resource = ResourceBundle.getBundle(MESSAGES, systemLocale);
			return MessageFormat.format(resource.getString(key), params);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public static String getMessageBoolean(final boolean param) {
		try {
			ResourceBundle resource = ResourceBundle.getBundle(MESSAGES, systemLocale);
			return resource.getString(param ? "message.yes" : "message.no");
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}