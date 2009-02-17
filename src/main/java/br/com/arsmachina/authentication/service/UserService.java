// Copyright 2009 Thiago H. de Paula Figueiredo
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

package br.com.arsmachina.authentication.service;

import br.com.arsmachina.authentication.entity.User;

/**
 * Service that provides a method, {@link #getUser()}, that returns the user using the application
 * in this thread.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public interface UserService {

	/**
	 * Returns the user using the application in this thread.
	 * 
	 * @return an {@link User} or null.
	 */
	User getUser();
	
	/**
	 * Tells if the user using this application in this thread is logged in.
	 * @return a <code>boolean</code>.
	 */
	boolean isLoggedIn();

}
