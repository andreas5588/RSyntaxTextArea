/*
 * 03/16/2015
 *
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE file for details.
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.awt.Color;

import org.fife.ui.rsyntaxtextarea.parser.ParserNotice.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Unit tests for the {@link DefaultParserNotice} class.
 *
 * @author Robert Futrell
 * @version 1.0
 */
public class DefaultParserNoticeTest {

	private MockParser parser;
	private DefaultParserNotice notice;


	@BeforeEach
	public void setUp() {
		parser = new MockParser();
	}


	@Test
	public void testDefaultParserNotice_3ArgConstructor() {

		notice = new DefaultParserNotice(parser, "Foo", 5);
		Assertions.assertEquals("Foo", notice.getMessage());
		Assertions.assertEquals(5, notice.getLine());
		Assertions.assertEquals(-1, notice.getOffset());
		Assertions.assertEquals(-1, notice.getLength());
		Assertions.assertFalse(notice.getKnowsOffsetAndLength());

	}


	@Test
	public void testDefaultParserNoticeParserNotice_5ArgConstructor() {

		notice = new DefaultParserNotice(parser, "Foo", 5, 4, 7);
		Assertions.assertEquals("Foo", notice.getMessage());
		Assertions.assertEquals(5, notice.getLine());
		Assertions.assertEquals(4, notice.getOffset());
		Assertions.assertEquals(7, notice.getLength());
		Assertions.assertTrue(notice.getKnowsOffsetAndLength());

	}


