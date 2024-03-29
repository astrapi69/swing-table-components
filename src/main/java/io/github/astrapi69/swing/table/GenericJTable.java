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
package io.github.astrapi69.swing.table;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

import lombok.Getter;
import lombok.NonNull;

import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.swing.listener.mouse.MouseDoubleClickListener;
import io.github.astrapi69.swing.table.model.GenericTableModel;

/**
 * The class GenericJXTable.
 *
 * @param <T>
 *            the generic type of the model
 */
@Getter
public class GenericJTable<T> extends JTable
{

	/** The generic table model. */
	private final GenericTableModel<T> genericTableModel;

	/** The sorter. */
	private final TableRowSorter<GenericTableModel<T>> sorter;

	/**
	 * Instantiates a new GenericJXTable.
	 *
	 * @param genericTableModel
	 *            the generic table model
	 */
	public GenericJTable(final @NonNull GenericTableModel<T> genericTableModel)
	{
		super();
		this.genericTableModel = genericTableModel;

		this.setModel(this.genericTableModel);

		this.sorter = new TableRowSorter<>(this.genericTableModel);

		this.setRowSorter(this.sorter);

		this.addMouseListener(new MouseDoubleClickListener()
		{
			@Override
			public void onSingleClick(MouseEvent mouseEvent)
			{
				if (mouseEvent.getButton() == MouseEvent.BUTTON1)
				{
					onSingleLeftClick(mouseEvent);
				}
				if (mouseEvent.getButton() == MouseEvent.BUTTON2)
				{
					onSingleMiddleClick(mouseEvent);
				}
				if (mouseEvent.getButton() == MouseEvent.BUTTON3)
				{
					onSingleRightClick(mouseEvent);
				}
			}

			@Override
			public void onDoubleClick(MouseEvent mouseEvent)
			{
				if (mouseEvent.getButton() == MouseEvent.BUTTON1)
				{
					onDoubleLeftClick(mouseEvent);
				}
				if (mouseEvent.getButton() == MouseEvent.BUTTON2)
				{
					onDoubleMiddleClick(mouseEvent);
				}
				if (mouseEvent.getButton() == MouseEvent.BUTTON3)
				{
					onDoubleRightClick(mouseEvent);
				}
			}
		});
	}

	/**
	 * Gets the selected row.
	 *
	 * @return the selected row
	 */
	@Override
	public int getSelectedRow()
	{
		int selectedRow = super.getSelectedRow();
		if (-1 < selectedRow)
		{
			// find the real selected row. If the rows was sorted the index from
			// the
			// model does not fit to the table.
			selectedRow = this.convertRowIndexToModel(selectedRow);
		}
		return selectedRow;
	}

	/**
	 * Gets an optional of the selected row data
	 * 
	 * @return an optional of the selected row data
	 */
	public Optional<T> getSingleSelectedRowData()
	{
		if (-1 < getSelectedRow())
		{
			return Optional.of(getGenericTableModel().get(getSelectedRow()));
		}
		return Optional.empty();
	}

	/**
	 * Gets a list of the selected row data
	 * 
	 * @return a list of the selected row data
	 */
	public List<T> getAllSelectedRowData()
	{
		int[] selectedRows = getSelectedRows();
		List<T> selectedData = ListFactory.newArrayList();
		for (int i = 0; i < selectedRows.length; i++)
		{
			selectedData.add(getGenericTableModel().get(selectedRows[i]));
		}
		return selectedData;
	}

	/**
	 * Gets the selected rows.
	 *
	 * @return the selected rows
	 */
	@Override
	public int[] getSelectedRows()
	{
		// find the real selected rows. If the rows was sorted the index from the
		// model does not fit to the table.
		final int[] selectedRows = super.getSelectedRows();
		final int[] sr = new int[selectedRows.length];
		for (int i = 0; i < selectedRows.length; i++)
		{
			sr[i] = this.convertRowIndexToModel(selectedRows[i]);
		}
		return sr;
	}

	/**
	 * The callback method on single left click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onSingleLeftClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on single middle click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onSingleMiddleClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on single right click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onSingleRightClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on double left click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onDoubleLeftClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on double middle click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onDoubleMiddleClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on double right click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onDoubleRightClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on delete the selected row
	 */
	protected void onDeleteSelectedRow()
	{
		final int selectedRow = getSelectedRow();
		if (-1 < selectedRow)
		{
			getGenericTableModel().removeAt(selectedRow);
		}
	}

	/**
	 * Returns true only if multiply rows are selected
	 *
	 * @return true only if multiply <code>rows</code> are selected, otherwise false
	 */
	public boolean isMultiplySelection()
	{
		return isSelectionNotEmpty() && !isSingleSelection();
	}

	/**
	 * Returns true only if one <code>row</code> is selected
	 *
	 * @return true only if one <code>row</code> is selected, otherwise false
	 */
	public boolean isSingleSelection()
	{
		return getSelectionModel().getSelectionMode() == ListSelectionModel.SINGLE_SELECTION;
	}

	/**
	 * Returns true only if no <code>row</code> is selected
	 *
	 * @return true only if no <code>row</code> is selected, otherwise false
	 */
	public boolean isSelectionEmpty()
	{
		return getSelectionModel().isSelectionEmpty();
	}

	/**
	 * Returns true only if any <code>row(s)</code> is/are selected
	 *
	 * @return true only if any <code>row(s)</code> is/are selected, otherwise false
	 */
	public boolean isSelectionNotEmpty()
	{
		return !isSelectionEmpty();
	}

	/**
	 * The callback method on delete the selected row
	 */
	protected void onDeleteSelectedRows()
	{
		getGenericTableModel().removeAll(getSelectedRows());
	}

	/**
	 * The callback method on add a new row
	 *
	 * @param newRow
	 *            the new row add
	 */
	protected void onAddNewRow(@NonNull T newRow)
	{
		getGenericTableModel().add(newRow);
	}

	/**
	 * Adds the given list
	 *
	 * @param list
	 *            the list of rows to add
	 */
	protected void onAddNewRows(@NonNull final List<T> list)
	{
		getGenericTableModel().addList(list);
	}

	/**
	 * This method deselects all selected rows and columns. It delegates to the method
	 * <code>clearSelection</code>
	 */
	public void deselectAll()
	{
		this.clearSelection();
	}

}
