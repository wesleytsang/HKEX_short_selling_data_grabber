import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import me.wesleytsang.model.DBConnect;
import me.wesleytsang.model.Record;
import me.wesleytsang.sql.Jdbcmysql;

public class decodeTest {

	@Test
	public void test() {
		ArrayList<Record> list = new ArrayList<Record>();
		DBConnect conn = new DBConnect("jdbc:mysql://localhost:3306/hkexdb", "root", "root");
		Jdbcmysql db = new Jdbcmysql(conn, "table");
		String data = "7362  FI MR HSCEI                1,500         21,660 %82822  CSOP A50 ETF-R            66,600        757,132";
		list = db.decodeData(data, "20170626", "MAIN");
		assertEquals(list.size(), 2);
		assertEquals(list.get(1).getCode(), "%82822");
	}

}