	@Test
	public void testCompareTo_null() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		Assertions.assertTrue(notice.compareTo(null) < 0);
	}


	@Test
	public void testCompareTo_differentLevels() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		notice.setLevel(Level.ERROR);
		DefaultParserNotice notice2 = new DefaultParserNotice(parser, "Foo", 5);
		notice2.setLevel(Level.INFO);
		Assertions.assertTrue(notice.compareTo(notice2) < 0);
		Assertions.assertTrue(notice2.compareTo(notice) > 0);
	}


	@Test
	public void testCompareTo_differentLines() {

		notice = new DefaultParserNotice(parser, "Foo", 5);
		notice.setLevel(Level.INFO);

		DefaultParserNotice notice2 = new DefaultParserNotice(parser, "Foo", 6);
		notice2.setLevel(Level.INFO);

		Assertions.assertTrue(notice.compareTo(notice2) < 0);
		Assertions.assertTrue(notice2.compareTo(notice) > 0);

	}


	@Test
	public void testCompareTo_differentMessages() {

		notice = new DefaultParserNotice(parser, "Foo", 5);
		notice.setLevel(Level.INFO);

		DefaultParserNotice notice2 = new DefaultParserNotice(parser, "FooBar", 5);
		notice2.setLevel(Level.INFO);

		Assertions.assertTrue(notice.compareTo(notice2) < 0);
		Assertions.assertTrue(notice2.compareTo(notice) > 0);

	}


	@Test
	public void testCompareTo_equal() {

		notice = new DefaultParserNotice(parser, "Foo", 5);
		notice.setLevel(Level.INFO);
		Assertions.assertEquals(0, notice.compareTo(notice));

		DefaultParserNotice notice2 = new DefaultParserNotice(parser, "Foo", 5);
		notice2.setLevel(Level.INFO);

		Assertions.assertEquals(0, notice.compareTo(notice2));
		Assertions.assertEquals(0, notice2.compareTo(notice));

	}


	@Test
	public void testContainsPosition() {

		int offs = 4;
		int len = 7;
		notice = new DefaultParserNotice(parser, "Foo", 5, offs, len);

		Assertions.assertFalse(notice.containsPosition(offs-1));
		for (int i=offs; i<offs+len; i++) {
			Assertions.assertTrue(notice.containsPosition(i));
		}
		Assertions.assertFalse(notice.containsPosition(offs+len));

	}


	@Test
	public void testEquals_null() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		Assertions.assertFalse(notice.equals(null));
	}


	@Test
	public void testEquals_differentObjectType() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		Assertions.assertFalse(notice.equals("Hello world"));
	}


	@Test
	public void testEquals_unequalParserNotice() {

		notice = new DefaultParserNotice(parser, "Foo", 5);
		notice.setLevel(Level.INFO);

		DefaultParserNotice notice2 = new DefaultParserNotice(parser, "Bar", 6);
		notice2.setLevel(Level.INFO);

		Assertions.assertFalse(notice.equals(notice2));
		Assertions.assertFalse(notice2.equals(notice));

	}


	@Test
	public void testEquals_equalParserNotice() {

		notice = new DefaultParserNotice(parser, "Foo", 5);
		notice.setLevel(Level.INFO);

		DefaultParserNotice notice2 = new DefaultParserNotice(parser, "Foo", 5);
		notice2.setLevel(Level.INFO);

		Assertions.assertTrue(notice.equals(notice));
		Assertions.assertTrue(notice.equals(notice2));
		Assertions.assertTrue(notice2.equals(notice));

	}


	@Test
	public void testGetColor() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		notice.setColor(Color.yellow);
		Assertions.assertEquals(Color.yellow, notice.getColor());
		notice.setColor(Color.orange);
		Assertions.assertEquals(Color.orange, notice.getColor());
	}


	@Test
	public void testGetKnowsOffsetAndLength() {

		notice = new DefaultParserNotice(parser, "Foo", 5);
		Assertions.assertFalse(notice.getKnowsOffsetAndLength());

		notice = new DefaultParserNotice(parser, "Foo", 5, 4, 7);
		Assertions.assertTrue(notice.getKnowsOffsetAndLength());

	}


	@Test
	public void testGetLength() {

		notice = new DefaultParserNotice(parser, "Foo", 5);
		Assertions.assertEquals(-1, notice.getLength());

		notice = new DefaultParserNotice(parser, "Foo", 5, 4, 7);
		Assertions.assertEquals(7, notice.getLength());

	}


	@Test
	public void testGetLevel() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		Assertions.assertEquals(Level.ERROR, notice.getLevel());
		notice.setLevel(Level.INFO);
		Assertions.assertEquals(Level.INFO, notice.getLevel());
		notice.setLevel(Level.WARNING);
		Assertions.assertEquals(Level.WARNING, notice.getLevel());
	}


	@Test
	public void testGetLine() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		Assertions.assertEquals(5, notice.getLine());
	}


	@Test
	public void testGetMessage() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		Assertions.assertEquals("Foo", notice.getMessage());
	}


	@Test
	public void testGetOffset() {
		notice = new DefaultParserNotice(parser, "Foo", 5, 4, 7);
		Assertions.assertEquals(4, notice.getOffset());
	}


	@Test
	public void testGetParser() {
		notice = new DefaultParserNotice(parser, "Foo", 5, 4, 7);
		Assertions.assertTrue(parser == notice.getParser());
	}


	@Test
	public void testGetShowInEditor() {
		notice = new DefaultParserNotice(parser, "Foo", 5, 4, 7);
		Assertions.assertTrue(notice.getShowInEditor());
		notice.setShowInEditor(false);
		Assertions.assertFalse(notice.getShowInEditor());
	}


	@Test
	public void testGetToolTipText() {

		notice = new DefaultParserNotice(parser, "Foo", 5, 4, 7);
		Assertions.assertEquals("Foo", notice.getToolTipText()); // Defaults to message

		final String TIP = "Hello world";
		notice.setToolTipText(TIP);
		Assertions.assertEquals(TIP, notice.getToolTipText());

	}


	@Test
	public void testHashCode() {

		int line = 5;
		DefaultParserNotice notice = new DefaultParserNotice(parser, "Foo", line);
		Assertions.assertEquals(line<<16 | notice.getOffset(), notice.hashCode());

		int offs = 3;
		notice = new DefaultParserNotice(parser, "Foo", line, offs, 4);
		Assertions.assertEquals(line<<16 | notice.getOffset(), notice.hashCode());

	}


	@Test
	public void testSetColor() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		notice.setColor(Color.yellow);
		Assertions.assertEquals(Color.yellow, notice.getColor());
		notice.setColor(Color.orange);
		Assertions.assertEquals(Color.orange, notice.getColor());
	}


	@Test
	public void testSetLevel() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		Assertions.assertEquals(Level.ERROR, notice.getLevel());
		notice.setLevel(Level.INFO);
		Assertions.assertEquals(Level.INFO, notice.getLevel());
		notice.setLevel(Level.WARNING);
		Assertions.assertEquals(Level.WARNING, notice.getLevel());
	}


	@Test
	public void testSetLevel_null() {
		notice = new DefaultParserNotice(parser, "Foo", 5);
		notice.setLevel(Level.INFO);
		Assertions.assertEquals(Level.INFO, notice.getLevel());
		notice.setLevel(null);
		Assertions.assertEquals(Level.ERROR, notice.getLevel());
	}


	@Test
	public void testSetShowInEditor() {
		notice = new DefaultParserNotice(parser, "Foo", 5, 4, 7);
		Assertions.assertTrue(notice.getShowInEditor());
		notice.setShowInEditor(false);
		Assertions.assertFalse(notice.getShowInEditor());
	}


	@Test
	public void testSetToolTipText() {

		notice = new DefaultParserNotice(parser, "Foo", 5, 4, 7);
		Assertions.assertEquals("Foo", notice.getToolTipText()); // Defaults to message

		final String TIP = "Hello world";
		notice.setToolTipText(TIP);
		Assertions.assertEquals(TIP, notice.getToolTipText());

	}


	@Test
	public void testToString() {
		notice = new DefaultParserNotice(parser, "Foo", 5, 4, 7);
		Assertions.assertEquals("Line 5: Foo", notice.toString());
	}

}
