package com.sorting;

import java.util.ArrayList;

public class clsMatching {
	public static void matching(
			ArrayList<clsProduct> products,
			ArrayList<clsListing> listings,
			ArrayList<String> results)
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
						if(isSameProduct(pstr,cstr) && cmfg.contains(pmfg)){
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
	public static boolean isSameProduct(String sname, String stitle)
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
	
}
