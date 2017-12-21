package com.coding.puzzle;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.coding.puzzle.repository.impl.GameRepositryTest;
import com.coding.puzzle.service.impl.GameDataInitializationServiceTest;
import com.coding.puzzle.service.impl.GameLevelServiceTest;
import com.coding.puzzle.service.impl.GameServiceTest;
import com.coding.puzzle.service.impl.LocationServiceTest;
import com.coding.puzzle.service.impl.PlayerServiceTest;
import com.coding.puzzle.service.impl.PurchaseServiceTest;
import com.coding.puzzle.service.impl.WeaponServiceTest;
import com.coding.puzzle.util.parsing.FileUtilTest;

@RunWith(Suite.class)
@SuiteClasses({ GameDataInitializationServiceTest.class, GameLevelServiceTest.class, GameServiceTest.class,
		LocationServiceTest.class, PlayerServiceTest.class, PurchaseServiceTest.class, WeaponServiceTest.class, GameRepositryTest.class, FileUtilTest.class })
public class AllTests {

}
