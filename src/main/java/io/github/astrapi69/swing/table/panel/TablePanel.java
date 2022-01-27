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
package io.github.astrapi69.swing.table.panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import lombok.Getter;
import io.github.astrapi69.model.GenericModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.component.factory.SwingContainerFactory;
import io.github.astrapi69.swing.table.GenericJXTable;
import io.github.astrapi69.swing.table.model.GenericTableModel;

/**
 * The class {@link TablePanel}.
 *
 * @param <T>
 *            the generic type of the model
 */
@Getter
public abstract class TablePanel<T> extends BasePanel<List<T>>
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The scroll pane for the main table. */
	protected JScrollPane scrMainTable;

	/** The main table. */
	private GenericJXTable<T> tblMainTable;

	public TablePanel()
	{
		this(GenericModel.ofList(new ArrayList<>()));
	}

	public TablePanel(final IModel<List<T>> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		// Create the tables and scrollpanes...
		tblMainTable = new GenericJXTable<>(newTableModel());
		scrMainTable = newTableScrollPane();
		scrMainTable.setViewportView(tblMainTable);
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
		GridLayout gridLayout = new GridLayout(1, 1, 50, 50);
		this.setLayout(gridLayout);
		add(scrMainTable);
	}

	/**
	 * Abstract factory method to provide the right table model.
	 *
	 * @return the generic table model
	 */
	protected abstract GenericTableModel<T> newTableModel();


	/**
	 * Factory method for creating the new {@link JScrollPane}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a {@link JScrollPane}
	 *
	 * @return the new {@link JScrollPane}
	 */
	protected JScrollPane newTableScrollPane()
	{
		return SwingContainerFactory.newScrollPane();
	}

}
