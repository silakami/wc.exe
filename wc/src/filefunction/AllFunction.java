package filefunction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class AllFunction{
    
    public int[] AllLineCount(String dir){
    	//allLineNum数组分别存储空行数，代码行数，注释行数
    	int[] allLineNum = new int[]{0, 0, 0};
    	int commentBlock = 0;
    	String t = null;
    	String str = null;
    	int l = 0;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dir)));
            while((t = br.readLine()) != null){				
            	l = 0;
            	//替换所有空白字符，检测空行
            	str = t.replaceAll("\\s", "");
            	if(str.length() <= 1 && commentBlock == 0){
            		allLineNum[0]++;
            		continue;
            	}
            	//单行注释
            	if(commentBlock != 0){
            		allLineNum[2]++;
            		continue;
            	}
            	if((str.startsWith("//") || str.startsWith("{//") || str.startsWith("}//")) && commentBlock == 0){
            		allLineNum[2]++;
            		continue;
            	}
            	//多行注释
            	else if(str.startsWith("/*")||str.startsWith("*/")||str.startsWith("{/*")||str.startsWith("*/}")){
            			if((str.startsWith("/*")||str.startsWith("{/*"))){
            				if(commentBlock == 0){
            					commentBlock = 1;
            					allLineNum[2]++;
            				}
            				//检测多行注释是否只在一行中
            				l = str.indexOf("*/");
            				if(l>0)
            					commentBlock = 0;
            			}
            			//注释不能嵌套，注释的结束只需要检测'*/',但要判断'*/'前是否存在符号
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
            System.out.println("文件不存在");
            return allLineNum;
        }
    }
}