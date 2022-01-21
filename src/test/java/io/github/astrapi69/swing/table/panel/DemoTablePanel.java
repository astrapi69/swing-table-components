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

import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.GenericModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.table.model.DemoPermissionsTableModel;
import io.github.astrapi69.swing.table.model.GenericTableModel;
import io.github.astrapi69.test.objects.Permission;
import io.github.astrapi69.window.adapter.CloseWindow;

public class DemoTablePanel extends TablePanel<Permission>
{


	public DemoTablePanel()
	{
		this(GenericModel.ofList(new ArrayList<>()));
	}

	public DemoTablePanel(Model<List<Permission>> model)
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
		frame.setTitle("Table panel");
		final DemoTablePanel panel = new DemoTablePanel(BaseModel.of(permissions));
		frame.add(panel);
		frame.setSize(700, 500);
		frame.setVisible(true);
	}

	@Override
	protected GenericTableModel<Permission> newTableModel()
	{
		DemoPermissionsTableModel demoPermissionsTableModel = new DemoPermissionsTableModel();
		demoPermissionsTableModel.setData(getModelObject());
		return demoPermissionsTableModel;
	}
}
