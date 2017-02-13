/**
 * Copyright 2017, Yahoo Inc.
 * Released under Apache 2.0 License.  See file LICENSE in project root.
 */

package com.yahoo.shiro.remoteuser.realm;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.Collections;

import com.yahoo.shiro.remoteuser.filter.RemoteUserAuthenticationToken;
import com.yahoo.shiro.remoteuser.entity.RemoteUser;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;

public class RemoteUserRealmTest {

    @DataProvider(name = "RemoteUserRealm")
    public Object[][] getRemoteUserRealm() {
        return new Object[][] {{ new RemoteUserRealm() }};
    }

    @DataProvider(name = "RemoteUserRealmAndToken")
    public Object[][] getRemoteUserRealmAndToken() {
        return new Object[][] {{ new RemoteUserRealm(),
                                 new RemoteUserAuthenticationToken("admin", Collections.singleton("A"))
            }};
    }


    @Test(dataProvider = "RemoteUserRealm")
    public void test1(RemoteUserRealm remoteUserRealm) {
        Assert.assertEquals(remoteUserRealm.getAuthenticationTokenClass(), RemoteUserAuthenticationToken.class);
    }

    @Test(dataProvider = "RemoteUserRealmAndToken")
    public void test2(RemoteUserRealm remoteUserRealm, RemoteUserAuthenticationToken token) {
        AuthenticationInfo authenticationInfo = remoteUserRealm.doGetAuthenticationInfo(token);
        RemoteUser remoteUser = (RemoteUser)(authenticationInfo.getPrincipals().getPrimaryPrincipal());
        Assert.assertEquals(remoteUser.getId(), "admin");
    }

    @Test(dataProvider = "RemoteUserRealm")
    public void test3(RemoteUserRealm remoteUserRealm) {
        Assert.assertEquals(remoteUserRealm.getAuthenticationTokenClass(), RemoteUserAuthenticationToken.class);
    }

    @Test(dataProvider = "RemoteUserRealmAndToken")
    public void test4(RemoteUserRealm remoteUserRealm, RemoteUserAuthenticationToken token) {
        AuthenticationInfo authenticationInfo = remoteUserRealm.doGetAuthenticationInfo(token);
        AuthorizationInfo authorizationInfo  = remoteUserRealm.doGetAuthorizationInfo(authenticationInfo.getPrincipals());
        Assert.assertTrue(authorizationInfo.getRoles().contains("A"));
    }

}
