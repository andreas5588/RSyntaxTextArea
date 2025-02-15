/*
 * 03/22/2019
 *
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE file for details.
 */
package org.fife.ui.rsyntaxtextarea.modes;

import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMaker;
import org.fife.ui.rsyntaxtextarea.TokenTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.Segment;


/**
 * Unit tests for the {@link CsvTokenMaker} class.
 *
 * @author Robert Futrell
 * @version 1.0
 */
public class CsvTokenMakerTest extends AbstractTokenMakerTest {


	/**
	 * Returns a new instance of the <code>TokenMaker</code> to test.
	 *
	 * @return The <code>TokenMaker</code> to test.
	 */
	private TokenMaker createTokenMaker() {
		return new CsvTokenMaker();
	}


	@Test
	void testCsv_comma() {

		Segment segment = createSegment(",");
		TokenMaker tm = createTokenMaker();
		Token token = tm.getTokenList(segment, TokenTypes.NULL, 0);
		Assertions.assertEquals(TokenTypes.OPERATOR, token.getType());
	}


	@Test
	void testCsv_4columns_noQuotes() {

		String code = "one,two,three,four";
		Segment segment = createSegment(code);
		TokenMaker tm = createTokenMaker();
		Token token = tm.getTokenList(segment, TokenTypes.NULL, 0);

		Assertions.assertTrue(token.is(TokenTypes.IDENTIFIER, "one"));
		token = token.getNextToken();
		Assertions.assertTrue(token.isSingleChar(TokenTypes.OPERATOR, ','));
		token = token.getNextToken();
		Assertions.assertTrue(token.is(TokenTypes.DATA_TYPE, "two"));
		token = token.getNextToken();
		Assertions.assertTrue(token.isSingleChar(TokenTypes.OPERATOR, ','));
		token = token.getNextToken();
		Assertions.assertTrue(token.is(TokenTypes.IDENTIFIER, "three"));
		token = token.getNextToken();
		Assertions.assertTrue(token.isSingleChar(TokenTypes.OPERATOR, ','));
		token = token.getNextToken();
		Assertions.assertTrue(token.is(TokenTypes.DATA_TYPE, "four"));

		Assertions.assertFalse(token.getNextToken().isPaintable());
	}


	@Test
	void testCsv_4columns_quotes() {

		String code = "\"one\",\"two\",\"three,threeagain\",\"four\"";
		Segment segment = createSegment(code);
		TokenMaker tm = createTokenMaker();
		Token token = tm.getTokenList(segment, TokenTypes.NULL, 0);

		Assertions.assertTrue(token.is(TokenTypes.IDENTIFIER, "\"one\""));
		token = token.getNextToken();
		Assertions.assertTrue(token.isSingleChar(TokenTypes.OPERATOR, ','));
		token = token.getNextToken();
		Assertions.assertTrue(token.is(TokenTypes.DATA_TYPE, "\"two\""));
		token = token.getNextToken();
		Assertions.assertTrue(token.isSingleChar(TokenTypes.OPERATOR, ','));
		token = token.getNextToken();
		Assertions.assertTrue(token.is(TokenTypes.IDENTIFIER, "\"three,threeagain\""));
		token = token.getNextToken();
		Assertions.assertTrue(token.isSingleChar(TokenTypes.OPERATOR, ','));
		token = token.getNextToken();
		Assertions.assertTrue(token.is(TokenTypes.DATA_TYPE, "\"four\""));

		Assertions.assertFalse(token.getNextToken().isPaintable());
	}


	@Test
	void testCsv_newlineInQuotedRegion() {

		String code = "one,\"unfinished-two";
		Segment segment = createSegment(code);
		TokenMaker tm = createTokenMaker();
		Token token = tm.getTokenList(segment, TokenTypes.NULL, 0);

		Assertions.assertTrue(token.is(Token.IDENTIFIER, "one"));
		token = token.getNextToken();
		Assertions.assertTrue(token.isSingleChar(TokenTypes.OPERATOR, ','));
		token = token.getNextToken();
		Assertions.assertTrue(token.is(Token.DATA_TYPE, "\"unfinished-two"));

		token = token.getNextToken();
		Assertions.assertEquals(token.getType(), CsvTokenMaker.INTERNAL_STRING | 1);
	}


	@Test
	void testCsv_quotedRegionContinuedOnNewLine() {

		String code = "continued-quoted-region\",another";
		Segment segment = createSegment(code);
		TokenMaker tm = createTokenMaker();
		Token token = tm.getTokenList(segment, CsvTokenMaker.INTERNAL_STRING | 1, 0);

		Assertions.assertTrue(token.is(Token.DATA_TYPE, "continued-quoted-region\""));
		token = token.getNextToken();
		Assertions.assertTrue(token.isSingleChar(TokenTypes.OPERATOR, ','));
		token = token.getNextToken();
		Assertions.assertTrue(token.is(Token.IDENTIFIER, "another"));

		Assertions.assertFalse(token.getNextToken().isPaintable());
	}


	@Test
	void testCsv_escapedQuotesInQuotedRegion() {

		String code = "\"quoted \"\" string\"";
		Segment segment = createSegment(code);
		TokenMaker tm = createTokenMaker();
		Token token = tm.getTokenList(segment, TokenTypes.NULL, 0);

		Assertions.assertTrue(token.is(Token.IDENTIFIER, "\"quoted \"\" string\""));
	}


	@Test
	@Override
	public void testGetLineCommentStartAndEnd() {
		Assertions.assertNull(createTokenMaker().getLineCommentStartAndEnd(0));
	}
}
