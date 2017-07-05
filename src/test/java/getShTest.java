import static org.junit.Assert.assertEquals;

import org.junit.Test;

import me.wesleytsang.model.Record;

public class getShTest {

	@Test
	public void test() throws Exception {
		Record r = new Record("12", "Something Holding", "20170627", "MAIN", 21231, 6546365);
		assertEquals(21231, r.getSh());
	}

}
