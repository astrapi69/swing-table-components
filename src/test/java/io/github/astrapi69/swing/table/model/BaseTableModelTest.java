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
package io.github.astrapi69.swing.table.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * The unit test class for the class {@link BaseTableModel}
 */
public class BaseTableModelTest
{

	/**
	 * Creates a model of rows of strings with the constructor without arguments
	 *
	 * @return the new model
	 */
	private static BaseTableModel<String> newModel()
	{
		return new BaseTableModel<>()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Object getValueAt(final int rowIndex, final int columnIndex)
			{
				return get(rowIndex);
			}
		};
	}

	/**
	 * Test that the constructor without arguments gives a model without rows and without a columns
	 * model, and that the columns model can be set afterwards
	 */
	@Test
	public void testNoArgumentConstructor()
	{
		final BaseTableModel<String> model = newModel();
		assertNull(model.getColumnsModel());
		assertTrue(model.getData().isEmpty());
		assertEquals(0, model.getRowCount());

		final TableColumnsModel columnsModel = TableColumnsModel.builder()
			.columnNames(new String[] { "Name" }).canEdit(new boolean[] { true })
			.columnClasses(new Class<?>[] { String.class }).build();
		model.setColumnsModel(columnsModel);
		assertSame(columnsModel, model.getColumnsModel());
		assertEquals(1, model.getColumnCount());
		assertEquals("Name", model.getColumnName(0));
		assertSame(String.class, model.getColumnClass(0));
		assertTrue(model.isCellEditable(0, 0));
	}
}
