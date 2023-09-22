module swing.table.components.main
{
	requires lombok;
	requires jobj.core.main;
	requires java.desktop;
	requires silly.collection;
	requires menu.actions.main;
	requires java.logging;
	requires silly.bean.main;
	requires org.apache.commons.lang3;
	requires jobj.reflect.main;
	requires model.data;
	requires swing.base.components.main;

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