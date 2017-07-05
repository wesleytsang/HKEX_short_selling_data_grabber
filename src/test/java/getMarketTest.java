import static org.junit.Assert.assertEquals;

import org.junit.Test;

import me.wesleytsang.collector.DataGetter;

public class getMarketTest {

	@Test
	public void test() throws Exception {
		DataGetter test = new DataGetter();
		String data = "Short Selling Turnover (Main Board) up to day close today          AMS/3 SHORTSELL REPORT   TRADING DATE : 26 JUN 2017 (MONDAY)                                          Turnover  CODE   NAME OF STOCK               (SH)            ($)      1  CKH HOLDINGS             149,000     14,703,250      2  CLP HOLDINGS              62,000      5,192,500";
		String data2 = "Short Selling Turnover (GEM) up to day close today          AMS/3 SHORTSELL REPORT   TRADING DATE : 26 JUN 2017 (MONDAY)                                          Turnover  CODE   NAME OF STOCK               (SH)            ($)   8008  SUNEVISION                47,000        247,820   8032  VIVA CHINA               144,000        105,600";
		String data3 = "Short Selling Turnover (NASDAQ Stocks) up to morning close today          AMS/3 SHORTSELL REPORT   TRADING DATE : 26 JUN 2017 (MONDAY)                                          Turnover  CODE   NAME OF STOCK               (SH)            ($)Total No. of Securities recording Short Selling                :     0Total No. of Designated Securities recording Short Selling     :     0";
		assertEquals("MAIN", test.getMarket(data));
		assertEquals("GEM", test.getMarket(data2));
		assertEquals("NASDAQ", test.getMarket(data3));
	}

}
