/**
 * Copyright 2017, Yahoo Inc.
 * Released under Apache 2.0 License.  See file LICENSE in project root.
 */

package com.yahoo.shiro.remoteuser.filter;

import java.util.Set;
import java.util.Collections;

import org.apache.shiro.authc.AuthenticationToken;

public class RemoteUserAuthenticationToken implements AuthenticationToken {
    private final String user;
    private final Set<String> roles;

    public RemoteUserAuthenticationToken(String user, Set<String> roles) {
        this.user = user;
        this.roles = Collections.unmodifiableSet(roles);
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public Object getCredentials() {
        return user;
    }

    public String getUserId() {
        return user;
    }

    public String getToken() {
        return user;
    }

    public Set<String> getRoles() {
        return roles;
    }


}
