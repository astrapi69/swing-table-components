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
package io.github.astrapi69.swing.table.model.quattro;

import java.io.Serializable;

/**
 * The record {@link Quattro} is a row of a {@link QuattroTableModel}: four values, shown in the
 * columns top left, top right, bottom left and bottom right. It takes the place of the Quattro of
 * silly-bean, which is archived (#28); a value may be null
 *
 * @param <TL>
 *            the type of the top left value
 * @param <TR>
 *            the type of the top right value
 * @param <BL>
 *            the type of the bottom left value
 * @param <BR>
 *            the type of the bottom right value
 * @param topLeft
 *            the top left value
 * @param topRight
 *            the top right value
 * @param bottomLeft
 *            the bottom left value
 * @param bottomRight
 *            the bottom right value
 */
public record Quattro<TL, TR, BL, BR> (TL topLeft, TR topRight, BL bottomLeft,
	BR bottomRight) implements Serializable {
}
