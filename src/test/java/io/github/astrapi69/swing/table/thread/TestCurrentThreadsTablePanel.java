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

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import io.github.astrapi69.awt.window.adapter.CloseWindow;

/**
 * Launches {@link DemoCurrentThreadsTablePanel} in a frame
 */
public class TestCurrentThreadsTablePanel
{

	/**
	 * Shows the demo panel; closing the window stops the panel's update thread before
	 * {@link CloseWindow} exits the application
	 *
	 * @param args
	 *            not used
	 */
	public static void main(final String[] args)
	{
		final JFrame frame = new JFrame();
		final DemoCurrentThreadsTablePanel viewer = new DemoCurrentThreadsTablePanel();
		frame.addWindowListener(new CloseWindow()
		{
			@Override
			public void windowClosing(final WindowEvent event)
			{
				viewer.interrupt();
				super.windowClosing(event);
			}
		});

		frame.setContentPane(viewer);
		frame.setSize(500, 300);
		frame.setVisible(true);

	}

}
