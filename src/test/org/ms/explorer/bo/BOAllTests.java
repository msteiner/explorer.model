package org.ms.explorer.bo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.ms.explorer.bo.file.ElementFileBOTest;
import org.ms.explorer.registry.RegistryTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    //RegistryTest.class, 
    ElementFileBOTest.class 
    })
public class BOAllTests {
}
