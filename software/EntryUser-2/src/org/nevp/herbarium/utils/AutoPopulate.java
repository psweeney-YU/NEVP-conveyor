package org.nevp.herbarium.utils;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
 
public class AutoPopulate {
 
    private static final String LCL = "abcdefghijklmnopqrstuvwxyz";
    private static final String UCL = LCL.toUpperCase();
    private static final String NUMS = "0123456789";
    private static final String[] items = new String[] { "Alpha", "Beta", "gaama", "pie",
        "alge","bata" };
 
 
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout(1, false));
        shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        shell.setSize(200, 200);
        shell.setText("Auto Complete Controls");
         
        Monitor primaryMonitor = display.getPrimaryMonitor ();
        Rectangle bounds = primaryMonitor.getBounds ();
        Rectangle rect = shell.getBounds ();
        int x = bounds.x + (bounds.width - rect.width) / 2 ;
        int y = bounds.y + (bounds.height - rect.height) / 2 ;
        shell.setLocation (x, y);
         
        Combo combo = new Combo(shell, SWT.NONE);
        combo.setItems(items);
        combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
 
        combo.addSelectionListener(new SelectionAdapter() {
             
            // called for keyboard enter press
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            }
 
            @Override
            public void widgetSelected(SelectionEvent e) {
            }
        });
         
//      Have a look at Jface AutoCompleteField class also
        enableContentProposal(combo);
         
        Text text = new Text(shell, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        enableContentProposal(text);
         
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
     
    static  void enableContentProposal(Control control)
        {
             
            SimpleContentProposalProvider proposalProvider = null;
            ContentProposalAdapter proposalAdapter = null;
            if (control instanceof Combo)
            {
                Combo combo = (Combo) control;
                proposalProvider = new SimpleContentProposalProvider(combo.getItems());
                proposalAdapter = new ContentProposalAdapter(
                    combo,
                    new ComboContentAdapter(),
                    proposalProvider,
                    getActivationKeystroke(),
                    getAutoactivationChars());
            }
            else if (control instanceof Text)
            {
                 
                Text text = (Text) control;
                proposalProvider = new SimpleContentProposalProvider(items);
                proposalAdapter = new ContentProposalAdapter(
                    text,
                    new TextContentAdapter(),
                    proposalProvider,
                    getActivationKeystroke(),
                    getAutoactivationChars());
            }
            proposalProvider.setFiltering(true);
            proposalAdapter.setPropagateKeys(true);
            proposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
 
        }
     
 
     
    // this logic is  from swt addons project
     static char[] getAutoactivationChars() {
 
        // To enable content proposal on deleting a char
         
        String delete = new String(new char[] { 8 });
        String allChars = LCL + UCL + NUMS + delete;
        return allChars.toCharArray();
    }
 
     static KeyStroke getActivationKeystroke() {
        KeyStroke instance = KeyStroke.getInstance(
                new Integer(SWT.CTRL).intValue(), new Integer(' ').intValue());
        return instance;
    }
 
}