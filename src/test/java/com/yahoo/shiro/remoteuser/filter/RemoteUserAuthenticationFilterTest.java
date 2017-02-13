/**
 * Copyright 2017, Yahoo Inc.
 * Released under Apache 2.0 License.  See file LICENSE in project root.
 */

package com.yahoo.shiro.remoteuser.filter;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

import java.util.Set;
import java.util.Collections;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;

public class RemoteUserAuthenticationFilterTest {
  private static final String X_REMOTE_USER = "X-Remote-User";
  private static final String X_REMOTE_ROLES = "X-Remote-Roles";


  private Object[] createRemoteUserAuthenticationFilterTest(RemoteUserAuthenticationFilter filter,
                                                            String username, String roles, String role) {
    HttpServletRequest adminRequest = mock(HttpServletRequest.class);
    when(adminRequest.getHeader(X_REMOTE_USER)).thenReturn(username);
    when(adminRequest.getHeader(X_REMOTE_ROLES)).thenReturn(roles);
    HttpServletResponse adminResponse = mock(HttpServletResponse.class);
    return new Object[] { filter, adminRequest, adminResponse, username, role };
  }

  private RemoteUserAuthenticationFilter createTestFilter(String separator) {
    RemoteUserAuthenticationFilter filter = new RemoteUserAuthenticationFilter();
    filter.setRemoteUserHeaderName(X_REMOTE_USER);
    filter.setRemoteRolesHeaderName(X_REMOTE_ROLES);
    filter.setRemoteRolesSeparator(separator);
    return filter;
  }


  @DataProvider(name = "RemoteUserAuthenticationFilter")
  public Object[][] getRemoteUserAuthenticationFilter() {
    return new Object[][] {
      createRemoteUserAuthenticationFilterTest(createTestFilter(""), "admin", "A", "A"),
      createRemoteUserAuthenticationFilterTest(createTestFilter(""), "admin", "AU", "A"),
      createRemoteUserAuthenticationFilterTest(createTestFilter(""), "admin", "AU", "U"),

      createRemoteUserAuthenticationFilterTest(createTestFilter(","), "admin", "A", "A"),
      createRemoteUserAuthenticationFilterTest(createTestFilter(","), "admin", "A, U", "A"),
      createRemoteUserAuthenticationFilterTest(createTestFilter(","), "admin", "A, U", "U"),

      createRemoteUserAuthenticationFilterTest(createTestFilter(" "), "admin", "A", "A"),
      createRemoteUserAuthenticationFilterTest(createTestFilter(" "), "admin", "A U", "A"),
      createRemoteUserAuthenticationFilterTest(createTestFilter(" "), "admin", "A U", "U")
    };
  }

  @Test(dataProvider = "RemoteUserAuthenticationFilter")
  public void test(RemoteUserAuthenticationFilter remoteUserAuthenticationFilter,
                   ServletRequest adminRequest,
                   ServletResponse adminResponse,
                   String username,
                   String role) throws IOException {
    AuthenticationToken token = remoteUserAuthenticationFilter.createToken(adminRequest, adminResponse);
    
    Assert.assertEquals(username, token.getPrincipal());
    Assert.assertTrue(remoteUserAuthenticationFilter.getRoles(adminRequest).contains(role));
  }
}
