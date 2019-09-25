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
	 * ��������з��࣬��������Ӧ�ķ���
	 */
	public int Chooser(String[] str){
		judge = 0;
		judgex = 0;
		//���Ƿ�����-x
		for(int i = 1; i < str.length-1; i++){
			if(str[i].contains("-s"))
				judge = 1;
			if(str[i].contains("-x"))
				judgex = 1;
		}
		//�ǵݹ����
		if(judge == 0 && judgex == 0){
			File file = new File(str[str.length-1]);
			if(file.isFile()){
				for(String strs : Suffix)
					if(file.toString().contains(strs))
						break;
					else{
						System.out.println("��֧�ָú�׺���Ĳ���");
						System.out.println("��֧��txt,c,java,py,cpp,h�Ĳ���");
						return judge;
					}
				for(int j = 1; j < str.length-1; j++){
					System.out.println("--------------------------------------------");
					switch (str[j]) {
					case "-a":
						allLineNumber = allFunction.AllLineCount(str[str.length-1]);
						System.out.println("���У�" + allLineNumber[0] + "\n�����У�" + allLineNumber[1] + "\nע����" + allLineNumber[2]);
						break;
					case "-l":
						lineNumber = lineFunction.lineCount(str[str.length-1]);
						System.out.println("������" + lineNumber);
						break;
					case "-w":
						wordNumber = wordFunction.WordCount(str[str.length-1]);
						System.out.println("��������" + wordNumber);
						break;
					case "-c":
						charNumber = charFunction.charCount(str[str.length-1]);
						System.out.println("�ַ��������ո񣩣�" + charNumber[0] + "\n�ַ����������ո񣩣�" + charNumber[1]);
						break;
					case "-s":
						break;
					default:
						System.out.println("�������������");
						break;
					}
				}
			}
			else{
				System.out.println("�ļ�·������ȷ������������");
				System.out.println("����ͨ�����������-s������Ҫͼ�λ����棬������-x");
				return judge;
			}
		}
		//-s����
		else if(judge == 1 && judgex == 0){
	        try{
	            fileList = fileSearch.getFilesPath(str[str.length-1]);
	            if(fileList == null){
	            	//�������·��
	            	System.out.println("·��������������ȷ��·��");
	            	return judge;
	            }
	            else{
	            	if(fileList.isEmpty()){
	            		System.out.println("����ʧ��");
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
	        						System.out.println("���У�" + allLineNumber[0] + "\n�����У�" + allLineNumber[1] + "\nע����" + allLineNumber[2]);
	        						break;
	        					case "-l":
	        						lineNumber = lineFunction.lineCount(strs);
	        						System.out.println("������" + lineNumber);
	        						break;
	        					case "-w":
	        						wordNumber = wordFunction.WordCount(strs);
	        						System.out.println("��������" + wordNumber);
	        						break;
	        					case "-c":
	        						charNumber = charFunction.charCount(strs);
	        						System.out.println("�ַ��������ո񣩣�" + charNumber[0] + "\n�ַ����������ո񣩣�" + charNumber[1]);
	        						break;
	        					case "-s":
	        						break;
	        					default:
	        						System.out.println("�������������");
	        						break;
	        					}
	            		}
	            	}
	            }
	        }catch (Exception e) {
				// TODO: handle exception
	        	e.getMessage();
	        	System.out.println("�����ļ�ʧ��");
	        	return judge;
			}
		}
		else {
			System.out.println("�����������������");
			return judge;
		}
		return judge;
	}
}
