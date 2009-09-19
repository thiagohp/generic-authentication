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

import br.com.arsmachina.authentication.controller.UserGroupController;
import br.com.arsmachina.authentication.dao.UserGroupDAO;
import br.com.arsmachina.authentication.entity.UserGroup;
import br.com.arsmachina.controller.impl.SpringControllerImpl;

/**
 * {@link UserGroupController} implementation.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class UserGroupControllerImpl extends SpringControllerImpl<UserGroup, Integer> implements
		UserGroupController {

	private UserGroupDAO dao;

	/**
	 * Single constructor of this class.
	 * 
	 * @param dao an {@link UserGroupDAO}. It cannot be <code>null</code>.
	 */
	public UserGroupControllerImpl(UserGroupDAO dao) {
		super(dao);
		this.dao = dao;
	}

	/**
	 * Invokes <code>dao.findByName()<code>.
	 * @param name a {@link String}.
	 * @return a {@link UserGroup} or <code>null</code>.
	 * @see br.com.arsmachina.authentication.dao.UserGroupDAO#findByName(java.lang.String)
	 */
	public UserGroup findByName(String name) {
		return dao.findByName(name);
	}

}
