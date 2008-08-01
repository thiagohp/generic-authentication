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

package net.sf.arsmachina.authentication.entity;

import java.util.ArrayList;
import java.util.List;


import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.arsmachina.authentication.entity.Permission;
import br.com.arsmachina.authentication.entity.PermissionGroup;
import br.com.arsmachina.authentication.entity.User;

/**
 * Test class for {@link User}.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
public class UserTest {

	@Test
	public void getRoles() {

		User user = new User();
		user.setName("Test");

		new ArrayList<Permission>();

		for (int i = 0; i < 2; i++) {
			
			final PermissionGroup group = new PermissionGroup();
			group.setName("Group " + i);
			user.getPermissionGroups().add(group);
			
		}

		for (int i = 0; i < 8; i++) {

			final Permission permission = new Permission();
			permission.setName("ROLE_" + i);

			user.getPermissionGroups().get(i % 2).getPermissions().add(permission);

			if (i % 4 == 0) {
				user.getRemovedPermissions().add(permission);
			}

		}

		List<Permission> permissions = user.getPermissions(); 

		Assert.assertEquals(permissions.size(), 6);

		for (Permission permission : permissions) {

			Assert.assertFalse(permission.getName().equals("ROLE_0"));
			Assert.assertFalse(permission.getName().equals("ROLE_4"));

		}

	}

}
