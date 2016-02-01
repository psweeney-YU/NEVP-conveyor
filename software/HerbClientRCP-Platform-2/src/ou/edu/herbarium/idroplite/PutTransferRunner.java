package ou.edu.herbarium.idroplite;

import java.util.List;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.pub.io.IRODSFileFactory;
import org.irods.jargon.core.transfer.TransferControlBlock;
import org.irods.jargon.httpstream.HttpStreamingException;
import org.irods.jargon.httpstream.HttpStreamingServiceImpl;
import org.slf4j.LoggerFactory;

public class PutTransferRunner implements Runnable {

//    private final List<File> sourceFiles = null;
    private final List<UploadDataObj> sourceFiles;
    private final String targetIrodsFileAbsolutePath;
    private final iDropLiteCore idropGui;
    private TransferControlBlock transferControlBlock;
    private CallbackListener listener;

    public PutTransferRunner(final iDropLiteCore gui,
            final String targetPath,
            final List<UploadDataObj> files)
            throws JargonException {
    	
        if (files == null) {
            throw new JargonException("null file list");
        }

        if (targetPath == null) {
            throw new JargonException("null target path");
        }

        if (gui == null) {
            throw new JargonException("null idrop gui");
        }

        this.targetIrodsFileAbsolutePath = targetPath;
        this.sourceFiles = files;
        this.idropGui = gui;
        this.listener = new CallbackListener(idropGui);

    }

    @Override
    public void run() {
        //for (File transferFile : sourceFiles) {
        for (UploadDataObj uploadData : sourceFiles) {
        	
            if (uploadData.isURL) {
                processPutURL(uploadData);
            } else {
                processPutFile(uploadData);
            }
        }
    }

    private void processPutFile(UploadDataObj uploadData) {
        // this is just a regular local file or folder

        //String localSourceAbsolutePath = transferFile.getAbsolutePath();
        String localSourceAbsolutePath = uploadData.getFile().getAbsolutePath();
        String sourceResource = idropGui.getIrodsAccount().getDefaultStorageResource();

        // need to create new Transfer Control Block for each transfer since it needs to be reset
        // on how many files there are to transfer and how many have been transferred so far
        try {
        	this.transferControlBlock =  idropGui.getIrodsFileSystem().getIRODSAccessObjectFactory().buildDefaultTransferControlBlockBasedOnJargonProperties();
            transferControlBlock.getTransferOptions().setIntraFileStatusCallbacks(true); 
            idropGui.setTransferControlBlock(transferControlBlock);
        } catch (JargonException ex) {
        	ex.printStackTrace();
        }
        try {
            idropGui.getTransferManager().putOperation(localSourceAbsolutePath,
                    targetIrodsFileAbsolutePath, sourceResource, listener, transferControlBlock);
        } catch (JargonException ex) {
            ex.printStackTrace();
        } finally {
            idropGui.getIrodsFileSystem().closeAndEatExceptions();
        }
    }

    private void processPutURL(UploadDataObj uploadData) {
        // this is an import from URL

        String localSourceAbsolutePath = uploadData.getFileName();

		// need to create new Transfer Control Block for each transfer since it needs to be reset
		// on how many files there are to transfer and how many have been transferred so far
        try {
           this.transferControlBlock =  idropGui.getIrodsFileSystem()
           		.getIRODSAccessObjectFactory().buildDefaultTransferControlBlockBasedOnJargonProperties();
           transferControlBlock.getTransferOptions().setIntraFileStatusCallbacks(true); 
           idropGui.setTransferControlBlock(transferControlBlock);
           
           IRODSFileFactory irodsFileFactory = idropGui.getIrodsFileSystem()
           		.getIRODSFileFactory(idropGui.getIrodsAccount());

           IRODSFile destFile = irodsFileFactory.instanceIRODSFile(targetIrodsFileAbsolutePath);
           
           HttpStreamingServiceImpl httpStreamingService = new HttpStreamingServiceImpl(
           		idropGui.getIrodsFileSystem().getIRODSAccessObjectFactory(),
           		idropGui.getIrodsAccount());
           httpStreamingService.streamHttpUrlContentsToIRODSFile(localSourceAbsolutePath, destFile,
        		   listener, transferControlBlock);
           
        } catch (JargonException ex) {
        	ex.printStackTrace();
        } catch (HttpStreamingException e) {
        	e.printStackTrace();
        } finally {
            idropGui.getIrodsFileSystem().closeAndEatExceptions();
        }
    }
}
