package filereader;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import uifunction.UIFunction;
import filefunction.*;

public class FileReader {
	AllFunction allFunction = new AllFunction();
	CharFunction charFunction = new CharFunction();
	WordFunction wordFunction = new WordFunction();
	LineFunction lineFunction = new LineFunction();
	FileDirectorySearch fileSearch = new FileDirectorySearch();
	UIFunction uiFunction = new UIFunction();
	
	int[] charNumber = new int[]{0, 0};
	int wordNumber = 0;
	int[] allLineNumber = new int[]{0, 0, 0, 0};
	int lineNumber = 0;
	List<String> fileList = new ArrayList<String>();
	String[] Suffix = {".txt", ".c", ".java", ".py", ".cpp", ".h"};
	int judge = 0;
	int judgex = 0;
	
	/*
	 * 对输入进行分类，并调用相应的方法
	 */
	public int Chooser(String[] str){
		judge = 0;
		judgex = 0;
		//检测非法输入-x
		for(int i = 1; i < str.length-1; i++){
			if(str[i].contains("-s"))
				judge = 1;
			if(str[i].contains("-x"))
				judgex = 1;
		}
		//非递归操作
		if(judge == 0 && judgex == 0){
			File file = new File(str[str.length-1]);
			if(file.isFile()){
				for(String strs : Suffix)
					if(file.toString().contains(strs))
						break;
					else{
						System.out.println("不支持该后缀名的查找");
						System.out.println("仅支持txt,c,java,py,cpp,h的查找");
						return judge;
					}
				for(int j = 1; j < str.length-1; j++){
					System.out.println("--------------------------------------------");
					switch (str[j]) {
					case "-a":
						allLineNumber = allFunction.AllLineCount(str[str.length-1]);
						System.out.println("空行：" + allLineNumber[0] + "\n代码行：" + allLineNumber[1] + "\n注释行" + allLineNumber[2]);
						break;
					case "-l":
						lineNumber = lineFunction.lineCount(str[str.length-1]);
						System.out.println("行数：" + lineNumber);
						break;
					case "-w":
						wordNumber = wordFunction.WordCount(str[str.length-1]);
						System.out.println("单词数：" + wordNumber);
						break;
					case "-c":
						charNumber = charFunction.charCount(str[str.length-1]);
						System.out.println("字符数（含空格）：" + charNumber[0] + "\n字符数（不含空格）：" + charNumber[1]);
						break;
					case "-s":
						break;
					default:
						System.out.println("操作符输入错误");
						break;
					}
				}
			}
			else{
				System.out.println("文件路径不正确，请重新输入");
				System.out.println("若有通配符，请输入-s，若需要图形化界面，请输入-x");
				return judge;
			}
		}
		//-s操作
		else if(judge == 1 && judgex == 0){
	        try{
	            fileList = fileSearch.getFilesPath(str[str.length-1]);
	            if(fileList == null){
	            	//输入错误路径
	            	System.out.println("路径错误，请输入正确的路径");
	            	return judge;
	            }
	            else{
	            	if(fileList.isEmpty()){
	            		System.out.println("查找失败");
	            		return judge;
	            	}
	            	else{
	            		String[] arr = fileList.toArray(new String[0]);
	            		for(String strs: arr){
	            			System.out.println("--------------------------------------------");
	            			System.out.println(strs);
	            			for(int j = 1; j < str.length-1; j++)
	        					switch (str[j]) {
	        					case "-a":
	        						allLineNumber = allFunction.AllLineCount(strs);
	        						System.out.println("空行：" + allLineNumber[0] + "\n代码行：" + allLineNumber[1] + "\n注释行" + allLineNumber[2]);
	        						break;
	        					case "-l":
	        						lineNumber = lineFunction.lineCount(strs);
	        						System.out.println("行数：" + lineNumber);
	        						break;
	        					case "-w":
	        						wordNumber = wordFunction.WordCount(strs);
	        						System.out.println("单词数：" + wordNumber);
	        						break;
	        					case "-c":
	        						charNumber = charFunction.charCount(strs);
	        						System.out.println("字符数（含空格）：" + charNumber[0] + "\n字符数（不含空格）：" + charNumber[1]);
	        						break;
	        					case "-s":
	        						break;
	        					default:
	        						System.out.println("操作符输入错误");
	        						break;
	        					}
	            		}
	            	}
	            }
	        }catch (Exception e) {
				// TODO: handle exception
	        	e.getMessage();
	        	System.out.println("读入文件失败");
	        	return judge;
			}
		}
		else {
			System.out.println("输入错误，请重新输入");
			return judge;
		}
		return judge;
	}
}
