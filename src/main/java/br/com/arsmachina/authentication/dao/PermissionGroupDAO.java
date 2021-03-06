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

package br.com.arsmachina.authentication.dao;

import java.util.List;

import br.com.arsmachina.authentication.entity.PermissionGroup;
import br.com.arsmachina.authentication.entity.UserGroup;
import br.com.arsmachina.dao.DAO;

/**
 * Data access object (DAO) for {@link PermissionGroup}.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public interface PermissionGroupDAO extends DAO<PermissionGroup, Integer> {

	/**
	 * Returns the permission group with the given name or <code>null</code> if there is no such one.
	 * 
	 * @param name a {@link String}.
	 * @return a {@link PermissionGroup} or <code>null</code>.
	 */
	public PermissionGroup findByName(String name);
	
	/**
	 * Returns the permission groups that can be used by a given user group, including the shared 
	 * ones.
	 * 
	 * @param name an {@link UserGroup}. It cannot be null. 
	 * @return a {@link PermissionGroup} or <code>null</code>.
	 */
	public List<PermissionGroup> findByUserGroup(UserGroup userGroup);

}
