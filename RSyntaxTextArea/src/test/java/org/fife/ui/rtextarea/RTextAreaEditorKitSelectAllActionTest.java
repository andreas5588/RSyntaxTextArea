/*
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE file for details.
 */
package org.fife.ui.rtextarea;

import org.fife.ui.SwingRunnerExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.swing.text.DefaultEditorKit;
import java.awt.event.ActionEvent;


/**
 * Unit tests for the {@link RTextAreaEditorKit.SelectAllAction} class.
 *
 * @author Robert Futrell
 * @version 1.0
 */
@ExtendWith(SwingRunnerExtension.class)
public class RTextAreaEditorKitSelectAllActionTest {


	@Test
	public void testConstructor_multiArg() {
		RTextAreaEditorKit.SelectAllAction action = new RTextAreaEditorKit.SelectAllAction(
			"name", null, "Description", 0, null);
		Assertions.assertEquals("name", action.getName());
		Assertions.assertEquals("Description", action.getDescription());
	}


	@Test
	public void testActionPerformedImpl_noSelection() {

		RTextArea textArea = new RTextArea("line 1\nline 2\nline 3");

		ActionEvent e = new ActionEvent(textArea, 0, "command");
		new RTextAreaEditorKit.SelectAllAction().actionPerformedImpl(e, textArea);

		Assertions.assertEquals(0, textArea.getSelectionStart());
		Assertions.assertEquals(textArea.getText().length(), textArea.getSelectionEnd());
		Assertions.assertEquals(textArea.getText().length(), textArea.getCaretPosition());
	}


	@Test
	public void testGetMacroID() {
		Assertions.assertEquals(DefaultEditorKit.selectAllAction,
			new RTextAreaEditorKit.SelectAllAction().getMacroID());
	}
}
