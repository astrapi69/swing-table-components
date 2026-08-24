module swing.table.components.main
{
	requires lombok;
	requires io.github.astrapisixtynine.jobj.core;
	requires java.desktop;
	requires io.github.astrapisixtynine.silly.collection;
	requires io.github.astrapisixtynine.menu.action;
	requires java.logging;
	requires io.github.astrapisixtynine.silly.bean;
	requires org.apache.commons.lang3;
	requires jobj.reflect.main;
	requires model.data;
	requires io.github.astrapisixtynine.swing.base.components;

	exports io.github.astrapi69.swing.table;
	exports io.github.astrapi69.swing.table.editor;
	exports io.github.astrapi69.swing.table.model;
	exports io.github.astrapi69.swing.table.model.dynamic;
	exports io.github.astrapi69.swing.table.model.properties;
	exports io.github.astrapi69.swing.table.model.quattro;
	exports io.github.astrapi69.swing.table.model.suffle;
	exports io.github.astrapi69.swing.table.model.suffle.action;
	exports io.github.astrapi69.swing.table.model.thread;
	exports io.github.astrapi69.swing.table.model.triple;
	exports io.github.astrapi69.swing.table.panel;
	exports io.github.astrapi69.swing.table.panel.shuffle;
	exports io.github.astrapi69.swing.table.renderer;
	exports io.github.astrapi69.swing.table.shuffle;

}