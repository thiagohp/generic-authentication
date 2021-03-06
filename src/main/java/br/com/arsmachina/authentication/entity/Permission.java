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

package br.com.arsmachina.authentication.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * Class that represents a single permission.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
@Entity
@Table(name = "permission")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "permission")
final public class Permission implements Comparable<Permission>, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Name of the role that all users have.
	 */
	public static final String USER_ROLE_NAME = "ROLE_USER"; 

	/**
	 * Name of the administrator role.
	 */
	public static final String ADMINISTRATOR_ROLE_NAME = "ROLE_ADMIN";

	/**
	 * Minimum name length.
	 */
	public static final int MINIMUM_NAME_LENGTH = 2;

	/**
	 * Maximum name length.
	 */
	public static final int MAXIMUM_NAME_LENGTH = 50;

	private Integer id;

	private String name;

	/**
	 * No-arg constructor. 
	 */
	public Permission() {
	}

	/**
	 * Constructor that receives a name.
	 * 
	 * @param name a {@link String}. It cannot be null.
	 * @throws IllegalArgumentException if <code>name</code> is null.
	 */
	public Permission(String name) {
		
		if (name == null) {
			throw new IllegalArgumentException("Parameter name cannot be null");
		}
		
		this.name = name;
		
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : super.hashCode();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Permission other = (Permission) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		}
		else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Permission o) {
		return getName().compareToIgnoreCase(o.getName());
	}

	/**
	 * Returns the <code>name</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@Override
	public String toString() {
		return getName();
	}

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
	 * Returns the value of the <code>name</code> property.
	 * 
	 * @return a {@link String}.
	 */
	@NotNull
	@Length(min = MINIMUM_NAME_LENGTH, max = MAXIMUM_NAME_LENGTH)
	@Column(nullable = false, unique = true, length = MAXIMUM_NAME_LENGTH)
	public String getName() {
		return name;
	}

	/**
	 * Changes the value of the <code>name</code> property.
	 * 
	 * @param name a {@link String}.
	 */
	public void setName(String name) {
		this.name = name;
	}

}
