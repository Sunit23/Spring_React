package com.tortuga.security.governance.platform;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({ SpringBootSecurityJwtMongodbApplicationTests.class,
  SpringRoleServicesTest.class })
public class AllTestSuite extends TestSuite {

}
