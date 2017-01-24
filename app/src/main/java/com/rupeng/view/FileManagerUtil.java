package com.rupeng.view;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileManagerUtil {

	public static final   int NEW_FILE=0;
	public static final   int NEW_DIRECTORY=1;
	
	public static String COPY_FILE_PATH="";
	public static String CUT_FILE_PAHT="";
	public static boolean checkFileName(String fileanme){
		if(fileanme.contains("/")){
			   return false;
		}
		return true;
		
	}
 	public static void moveFile(String source, String destination)
	{
		new File(source).renameTo(new File(destination));   
	}
	public  static  void copyFile(String src, String target)
	{
		InputStream in = null;
		OutputStream out = null;

		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		try
		{
			in = new FileInputStream(src);
			out = new FileOutputStream(target);
			bin = new BufferedInputStream(in);
			bout = new BufferedOutputStream(out);

			byte[] b = new byte[8192];
			int len = bin.read(b);
			while (len != -1)
			{
				bout.write(b, 0, len);
				len = bin.read(b);
			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (bin != null)
				{
					bin.close();
				}
				if (bout != null)
				{
					bout.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}}

	public static boolean isAlreadyExgit(String filename,File[] filelist){
		for(int i=0;i<filelist.length;i++){
			 if(filelist[i].getName().equalsIgnoreCase(filename)){
				    return true;
			 }
		}
		return false;

	}


}
