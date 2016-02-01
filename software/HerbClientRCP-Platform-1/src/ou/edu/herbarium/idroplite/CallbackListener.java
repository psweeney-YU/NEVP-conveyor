package ou.edu.herbarium.idroplite;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.transfer.TransferStatus;
import org.irods.jargon.core.transfer.TransferStatusCallbackListener;

public class CallbackListener implements TransferStatusCallbackListener {

	iDropLiteCore core;
	
    private Boolean transferInProgress = false;
    private Boolean transferCancelled = false;
	public CallbackListener(iDropLiteCore core){
		this.core = core;
	}
	@Override
	public void overallStatusCallback(TransferStatus ts)
			throws JargonException {
		// TODO Auto-generated method stub

	}

	@Override
	public void statusCallback(TransferStatus ts) throws JargonException {
		// TODO Auto-generated method stub
		if (ts.getTransferState() == TransferStatus.TransferState.FAILURE) {
            // an error occurs, stop the transfer
            if (ts.getTransferException() == null) {
                System.out.println("An error occurred in the transfer, this transfer will be cancelled");
            } else {
                ts.getTransferException().printStackTrace();
            }
            core.getTransferControlBlock().setCancelled(true);
            // reset process transfer flags
            if (isTransferInProgress()) {
                setTransferCancelled(true);
                setTransferInProgress(false);
            }
            
        } else if (ts.isIntraFileStatusReport()) {

        	
        	System.out.println("Current File 1 (kb) :"+ (ts.getBytesTransfered() / 1024) + " / "
                    + (ts.getTotalSize() / 1024));
            // intra file reports update the progress bar

            // if uploading from mode 2 table
        } else if (ts.getTransferState() == TransferStatus.TransferState.IN_PROGRESS_START_FILE) {

            // start of a file operation
//            progressIntraFile.setMinimum(0);
//            progressIntraFile.setMaximum((int) ts.getTotalSize());
//            progressIntraFile.setValue(0);
//            lblCurrentFile.setText(abbreviateFileName(ts.getSourceFileAbsolutePath()));
//
//            // need to do this because an IN_PROGRESS_START_FILE message is sent first that says
//            // all of the bytes have been transferred which of course is incorrect, so must ignore
//            // that first message
//            if (!(ts.getTotalSize() == ts.getBytesTransfered())) {
//                if (currentUploadFile != null) {
//                    tableRow = getUploadTableProgressRow(currentUploadFile);
//                }
//                if ((tableRow >= 0)) {
//                    TransferProgressInfo tpi = new TransferProgressInfo(ts.getTotalSize(), ts.getBytesTransfered(),
//                            ts.getTotalFilesToTransfer(), ts.getTotalFilesTransferredSoFar());
//                    tblUploadTable1.getModel().setValueAt(tpi, tableRow, 2);
//                }
//            }
        	System.out.println("Current File 2(kb):"+ (ts.getBytesTransfered() / 1024) + " / "
                    + (ts.getTotalSize() / 1024));
            
        } else if (ts.getTransferState() == TransferStatus.TransferState.IN_PROGRESS_COMPLETE_FILE) {
            
        	
        	System.out.println("Current File 3(kb):"+ (ts.getBytesTransfered() / 1024) + " / "
                    + (ts.getTotalSize() / 1024));
//            progressIntraFile.setMinimum(0);
//            progressIntraFile.setMaximum(10);
//            progressIntraFile.setValue(10);
//            lblTransferByteCounts.setText("Current File (kb):"
//                    + (ts.getTotalSize() / 1024) + " / "
//                    + (ts.getTotalSize() / 1024));
//            transferStatusProgressBar.setMaximum(ts.getTotalFilesToTransfer());
//            transferStatusProgressBar.setValue(ts.getTotalFilesTransferredSoFar());
//            pbIdropWebModeDownloadProgress.setMaximum(ts.getTotalFilesToTransfer());
//            pbIdropWebModeDownloadProgress.setValue(ts.getTotalFilesTransferredSoFar());
//            lblTransferFilesCounts.setText("Files: "
//                    + ts.getTotalFilesTransferredSoFar() + " / "
//                    + ts.getTotalFilesToTransfer());
//
//            // if uploading from mode 2 table
//            if ((tableRow >= 0) && (ts.getTotalSize() > 0)) {
//                float bt = ts.getBytesTransfered() * 100;
//                float tot = ts.getTotalSize();
//                float percentDone = bt / tot;
//                //tblUploadTable1.getModel().setValueAt((int) percentDone, tableRow, 2);
//                TransferProgressInfo tpi = new TransferProgressInfo(ts.getTotalSize(), ts.getBytesTransfered(),
//                        ts.getTotalFilesToTransfer(), ts.getTotalFilesTransferredSoFar());
//                tblUploadTable1.getModel().setValueAt(tpi, tableRow, 2);
//            }
            
        } else {
        	System.out.println("Current File 4(kb):"+ (ts.getBytesTransfered() / 1024) + " / "
                    + (ts.getTotalSize() / 1024));
            
        }
	}

	@Override
	public CallbackResponse transferAsksWhetherToForceOperation(String arg0,
			boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	public Boolean isTransferInProgress() {
		return transferInProgress;
	}
	public void setTransferInProgress(Boolean transferInProgress) {
		this.transferInProgress = transferInProgress;
	}
	public Boolean isTransferCancelled() {
		return transferCancelled;
	}
	public void setTransferCancelled(Boolean transferCancelled) {
		this.transferCancelled = transferCancelled;
	}

}
