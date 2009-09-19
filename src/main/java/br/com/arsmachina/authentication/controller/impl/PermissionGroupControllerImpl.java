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

import br.com.arsmachina.authentication.controller.PermissionGroupController;
import br.com.arsmachina.authentication.dao.PermissionGroupDAO;
import br.com.arsmachina.authentication.entity.PermissionGroup;
import br.com.arsmachina.authentication.entity.UserGroup;
import br.com.arsmachina.controller.impl.SpringControllerImpl;

/**
 * {@link PermissionGroupController} implementation.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class PermissionGroupControllerImpl extends SpringControllerImpl<PermissionGroup, Integer>
		implements PermissionGroupController {

	private PermissionGroupDAO dao;

	/**
	 * Single constructor of this class.
	 * 
	 * @param dao an {@link PermissionGroupDAO}. It cannot be <code>null</code>.
	 */
	public PermissionGroupControllerImpl(PermissionGroupDAO dao) {
		super(dao);
		this.dao = dao;
	}

	public PermissionGroup findByName(String name) {
		return dao.findByName(name);
	}

	public List<PermissionGroup> findByUserGroup(UserGroup userGroup) {
		return dao.findByUserGroup(userGroup);
	}

}
