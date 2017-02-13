/**
 * Copyright 2017, Yahoo Inc.
 * Released under Apache 2.0 License.  See file LICENSE in project root.
 */

package com.yahoo.shiro.remoteuser.entity;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.Collections;

import org.testng.annotations.DataProvider;

public class RemoteUserTest {

    @DataProvider(name = "RemoteUser")
    Object[][] getRemoteUser() {
        return new Object[][] {{ new RemoteUser("admin", Collections.singleton("A")) }};
    };


    @Test(dataProvider = "RemoteUser")
    public void test1(RemoteUser remoteUser) {
        Assert.assertEquals(remoteUser.getPrincipal(), "admin");
    }
    
    @Test(dataProvider = "RemoteUser")
    public void test2(RemoteUser remoteUser) {
        Assert.assertEquals(remoteUser.getCredentials(), "admin");
    }
    
    @Test(dataProvider = "RemoteUser")
    public void test3(RemoteUser remoteUser) {
        Assert.assertEquals(remoteUser.getId(), "admin");
    }

    @Test(dataProvider = "RemoteUser")
    public void test4(RemoteUser remoteUser) {
        Assert.assertEquals(remoteUser.getRoles(), Collections.singleton("A"));
    }

    @Test(dataProvider = "RemoteUser")
    public void test5(RemoteUser remoteUser) {
        Assert.assertEquals(remoteUser.toString(), remoteUser.getId());
    }
}
