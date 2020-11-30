/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Cache;

/**
 * @author a
 *
 */
public class CacheTest {

	private Cache cac;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cac = new Cache();
	}

	/**
	 * Test method for {@link data.Cache#getHistory(java.lang.String)}.
	 */
	@Test
	public final void testGetHistory() {
		assertEquals(null, cac.getHistory(0));
		assertEquals(null, cac.getHistory(4));

		cac.addHistoryLine("line");
		assertEquals("line", cac.getHistory(0));
		assertEquals(null, cac.getHistory(-1));
		assertEquals(null, cac.getHistory(1));
	}

	/**
	 * Test method for {@link data.Cache#popDirectoryStack(java.lang.String)}.
	 */
	@Test
	public final void testPopDirectoryStack() {
		assertEquals(null, cac.popDirectoryStack());

		cac.pushDirectoryStack("hi");
		assertEquals("hi", cac.popDirectoryStack());
		assertEquals(null, cac.popDirectoryStack());

		cac.pushDirectoryStack("and");
		cac.pushDirectoryStack("next");
		assertEquals("next", cac.popDirectoryStack());
		assertEquals("and", cac.popDirectoryStack());
		assertEquals(null, cac.popDirectoryStack());	
	}


	/**
	 * Test method for {@link data.Cache#removeDirectory(java.lang.String)}.
	 */
	@Test
	public final void testRemoveDirectory() {
		cac.pushDirectoryStack("/q");
		cac.pushDirectoryStack("/path/one");
		cac.pushDirectoryStack("/");
		cac.pushDirectoryStack("/path2/a");
		cac.pushDirectoryStack("/q");
		cac.pushDirectoryStack("/p2/a");

		cac.removeDirectory("/q");

		assertEquals("/p2/a", cac.popDirectoryStack());
		assertEquals("/path2/a", cac.popDirectoryStack());
		assertEquals("/", cac.popDirectoryStack());
		assertEquals("/path/one", cac.popDirectoryStack());
		assertEquals(null, cac.popDirectoryStack());
	}

}
