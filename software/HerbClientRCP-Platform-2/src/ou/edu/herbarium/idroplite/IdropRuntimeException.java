/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ou.edu.herbarium.idroplite;

/**
 * @author Mike Conway - DICE (www.irods.org)
 */
public class IdropRuntimeException extends RuntimeException {

    public IdropRuntimeException(Throwable cause) {
        super(cause);
    }

    public IdropRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdropRuntimeException(String message) {
        super(message);
    }

    public IdropRuntimeException() {
    }

}
