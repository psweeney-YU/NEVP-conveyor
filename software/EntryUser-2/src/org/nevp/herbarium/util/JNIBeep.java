package org.nevp.herbarium.util;

public class JNIBeep {
	static {
		System.loadLibrary("beep"); // hello.dll (Windows) or libhello.so
	}
	// A native method that receives nothing and returns void
	private native void sayHello();
	
	private native void sayHello(String text);
	
	private native void sayHello(String text, int value);

	public static void main(String[] args) {
		JNIBeep beep = new JNIBeep();
		beep.sayHello(); // invoke the native method
		beep.sayHello("nima");
		beep.sayHello("nima",2);
	}
}
