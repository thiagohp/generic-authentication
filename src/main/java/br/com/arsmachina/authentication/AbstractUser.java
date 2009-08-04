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

package br.com.arsmachina.authentication;

import java.util.List;

import br.com.arsmachina.authentication.entity.Permission;
import br.com.arsmachina.authentication.entity.PermissionGroup;
import br.com.arsmachina.authentication.entity.Role;

/**
 * Class that represents an application user. Each user can belong to any number of
 * {@link Permission}s. When a given user belongs to a {@link Permission}, but cannot be granted
 * some {@link Permission} in that group, this permission must be added to the list of removed
 * permissions (<code>removedPermissions</code> property).
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public interface AbstractUser {

	/**
	 * Tells if this user has some a given role type.
	 * 
	 * @param <R> a {@link Role} subclass.
	 * @param roleClass a {@link Class}.
	 * @return a <code>boolean</code>.
	 */
	<R extends Role> boolean hasRole(Class<R> roleClass);

	/**
	 * Returns the value of the <code>email</code> property.
	 * 
	 * @return a {@link String}.
	 */
	String getEmail();

	/**
	 * Returns the value of the <code>login</code> property.
	 * 
	 * @return a {@link String}.
	 */
	String getLogin();

	/**
	 * Returns the value of the <code>name</code> property.
	 * 
	 * @return a {@link String}.
	 */
	String getName();

	/**
	 * Returns the value of the <code>password</code> property.
	 * 
	 * @return a {@link String}.
	 */
	String getPassword();

	/**
	 * Returns the value of the <code>permissionGroups</code> property.
	 * 
	 * @return a {@link List<PermissionGroup>}.
	 */
	List<PermissionGroup> getPermissionGroups();

	/**
	 * Returns an unmodifiable list containing all the permissions granted to this user. It is
	 * comprised by the sum of all permissions in its permission groups, except the ones in its
	 * removed permissions list.
	 * 
	 * @return a {@link List} of {@link Permission}s.
	 */
	List<Permission> getPermissions();

	/**
	 * Given a {@link Class} object, returns the corresponding {@link Role} instance or null if this
	 * user has no such role.
	 * 
	 * @param <T> a {@link Role} subclass.
	 * @param clasz a {@link Class<T>}.
	 * @return a {@link #T}.
	 */
	<T extends Role> T getRole(Class<T> clasz);

	/**
	 * Returns the value of the <code>roles</code> property.
	 * 
	 * @return a {@link List<Role>}.
	 */
	List<Role> getRoles();

	/**
	 * Tells if this user has at least one of a set of permissions.
	 * 
	 * @param permissionName an array of {@link String}s.
	 * @return a <code>boolean</code>.
	 */
	boolean hasPermission(String... permissionNames);

	/**
	 * Are this user's credentials expired?
	 * 
	 * @return a {@link boolean}.
	 */
	boolean isCredentialsExpired();

	/**
	 * Is this user's account enabled?.
	 * 
	 * @return a {@link boolean}.
	 */
	boolean isEnabled();

	/**
	 * Is this user's account expired?
	 * 
	 * @return a {@link boolean}.
	 */
	boolean isExpired();

	/**
	 * Is this user's account locked?
	 * 
	 * @return a {@link boolean}.
	 */
	boolean isLocked();

	/**
	 * Is this user's logged in now?
	 * 
	 * @return a {@link boolean}.
	 */
	boolean isLoggedIn();

}
