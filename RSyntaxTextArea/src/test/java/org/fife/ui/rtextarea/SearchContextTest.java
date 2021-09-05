/*
 * 01/29/2016
 *
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE file for details.
 */
package org.fife.ui.rtextarea;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.BadLocationException;


/**
 * Unit tests for the {@code SearchContext} class.
 *
 * @author Robert Futrell
 * @version 1.0
 */
public class SearchContextTest {


	@Test
	public void testConstructor_2Arg() {
		SearchContext sc = new SearchContext("foo", true);
		Assertions.assertEquals("foo", sc.getSearchFor());
		Assertions.assertTrue(sc.getMatchCase());
	}


	@Test
	public void testAddPropertyChangeListener() {
		TestPropertyChangeListener pcl = new TestPropertyChangeListener();
		SearchContext sc = new SearchContext();
		sc.addPropertyChangeListener(pcl);
		sc.firePropertyChange("fooProp", false, true);
		Assertions.assertTrue(pcl.called);
	}


	@Test
	public void testClone() {

		String searchFor = "foo";
		boolean matchCase = true;
		boolean markAll = false;
		boolean regex = true;
		String replaceWith = "bar";
		boolean forward = false;
		boolean selectionOnly = false;//true; // "true" not yet supported
		boolean wholeWord = true;

		SearchContext sc = new SearchContext(searchFor, matchCase);
		sc.setMarkAll(markAll);
		sc.setRegularExpression(regex);
		sc.setReplaceWith(replaceWith);
		sc.setSearchForward(forward);
		sc.setSearchSelectionOnly(selectionOnly);
		sc.setWholeWord(wholeWord);

		SearchContext sc2 = sc.clone();
		Assertions.assertEquals(sc2.getSearchFor(), sc.getSearchFor());
		Assertions.assertEquals(sc2.getMatchCase(), sc.getMatchCase());
		Assertions.assertEquals(sc2.getMarkAll(), sc.getMarkAll());
		Assertions.assertEquals(sc2.isRegularExpression(), sc.isRegularExpression());
		Assertions.assertEquals(sc2.getReplaceWith(), sc.getReplaceWith());
		Assertions.assertEquals(sc2.getSearchForward(), sc.getSearchForward());
		Assertions.assertEquals(sc2.getSearchSelectionOnly(), sc.getSearchSelectionOnly());
		Assertions.assertEquals(sc2.getWholeWord(), sc.getWholeWord());

	}


	@Test
	public void testFirePropertyChange_booleanArgs() {
		TestPropertyChangeListener pcl = new TestPropertyChangeListener();
		SearchContext sc = new SearchContext();
		sc.addPropertyChangeListener(pcl);
		sc.firePropertyChange("fooProp", false, true);
		Assertions.assertTrue(pcl.called);
	}


	@Test
	public void testFirePropertyChange_stringArgs() {
		TestPropertyChangeListener pcl = new TestPropertyChangeListener();
		SearchContext sc = new SearchContext();
		sc.addPropertyChangeListener(pcl);
		sc.firePropertyChange("fooProp", "old", "new");
		Assertions.assertTrue(pcl.called);
	}


	@Test
	public void testRemovePropertyChangeListener() {
		TestPropertyChangeListener pcl = new TestPropertyChangeListener();
		SearchContext sc = new SearchContext();
		sc.addPropertyChangeListener(pcl);
		sc.removePropertyChangeListener(pcl);
		sc.firePropertyChange("fooProp", false, true);
		Assertions.assertFalse(pcl.called);
	}


	@Test
	public void testSetSearchSelectionOnly_true() {
		Assertions.assertThrows(UnsupportedOperationException.class, () -> {
			SearchContext sc = new SearchContext();
			sc.setSearchSelectionOnly(true);
		});
	}


	@Test
	public void testToString() {
		final String EXPECTED = "[SearchContext: searchFor=null, " +
				"replaceWith=null, matchCase=false, wholeWord=false, " +
				"regex=false, markAll=true]";
		SearchContext sc = new SearchContext();
		Assertions.assertEquals(EXPECTED, sc.toString());
	}


	private static class TestPropertyChangeListener
			implements PropertyChangeListener {

		public boolean called;

		public TestPropertyChangeListener() {
			called = false;
		}

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			called = true;
		}

	}


}
