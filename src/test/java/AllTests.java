import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ getCodeTest.class, getDateTest.class, getDollarTest.class, getMarketTest.class, getMorningTest.class,
		getNameTest.class, getRecordDateTest.class, getRecordMarketTest.class, getShTest.class, decodeTest.class })
public class AllTests {

}
