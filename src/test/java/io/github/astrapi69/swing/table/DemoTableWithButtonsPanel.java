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

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;

import io.github.astrapi69.collection.pair.KeyValuePair;
import io.github.astrapi69.model.GenericModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.table.editor.TableCellButtonEditor;
import io.github.astrapi69.swing.table.model.TableColumnsModel;
import io.github.astrapi69.swing.table.model.properties.StringKeyValueTableModel;
import io.github.astrapi69.swing.table.renderer.TableCellButtonRenderer;
import io.github.astrapi69.awt.window.adapter.CloseWindow;

public class DemoTableWithButtonsPanel extends BasePanel<List<KeyValuePair<String, String>>>
{

	private static final long serialVersionUID = 1L;
	private JScrollPane scrTable;
	private GenericJTable<KeyValuePair<String, String>> table;

	public DemoTableWithButtonsPanel(final IModel<List<KeyValuePair<String, String>>> model)
	{
		super(model);
	}

	public static void main(final String[] args)
	{
		final List<KeyValuePair<String, String>> list = new ArrayList<>();
		list.add(KeyValuePair.<String, String> builder().key("foo").value("overview").build());
		list.add(KeyValuePair.<String, String> builder().key("bar").value("overview").build());
		list.add(KeyValuePair.<String, String> builder().key("bla").value("overview").build());

		final DemoTableWithButtonsPanel panel = new DemoTableWithButtonsPanel(
			GenericModel.ofList(list));

		final JFrame frame = new JFrame();
		frame.addWindowListener(new CloseWindow());
		frame.setTitle("DemoTableWithButtonsPanel");


		frame.add(panel);
		frame.setBounds(0, 0, 1020, 420);
		frame.setVisible(true);

	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		final StringKeyValueTableModel tableModel = new StringKeyValueTableModel(
			TableColumnsModel.builder().columnNames(new String[] { "Key", "Value" })
				.canEdit(new boolean[] { false, true })
				.columnClasses(new Class<?>[] { String.class, String.class }).build());
		tableModel.addList(getModelObject());
		table = new GenericJTable<>(tableModel);
		final TableColumn valueColumn = table.getColumn("Value");
		valueColumn.setCellRenderer(new TableCellButtonRenderer(null, null));
		valueColumn.setCellEditor(new TableCellButtonEditor(new JCheckBox()));
		scrTable = new JScrollPane(table);
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
		final BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(BorderLayout.CENTER, scrTable);
	}
}
