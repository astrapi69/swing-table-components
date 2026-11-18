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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.reflection.ReflectionExtensions;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link DynamicTableColumnsModel} encapsulates the column data for a table model that
 * can resolve the columns dynamically from the given class type
 *
 * The accessors and {@link #toString()} come from Lombok; {@link #equals(Object)},
 * {@link #hashCode()} and {@link #canEqual(Object)} are written out from what Lombok's
 * {@code @Data} generated, so that {@link #canEqual(Object)} carries its Javadoc
 *
 * @param <T>
 *            the type whose declared fields give the columns
 */
@Getter
@Setter
@ToString
public class DynamicTableColumnsModel<T>
{

	/**
	 * The class type.
	 *
	 * @return the class type whose declared fields give the columns
	 */
	@NonNull
	private final Class<T> type;
	/**
	 * The flag for the column if they can be edited.
	 *
	 * @param canEdit
	 *            the flag for the column if they can be edited
	 * @return the flag for the column if they can be edited
	 */
	private boolean[] canEdit;
	/**
	 * The column classes.
	 *
	 * @param columnClasses
	 *            the column classes
	 * @return the column classes
	 */
	private Class<?>[] columnClasses;
	/**
	 * The column names.
	 *
	 * @param columnNames
	 *            the column names
	 * @return the column names
	 */
	private String[] columnNames;

	/**
	 * Instantiates a new {@link DynamicTableColumnsModel} object
	 *
	 * @param type
	 *            the class type
	 */
	public DynamicTableColumnsModel(@NonNull Class<T> type)
	{
		this.type = type;
		onSetColumnNames();
		onSetColumnClasses();
		onSetCanEdit();
	}

	private Field[] getFields()
	{
		return ReflectionExtensions.getAllDeclaredFields(getType(), "serialVersionUID",
			"$jacocoData");
	}

	/**
	 * Callback method for set the canEdit array from the generic given type. This method is invoked
	 * in the constructor from the derived classes and can be overridden so users can provide their
	 * own version of a column classes
	 */
	protected void onSetCanEdit()
	{
		Field[] fields = getFields();
		canEdit = new boolean[fields.length];
		for (int i = 0; i < fields.length; i++)
		{
			canEdit[i] = false;
		}
	}

	/**
	 * Callback method for set the column classes array from the generic given type. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a column classes
	 */
	protected void onSetColumnClasses()
	{
		Field[] fields = getFields();
		columnClasses = new Class<?>[fields.length];
		for (int i = 0; i < fields.length; i++)
		{
			columnClasses[i] = fields[i].getType();
		}
	}

	/**
	 * Callback method for set the column names array from the generic given type. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a column names
	 */
	protected void onSetColumnNames()
	{
		columnNames = ReflectionExtensions.getAllDeclaredFieldNames(getType(),
			ListFactory.newArrayList("serialVersionUID", "$jacocoData"));
		for (int i = 0; i < columnNames.length; i++)
		{
			columnNames[i] = StringUtils.capitalize(columnNames[i]);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object o)
	{
		if (o == this)
			return true;
		if (!(o instanceof DynamicTableColumnsModel))
			return false;
		final DynamicTableColumnsModel<?> other = (DynamicTableColumnsModel<?>)o;
		if (!other.canEqual(this))
			return false;
		if (!Objects.equals(this.getType(), other.getType()))
			return false;
		if (!Arrays.equals(this.getCanEdit(), other.getCanEdit()))
			return false;
		if (!Arrays.deepEquals(this.getColumnClasses(), other.getColumnClasses()))
			return false;
		if (!Arrays.deepEquals(this.getColumnNames(), other.getColumnNames()))
			return false;
		return true;
	}

	/**
	 * Tells whether the given object may be equal to this one: a subclass that adds state overrides
	 * it, so that {@link #equals(Object)} stays symmetric
	 *
	 * @param other
	 *            the object to compare with
	 * @return true if the given object is a {@link DynamicTableColumnsModel}
	 */
	protected boolean canEqual(final Object other)
	{
		return other instanceof DynamicTableColumnsModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		final int PRIME = 59;
		int result = 1;
		final Object type = this.getType();
		result = result * PRIME + (type == null ? 43 : type.hashCode());
		result = result * PRIME + Arrays.hashCode(this.getCanEdit());
		result = result * PRIME + Arrays.deepHashCode(this.getColumnClasses());
		result = result * PRIME + Arrays.deepHashCode(this.getColumnNames());
		return result;
	}

}
