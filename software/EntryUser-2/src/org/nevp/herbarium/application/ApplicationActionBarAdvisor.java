package org.nevp.herbarium.application;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.nevp.herbarium.actions.OpenViewAction;
import org.nevp.herbarium.actions.SwitchPerspectiveAction;
import org.nevp.herbarium.views.View;

import org.nevp.herbarium.actions.CollectorAction;
import org.nevp.herbarium.actions.ColorAction;
import org.nevp.herbarium.actions.ConveyorSettingAction;
import org.nevp.herbarium.actions.ExportAction;
import org.nevp.herbarium.actions.InstituteAction;
import org.nevp.herbarium.actions.NewSessionAction;
import org.nevp.herbarium.actions.NewUserAction;
import org.nevp.herbarium.actions.PlatformAction;
import org.nevp.herbarium.actions.StatisticsAction;
import org.nevp.herbarium.actions.TestModeAction;

import org.nevp.herbarium.Activator;


/**
 * An action bar advisor is responsible for creating, adding, and disposing of the
 * actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    // Actions - important to allocate these only in makeActions, and then use them
    // in the fill methods.  This ensures that the actions aren't recreated
    // when fillActionBars is called with FILL_PROXY.
	
//	private IWorkbenchAction introAction;
//	private NewUserAction newUserAction;
//	private NewSessionAction newSessionAction;
//	private InstituteAction instituteAction;
//	private PlatformAction platformAction;
//	private StatisticsAction statisticsAction;
//	private IWorkbenchAction exitAction;
//	private IWorkbenchAction aboutAction;
//	private CollectorAction collectorAction;
//	private ExportAction exportAction;
//	private TestModeAction testModeAction;
	private SwitchPerspectiveAction switchPerspectiveAction;
//	private ConveyorSettingAction conveyorSettingAction;
//	private ColorAction testColorAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }
    
    protected void makeActions(final IWorkbenchWindow window) {
        // Creates the actions and registers them.
        // Registering is needed to ensure that key bindings work.
        // The corresponding commands keybindings are defined in the plugin.xml file.
        // Registering also provides automatic disposal of the actions when
        // the window is closed.

//        exitAction = ActionFactory.QUIT.create(window);
//        register(exitAction);
//        
//        aboutAction = ActionFactory.ABOUT.create(window);
//        register(aboutAction);
//        
//        introAction = ActionFactory.INTRO.create(window);
//		register(introAction);
//		newSessionAction = new NewSessionAction();
//		register(newSessionAction);
//		instituteAction = new InstituteAction();
//		register(instituteAction);
//		platformAction = new PlatformAction();
//		register(platformAction);
//		newUserAction = new NewUserAction();
//		
//		register(newUserAction);
//		
//		statisticsAction = new StatisticsAction();
//		register(statisticsAction);
//		
//		collectorAction = new CollectorAction();
//		register(collectorAction);
//		//help
//		aboutAction = ActionFactory.ABOUT.create(window);
//		aboutAction.setText("About");
//		register(aboutAction);
////		exitAction = ActionFactory.QUIT.create(window);		
//		register(exitAction);
//		exportAction = new ExportAction();
//		register(exportAction);
		
//		testModeAction = new TestModeAction();
//		register(testModeAction);
//		testColorAction = new ColorAction();
//		register(testColorAction);
//        
//        
//        
        switchPerspectiveAction = new SwitchPerspectiveAction("Camera Setting", window);
        register(switchPerspectiveAction);
//        
//        conveyorSettingAction = new ConveyorSettingAction();
//        register(conveyorSettingAction);
        
    }
    
    protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager settingMenu = new MenuManager("&Setting", "Settings");
		menuBar.add(settingMenu);
//		startMenu.add(introAction);
//		startMenu.add(newSessionAction);
//		startMenu.add(exportAction);
		settingMenu.add(switchPerspectiveAction);
//		startMenu.add(conveyorSettingAction);
//		helpMenu.add(exitAction);
//		settingMenu.add(newUserAction);
//		settingMenu.add(testColorAction);
//		settingMenu.add(collectorAction);
//		settingMenu.add(testModeAction);
//		settingMenu.add(statisticsAction);
//		settingMenu.add(new Separator());
//		platformMenu.add(instituteAction);
//		platformMenu.add(platformAction);
//		settingMenu.add(platformMenu);
//		settingMenu.setVisible(false);
//		helpMenu.add(aboutAction);
//		menuBar.add(settingMenu);
    }
}
