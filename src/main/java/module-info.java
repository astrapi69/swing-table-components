/**
 * The MIT License
 *
 * Copyright (C) 2021 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
module swing.table.components.main
{
	requires static lombok;
	requires io.github.astrapisixtynine.jobj.core;
	requires java.desktop;
	requires io.github.astrapisixtynine.silly.collection;
	requires io.github.astrapisixtynine.menu.action;
	requires java.logging;
	requires org.apache.commons.lang3;
	requires io.github.astrapisixtynine.jobj.reflect;
	requires model.data;
	requires io.github.astrapisixtynine.swing.base.components;

	exports io.github.astrapi69.swing.table;
	exports io.github.astrapi69.swing.table.editor;
	exports io.github.astrapi69.swing.table.model;
	exports io.github.astrapi69.swing.table.model.dynamic;
	exports io.github.astrapi69.swing.table.model.properties;
	exports io.github.astrapi69.swing.table.model.quattro;
	exports io.github.astrapi69.swing.table.model.thread;
	exports io.github.astrapi69.swing.table.model.triple;
	exports io.github.astrapi69.swing.table.renderer;

}