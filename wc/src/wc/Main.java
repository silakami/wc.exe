package wc;

import java.util.*;
import java.util.regex.Pattern;
import filereader.*;
import uifunction.UIFunction;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("--------------Դ��������ͳ�Ƴ���-------------");
		System.out.println("����˵�����£�");
        System.out.println("ͳ���ַ���:wc.exe -c [�ļ�·��]");
        System.out.println("ͳ�ƴ���:wc.exe -w [�ļ�·��]");
        System.out.println("ͳ������:wc.exe -l [�ļ�·��]");
        System.out.println("ͳ�ƿ��С������С�ע����:wc.exe -a [�ļ�·��]");
        System.out.println("�ݹ鴦���ļ�������Ϊһ���ļ�Ŀ¼���ߴ���ͨ�����:wc.exe -s -�����еĲ��� [�ļ�·��]");
        System.out.println("���ϲ������Զ��һ��ʹ�ã��磺wc.exe -s -a [�ļ�·��]");
        System.out.println("���ӻ�����:wc.exe -x");
        System.out.println("�˳�����:quit");
        System.out.println("--------------------------------------------");
        System.out.println("������ָ�");
		
        String fileHandle = null;
        String[] fileSpit = null;
        //ʹ��������ʽ�涨�����ʽ
        final String FILEMATCH = "(wc.exe|WC.exe)(\\s+(-c|-w|-l|-a|-s|-x))+(\\s+\\S+)";
        FileReader fileReader = new FileReader();
        UIFunction uiFunction = new UIFunction();
        
		while(true){
			Scanner instruct = new Scanner(System.in);
			fileHandle = instruct.nextLine();
			fileSpit = fileHandle.split("\\s+");
			//ͼ�λ�����
			if(fileHandle.contains("-x") && fileSpit.length == 2){
				uiFunction.UIView();
				System.out.println("--------------------------------------------");
				System.out.println("������ָ�");
				continue;
			}
			if(fileHandle.equals("quit")){
				System.out.println("�������˳�");
				break;
			}
			//-a,-s,-w,-l,-c����
			if(Pattern.matches(FILEMATCH, fileHandle))
				fileReader.Chooser(fileSpit);
			else
				System.out.println("�����������������");
			System.out.println("--------------------------------------------");
			System.out.println("������ָ�");
		}
	}
}
