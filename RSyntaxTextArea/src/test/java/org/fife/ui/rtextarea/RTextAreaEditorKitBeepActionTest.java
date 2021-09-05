/*
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE file for details.
 */
package org.fife.ui.rtextarea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Unit tests for the {@link RTextAreaEditorKit.BeepAction} class.
 *
 * @author Robert Futrell
 * @version 1.0
 */
public class RTextAreaEditorKitBeepActionTest {


	@Test
	public void testActionPerformedImpl() {
		new RTextAreaEditorKit.BeepAction().actionPerformedImpl(null, null);
	}


	@Test
	public void testGetMacroID() {
		Assertions.assertEquals(RTextAreaEditorKit.beepAction, new RTextAreaEditorKit.BeepAction().getMacroID());
	}
}
