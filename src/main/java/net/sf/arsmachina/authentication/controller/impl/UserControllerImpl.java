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

package net.sf.arsmachina.authentication.controller.impl;

import java.util.List;

import net.sf.arsmachina.authentication.controller.PasswordEncrypter;
import net.sf.arsmachina.authentication.controller.PermissionController;
import net.sf.arsmachina.authentication.controller.PermissionGroupController;
import net.sf.arsmachina.authentication.controller.UserController;
import net.sf.arsmachina.authentication.dao.UserDAO;
import net.sf.arsmachina.authentication.entity.Permission;
import net.sf.arsmachina.authentication.entity.PermissionGroup;
import net.sf.arsmachina.authentication.entity.Role;
import net.sf.arsmachina.authentication.entity.User;
import net.sf.arsmachina.controller.impl.SpringControllerImpl;

/**
 * {@link UserController} implementation.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
public class UserControllerImpl extends SpringControllerImpl<User, Integer> implements
		UserController {

	private UserDAO dao;

	private PermissionGroup allUsersPermissionGroup;

	private PasswordEncrypter passwordEncrypter;

	private PermissionController permissionController;

	private PermissionGroupController permissionGroupController;

	/**
	 * Single constructor of this class.
	 * 
	 * @param dao an {@link UserDAO}. It cannot be <code>null</code>.
	 * @param passwordEncrypter a {@link PasswordEncrypter}. It cannot be <code>null</code>.
	 * @param permissionController a {@link PermissionController}. It cannot be <code>null</code>. .
	 * @param permissionGroupController a {@link PermissionGroupController}. It cannot be
	 * <code>null</code>.
	 */
	public UserControllerImpl(UserDAO dao, PasswordEncrypter passwordEncrypter,
			PermissionController permissionController,
			PermissionGroupController permissionGroupController) {

		super(dao);
		this.dao = dao;

		if (permissionController == null) {
			throw new IllegalArgumentException("Parameter permissionController cannot be null");
		}

		if (permissionGroupController == null) {
			throw new IllegalArgumentException("Parameter permissionGroupController cannot be null");
		}

		if (passwordEncrypter == null) {
			throw new IllegalArgumentException("Parameter passwordEncrypter cannot be null");
		}

		this.permissionController = permissionController;
		this.permissionGroupController = permissionGroupController;
		this.passwordEncrypter = passwordEncrypter;

	}

	/**
	 * Ensures basic permissions and group permissions exist.
	 * 
	 * @param permissionController a {@link PermissionController}.
	 * @param permissionGroupController a {@link PermissionGroupController}.
	 */
	private void ensureBasicPermissionsExist(PermissionController permissionController,
			PermissionGroupController permissionGroupController) {

		final String roleName = Permission.USER_ROLE_NAME;
		Permission userPermission = permissionController.findByName(roleName);

		if (userPermission == null) {

			userPermission = new Permission();
			userPermission.setName(Permission.USER_ROLE_NAME);
			permissionController.save(userPermission);

		}

		final String groupName = PermissionGroup.ALL_USERS_PERMISSION_GROUP_NAME;
		PermissionGroup group = permissionGroupController.findByName(groupName);

		if (group == null) {

			group = new PermissionGroup();
			group.setName(groupName);
			group.add(userPermission);
			permissionGroupController.save(group);

		}

		allUsersPermissionGroup = group;

	}

	/**
	 * Invokes <code>dao.findByLoginAndPassword()<code>.
	 * @param login
	 * @param password
	 * @return
	 * @see net.sf.arsmachina.authentication.dao.UserDAO#findByLoginAndPassword(java.lang.String, java.lang.String)
	 */
	public User findByLoginAndPassword(String login, String password) {
		return dao.findByLoginAndPassword(login, password);
	}

	/**
	 * Invokes <code>dao.findByLogin()<code>.
	 * @param login
	 * @return
	 * @see net.sf.arsmachina.authentication.dao.UserDAO#findByLogin(java.lang.String)
	 */
	public User findByLogin(String login) {
		return dao.findByLogin(login);
	}

	/**
	 * Invokes <code>delegate.findByRole()<code>.
	 * @param <T>
	 * @param roleClass
	 * @return
	 * @see net.sf.arsmachina.authentication.dao.UserDAO#findByRole(java.lang.Class)
	 */
	public <T extends Role> List<User> findByRole(Class<T> roleClass) {
		return dao.findByRole(roleClass);
	}

	/**
	 * @see net.sf.arsmachina.controller.impl.SpringControllerImpl#save(java.lang.Object)
	 */
	@Override
	public void save(User user) {

		if (allUsersPermissionGroup == null) {
			ensureBasicPermissionsExist(permissionController, permissionGroupController);
		}

		if (user.getPermissionGroups().contains(allUsersPermissionGroup) == false) {
			user.add(allUsersPermissionGroup);
		}

		encryptPassword(user);

		super.save(user);

	}

	/**
	 * @see net.sf.arsmachina.controller.impl.SpringControllerImpl#update(java.lang.Object)
	 */
	@Override
	public void update(User user) {
		
		encryptPassword(user);
		super.update(user);
		
	}

	/**
	 * @param user
	 */
	private void encryptPassword(User user) {
		
		String password = user.getPassword();
		password = passwordEncrypter.encrypt(password);
		user.setPassword(password);
		
	}

}
