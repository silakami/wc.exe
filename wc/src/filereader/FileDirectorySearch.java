package filereader;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileDirectorySearch {
	static class StopMsgException extends RuntimeException {
		//��ֹ�ݹ����Զ���ݹ��쳣
		public StopMsgException() {
			// TODO Auto-generated constructor stub
			super("�ݹ����");
		}
    }

	/*
	 * �ݹ�����ļ�
	 */
	
	public List<String> getFilesPath(String str){
		List<String> list = new ArrayList<String>();
		File file = new File(str);
		String dir = null;
		String filePath = null;
		String[] Suffix = {".txt", ".c", ".java", ".py", ".cpp", ".h"};		//�涨�ó�����Զ�����ļ�
		if(str.contains("?") || str.contains("*")){
			//����ͨ����������ļ�·����'\'�ָ������Լ������'\'����ȡ�ļ����ڵ�Ŀ¼����չ��
			filePath = str.substring(0, str.lastIndexOf("\\"));
			dir = str.substring(str.lastIndexOf("\\") + 1);	
		}
		else{
			if(file.isFile()){
				//�����ļ������˲���Ҫ����ļ�
				for(String strs : Suffix)
					if(file.toString().contains(strs)){
						list.add(str);
						break;
					}
					else{
						System.out.println("��֧�ָú�׺���Ĳ���");
						System.out.println("��֧��txt,c,java,py,cpp,h�Ĳ���");
						return list;
					}
				return list;
			}
			else if(file.isDirectory()){
				//�����ļ���
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
				System.out.println("�Ҳ����������ļ���ͨ������벻��ȷ");
				System.out.println("��֧��txt,c,java,py,cpp,h�Ĳ���");
			}
		}catch(StopMsgException e){
			System.out.println("����ģ��������������·��");
		}
		return list;
	}
 
	
	public Pattern getFiles(String s) {   
		//ͨ����任     
		s = s.replace('*', '#');   
		s = s.replaceAll("#", ".*");   
		s = s.replace('?', '#');   
		s = s.replaceAll("#", ".?");     
		System.out.println(s);
		Pattern p = Pattern.compile(s);
		return p;
	}   

	
	public List<String> filePathGet(File file, Pattern p, int deep){
		//���еݹ������ļ�������һ���ַ����ļ��б�
		List<String> list = new ArrayList<String>();
		//������Ϊһ���ļ�Ŀ¼ʱ����ֻ���ȡ�⼸�ֺ�׺���ļ�
		String[] Suffix = {".txt", ".c", ".java", ".py", ".cpp", ".h"};
		Matcher matchFile = p.matcher(file.getName());
		if(file.isFile() && matchFile.matches()){
			//�����ļ��������ļ���
			for(String strs : Suffix){
				if(file.getName().endsWith(strs)){
					list.add(file.toString());
					return list;
				}
			}
			return list;
		}
		else if(file.isDirectory()){
			//�����ļ��У���ȡ�ļ��������Ŀ¼
			File[] filePaths = file.listFiles();
			if(filePaths != null){
				for(int j = 0; j < filePaths.length; j++){
					//deep�������Ƿ�ֹ�ݹ���������������
					if(deep <= 100){
						//�����ݹ�
						List<String> deepList = filePathGet(filePaths[j], p, ++deep);
						if(!deepList.isEmpty())
							list.addAll(deepList);
					}
					else{
						//�׳��Զ���ݹ�����쳣
						throw new StopMsgException();
					}
				}
			}
			return list;
		}
		return list;
	}
	  
	
	
	
}  
