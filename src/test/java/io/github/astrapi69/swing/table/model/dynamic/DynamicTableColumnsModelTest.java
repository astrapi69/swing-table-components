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
package io.github.astrapi69.swing.table.model.dynamic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.collection.array.ArrayFactory;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumeration.Gender;

/**
 * The unit test class for the class {@link DynamicTableColumnsModel}
 */
public class DynamicTableColumnsModelTest
{

	/**
	 * Test method for the constructor with class argument
	 */
	@Test
	public void testNewGenericTableColumnsModel()
	{
		DynamicTableColumnsModel<Person> columnsModel = new DynamicTableColumnsModel<>(
			Person.class);
		boolean[] expectedCanEdit = ArrayFactory.newBooleanArray(false, false, false, false, false);
		boolean[] canEdit = columnsModel.getCanEdit();
		for (int i = 0; i < canEdit.length; i++)
		{
			assertEquals(canEdit[i], expectedCanEdit[i]);
		}
		Class<?>[] expectedColumnClasses = ArrayFactory.newArray(String.class, Gender.class,
			Boolean.class, String.class, String.class);
		Class<?>[] columnClasses = columnsModel.getColumnClasses();
		for (int i = 0; i < columnClasses.length; i++)
		{
			assertEquals(columnClasses[i], expectedColumnClasses[i]);
		}
		String[] expectedColumnNames = ArrayFactory.newArray("About", "Gender", "Married", "Name",
			"Nickname");
		String[] columnNames = columnsModel.getColumnNames();
		for (int i = 0; i < columnNames.length; i++)
		{
			assertEquals(columnNames[i], expectedColumnNames[i]);
		}
	}

	/**
	 * Test that the setters keep the given arrays, not copies of them
	 */
	@Test
	public void testSettersKeepTheGivenArrays()
	{
		final DynamicTableColumnsModel<Person> columnsModel = new DynamicTableColumnsModel<>(
			Person.class);
		final boolean[] canEdit = { true };
		final Class<?>[] columnClasses = { String.class };
		final String[] columnNames = { "Name" };
		columnsModel.setCanEdit(canEdit);
		columnsModel.setColumnClasses(columnClasses);
		columnsModel.setColumnNames(columnNames);
		assertSame(Person.class, columnsModel.getType());
		assertSame(canEdit, columnsModel.getCanEdit());
		assertSame(columnClasses, columnsModel.getColumnClasses());
		assertSame(columnNames, columnsModel.getColumnNames());
	}

	/**
	 * Test that two models are equal when they have the same type and arrays with equal elements,
	 * and not equal when the type or one of the arrays differs, for null, for another type and for
	 * a subclass that refuses through {@code canEqual}; and that {@code hashCode} is the one Lombok
	 * computes
	 */
	@Test
	public void testEqualsAndHashCode()
	{
		final DynamicTableColumnsModel<Person> model = new DynamicTableColumnsModel<>(Person.class);
		final DynamicTableColumnsModel<Person> equal = new DynamicTableColumnsModel<>(Person.class);
		assertEquals(model, model);
		assertEquals(model, equal);
		assertEquals(equal, model);
		assertEquals(model.hashCode(), equal.hashCode());
		final int expected = (((59 + Person.class.hashCode()) * 59
			+ Arrays.hashCode(model.getCanEdit())) * 59
			+ Arrays.deepHashCode(model.getColumnClasses())) * 59
			+ Arrays.deepHashCode(model.getColumnNames());
		assertEquals(expected, model.hashCode());

		equal.setCanEdit(new boolean[] { true, false, false, false, false });
		assertNotEquals(model, equal);
		equal.setCanEdit(model.getCanEdit().clone());
		assertEquals(model, equal);
		equal.setColumnClasses(new Class<?>[] { String.class });
		assertNotEquals(model, equal);
		equal.setColumnClasses(model.getColumnClasses().clone());
		assertEquals(model, equal);
		equal.setColumnNames(new String[] { "Name" });
		assertNotEquals(model, equal);
		equal.setColumnNames(model.getColumnNames().clone());
		assertEquals(model, equal);

		final DynamicTableColumnsModel<Gender> otherType = new DynamicTableColumnsModel<>(
			Gender.class);
		otherType.setCanEdit(model.getCanEdit());
		otherType.setColumnClasses(model.getColumnClasses());
		otherType.setColumnNames(model.getColumnNames());
		assertNotEquals(model, otherType);
		assertNotEquals(model, null);
		assertNotEquals(model, Person.class);

		final DynamicTableColumnsModel<Person> refusing = new DynamicTableColumnsModel<>(
			Person.class)
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
	 * Test that {@code toString} names the class, the type and the elements of the three arrays
	 */
	@Test
	public void testToString()
	{
		final DynamicTableColumnsModel<Person> columnsModel = new DynamicTableColumnsModel<>(
			Person.class);
		assertEquals("DynamicTableColumnsModel(type=class io.github.astrapi69.test.object.Person, "
			+ "canEdit=[false, false, false, false, false], "
			+ "columnClasses=[class java.lang.String, class io.github.astrapi69.test.object.enumeration.Gender, "
			+ "class java.lang.Boolean, class java.lang.String, class java.lang.String], "
			+ "columnNames=[About, Gender, Married, Name, Nickname])", columnsModel.toString());
	}
}
