// Copyright 2008 Thiago H. de Paula Figueiredo
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

package br.com.arsmachina.authentication.controller;

import java.util.List;

import br.com.arsmachina.authentication.entity.Role;
import br.com.arsmachina.authentication.entity.User;
import br.com.arsmachina.controller.Controller;


/**
 * Controller definition for {@link User}.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
public interface UserController extends Controller<User, Integer> {

	/**
	 * Returns the user with a given login and password or <code>null</code> if no such user
	 * exists.
	 * 
	 * @param login a <code>String</code>.
	 * @return
	 */
	User findByLoginAndPassword(String login, String password);

	/**
	 * Returns the user with a given login or <code>null</code> if no such user exists.
	 * 
	 * @param login a <code>String</code>.
	 * @return an {@link User}.
	 */
	User findByLogin(String login);

	/**
	 * Returns all users with a given {@link Role} subclass.
	 *  
	 * @param role a {@link Class}. It must be a {@link Role} subclass and cannot be null.
	 * @return a {@link List} of {@link User}s.
	 */
	<T extends Role> List<User> findByRole(Class<T> roleClass);
}