package filefunction;

import java.io.*;

public class WordFunction{

    public int WordCount(String dir){
    	char[] charArray;    	
        String t = null;
        int wordNumber = 0;
        int j = 0;
        int	word = 0; 
        int num  = 0;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dir)));	
            while((t = br.readLine()) != null){				
                charArray = t.toCharArray();
                word = num = charArray.length;
                for(j = 0; j < num; j++){
                    if(j == 0){						
                    	if((charArray[j] >= 65 && charArray[j] <=90)|| (charArray[j]>=97 && charArray[j] <= 122)){
                    		//如果第一个字符是英文，那么就单词数加1
                    		word++;
                    	}
                    }
        			else if(j > 0 && (charArray[j-1]<65 || charArray[j-1]>122 || (charArray[j-1]>90 && charArray[j-1]<97))){  
        				//否则，计算后面的单词数
        					if((charArray[j]>=65 && charArray[j]<=90)|| (charArray[j]>=97 && charArray[j]<=122)){
        						word++;
        					}
        			}
                    word--;
                }
                wordNumber = wordNumber + word;
            }
            br.close();
            return wordNumber;
        }catch(Exception ex){
            ex.getMessage();
            System.out.println("文件不存在");
            return wordNumber;
        }

    }
}
