package ou.edu.herbarium;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import ou.edu.herbarium.actions.CollectorAction;
import ou.edu.herbarium.actions.ColorAction;
import ou.edu.herbarium.actions.ExportAction;
import ou.edu.herbarium.actions.InstituteAction;
import ou.edu.herbarium.actions.NewSessionAction;
import ou.edu.herbarium.actions.NewUserAction;
import ou.edu.herbarium.actions.PlatformAction;
import ou.edu.herbarium.actions.StatisticsAction;
import ou.edu.herbarium.actions.TestModeAction;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	// Actions - important to allocate these only in makeActions, and then use
	// them
	// in the fill methods. This ensures that the actions aren't recreated
	// when fillActionBars is called with FILL_PROXY.
	
	private IWorkbenchAction introAction;
	private NewUserAction newUserAction;
	private NewSessionAction newSessionAction;
	private InstituteAction instituteAction;
	private PlatformAction platformAction;
	private StatisticsAction statisticsAction;
	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	private CollectorAction collectorAction;
	private ExportAction exportAction;
	private TestModeAction testModeAction;
	
	private ColorAction testColorAction;
	

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	protected void makeActions(IWorkbenchWindow window) {
		//start
		introAction = ActionFactory.INTRO.create(window);
		register(introAction);
		newSessionAction = new NewSessionAction();
		register(newSessionAction);
		instituteAction = new InstituteAction();
		register(instituteAction);
		platformAction = new PlatformAction();
		register(platformAction);
		newUserAction = new NewUserAction();
		register(newUserAction);
		
		statisticsAction = new StatisticsAction();
		register(statisticsAction);
		
		collectorAction = new CollectorAction();
		register(collectorAction);
		//help
		aboutAction = ActionFactory.ABOUT.create(window);
		aboutAction.setText("About");
		register(aboutAction);
		exitAction = ActionFactory.QUIT.create(window);		
		register(exitAction);
		exportAction = new ExportAction();
		register(exportAction);
		
		testModeAction = new TestModeAction();
		register(testModeAction);
		testColorAction = new ColorAction();
		register(testColorAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		
		MenuManager startMenu = new MenuManager("&Start", IWorkbenchActionConstants.M_EDIT);
		MenuManager settingMenu = new MenuManager("&Setting", IWorkbenchActionConstants.M_EDIT);
		MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
		MenuManager platformMenu = new MenuManager("&Platform", IWorkbenchActionConstants.M_EDIT);
		menuBar.add(startMenu);
		if(Activator.userId == 1){
			menuBar.add(settingMenu);
		}
		menuBar.add(helpMenu);
		startMenu.add(introAction);
		startMenu.add(newSessionAction);
		startMenu.add(exportAction);
		helpMenu.add(exitAction);
		settingMenu.add(newUserAction);
		settingMenu.add(testColorAction);
		settingMenu.add(collectorAction);
		settingMenu.add(testModeAction);
		settingMenu.add(statisticsAction);
		settingMenu.add(new Separator());
		platformMenu.add(instituteAction);
		platformMenu.add(platformAction);
		settingMenu.add(platformMenu);
		helpMenu.add(aboutAction);
	}
}
