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
package io.github.astrapi69.swing.table.model.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * The unit test class for the classes {@link KeyValueTableModel} and
 * {@link StringKeyValueTableModel}
 */
public class KeyValueTableModelTest
{

	/**
	 * Test that a cell gives its part of the row: the key in column 0, the value in column 1, and
	 * null in a column the model does not have
	 *
	 * @param column
	 *            the index of the column
	 * @param expected
	 *            the value the cell gives
	 */
	@ParameterizedTest(name = "column {0}: {1}")
	@CsvSource(value = { "0, foo", "1, bar", "2, null" }, nullValues = "null")
	public void testGetValueAt(final int column, final String expected)
	{
		final KeyValueTableModel<String, String> model = new KeyValueTableModel<>();
		model.add(Map.entry("foo", "bar"));
		assertEquals(expected, model.getValueAt(0, column));
	}

	/**
	 * Test that the string model reads its rows the same way and names String as the class of both
	 * columns
	 */
	@Test
	public void testStringKeyValueTableModel()
	{
		final StringKeyValueTableModel model = new StringKeyValueTableModel();
		model.add(Map.entry("foo", "bar"));
		assertEquals("foo", model.getValueAt(0, 0));
		assertEquals("bar", model.getValueAt(0, 1));
		assertEquals(String.class, model.getColumnClass(0));
		assertEquals(String.class, model.getColumnClass(1));
	}
}
