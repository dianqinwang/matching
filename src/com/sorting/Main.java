package com.sorting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
	static ArrayList<clsProduct> products=null;
	static ArrayList<clsListing> listings=null;
	static ArrayList<String> results=null;
	public static void main(String[] args) {
		
		products= new ArrayList<clsProduct>();
		listings= new ArrayList<clsListing>();
		results=new ArrayList<String>();
		
		clsProduct.getFromFile(products);
		clsListing.getFromFile(listings);
		
		cmpProdList();
		
		saveToFile("results.txt",results);		
		
		close();
		return;
	}
	
	public static void cmpProdList()
	{
		for(int i =0;i<products.size(); i++){
			try{
				String pstr=products.get(i).product_name;
				pstr=pstr.toUpperCase();
			
				String pmfg=products.get(i).manufacturer;
				pmfg=pmfg.toUpperCase();
			
				for(int j=0;j<listings.size();j++){
					try{
						String cstr=listings.get(j).title;
						cstr=cstr.toUpperCase();
				
						String cmfg=listings.get(j).manufacturer;
						cmfg=cmfg.toUpperCase();
						if(cmpStrs(pstr,cstr) && cmfg.contains(pmfg)){
							products.get(i).addStr(listings.get(j).str_json);
						}
					}catch(Exception e){}
				}
			}catch(Exception e){}
			String pout=products.get(i).toString() +"\n";
			results.add(pout);			
		}
		
		return;
	}
	public static boolean cmpStrs(String sname, String stitle)
	{
		boolean bret=true;
		sname=sname.toUpperCase();
		stitle=stitle.toUpperCase();
		
		String[] astr=sname.split("[ \\-_]+");

		boolean mret=true;
		for(int i=0;i<astr.length; i++){
			mret=mret && stitle.contains(astr[i]);
		}
		if(astr.length<=0)mret=false;
		
		bret=bret && mret;
		
				
		return bret;
	}
	
	public static void saveToFile(String fpath,ArrayList<String> arlist)
	{
		String fname="";
	    FileOutputStream _out=null;
	   	try{
	   		File _file = new File(fpath);
	   		try{
            if( _file.exists() ) {            	
                _file.delete();
            }
            _file.createNewFile();
            fname=_file.getAbsolutePath();
            
	   		}catch(IOException e){
	   			System.out.println(e.getMessage());
	   		}
           _out = new FileOutputStream(_file); 
           
           for(int i=0;i<arlist.size();i++){
        	   byte[] raw=arlist.get(i).getBytes();
        	   _out.write( raw, 0, raw.length );
           }
           
	   	}catch(Exception e){
	   		System.out.println(e.getMessage());           
           try {
			_out.flush();
			_out.close();
           } catch (IOException e1) {
        	   System.out.println(e1.getMessage());
           }			
        }
	   	
	   	System.out.println("Output:"+fname);
	}

	public static void close()
	{
		try{
			for(int i=0;i<products.size();i++){
				products.get(i).close();
			}			
		}catch(Exception e){
			System.out.println(e.getMessage()); 
		}
		try{
			for(int i=0;i<listings.size();i++){
				listings.get(i).close();
			}			
		}catch(Exception e){
			System.out.println(e.getMessage()); 
		}
		try{
			results.clear();
			products.clear();
			listings.clear();
		}catch(Exception e){
			System.out.println(e.getMessage()); 
		}
	}
}
