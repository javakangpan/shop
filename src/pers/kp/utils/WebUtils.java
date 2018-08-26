package pers.kp.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	public static int parseInt(String str, int def) {
		int result = def;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			return def;
		}
		return result;

	}
	public static double parseDouble(String str,double def){
		double result=def;
		try {
			result = Double.parseDouble(str);
		} catch (Exception e) {
			return def;
		}
		return result;
		
	}
	public static void showMsg(String msg,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		out.write("<script>");
		out.write("alert('");
		out.write(msg);
		out.write("');");
		out.write("</script>");
		out.flush();
		out.close();
	}
	
	
	
	public static int getPageSize(HttpServletRequest request){
		String strPageSize=request.getParameter("pageSize");
		if(!isEmpty(strPageSize)){
			return parseInt(strPageSize,1);
		}
		
		
		return 3;
		
	}
	public static int getCurrentPage(HttpServletRequest request){
		String strCurrentPage=request.getParameter("currentPage");
		if(!isEmpty(strCurrentPage)){
			return parseInt(strCurrentPage,1);
		}
		
		
		return 1;
		
	}
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str)){
			return true;
		}
		return false;
	}

	public static synchronized String getFileName(String fileName){
		
		int index=fileName.lastIndexOf(".");
		String str=fileName.substring(index);
		return new Date().getTime()+str;		
		
	}
}
