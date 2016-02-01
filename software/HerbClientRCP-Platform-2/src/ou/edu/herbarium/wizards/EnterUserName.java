package ou.edu.herbarium.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;
import ou.edu.herbarium.wsclient.User;


public class EnterUserName extends WizardPage{

	Text username;
	Text password;
	Text verifyPassword;
	Text email;
	
	protected EnterUserName() {
		
		super(NewUserWizard.Q1,"Create a new Project",ImageDescriptor.createFromFile(EnterUserName.class,"icons/action1.gif"));
		this.setMessage("Create an new user.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(new GridLayout(2,false));
		new Label(composite, SWT.NONE).setText("Username:");
		username = new Text(composite,SWT.BORDER);
		new Label(composite, SWT.NONE).setText("Password:");
		password = new Text(composite,SWT.PASSWORD | SWT.BORDER);
		new Label(composite, SWT.NONE).setText("Verify Password:");
		verifyPassword = new Text(composite,SWT.PASSWORD | SWT.BORDER);
		new Label(composite, SWT.NONE).setText("Email:");
		email = new Text(composite,SWT.BORDER);
		username.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				setErrorMessage(null);
				if(username.getText()==null||username.getText().equals("")){
					setErrorMessage("Username could not be empty.");
					setPageComplete(false);
				}
				else{
					DataUtilsService service = new DataUtilsService();//Beep
					DataUtilsDelegate delegate = service.getDataUtilsPort();
					User u = delegate.getUserByName(username.getText());
					if(u!=null){
						setErrorMessage("Username exists.");
						setPageComplete(false);
					}
				}
			}
		});
		
//		username.addVerifyListener(new VerifyListener(){
//			@Override
//			public void verifyText(VerifyEvent e) {
//				// TODO Auto-generated method stub
//				DataUtilsService service = new DataUtilsService();//Beep
//				DataUtilsDelegate delegate = service.getDataUtilsPort();
//				if(username.getText()==null||username.getText().trim().equals("")){
//					setErrorMessage("Username could not be empty.");
//					setPageComplete(false);
//					return;
//				}
//				User u = delegate.getUserByName(username.getText());
//				if(u!=null){
//					setErrorMessage("Username exists.");
//					setPageComplete(false);
//					return;
//				}
//			}
//		});
		
		password.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				setErrorMessage(null);
				if(password.getText()==null||password.getText().equals("")){
					setErrorMessage("password could not be empty.");
					setPageComplete(false);
				}
			}
		});
		
//		password.addVerifyListener(new VerifyListener(){
//			@Override
//			public void verifyText(VerifyEvent e) {
//				// TODO Auto-generated method stub
//				if(password.getText()==null||password.getText().trim().equals("")){
//					setErrorMessage("password could not be empty.");
//					setPageComplete(false);
//					return;
//				}
//			}
//		});
		verifyPassword.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				setErrorMessage(null);
				if(verifyPassword.getText()==null||verifyPassword.getText().equals("")){
					setErrorMessage("Verify password could not be empty.");
					setPageComplete(false);
				}
				else if(verifyPassword.getText().equals(password.getText())==false){
					setErrorMessage("Verify password failed.");
					setPageComplete(false);
				}
			}
		});
		
		email.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				setErrorMessage(null);
				if(email.getText().contains("@")==false){
					setErrorMessage("invalid email format");
					setPageComplete(false);
				}
				else setPageComplete(true);
			}
		});
		
		username.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		verifyPassword.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		email.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    setControl(composite);    
	}

	public String getName() {
		return "";
			
	}
}
