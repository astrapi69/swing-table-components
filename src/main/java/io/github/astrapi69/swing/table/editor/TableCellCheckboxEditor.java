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
package io.github.astrapi69.swing.table.editor;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link TableCellCheckboxEditor}.
 */
@Getter
@Setter
public class TableCellCheckboxEditor extends DefaultCellEditor
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The button. */
	private JCheckBox button;

	/** The flag if the button was clicked. */
	private boolean clicked;

	/** The column index. */
	private int column;

	/** The row index. */
	private int row;

	/** The value. */
	private Object value;

	/**
	 * Instantiates a new {@link TableCellCheckboxEditor} object.
	 *
	 * @param checkBox
	 *            the check box
	 */
	public TableCellCheckboxEditor(final JCheckBox checkBox)
	{
		super(checkBox);
		setButton(new JCheckBox());
		getButton().setOpaque(true);
		getButton().addActionListener(e -> {
			onClick();
			fireEditingStopped();
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getCellEditorValue()
	{
		if (isClicked())
		{
			JOptionPane.showMessageDialog(button, "You clicked the button with the value "
				+ this.value + " in row index " + row + " and in colunm index " + column + ".");
		}
		setClicked(false);
		String text = "";
		if (getValue() != null)
		{
			text = getValue().toString();
		}
		return text;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getTableCellEditorComponent(final JTable table, final Object value,
		final boolean isSelected, final int row, final int column)
	{
		setRow(row);
		setColumn(column);
		setValue(value);
		if (isSelected)
		{
			getButton().setForeground(table.getSelectionForeground());
			getButton().setBackground(table.getSelectionBackground());
		}
		else
		{
			getButton().setForeground(table.getForeground());
			getButton().setBackground(table.getBackground());
		}
		String text = "";
		if (value != null)
		{
			text = getValue().toString();
		}
		getButton().setText(text);
		setClicked(true);
		return getButton();
	}

	/**
	 * Callback method to interact when the button is clicked.
	 */
	protected void onClick()
	{

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean stopCellEditing()
	{
		setClicked(false);
		return super.stopCellEditing();
	}
}
