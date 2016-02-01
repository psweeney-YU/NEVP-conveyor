package org.nevp.herbarium.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;


public class SyncIDropAction extends Action {
	IStatusLineManager manager;

	public SyncIDropAction(String title, ImageDescriptor image,
			IStatusLineManager manager) {
		super(title, image);
		this.manager = manager;//Export 
	}

	public void run() {
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) {
				monitor.beginTask("test", 10);
				for(int i=1;i<11;i++){
					monitor.worked(1);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				monitor.done();
			}
		};
		try {
			op.run(manager.getProgressMonitor());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class UploadThread implements Runnable {
	Display display;
	IProgressMonitor manager;

	public UploadThread(Display display, IProgressMonitor manager) {
		super();
		this.display = display;
		this.manager = manager;
	}

	@Override
	public void run() {
		UploadRunnable rr = new UploadRunnable(manager);
		display.asyncExec(rr);
	}
}

class UploadRunnable implements Runnable {

	IProgressMonitor monitor;

	public UploadRunnable(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			monitor.beginTask("Performing decathlon: ", 10);
			// perform the hammer throw
			for (int i = 1; i < 11; i++) {
				Thread.sleep(1000);
				monitor.worked(1);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			monitor.done();
		}
	}
}
