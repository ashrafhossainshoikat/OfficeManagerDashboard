package dummymodels.test;

import java.sql.Types;

import com.avaje.ebean.CallableSql;
import com.avaje.ebean.Ebean;


public class SpTest {
	public static void getMenu() {
		String sql = "{call MenuGet(?)}";

		CallableSql cs = Ebean.createCallableSql(sql);
		cs.setParameter(1, 8747);
		cs.registerOut(1, Types.INTEGER);
		cs.registerOut(2, Types.INTEGER);
		cs.registerOut(3, Types.INTEGER);
		cs.registerOut(4, Types.VARCHAR);
		cs.registerOut(5, Types.VARCHAR);
		cs.registerOut(6, Types.INTEGER);
		cs.registerOut(7, Types.BOOLEAN);
		cs.registerOut(8, Types.VARCHAR);
		cs.registerOut(9, Types.VARCHAR);

		//Ebean.execute(cs);
		Ebean.getServer("connectplus").execute(cs);
		//System.out.println("Count ---> "+Ebean.getServer("connectplus").execute(cs));

		// read the out parameter
		//Integer returnValue = (Integer) cs.getObject(2);
	}
}
