package filefunction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class AllFunction{
    
    public int[] AllLineCount(String dir){
    	//allLineNum����ֱ�洢������������������ע������
    	int[] allLineNum = new int[]{0, 0, 0};
    	int commentBlock = 0;
    	String t = null;
    	String str = null;
    	int l = 0;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dir)));
            while((t = br.readLine()) != null){				
            	l = 0;
            	//�滻���пհ��ַ���������
            	str = t.replaceAll("\\s", "");
            	if(str.length() <= 1 && commentBlock == 0){
            		allLineNum[0]++;
            		continue;
            	}
            	//����ע��
            	if(commentBlock != 0){
            		allLineNum[2]++;
            		continue;
            	}
            	if((str.startsWith("//") || str.startsWith("{//") || str.startsWith("}//")) && commentBlock == 0){
            		allLineNum[2]++;
            		continue;
            	}
            	//����ע��
            	else if(str.startsWith("/*")||str.startsWith("*/")||str.startsWith("{/*")||str.startsWith("*/}")){
            			if((str.startsWith("/*")||str.startsWith("{/*"))){
            				if(commentBlock == 0){
            					commentBlock = 1;
            					allLineNum[2]++;
            				}
            				//������ע���Ƿ�ֻ��һ����
            				l = str.indexOf("*/");
            				if(l>0)
            					commentBlock = 0;
            			}
            			//ע�Ͳ���Ƕ�ף�ע�͵Ľ���ֻ��Ҫ���'*/',��Ҫ�ж�'*/'ǰ�Ƿ���ڷ���
            			l = str.indexOf("*/");
            			if(commentBlock == 1 && (str.startsWith("*/") || l > 0)){
            				commentBlock = 0;
            			}
            	}
            	else
            		allLineNum[1]++;
            }
            br.close();
        	return allLineNum;
        }catch(Exception ex){
            ex.getMessage();
            System.out.println("�ļ�������");
            return allLineNum;
        }
    }
}