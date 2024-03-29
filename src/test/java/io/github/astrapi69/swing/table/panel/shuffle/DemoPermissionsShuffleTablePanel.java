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

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.table.GenericJTable;
import io.github.astrapi69.swing.table.model.DemoPermissionsTableModel;
import io.github.astrapi69.swing.table.shuffle.GenericShuffleJTable;
import io.github.astrapi69.test.object.Permission;
import io.github.astrapi69.awt.window.adapter.CloseWindow;

public class DemoPermissionsShuffleTablePanel extends ShuffleTablePanel<Permission>
	implements
		ActionListener
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public DemoPermissionsShuffleTablePanel(final IModel<List<Permission>> model)
	{
		super(model);
	}

	public static void main(final String[] args)
	{
		// 1. Create a list with data.
		final List<Permission> permissions = new ArrayList<>();

		permissions
			.add(Permission.builder().name("read").description("Permission to read.").build());
		permissions
			.add(Permission.builder().name("write").description("Permission to write.").build());
		permissions
			.add(Permission.builder().name("delete").description("Permission to delete.").build());
		permissions.add(
			Permission.builder().name("execute").description("Permission to execute.").build());
		permissions.add(Permission.builder().name("buy").description("Permission to buy.").build());
		permissions
			.add(Permission.builder().name("sale").description("Permission to sale.").build());

		final Frame frame = new Frame();
		frame.addWindowListener(new CloseWindow());
		frame.setTitle("Shuffle table panel");
		final DemoPermissionsShuffleTablePanel panel = new DemoPermissionsShuffleTablePanel(
			BaseModel.of(permissions));
		frame.add(panel);
		frame.setSize(700, 500);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(final ActionEvent event)
	{

		if (event.getSource() == this.btnAdd)
		{
			if (0 < this.tblAvailableElements.getSelectedRowCount())
			{
				this.shuffleTable.shuffleSelectedLeftRowToRightTable();
			}

		}
		else if (event.getSource() == this.btnAddAll)
		{

			this.shuffleTable.addAllLeftRowsToRightTable();

		}
		else if (event.getSource() == this.btnRemoveAll)
		{

			this.shuffleTable.addAllRightRowsToLeftTable();

		}
		else if (event.getSource() == this.btnRemove)
		{
			if (0 < this.tblSelectedElements.getSelectedRowCount())
			{
				this.shuffleTable.shuffleSelectedRightRowsToLeftTable();
			}
		}
	}

	@Override
	protected void onInitializeComponents()
	{
		this.btnAddAll = new JButton("Add all >>");
		this.btnAddAll.addActionListener(this);
		this.btnAdd = new JButton("Add >");
		this.btnAdd.addActionListener(this);
		this.btnRemoveAll = new JButton("<< Remove all");
		this.btnRemoveAll.addActionListener(this);
		this.btnRemove = new JButton("< Remove");
		this.btnRemove.addActionListener(this);
		this.lblAvailableElements = new JLabel("Available permissions:");
		this.lblSelectedElements = new JLabel("Selected permissions:");
		this.scrPnTblAvailableElements = new JScrollPane();
		this.scrPnTblSelectedElements = new JScrollPane();
		// 2. Create a generic table model for the class Permission.
		final DemoPermissionsTableModel permissionsTableModel = new DemoPermissionsTableModel();
		// 3. Add the data to the model.
		permissionsTableModel.addList(getModelObject());
		// 4. Create the generic table and associate with the generic table
		// model.
		this.tblAvailableElements = new GenericJTable<>(permissionsTableModel);
		this.tblSelectedElements = new GenericJTable<>(new DemoPermissionsTableModel());
		this.shuffleTable = new GenericShuffleJTable<>(this.tblAvailableElements,
			this.tblSelectedElements);
		this.scrPnTblAvailableElements.setViewportView(this.tblAvailableElements);
		this.scrPnTblSelectedElements.setViewportView(this.tblSelectedElements);
	}

	@Override
	protected void onInitializeLayout()
	{
		final GridBagLayout gbl = new GridBagLayout();
		final GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(gbl);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 10;
		gbc.weighty = 0;
		gbc.weightx = 0;
		add(this.scrPnTblAvailableElements, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0;
		gbc.weightx = 0;
		add(this.btnAdd, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0;
		gbc.weightx = 0;
		add(this.btnAddAll, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0;
		gbc.weightx = 0;
		add(this.btnRemoveAll, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0;
		gbc.weightx = 0;
		add(this.btnRemove, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 10;
		gbc.weighty = 0;
		gbc.weightx = 0;
		add(this.scrPnTblSelectedElements, gbc);

	}

}
