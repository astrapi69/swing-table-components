/**
 * The MIT License
 *
 * Copyright (C) 2021 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.swing.table.model.quattro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * The unit test class for the class {@link QuattroTableModel}
 */
public class QuattroTableModelTest
{

	/**
	 * Test that the model of the default constructor tells for each of its four columns whether a
	 * cell is editable: all four are, as in TripleTableModel; the fourth was not while its editable
	 * flags had only three entries
	 *
	 * @param column
	 *            the index of the column
	 * @param editable
	 *            whether a cell of the column is editable
	 */
	@ParameterizedTest(name = "column {0} editable: {1}")
	@CsvSource({ "0, true", "1, true", "2, true", "3, true" })
	public void testIsCellEditableOfTheDefaultColumns(final int column, final boolean editable)
	{
		final QuattroTableModel<String, String, String, String> model = new QuattroTableModel<>();
		assertEquals(4, model.getColumnCount());
		assertEquals(editable, model.isCellEditable(0, column));
	}
}
