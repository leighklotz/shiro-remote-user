/**
 * Copyright 2017, Yahoo Inc.
 * Released under Apache 2.0 License.  See file LICENSE in project root.
 */

package com.yahoo.shiro.remoteuser.filter;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.Collections;

public class RemoteUserAuthenticationTokenTest {

    @DataProvider(name = "RemoteUserAuthenticationToken")
    Object[][] getRemoteUserAuthenticationToken() {
        return new Object[][] {{ new RemoteUserAuthenticationToken("admin", Collections.singleton("A")) }};
    }


    @Test(dataProvider = "RemoteUserAuthenticationToken")
    public void test1(RemoteUserAuthenticationToken token) {
        Assert.assertEquals(token.getPrincipal(), "admin");
    }

    @Test(dataProvider = "RemoteUserAuthenticationToken")
    public void test2(RemoteUserAuthenticationToken token) {
        Assert.assertEquals(token.getCredentials(), "admin");
    }

    @Test(dataProvider = "RemoteUserAuthenticationToken")
    public void test3(RemoteUserAuthenticationToken token) {
        Assert.assertEquals(token.getToken(), "admin");
    }

    @Test(dataProvider = "RemoteUserAuthenticationToken")
    public void test4(RemoteUserAuthenticationToken token) {
        Assert.assertEquals(token.getUserId(), "admin");
    }

    @Test(dataProvider = "RemoteUserAuthenticationToken")
    public void test5(RemoteUserAuthenticationToken token) {
        Assert.assertEquals(token.getRoles(), Collections.singleton("A"));
    }
}
