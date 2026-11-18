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

import java.util.Arrays;

/**
 * The class {@link TableColumnsModel} encapsulates the column data for a table model like the
 * column names if they are editable and the column classes.
 *
 * Written out from what Lombok's {@code @Getter}, {@code @Setter}, {@code @EqualsAndHashCode},
 * {@code @ToString}, {@code @NoArgsConstructor}, {@code @AllArgsConstructor} and
 * {@code @Builder(toBuilder = true)} generated, so that every generated member carries its Javadoc;
 * the members, their signatures and the texts of {@link #toString()} are the ones Lombok generated
 */
public class TableColumnsModel
{

	/** The flag for the column if they can be edited. */
	private boolean[] canEdit;

	/** The column classes. */
	private Class<?>[] columnClasses;

	/** The column names. */
	private String[] columnNames;

	/**
	 * Instantiates a new {@link TableColumnsModel} without column names, classes and editable flags
	 */
	public TableColumnsModel()
	{
	}

	/**
	 * Instantiates a new {@link TableColumnsModel} with the given arrays, which it keeps as given
	 *
	 * @param canEdit
	 *            the flag for the column if they can be edited
	 * @param columnClasses
	 *            the column classes
	 * @param columnNames
	 *            the column names
	 */
	public TableColumnsModel(final boolean[] canEdit, final Class<?>[] columnClasses,
		final String[] columnNames)
	{
		this.canEdit = canEdit;
		this.columnClasses = columnClasses;
		this.columnNames = columnNames;
	}

	/**
	 * Creates a new builder without column names, classes and editable flags
	 *
	 * @return the new builder
	 */
	public static TableColumnsModel.TableColumnsModelBuilder builder()
	{
		return new TableColumnsModel.TableColumnsModelBuilder();
	}

	/**
	 * Creates a new builder that starts with the arrays of this model
	 *
	 * @return the new builder
	 */
	public TableColumnsModel.TableColumnsModelBuilder toBuilder()
	{
		return new TableColumnsModel.TableColumnsModelBuilder().canEdit(this.canEdit)
			.columnClasses(this.columnClasses).columnNames(this.columnNames);
	}

	/**
	 * Gets the flag for the column if they can be edited
	 *
	 * @return the flag for the column if they can be edited
	 */
	public boolean[] getCanEdit()
	{
		return this.canEdit;
	}

	/**
	 * Gets the column classes
	 *
	 * @return the column classes
	 */
	public Class<?>[] getColumnClasses()
	{
		return this.columnClasses;
	}

	/**
	 * Gets the column names
	 *
	 * @return the column names
	 */
	public String[] getColumnNames()
	{
		return this.columnNames;
	}

	/**
	 * Sets the flag for the column if they can be edited
	 *
	 * @param canEdit
	 *            the flag for the column if they can be edited
	 */
	public void setCanEdit(final boolean[] canEdit)
	{
		this.canEdit = canEdit;
	}

	/**
	 * Sets the column classes
	 *
	 * @param columnClasses
	 *            the column classes
	 */
	public void setColumnClasses(final Class<?>[] columnClasses)
	{
		this.columnClasses = columnClasses;
	}

	/**
	 * Sets the column names
	 *
	 * @param columnNames
	 *            the column names
	 */
	public void setColumnNames(final String[] columnNames)
	{
		this.columnNames = columnNames;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object o)
	{
		if (o == this)
			return true;
		if (!(o instanceof TableColumnsModel))
			return false;
		final TableColumnsModel other = (TableColumnsModel)o;
		if (!other.canEqual(this))
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
	 * @return true if the given object is a {@link TableColumnsModel}
	 */
	protected boolean canEqual(final Object other)
	{
		return other instanceof TableColumnsModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		final int PRIME = 59;
		int result = 1;
		result = result * PRIME + Arrays.hashCode(this.getCanEdit());
		result = result * PRIME + Arrays.deepHashCode(this.getColumnClasses());
		result = result * PRIME + Arrays.deepHashCode(this.getColumnNames());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "TableColumnsModel(canEdit=" + Arrays.toString(this.getCanEdit())
			+ ", columnClasses=" + Arrays.deepToString(this.getColumnClasses()) + ", columnNames="
			+ Arrays.deepToString(this.getColumnNames()) + ")";
	}

	/**
	 * The builder of a {@link TableColumnsModel}
	 */
	public static class TableColumnsModelBuilder
	{

		/** The flag for the column if they can be edited. */
		private boolean[] canEdit;

		/** The column classes. */
		private Class<?>[] columnClasses;

		/** The column names. */
		private String[] columnNames;

		TableColumnsModelBuilder()
		{
		}

		/**
		 * Sets the flag for the column if they can be edited
		 *
		 * @param canEdit
		 *            the flag for the column if they can be edited
		 * @return {@code this}
		 */
		public TableColumnsModel.TableColumnsModelBuilder canEdit(final boolean[] canEdit)
		{
			this.canEdit = canEdit;
			return this;
		}

		/**
		 * Sets the column classes
		 *
		 * @param columnClasses
		 *            the column classes
		 * @return {@code this}
		 */
		public TableColumnsModel.TableColumnsModelBuilder columnClasses(
			final Class<?>[] columnClasses)
		{
			this.columnClasses = columnClasses;
			return this;
		}

		/**
		 * Sets the column names
		 *
		 * @param columnNames
		 *            the column names
		 * @return {@code this}
		 */
		public TableColumnsModel.TableColumnsModelBuilder columnNames(final String[] columnNames)
		{
			this.columnNames = columnNames;
			return this;
		}

		/**
		 * Builds a new {@link TableColumnsModel} with the arrays given to this builder
		 *
		 * @return the new {@link TableColumnsModel}
		 */
		public TableColumnsModel build()
		{
			return new TableColumnsModel(this.canEdit, this.columnClasses, this.columnNames);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString()
		{
			return "TableColumnsModel.TableColumnsModelBuilder(canEdit="
				+ Arrays.toString(this.canEdit) + ", columnClasses="
				+ Arrays.deepToString(this.columnClasses) + ", columnNames="
				+ Arrays.deepToString(this.columnNames) + ")";
		}
	}
}
