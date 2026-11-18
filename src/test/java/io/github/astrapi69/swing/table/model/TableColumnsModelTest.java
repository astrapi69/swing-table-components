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
package io.github.astrapi69.swing.table.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * The unit test class for the class {@link TableColumnsModel}: its constructors, accessors,
 * builder, {@code equals}, {@code hashCode} and {@code toString}, as Lombok generated them
 */
public class TableColumnsModelTest
{

	/**
	 * Test that the constructor without arguments leaves all three arrays null
	 */
	@Test
	public void testNoArgumentConstructor()
	{
		final TableColumnsModel model = new TableColumnsModel();
		assertNull(model.getCanEdit());
		assertNull(model.getColumnClasses());
		assertNull(model.getColumnNames());
	}

	/**
	 * Test that the constructor with all arguments and the setters keep the given arrays, not
	 * copies of them
	 */
	@Test
	public void testConstructorAndSettersKeepTheGivenArrays()
	{
		final boolean[] canEdit = { true, false };
		final Class<?>[] columnClasses = { String.class, Integer.class };
		final String[] columnNames = { "Name", "Age" };
		final TableColumnsModel model = new TableColumnsModel(canEdit, columnClasses, columnNames);
		assertSame(canEdit, model.getCanEdit());
		assertSame(columnClasses, model.getColumnClasses());
		assertSame(columnNames, model.getColumnNames());

		final boolean[] otherCanEdit = { false };
		final Class<?>[] otherColumnClasses = { Boolean.class };
		final String[] otherColumnNames = { "Flag" };
		model.setCanEdit(otherCanEdit);
		model.setColumnClasses(otherColumnClasses);
		model.setColumnNames(otherColumnNames);
		assertSame(otherCanEdit, model.getCanEdit());
		assertSame(otherColumnClasses, model.getColumnClasses());
		assertSame(otherColumnNames, model.getColumnNames());
	}

	/**
	 * Test that the builder builds a model with the arrays it was given, null for the ones it was
	 * not given, and names them in its {@code toString}
	 */
	@Test
	public void testBuilder()
	{
		final boolean[] canEdit = { true, false };
		final Class<?>[] columnClasses = { String.class, Integer.class };
		final String[] columnNames = { "Name", "Age" };
		final TableColumnsModel.TableColumnsModelBuilder builder = TableColumnsModel.builder()
			.canEdit(canEdit).columnClasses(columnClasses).columnNames(columnNames);
		assertEquals("TableColumnsModel.TableColumnsModelBuilder(canEdit=[true, false], "
			+ "columnClasses=[class java.lang.String, class java.lang.Integer], "
			+ "columnNames=[Name, Age])", builder.toString());
		final TableColumnsModel model = builder.build();
		assertSame(canEdit, model.getCanEdit());
		assertSame(columnClasses, model.getColumnClasses());
		assertSame(columnNames, model.getColumnNames());

		final TableColumnsModel empty = TableColumnsModel.builder().build();
		assertNull(empty.getCanEdit());
		assertNull(empty.getColumnClasses());
		assertNull(empty.getColumnNames());
		assertEquals(
			"TableColumnsModel.TableColumnsModelBuilder(canEdit=null, columnClasses=null, columnNames=null)",
			TableColumnsModel.builder().toString());
	}

	/**
	 * Test that {@code toBuilder} starts a builder with the arrays of the model, and that changing
	 * that builder leaves the model as it was
	 */
	@Test
	public void testToBuilder()
	{
		final boolean[] canEdit = { true };
		final Class<?>[] columnClasses = { String.class };
		final String[] columnNames = { "Name" };
		final TableColumnsModel model = new TableColumnsModel(canEdit, columnClasses, columnNames);
		final TableColumnsModel copy = model.toBuilder().build();
		assertNotSame(model, copy);
		assertSame(canEdit, copy.getCanEdit());
		assertSame(columnClasses, copy.getColumnClasses());
		assertSame(columnNames, copy.getColumnNames());

		final String[] otherColumnNames = { "Other" };
		final TableColumnsModel changed = model.toBuilder().columnNames(otherColumnNames).build();
		assertSame(otherColumnNames, changed.getColumnNames());
		assertSame(columnNames, model.getColumnNames());
	}

	/**
	 * Test that two models are equal when their arrays have equal elements, and not equal when one
	 * of the arrays differs, for null, for another type and for a subclass that refuses through
	 * {@code canEqual}
	 */
	@Test
	public void testEquals()
	{
		final TableColumnsModel model = new TableColumnsModel(new boolean[] { true, false },
			new Class<?>[] { String.class, Integer.class }, new String[] { "Name", "Age" });
		final TableColumnsModel equal = new TableColumnsModel(new boolean[] { true, false },
			new Class<?>[] { String.class, Integer.class }, new String[] { "Name", "Age" });
		assertEquals(model, model);
		assertEquals(model, equal);
		assertEquals(equal, model);
		assertEquals(new TableColumnsModel(), new TableColumnsModel());

		assertNotEquals(model, new TableColumnsModel(new boolean[] { true, true },
			new Class<?>[] { String.class, Integer.class }, new String[] { "Name", "Age" }));
		assertNotEquals(model, new TableColumnsModel(new boolean[] { true, false },
			new Class<?>[] { String.class, Long.class }, new String[] { "Name", "Age" }));
		assertNotEquals(model, new TableColumnsModel(new boolean[] { true, false },
			new Class<?>[] { String.class, Integer.class }, new String[] { "Name", "Years" }));
		assertNotEquals(model, new TableColumnsModel());
		assertNotEquals(new TableColumnsModel(), model);
		assertNotEquals(model, null);
		assertNotEquals(model, "Name");

		final TableColumnsModel refusing = new TableColumnsModel(new boolean[] { true, false },
			new Class<?>[] { String.class, Integer.class }, new String[] { "Name", "Age" })
		{
			@Override
			protected boolean canEqual(final Object other)
			{
				return false;
			}
		};
		assertNotEquals(model, refusing);
	}

	/**
	 * Test that {@code hashCode} is the one Lombok computes, from the element hash codes of the
	 * three arrays
	 */
	@Test
	public void testHashCode()
	{
		final boolean[] canEdit = { true, false };
		final Class<?>[] columnClasses = { String.class, Integer.class };
		final String[] columnNames = { "Name", "Age" };
		final TableColumnsModel model = new TableColumnsModel(canEdit, columnClasses, columnNames);
		final int expected = ((59 + Arrays.hashCode(canEdit)) * 59
			+ Arrays.deepHashCode(columnClasses)) * 59 + Arrays.deepHashCode(columnNames);
		assertEquals(expected, model.hashCode());
		assertEquals(model.hashCode(),
			new TableColumnsModel(canEdit.clone(), columnClasses.clone(), columnNames.clone())
				.hashCode());
		assertEquals(205379, new TableColumnsModel().hashCode());
	}

	/**
	 * Test that {@code toString} names the class and the elements of the three arrays
	 */
	@Test
	public void testToString()
	{
		final TableColumnsModel model = new TableColumnsModel(new boolean[] { true, false },
			new Class<?>[] { String.class, Integer.class }, new String[] { "Name", "Age" });
		assertEquals("TableColumnsModel(canEdit=[true, false], "
			+ "columnClasses=[class java.lang.String, class java.lang.Integer], "
			+ "columnNames=[Name, Age])", model.toString());
		assertEquals("TableColumnsModel(canEdit=null, columnClasses=null, columnNames=null)",
			new TableColumnsModel().toString());
	}
}
