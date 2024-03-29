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
package io.github.astrapi69.swing.table.model.suffle.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import io.github.astrapi69.check.Check;
import io.github.astrapi69.swing.table.shuffle.GenericShuffleJTable;

public class AddAction<T> extends AbstractAction
{

	private static final long serialVersionUID = 1L;

	private final GenericShuffleJTable<T> shuffleJTable;

	public AddAction(final GenericShuffleJTable<T> shuffleJTable)
	{
		Check.get().notNull(shuffleJTable, "shuffleJTable");
		this.shuffleJTable = shuffleJTable;
	}

	@Override
	public void actionPerformed(final ActionEvent e)
	{
		final int[] selectedRows = shuffleJTable.getLeftTable().getSelectedRows();
		if (selectedRows.length == 0)
		{
			JOptionPane.showMessageDialog(null, "You have to selected at least one row.");
		}
		else
		{
			shuffleJTable.shuffleSelectedLeftRowsToRightTable();
		}
	}

}
