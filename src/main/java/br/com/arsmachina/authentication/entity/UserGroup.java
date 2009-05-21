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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * Class that represents a group of users.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
@Entity
@Table(name = "usergroup")
public class UserGroup {

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

	private List<User> users = new ArrayList<User>();

	/**
	 * Retorna o valor da propriedade <code>id</code>.
	 * 
	 * @return o valor de <code>id</code>.
	 */
	@Id
	@GeneratedValue
	@Column(nullable = false)
	public Integer getId() {
		return id;
	}

	/**
	 * Altera o valor da propriedade <code>id</code>.
	 * 
	 * @param <code>id</code> o novo valor da propriedade <code>id</code>.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Retorna o valor da propriedade <code>name</code>.
	 * 
	 * @return o valor de <code>name</code>.
	 */
	@Column(nullable = false, length = MAXIMUM_NAME_LENGTH, unique = true)
	@NotNull
	@Length(min = User.MINIMUM_NAME_LENGTH, max = User.MAXIMUM_NAME_LENGTH)
	public String getName() {
		return name;
	}

	/**
	 * Altera o valor da propriedade <code>name</code>.
	 * 
	 * @param <code>name</code> o novo valor da propriedade <code>name</code>.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retorna o valor da propriedade <code>users</code>.
	 * 
	 * @return o valor de <code>users</code>.
	 */
	@ManyToMany
	@JoinTable(name = "usergroup_user",
			joinColumns = @JoinColumn(name = "usergroup_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false),
			uniqueConstraints = @UniqueConstraint(columnNames = {"usergroup_id", "user_id"}))
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Altera o valor da propriedade <code>users</code>.
	 * 
	 * @param <code>users</code> o novo valor da propriedade <code>users</code>.
	 */
	@Deprecated
	public void setUsers(List<User> users) {
		this.users = users;
	}

}
