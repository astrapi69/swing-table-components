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
package io.github.astrapi69.swing.table.panel.shuffle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import io.github.astrapi69.swing.table.GenericJTable;
import io.github.astrapi69.swing.table.shuffle.GenericShuffleJTable;
import lombok.Getter;
import lombok.Setter;

import io.github.astrapi69.model.GenericModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;

@Getter
@Setter
public class ShuffleTablePanel<T> extends BasePanel<List<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	protected JButton btnAdd;

	protected JButton btnAddAll;

	protected JButton btnRemove;

	protected JButton btnRemoveAll;

	protected JLabel lblAvailableElements;

	protected JLabel lblSelectedElements;

	protected JScrollPane scrPnTblAvailableElements;

	protected JScrollPane scrPnTblSelectedElements;

	protected GenericShuffleJTable<T> shuffleTable;

	protected GenericJTable<T> tblAvailableElements;

	protected GenericJTable<T> tblSelectedElements;

	public ShuffleTablePanel()
	{
		this(GenericModel.ofList(new ArrayList<>()));
	}

	public ShuffleTablePanel(final IModel<List<T>> model)
	{
		super(model);
	}

}
