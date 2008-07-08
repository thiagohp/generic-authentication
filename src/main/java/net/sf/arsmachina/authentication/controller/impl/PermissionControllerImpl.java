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

import net.sf.arsmachina.authentication.controller.PermissionController;
import net.sf.arsmachina.authentication.dao.PermissionDAO;
import net.sf.arsmachina.authentication.entity.Permission;
import net.sf.arsmachina.controller.impl.SpringControllerImpl;

/**
 * {@link PermissionController} implementation.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
public class PermissionControllerImpl extends SpringControllerImpl<Permission, Integer> implements
		PermissionController {

	private PermissionDAO dao;

	/**
	 * Single constructor of this class.
	 * 
	 * @param dao an {@link PermissionDAO}. It cannot be <code>null</code>.
	 */
	public PermissionControllerImpl(PermissionDAO dao) {
		super(dao);
		this.dao = dao;
	}

	/**
	 * Invokes <code>dao.findByName()<code>.
	 * @param name a {@link String}.
	 * @return a {@link Permission} or <code>null</code>.
	 * @see net.sf.arsmachina.authentication.dao.PermissionDAO#findByName(java.lang.String)
	 */
	public Permission findByName(String name) {
		return dao.findByName(name);
	}

}
