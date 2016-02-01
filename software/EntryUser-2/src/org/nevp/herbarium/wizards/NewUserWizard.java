package org.nevp.herbarium.wizards;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.PlatformUI;

import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;



public class NewUserWizard  extends Wizard {

	public static final String Q1 = "QUESTION_1";
	public static final String Q2 = "QUESTION_2";
	private EnterUserName one;
	private EnterIplantInfo two;
	
	public NewUserWizard(){    
		Platform.getInstanceLocation().getURL().getPath();
		System.out.println("1");
		one = new EnterUserName();
		System.out.println("2");
		two = new EnterIplantInfo();
		this.addPage( one );
		this.addPage( two );
		this.setWindowTitle("Add User");
		this.setHelpAvailable( true );
	}
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		DataUtilsService service = new DataUtilsService();//Beep
		DataUtilsDelegate delegate = service.getDataUtilsPort();
		delegate.insertUser(one.username.getText(), 
						one.password.getText(), 
						one.email.getText(), 
						two.username.getText(), 
						two.password.getText(),
						two.address.getText(), 
						two.zone.getText());
		return true;
	}
	
	public boolean canFinish() {
		if (this.getContainer().getCurrentPage() == two)
			return true;
		else
			return false;
	}

}
