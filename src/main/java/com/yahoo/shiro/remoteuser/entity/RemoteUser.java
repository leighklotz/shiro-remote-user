/**
 * Copyright 2017, Yahoo Inc.
 * Released under Apache 2.0 License.  See file LICENSE in project root.
 */

package com.yahoo.shiro.remoteuser.entity;

import java.util.Set;
import java.util.Collections;

public class RemoteUser {
    private final String id;
    private final Set<String> roles;

    public RemoteUser(String id, Set<String> roles) {
        this.id = id;
        this.roles = Collections.unmodifiableSet(roles);
    }

    public Object getPrincipal() {
        return getId();
    }

    public Object getCredentials() {
        return id;
    }

    public String getId() {
        return id;
    }

    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return this.getId();
    }

}
