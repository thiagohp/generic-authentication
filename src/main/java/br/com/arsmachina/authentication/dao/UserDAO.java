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

package br.com.arsmachina.authentication.dao;

import java.util.List;

import br.com.arsmachina.authentication.entity.Role;
import br.com.arsmachina.authentication.entity.User;
import br.com.arsmachina.dao.DAO;


/**
 * Data access object (DAO) for {@link User}.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public interface UserDAO extends DAO<User, Integer> {

	/**
	 * Returns the user with a given login and password or <code>null</code> if no such
	 * user exists.
	 * 
	 * @param login a <code>String</code>.
	 * @param password a <code>String</code>.
	 * @return an {@link User}.
	 */
	User findByLoginAndPassword(String login, String password);

	/**
	 * Returns the user with a given login or <code>null</code> if no such
	 * user exists.
	 * 
	 * @param login a <code>String</code>.
	 * @return an {@link User}.
	 */
	User findByLogin(String login);

	/**
	 * Loads the user and their permissions with a given login or <code>null</code> if no such
	 * user exists. This method prefetches the user's permissions.
	 * 
	 * @param login a <code>String</code>.
	 * @return an {@link User}.
	 */
	User loadForAuthentication(String login);

	/**
	 * Loads the user and their permissions with a given login or <code>null</code> if no such
	 * user exists. This method prefetches the user's permissions and roles.
	 * 
	 * @param login a <code>String</code>.
	 * @return an {@link User}.
	 */
	User loadEverything(String login);

	/**
	 * Returns all users with a given {@link Role} subclass.
	 *  
	 * @param role a {@link Class}. It must be a {@link Role} subclass and cannot be null.
	 * @return a {@link List} of {@link User}s.
	 */
	<T extends Role> List<User> findByRole(Class<T> roleClass);
	
	/**
	 * Tells if some user with a given login exists. 
	 * @param login a {@link String}. It cannot be null.
	 * 
	 * @return a <code>boolean</code>.
	 */
	boolean hasUserWithLogin(String login);

}
