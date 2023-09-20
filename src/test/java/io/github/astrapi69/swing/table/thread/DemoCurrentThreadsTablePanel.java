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
package io.github.astrapi69.swing.table.thread;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import io.github.astrapi69.swing.table.GenericJTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import io.github.astrapi69.lang.thread.ThreadDataBean;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.table.model.thread.ThreadsTableModel;

/**
 * The class {@link DemoCurrentThreadsTablePanel} shows all running threads in an application.
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DemoCurrentThreadsTablePanel extends BasePanel<ThreadsTableModel>
{

	private static final long serialVersionUID = 1L;

	JScrollPane scrThreadTable;

	GenericJTable<ThreadDataBean> threadTable;

	public DemoCurrentThreadsTablePanel()
	{
		this(BaseModel.of(new ThreadsTableModel()));
	}

	public DemoCurrentThreadsTablePanel(final IModel<ThreadsTableModel> model)
	{
		super(model);
	}

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		interrupt();
	}

	public void interrupt()
	{
		getModelObject().interrupt();
	}

	protected ThreadsTableModel newThreadsTableModel()
	{
		return new ThreadsTableModel();
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		threadTable = new GenericJTable<>(getModelObject());
		scrThreadTable = new JScrollPane(threadTable);
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeComponents();
		setLayout(new BorderLayout());
		add(scrThreadTable, BorderLayout.CENTER);
	}

}
