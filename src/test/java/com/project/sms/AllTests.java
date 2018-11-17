package com.project.sms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CountyTestCase.class, CityTestCase.class, AddressTestCase.class })
public class AllTests {

}
