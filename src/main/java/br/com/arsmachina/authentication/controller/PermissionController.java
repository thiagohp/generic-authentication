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

package br.com.arsmachina.authentication.controller;

import br.com.arsmachina.authentication.entity.Permission;
import br.com.arsmachina.controller.Controller;

/**
 * Controller definition for {@link Permission}.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public interface PermissionController extends Controller<Permission, Integer> {

	/**
	 * Returns the permission with the given name or <code>null</code> if there is no such one.
	 * 
	 * @param name a {@link String}.
	 * @return a {@link Permission} or <code>null</code>.
	 */
	public Permission findByName(String name);

}
