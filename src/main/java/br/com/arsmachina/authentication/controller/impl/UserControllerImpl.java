// Copyright 2008-2009 Thiago H. de Paula Figueiredo
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

package br.com.arsmachina.authentication.controller.impl;

import java.util.List;
import java.util.Random;

import org.springframework.transaction.annotation.Transactional;

import br.com.arsmachina.authentication.controller.PermissionController;
import br.com.arsmachina.authentication.controller.PermissionGroupController;
import br.com.arsmachina.authentication.controller.UserController;
import br.com.arsmachina.authentication.dao.UserDAO;
import br.com.arsmachina.authentication.encryption.PasswordEncrypter;
import br.com.arsmachina.authentication.entity.Permission;
import br.com.arsmachina.authentication.entity.PermissionGroup;
import br.com.arsmachina.authentication.entity.Role;
import br.com.arsmachina.authentication.entity.User;
import br.com.arsmachina.controller.impl.SpringControllerImpl;

/**
 * {@link UserController} implementation.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class UserControllerImpl extends SpringControllerImpl<User, Integer> implements
		UserController {

	final private static int GENERATED_PASSWORD_LENGTH = 7;

	private Random random = new Random();

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

	@Transactional(readOnly = true)
	public User findByLoginAndPassword(String login, String password) {
		return dao.findByLoginAndPassword(login, password);
	}

	@Transactional(readOnly = true)
	public User findByLogin(String login) {
		return dao.findByLogin(login);
	}

	@Transactional(readOnly = true)
	public <T extends Role> List<User> findByRole(Class<T> roleClass) {
		return dao.findByRole(roleClass);
	}

	@Transactional
	@Override
	public void save(User user) {

		if (allUsersPermissionGroup == null) {
			ensureBasicPermissionsExist(permissionController, permissionGroupController);
		}

		br.com.arsmachina.authentication.entity.User entity = (br.com.arsmachina.authentication.entity.User) user;

		if (user.getPermissionGroups().contains(allUsersPermissionGroup) == false) {
			entity.add(allUsersPermissionGroup);
		}

		setPasswordIfNeeded(entity);

		encryptPassword(entity);

		super.save(user);

	}

	@Override
	@Transactional
	public User update(User user) {

		encryptPassword((br.com.arsmachina.authentication.entity.User) user);
		return super.update(user);

	}

	@Transactional(readOnly = true)
	public User loadForAuthentication(String login) {
		return dao.loadForAuthentication(login);
	}

	@Transactional(readOnly = true)
	public User loadEverything(String login) {
		return dao.loadEverything(login);
	}

	@Transactional(readOnly = true)
	public boolean existsUserWithLogin(String login) {
		return dao.hasUserWithLogin(login);
	}

	private void encryptPassword(br.com.arsmachina.authentication.entity.User user) {

		String password = user.getPassword();
		password = passwordEncrypter.encrypt(password);
		user.setPassword(password);

	}

	/**
	 * Creates a temporary throw-away random password if it was not set yet.
	 * 
	 * @param user an {@link br.com.arsmachina.authentication.entity.User}.
	 */
	private void setPasswordIfNeeded(br.com.arsmachina.authentication.entity.User user) {

		if (user.getPassword() == null) {
			user.setPassword(Integer.toHexString(random.nextInt()));
		}

	}

	@Transactional
	@Override
	public User reattach(User user) {

		user = super.reattach(user);

		final List<PermissionGroup> permissionGroups = user.getPermissionGroups();

		for (PermissionGroup permissionGroup : permissionGroups) {
			permissionGroupController.reattach(permissionGroup);
		}

		return user;

	}

	@Transactional
	@Override
	public void markLoggedIn(User user) {
		dao.markLoggedIn(user);
	}

	@Transactional
	@Override
	public void markLoggedOut(User user) {
		dao.markLoggedOut(user);
	}

	@Transactional
	@Override
	public String setRandomPassword(User user) {

		if (user == null) {
			throw new IllegalArgumentException("Parameter user cannot be null.");
		}

		String password = generateRandomPassword();
		user.setPassword(password);

		update(user);

		return password;

	}

	@Override
	public String generateRandomPassword() {
		
		StringBuilder builder = new StringBuilder(GENERATED_PASSWORD_LENGTH);

		for (int i = 0; i < GENERATED_PASSWORD_LENGTH; i++) {

			char c = (char) (random.nextInt(26) + 'a');
			builder.append(c);

		}

		return builder.toString();
		
	}

}
