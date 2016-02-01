package org.nevp.herbarium.collector;

import java.io.*;
import java.util.ArrayList;

import org.nevp.herbarium.xml.XmlTool;
import org.nevp.herbarium.xml.XmlToolException;
import org.w3c.dom.Element;

public class CollectorInitialize {
	public static void main(String args[]) throws XmlToolException, IOException {
//		File file = new File("/home/flowvisor/Desktop/plugin.xml");
//		File nameList = new File(
//				"/home/flowvisor/Desktop/all_botanists_2012July02.tab");
//		XmlTool xml = new XmlTool();
//		xml.initialize(file);
//		FileReader reader = new FileReader(nameList);
//		BufferedReader br = new BufferedReader(reader);
//		String nextLine = "";
//		int index = 0;
//		String temp = "";
//		ArrayList<String> names = new ArrayList<String>();
//		while ((nextLine = br.readLine()) != null) {
//			String parts[] = nextLine.split("	");
//			if (parts.length == 10) {
////				System.out.println(parts[2] + " " + index);
//				names.add(parts[2]);
//			}
//			index++;
//		}
//		String name_array [] = new String [names.size()];
//		names.toArray(name_array);
//		int k = name_array.length;
//		for (int i = 0; i < k; i++){
//			System.out.println("sorting "+i);
//			for (int j = i + 1; j < k; j++) {
//				if (name_array[i].compareTo(name_array[j]) > 0) {
//					temp = name_array[i];
//					name_array[i] = name_array[j];
//					name_array[j] = temp;
//				}
//			}
//		}
//		String result = "";
//		for(String str : name_array){
//			System.out.println(str);
//			result+=str+"@\n";
//		}
//		
//		BufferedWriter writer = null;
//		try
//		{
//		    writer = new BufferedWriter( new FileWriter( "/home/flowvisor/Desktop/namelist2.txt"));
//		    writer.write( result);
//		}
//		catch ( IOException e)
//		{
//		}
//		finally
//		{
//		    try
//		    {
//		        if ( writer != null)
//		        writer.close( );
//		    }
//		    catch ( IOException e)
//		    {
//		    }
//		}
		build();
	}

	public static void build() throws IOException {
		File file = new File("/home/flowvisor/Desktop/namelist2.txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		ArrayList<String> names = new ArrayList<String>();
		String nextLine = "";
		while((nextLine=br.readLine())!=null){
			names.add(nextLine);
		}
		TrieItem root = new TrieItem('%', null);
		recursiveBuild("",root, names);
		
	}
	
	public static void recursiveBuild(String span,TrieItem parent, ArrayList<String> names){
		char marker = ' ';
		int counter = 1;
		System.out.println("name size = "+names.size());
		ArrayList<TrieItem> items = new ArrayList<TrieItem>();
		ArrayList<String> subNames = new ArrayList<String>();
		for(int i=0;i<names.size();i++){
			if((i+1)<names.size()&&names.get(i+1).charAt(0)!=marker){
				System.out.println("counter="+span+counter);
				System.out.println("names.get(i)="+names.get(i));
				subNames.add(names.get(i).substring(1));
				if(counter==1){
					
				}
				else{
					TrieItem item = new TrieItem(marker, null);
					recursiveBuild(span+"    ",item,subNames);
					items.add(item);
					counter = 1;
					marker = names.get(i+1).charAt(0);
				}
			}
			else if((i+1)==names.size()){
				subNames.add(names.get(i).substring(1));
				TrieItem item = new TrieItem(marker, null);
				recursiveBuild(span+"    ",item,subNames);
				items.add(item);
			}
			else{
//				if(counter==0)
//					counter=2;
				counter++;
				subNames.add(names.get(i).substring(1));
			}
		}
		parent.setItems(items);
	}
}

class TrieItem{
	char marker;
	ArrayList<TrieItem> items;
	public TrieItem(char marker, ArrayList<TrieItem> items) {
		super();
		this.marker = marker;
		this.items = items;
	}
	public char getMarker() {
		return marker;
	}
	public void setMarker(char marker) {
		this.marker = marker;
	}
	public ArrayList<TrieItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<TrieItem> items) {
		this.items = items;
	}
}
