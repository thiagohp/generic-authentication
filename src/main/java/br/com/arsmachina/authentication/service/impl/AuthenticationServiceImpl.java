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

package br.com.arsmachina.authentication.service.impl;

import br.com.arsmachina.authentication.controller.UserController;
import br.com.arsmachina.authentication.encryption.PasswordEncrypter;
import br.com.arsmachina.authentication.entity.User;
import br.com.arsmachina.authentication.exception.BadCredentialsException;
import br.com.arsmachina.authentication.exception.DisabledUserException;
import br.com.arsmachina.authentication.exception.ExpiredUserException;
import br.com.arsmachina.authentication.exception.SimultaneousLoginForbiddenException;
import br.com.arsmachina.authentication.exception.LockedUserException;
import br.com.arsmachina.authentication.service.AuthenticationService;
import br.com.arsmachina.authentication.service.UserService;

/**
 * Default {@link AuthenticationService} implementation.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class AuthenticationServiceImpl implements AuthenticationService {

	final private UserService userService;

	final private PasswordEncrypter passwordEncrypter;

	final private UserController userController;

	final private boolean allowSimultaneousLogins;

	/**
	 * Single constructor of this class.
	 * 
	 * @param passwordEncrypter a {@link PasswordEncrypter}. It cannot be null.
	 * @param userController a {@link UserController}. It cannot be null.
	 * @param userService an {@link UserService}. It cannot be null.
	 * @param allowSimultaneousLogins a <code>boolean</code>.
	 */
	public AuthenticationServiceImpl(PasswordEncrypter passwordEncrypter,
			UserController userController, UserService userService, boolean allowSimultaneousLogins) {

		if (passwordEncrypter == null) {
			throw new IllegalArgumentException("Parameter passwordEncrypter cannot be null");
		}

		if (userController == null) {
			throw new IllegalArgumentException("Parameter userController cannot be null");
		}

		if (userService == null) {
			throw new IllegalArgumentException("Parameter userService cannot be null");
		}

		this.passwordEncrypter = passwordEncrypter;
		this.userController = userController;
		this.userService = userService;
		this.allowSimultaneousLogins = allowSimultaneousLogins;

	}

	public User authenticate(String login, String password) {

		if (login == null) {
			throw new IllegalArgumentException("Parameter login cannot be null");
		}

		if (password == null) {
			throw new IllegalArgumentException("Parameter password cannot be null");
		}

		String encryptedPassword = passwordEncrypter.encrypt(password);

		final User user = userController.findByLoginAndPassword(login, encryptedPassword);

		if (user == null) {
			throw new BadCredentialsException();
		}

		if (user.isCredentialsExpired()) {
			throw new ExpiredUserException();
		}

		if (user.isLocked()) {
			throw new LockedUserException();
		}

		if (user.isEnabled() == false) {
			throw new DisabledUserException();
		}

		final boolean loggedIn = user.isLoggedIn();
		if (allowSimultaneousLogins == false && loggedIn) {
			throw new SimultaneousLoginForbiddenException();
		}
		
		if (loggedIn == false) {
			user.setLoggedIn(true);
			userController.update(user);
		}
		
		userService.setUser(user);

		return user;

	}

}
