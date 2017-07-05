import static org.junit.Assert.assertEquals;

import org.junit.Test;

import me.wesleytsang.collector.DataGetter;

public class getDateTest {

	@Test
	public void test() throws Exception {
		DataGetter test = new DataGetter();
		String data = "Short Selling Turnover (Main Board) up to day close today          AMS/3 SHORTSELL REPORT   TRADING DATE : 26 JUN 2017 (MONDAY)                                          Turnover  CODE   NAME OF STOCK               (SH)            ($)      1  CKH HOLDINGS             149,000     14,703,250      2  CLP HOLDINGS              62,000      5,192,500";
		assertEquals("20170626", test.getDate(data));
	}

}
