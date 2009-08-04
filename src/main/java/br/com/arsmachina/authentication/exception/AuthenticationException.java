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

package br.com.arsmachina.authentication.exception;

/**
 * Class that serves as the root of the authentication excpetion classes.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public abstract class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor without arguments.
	 */
	public AuthenticationException() {
		super();
	}

	/**
	 * Constructor that receives a message and a cause.
	 * 
	 * @param message a {@link String}. It cannot be null nor empty.
	 * @param cause a {@link Throwable}.
	 */
	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor that receives a message.
	 * 
	 * @param message a {@link String}. It cannot be null nor empty.
	 */
	public AuthenticationException(String message) {
		this(message, null);
	}

	/**
	 * Constructor that receives a cause.
	 * 
	 * @param cause a {@link Throwable}.
	 */
	public AuthenticationException(Throwable cause) {
		this(null, cause);
	}

}
