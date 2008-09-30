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

package br.com.arsmachina.authentication.controller.impl;

import br.com.arsmachina.authentication.controller.PasswordEncrypter;

/**
 * {@link PasswordEncrypter} implementation that does not really encrypt passwords.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class DummyPasswordEncrypter implements PasswordEncrypter {

	/**
	 * Returns <code>password</code> unchanged.
	 * 
	 * @param password a {@link String}.
	 * @return <code>password</code>.
	 * @see br.com.arsmachina.authentication.controller.PasswordEncrypter#encrypt(java.lang.String)
	 */
	public String encrypt(String password) {
		return password;
	}

}
