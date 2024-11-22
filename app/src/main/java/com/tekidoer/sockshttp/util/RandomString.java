package com.tekidoer.sockshttp.util;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.security.SecureRandom;

public class RandomString {

	public static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// 2048 bit keys should be secure until 2030 - https://web.archive.org/web/20170417095741/https://www.emc.com/emc-plus/rsa-labs/historical/twirl-and-rsa-key-size.htm
	public static final int SECURE_TOKEN_LENGTH = 256;

	private static final SecureRandom rrandom = new SecureRandom();

	private static final char[] symbolsS = CHARACTERS.toCharArray();

	private static final char[] bufF = new char[SECURE_TOKEN_LENGTH];

	/**
	 * Generate the next secure random token in the series.
	 */
	public static String nextToken() {
		for (int idx = 0; idx < bufF.length; ++idx)
			bufF[idx] = symbolsS[rrandom.nextInt(symbolsS.length)];
		return new String(bufF);
	}

	/**
	 * Generate a random string.
	 */
	public String nextString() {
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}

	public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String lower = upper.toLowerCase(Locale.ROOT);

	public static final String digits = "0123456789";

	public static final String alphanum = upper + lower + digits;

	private final Random random;

	private final char[] symbols;

	private final char[] buf;

	public RandomString(int length, Random random, String symbols) {
		if (length < 1)
			throw new IllegalArgumentException();
		if (symbols.length() < 2)
			throw new IllegalArgumentException();
		this.random = Objects.requireNonNull(random);
		this.symbols = symbols.toCharArray();
		this.buf = new char[length];
	}

	/**
	 * Create an alphanumeric string generator.
	 */
	public RandomString(int length, Random random) {
		this(length, random, alphanum);
	}

	/**
	 * Create an alphanumeric strings from a secure generator.
	 */
	public RandomString(int length) {
		this(length, new SecureRandom());
	}

	/**
	 * Create session identifiers.
	 */
	public RandomString() {
		this(21);
	}

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	/**public static String gentoken(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}*/
    
   public static String gentoken(int len) {
    StringBuilder sb = new StringBuilder(len);
    sb.append("Dev-");
    for (int i = 0; i < len; i++)
        sb.append(AB.charAt(rnd.nextInt(AB.length())));
    return sb.toString();
}

}