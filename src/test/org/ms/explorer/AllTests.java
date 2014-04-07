package org.ms.explorer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.ms.explorer.bo.BOAllTests;
import org.ms.explorer.bo.file.ElementFileBOTest;
import org.ms.explorer.registry.RegistryAllTests;
import org.ms.explorer.registry.RegistryTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BOAllTests.class, 
                      RegistryAllTests.class })
public class AllTests {
}
