/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ou.edu.herbarium.idroplite;

import java.awt.Container;
import java.awt.Frame;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.pub.DataTransferOperationsImpl;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.io.IRODSFileFactory;
//import org.irods.jargon.idrop.desktop.systraygui.services.IconManager;
//import org.irods.jargon.idrop.desktop.systraygui.utils.IdropConfig;
import org.irods.jargon.core.transfer.DefaultTransferControlBlock;
import org.irods.jargon.core.transfer.TransferControlBlock;

public class iDropLiteCore {

    private IRODSAccount irodsAccount = null;

    private IRODSFileSystem irodsFileSystem = null;
    
    private DataTransferOperations dataTransferOps = null;
    
    private TransferControlBlock transferControlBlock = null;
    
    private DefaultTableModel tmUploadTable = null;

     private String basePath = null;
    
    public IRODSFileSystem getIrodsFileSystem() {
        return irodsFileSystem;
    }

    public void setIrodsFileSystem(IRODSFileSystem irodsFileSystem) {
        this.irodsFileSystem = irodsFileSystem;
    }

    private IdropConfig idropConfig = null;

 
    public IRODSAccount getIrodsAccount() {
        return irodsAccount;
    }

    public void setIrodsAccount(IRODSAccount irodsAccount) {
        this.irodsAccount = irodsAccount;
    }

    public void setIrodsAccount(String sessionID) {
        // FIX ME: need to implement this
    }

    public IdropConfig getIdropConfig() {
        return idropConfig;
    }

    public void setIdropConfig(IdropConfig idropConfig) {
        this.idropConfig = idropConfig;
    }

    public DataTransferOperations getTransferManager() {
        return dataTransferOps;
    }

    public void setTransferManager(DataTransferOperations transferOps) {
        this.dataTransferOps = transferOps;
    }
    
    /**
     * Handy method that delegates the process of getting an <code>IRODSAccessObjectFactory</code>.
     * @return {@link IRODSAccessObjectFactory}
     */
    public IRODSAccessObjectFactory getIRODSAccessObjectFactory() {
        if (irodsFileSystem == null) {
            throw new IdropRuntimeException("No IRODSFileSystem set, cannot obtain the IRODSAccessObjectFactory");
        }
        try {
            return irodsFileSystem.getIRODSAccessObjectFactory();
        } catch (JargonException ex) {
            Logger.getLogger(iDropLiteCore.class.getName()).log(Level.SEVERE, null, ex);
            throw new IdropRuntimeException("exception getting IRODSAccessObjectFactory");
        }
    }

    /**
     * Method to close any iRODS connections in the current thread.   This delegates to the <code>IRODSFileSystem</code>.
     */
    public void closeAllIRODSConnections() {
          if (irodsFileSystem == null) {
            throw new IdropRuntimeException("No IRODSFileSystem set, cannot obtain the IRODSAccessObjectFactory");
        }
       irodsFileSystem.closeAndEatExceptions();
    }

    /**
     * Method to close  iRODS connection denoted by the logged in <code>IRODSAccount</code>.
     */
    public void closeIRODSConnectionForLoggedInAccount() {
          if (irodsFileSystem == null) {
            throw new IdropRuntimeException("No IRODSFileSystem set, cannot obtain the IRODSAccessObjectFactory");
        }
       irodsFileSystem.closeAndEatExceptions(irodsAccount);
    }

    /**
     * Method to close  iRODS connection denoted by the given <code>IRODSAccount</code> in the current thread.   This delegates to the <code>IRODSFileSystem</code>.
     */
    public void closeIRODSConnection(final IRODSAccount irodsAccount) {
          if (irodsFileSystem == null) {
            throw new IdropRuntimeException("No IRODSFileSystem set, cannot obtain the IRODSAccessObjectFactory");
        }
       irodsFileSystem.closeAndEatExceptions(irodsAccount);
    }
    /**
     * Get the <code>IRODSFileFactory</code> for the given account
     * @return {@link IRODSFileFactory} associated with the account currently logged in
     */
    public IRODSFileFactory getIRODSFileFactory(final IRODSAccount irodsAccount) {
          if (irodsFileSystem == null) {
            throw new IdropRuntimeException("No IRODSFileSystem set, cannot obtain the IRODSAccessObjectFactory");
        }
           if (irodsAccount == null) {
            throw new IdropRuntimeException("No IRODSAccount set, cannot obtain the IRODSAccessObjectFactory");
        }
        try {
            return irodsFileSystem.getIRODSFileFactory(irodsAccount);
        } catch (JargonException ex) {
            Logger.getLogger(iDropLiteCore.class.getName()).log(Level.SEVERE, null, ex);
            throw new IdropRuntimeException("Exception getting iRODS file factory", ex);
        }

    }

     /**
     * Get the <code>IRODSFileFactory</code> for the current logged-in account.
     * @return {@link IRODSFileFactory} associated with the account currently logged in
     */
    public IRODSFileFactory getIRODSFileFactoryForLoggedInAccount() {
          if (irodsFileSystem == null) {
            throw new IdropRuntimeException("No IRODSFileSystem set, cannot obtain the IRODSAccessObjectFactory");
        }
           if (irodsAccount == null) {
            throw new IdropRuntimeException("No IRODSAccount set, cannot obtain the IRODSAccessObjectFactory");
        }
        try {
            return irodsFileSystem.getIRODSFileFactory(irodsAccount);
        } catch (JargonException ex) {
            Logger.getLogger(iDropLiteCore.class.getName()).log(Level.SEVERE, null, ex);
            throw new IdropRuntimeException("Exception getting iRODS file factory", ex);
        }

    }
    
    public void setTransferControlBlock(TransferControlBlock tcb) {
    	this.transferControlBlock = tcb;
    }
    
    public TransferControlBlock getTransferControlBlock() {
    	return this.transferControlBlock;
    }
    
    protected void setUploadTableModel(DefaultTableModel tm) {
    	this.tmUploadTable = tm;
    }
    
    public DefaultTableModel getUploadTableModel() {
	   return this.tmUploadTable;
    }
    
//    protected Frame findAppletParentFrame(iDropLiteApplet applet) {
//    	Container c = applet; 
//        while(c != null){ 
//          if (c instanceof Frame) 
//            return (Frame)c; 
//
//          c = c.getParent(); 
//        } 
//        return (Frame)null;
//    }
    
   
    public synchronized String getBasePath() {
        return basePath;
    }

    public synchronized void setBasePath(String basePath) {
        this.basePath = basePath;
    }
    
    public String setLookAndFeel() {
    	
    	String lookAndFeel = null;
    	
    	for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                lookAndFeel = info.getClassName();
                break;
            }
        }
    	
    	try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return lookAndFeel;
    }
   
}

