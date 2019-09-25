package filereader;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileDirectorySearch {
	static class StopMsgException extends RuntimeException {
		//防止递归过深，自定义递归异常
		public StopMsgException() {
			// TODO Auto-generated constructor stub
			super("递归过深");
		}
    }

	/*
	 * 递归查找文件
	 */
	
	public List<String> getFilesPath(String str){
		List<String> list = new ArrayList<String>();
		File file = new File(str);
		String dir = null;
		String filePath = null;
		String[] Suffix = {".txt", ".c", ".java", ".py", ".cpp", ".h"};		//规定该程序可以读入的文件
		int judges = 0;
		if(str.contains("?") || str.contains("*")){
			//处理通配符，由于文件路径以'\'分隔，所以检测最后的'\'来获取文件所在的目录和扩展名
			filePath = str.substring(0, str.lastIndexOf("\\"));
			dir = str.substring(str.lastIndexOf("\\") + 1);	
		}
		else{
			if(file.isFile()){
				//处理文件，过滤不合要求的文件
				for(String strs : Suffix)
					if(file.toString().contains(strs)){
						list.add(str);
						judges = 1;
						break;
					}
				if(judges != 1){
					System.out.println("不支持该后缀名的查找");
					System.out.println("仅支持txt,c,java,py,cpp,h的查找");
					return list;
				}
				return list;
			}
			else if(file.isDirectory()){
				//处理文件夹
				dir = "*"; 
				filePath = str;
			}else
				return null;
		}
		try{
			File file2 = new File(filePath);
			Pattern p = getFiles(dir);
			list = filePathGet(file2, p, 0);		
			if(list.isEmpty()){
				System.out.println("找不到该类型文件或通配符输入不正确");
				System.out.println("仅支持txt,c,java,py,cpp,h的查找");
			}
		}catch(StopMsgException e){
			System.out.println("过于模糊，请重新输入路径");
		}
		return list;
	}
 
	
	public Pattern getFiles(String s) {   
		//通配符变换     
		s = s.replace('*', '#');   
		s = s.replaceAll("#", ".*");   
		s = s.replace('?', '#');   
		s = s.replaceAll("#", ".?");     
		Pattern p = Pattern.compile(s);
		return p;
	}   

	
	public List<String> filePathGet(File file, Pattern p, int deep){
		//进行递归搜索文件，返回一个字符串文件列表
		List<String> list = new ArrayList<String>();
		//当输入为一个文件目录时，本只会读取这几种后缀的文件
		String[] Suffix = {".txt", ".c", ".java", ".py", ".cpp", ".h"};
		Matcher matchFile = p.matcher(file.getName());
		if(file.isFile() && matchFile.matches()){
			//遇到文件，返回文件名
			for(String strs : Suffix){
				if(file.getName().endsWith(strs)){
					list.add(file.toString());
					return list;
				}
			}
			return list;
		}
		else if(file.isDirectory()){
			//遇到文件夹，获取文件夹下面的目录
			File[] filePaths = file.listFiles();
			if(filePaths != null){
				for(int j = 0; j < filePaths.length; j++){
					//deep的作用是防止递归过深，程序卡死的问题
					if(deep <= 100){
						//继续递归
						List<String> deepList = filePathGet(filePaths[j], p, ++deep);
						if(!deepList.isEmpty())
							list.addAll(deepList);
					}
					else{
						//抛出自定义递归过深异常
						throw new StopMsgException();
					}
				}
			}
			return list;
		}
		return list;
	}
	  
	
	
	
}  
