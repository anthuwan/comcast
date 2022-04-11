package com.comcast.stringinator;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ApplicationTest {
  @Autowired private ApplicationContext applicationContext;

  @Test
  public void contextLoads() {
    Assert.assertNotNull("ApplicationContext should not be null", applicationContext);
  }
}
