package receiver;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestPressePapiers {

	private static PressePapiers pp;
	
	@BeforeClass
	static public void initTest() {
		pp = new PressePapiers();
	}
	
	@Test
	public void testAccesseurs() {
		String s = "Bulles carrées";
		pp.setContenu(s);
		assertEquals(s, pp.getContenu());
	}

}
