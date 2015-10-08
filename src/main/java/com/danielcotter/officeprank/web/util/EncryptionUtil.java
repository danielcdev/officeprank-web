package com.danielcotter.officeprank.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {

	public static String shaHash(String message)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] hash = new byte[0];
		hash = md.digest(message.getBytes());

		StringBuilder sb = new StringBuilder(2 * hash.length);

		for (byte b : hash)
			sb.append(String.format("%02x", b & 0xff));

		return sb.toString();
	}
}
