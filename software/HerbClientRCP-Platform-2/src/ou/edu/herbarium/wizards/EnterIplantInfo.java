package ou.edu.herbarium.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class EnterIplantInfo  extends WizardPage{
	Text username;
	Text password;
	Text address;
	Text zone;
	protected EnterIplantInfo() {
		super(NewUserWizard.Q2,"Enter iPlant account info",ImageDescriptor.createFromFile(EnterIplantInfo.class,"icons/action1.gif"));
		this.setMessage("iPlant account info");
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
		new Label(composite, SWT.NONE).setText("address:");
		address = new Text(composite,SWT.BORDER);
		new Label(composite, SWT.NONE).setText("zone:");
		zone = new Text(composite,SWT.BORDER);
		
		
		username.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		address.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		zone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		setControl(composite);
	}

}
