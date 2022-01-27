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

import javax.swing.*;

import io.github.astrapi69.model.GenericModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.table.model.DemoPermissionsTableModel;
import io.github.astrapi69.swing.table.model.GenericTableModel;
import io.github.astrapi69.test.instances.TestPermissionFactory;
import io.github.astrapi69.test.objects.Permission;
import io.github.astrapi69.window.adapter.CloseWindow;

public class DemoShuffleTablePanel extends AbstractShuffleTablePanel<Permission>
{

	public DemoShuffleTablePanel()
	{
		this(GenericModel.ofList(new ArrayList<>()));
	}

	public DemoShuffleTablePanel(final IModel<List<Permission>> ofList)
	{
		super(ofList);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args)
	{
		// 1. Create a list with data.
		final List<Permission> permissions = TestPermissionFactory.getPermissions();
		// 2. Create a panel with that encapsulates the two tables and buttons.
		final DemoShuffleTablePanel panel = new DemoShuffleTablePanel(
			GenericModel.ofList(permissions));
		// 3. Create a Frame for displaying the shuffle table.
		final JFrame frame = new JFrame();
		frame.addWindowListener(new CloseWindow());
		// 4. Add the Panel to the Frame.
		frame.add(panel);
		frame.pack();
		// 5. Show the Frame.
		frame.setVisible(true);
		if (!frame.isActive())
		{
			frame.toFront();
		}
	}

	@Override
	protected GenericTableModel<Permission> newLeftTableModel()
	{
		return new DemoPermissionsTableModel();
	}

	@Override
	protected GenericTableModel<Permission> newRightTableModel()
	{
		return new DemoPermissionsTableModel();
	}
}
