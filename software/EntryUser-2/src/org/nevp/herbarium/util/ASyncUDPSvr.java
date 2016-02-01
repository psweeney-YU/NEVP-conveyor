package org.nevp.herbarium.util;
import java.io.IOException;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

import org.nevp.herbarium.sharedata.ErrorData;

public class ASyncUDPSvr {
    int BUF_SZ = 1024;
    int port = 60005;//60005
    boolean received = false;
    boolean begin = true;
    String tag = "";
    DatagramChannel channel;
    Selector selector;
    SelectionKey clientKey;
    class Con {
        ByteBuffer req;
        ByteBuffer resp;
        SocketAddress sa;
        public Con() {
            req = ByteBuffer.allocate(BUF_SZ);
        }
    }
    
    public ASyncUDPSvr(int port){
    	this.port = port;
    	this.begin = true;
    	try {
			selector = Selector.open();
	        channel = DatagramChannel.open();
	        InetSocketAddress isa = new InetSocketAddress(port);
	        channel.socket().bind(isa);
	        channel.configureBlocking(false);
	        clientKey = channel.register(selector, SelectionKey.OP_READ);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void close(){
    	try {
			channel.disconnect();
			channel.close();
			selector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public String process() {
        try {
            clientKey.attach(new Con());
            begin = true;
            long initMilli = System.currentTimeMillis();
            while (true) {
                try {
                    selector.select(60000);
                    long currentMilli = System.currentTimeMillis();
                    if((currentMilli-initMilli)>60000){
                    	System.out.println("null1");
                    	return null;
                    }
                    Iterator selectedKeys = selector.selectedKeys().iterator();
                    while (selectedKeys.hasNext()) {
                        try {
                            SelectionKey key = (SelectionKey) selectedKeys.next();
                            selectedKeys.remove();

                            if (!key.isValid()) {
                              continue;
                            }

                            if (key.isReadable()) {
                                String res = read(key);
                                key.interestOps(SelectionKey.OP_WRITE);
                                received = true;
                                if(res.startsWith("ERROR"))
                                	return null;
                                else return res;
                            } else if (key.isWritable()) {
                                write(key);
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        } catch (IOException e) {
                            System.err.println("glitch, continuing... " +(e.getMessage()!=null?e.getMessage():""));
                            System.out.println("null2");
                            return null;
                        }
                    }
                    return null;
                } catch (IOException e) {
                    System.err.println("glitch, continuing... " +(e.getMessage()!=null?e.getMessage():""));
                    System.out.println("null2");
                    return null;
                }
            }
        } catch (Exception e) {
            System.err.println("network error: " + (e.getMessage()!=null?e.getMessage():""));
            System.out.println("null3");
            return null;
        }
    }

    private String read(SelectionKey key) throws IOException {
        DatagramChannel chan = (DatagramChannel)key.channel();
        Con con = (Con)key.attachment();
        con.req.clear();
        con.sa = chan.receive(con.req);
        String result = new String(con.req.array(), "UTF-8");
        con.resp = Charset.forName( "UTF-8" ).newEncoder().encode(CharBuffer.wrap("send the same string"));
        return result;
    }

    private void write(SelectionKey key) throws IOException {
        DatagramChannel chan = (DatagramChannel)key.channel();
        Con con = (Con)key.attachment();
        chan.send(con.resp, con.sa);
    }

}