package com.cfm.productline.editor;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.cfm.productline.configurator.gui.actions.ConfigureAction;
import com.cfm.productline.configurator.gui.actions.LoadConfigurationAction;
import com.cfm.productline.configurator.gui.actions.SaveConfigurationAction;
import com.cfm.productline.configurator.gui.actions.SaveProductsAction;
import com.cfm.productline.editor.actions.ExitAction;
import com.cfm.productline.editor.actions.NewAction;
import com.cfm.productline.editor.actions.OpenAction;
import com.cfm.productline.editor.actions.SaveAction;
import com.cfm.productline.editor.actions.ToggleAssetVisibilityAction;
import com.cfm.productline.editor.actions.TogglePLVisibilityAction;
import com.cfm.productline.editor.actions.VerifyDeadElementAction;
import com.cfm.productline.editor.actions.VerifyFalseOptionalElementAction;
import com.cfm.productline.editor.actions.VerifyFalseProductLineModelAction;
import com.cfm.productline.editor.actions.VerifyVoidModelAction;
import com.mxgraph.util.mxResources;

@SuppressWarnings("serial")
public class ProductLineMenuBar extends JMenuBar{
	
	ProductLineEditor editor;
	
	public ProductLineMenuBar(ProductLineEditor editor){
		init(editor);
	}
	
	private void init(ProductLineEditor editor){
		JMenu menu = new JMenu("File");
		menu.add(editor.bind(mxResources.get("new"), new NewAction()));
		menu.add(editor.bind(mxResources.get("load"), new OpenAction()));
		menu.addSeparator();
		menu.add(editor.bind(mxResources.get("save"), new SaveAction(false)));
		menu.add(editor.bind(mxResources.get("saveAs"), new SaveAction(true)));
		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("exit"), new ExitAction()));
		
		add(menu);
		
		
		menu = (JMenu) menu.add(new JMenu(mxResources.get("layout")));
		
		menu.add(editor.bind("Toggle Assets", new ToggleAssetVisibilityAction()));
		menu.add(editor.bind("Toggle Variability Elements", new TogglePLVisibilityAction()));
		menu.addSeparator();
		
		menu.add(editor.graphLayout("verticalHierarchical", true));
		menu.add(editor.graphLayout("horizontalHierarchical", true));

		menu.addSeparator();

//		menu.add(editor.graphLayout("verticalPartition", false));
//		menu.add(editor.graphLayout("horizontalPartition", false));
//
//		menu.addSeparator();

		menu.add(editor.graphLayout("verticalStack", true));
		menu.add(editor.graphLayout("horizontalStack", true));

		menu.addSeparator();

		menu.add(editor.graphLayout("verticalTree", true));
		menu.add(editor.graphLayout("horizontalTree", true));

		menu.addSeparator();

		menu.add(editor.graphLayout("placeEdgeLabels", true));
		menu.add(editor.graphLayout("parallelEdges", true));

		menu.addSeparator();

		menu.add(editor.graphLayout("organicLayout", true));
		menu.add(editor.graphLayout("circleLayout", true));
		add(menu);
		
		menu = (JMenu) menu.add(new JMenu(mxResources.get("verifyDefects")));
		menu.add(editor.bind(mxResources.get("verifyVoidModel"), new VerifyVoidModelAction()));
		menu.add(editor.bind(mxResources.get("verifyFalseProductLine"), new VerifyFalseProductLineModelAction()));
		menu.add(editor.bind(mxResources.get("verifyDeadElement"), new VerifyDeadElementAction()));
		menu.add(editor.bind(mxResources.get("verifyFalseOptionalElements"), new  VerifyFalseOptionalElementAction()));
		menu.addSeparator();
		add(menu);

		menu = (JMenu) menu.add(new JMenu(mxResources.get("configurationTab")));
		menu.add(editor.bind(mxResources.get("configure"), new ConfigureAction()));
		menu.add(editor.bind(mxResources.get("saveConfiguration"), new SaveConfigurationAction(true)));
		menu.add(editor.bind(mxResources.get("loadConfiguration"), new LoadConfigurationAction()));
		menu.add(editor.bind(mxResources.get("saveProducts"), new SaveProductsAction()));
		add(menu);
	}
	
	
}