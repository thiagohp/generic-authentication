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

package br.com.arsmachina.authentication.service;

import br.com.arsmachina.authentication.entity.User;
import br.com.arsmachina.authentication.exception.BadCredentialsException;
import br.com.arsmachina.authentication.exception.DisabledUserException;
import br.com.arsmachina.authentication.exception.LockedUserException;

/**
 * Service that authenticates users.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public interface AuthenticationService {

	/**
	 * Authenticates a login/passwords pair and returns the corresponding {@link User} instance.
	 * 
	 * @param login a {@link String}. It cannot be null.
	 * @param password a {@link String}. It cannot be null.
	 * @return an {@link User}.
	 * 
	 * @throws BadCredentialsException if the provided login/password pair is invalid.
	 * @throws LockedUserException if the user is locked.
	 * @throws ExpiredUserException if the user is expired.
	 * @throws DisabledUserException if the user is disabled.
	 */
	User authenticate(String login, String password);
	
}
