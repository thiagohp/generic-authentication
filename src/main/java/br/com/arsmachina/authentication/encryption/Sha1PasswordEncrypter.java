// Copyright 2009 Thiago H. de Paula Figueiredo
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package br.com.arsmachina.authentication.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * {@link PasswordEncrypter} implementation using the SHA-1 algorithm.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class Sha1PasswordEncrypter implements PasswordEncrypter {

	final private String salt;

	/**
	 * Constructor that receives a salt value.
	 * 
	 * @param salt a {@link String}.
	 */
	public Sha1PasswordEncrypter(String salt) {
		this.salt = salt;
	}
	
	/**
	 * Constructor without arguments. Creates a password encrypter that does not use salt.
	 */
	public Sha1PasswordEncrypter() {
		this(null);
	}

	public String encrypt(String password) {

		// already encrypted
		if (password.length() == 40) {
			return password;
		}
		
		if (salt != null) {
			password = password + salt;
		}

		MessageDigest messageDigest = null;
		
		try {
			messageDigest = MessageDigest.getInstance("SHA1");
		}
		catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		
		messageDigest.reset();
		messageDigest.update(password.getBytes());

		final byte[] bytes = messageDigest.digest();
		String encrypted = new BigInteger(1, bytes).toString(16);
		
		if (encrypted.length() < 40) {
			
			final StringBuilder builder = new StringBuilder(encrypted);
			while (builder.length() < 40) {
				builder.insert(0, '0');
			}
			
			encrypted = builder.toString();
			
		}
		
		return encrypted;
		
	}

}
