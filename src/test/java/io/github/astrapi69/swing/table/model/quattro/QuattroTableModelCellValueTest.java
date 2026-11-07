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

import io.github.astrapi69.collection.pair.Quattro;

/**
 * The unit test class for the cell values of the class {@link QuattroTableModel}
 */
public class QuattroTableModelCellValueTest
{

	/**
	 * Test that a cell gives its part of the row: top left, top right, bottom left and bottom right
	 * in the columns 0 to 3, and null in a column the model does not have
	 *
	 * @param column
	 *            the index of the column
	 * @param expected
	 *            the value the cell gives
	 */
	@ParameterizedTest(name = "column {0}: {1}")
	@CsvSource(value = { "0, top left", "1, top right", "2, bottom left", "3, bottom right",
			"4, null" }, nullValues = "null")
	public void testGetValueAt(final int column, final String expected)
	{
		final QuattroTableModel<String, String, String, String> model = new QuattroTableModel<>();
		model.add(Quattro.<String, String, String, String> builder().topLeft("top left")
			.topRight("top right").bottomLeft("bottom left").bottomRight("bottom right").build());
		assertEquals(expected, model.getValueAt(0, column));
	}
}
