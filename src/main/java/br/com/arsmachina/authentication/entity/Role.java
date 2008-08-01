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

package br.com.arsmachina.authentication.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.NotNull;

/**
 * Class that represents a role an user can have in the application. It is used to avoid creating
 * {@link User} subclasses, thus solving the problem of the same user having more than one role at
 * the same time.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Role {

	private Integer id;

	@NotNull
	private User user;

	/**
	 * Returns a set of permissions all users with this role have.
	 * This set must be fixed.
	 * 
	 * @return a {@link Set} of {@link Permission}s.
	 */
	@Transient
	public abstract Set<Permission> getPermissions();

	/**
	 * Returns the value of the <code>id</code> property.
	 * 
	 * @return a {@link Integer}.
	 */
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	/**
	 * Changes the value of the <code>id</code> property.
	 * 
	 * @param id a {@link Integer}.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Returns the value of the <code>user</code> property.
	 * 
	 * @return a {@link User}.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	public User getUser() {
		return user;
	}

	/**
	 * Changes the value of the <code>user</code> property.
	 * 
	 * @param user a {@link User}.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : super.hashCode();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}

}
