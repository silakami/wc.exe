package filefunction;

import java.io.*;

public class CharFunction{

    public int[] charCount(String dir){
    	char[] charArray = null;
        String t = null;
        int j = 0; 
        int[] charNumber = new int[]{0,0};
        int num  = 0;
        int num1 = 0;
        int num2 = 0;
        try{
        	//读入文件只支持GKB编码，否则中文会出现乱码
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dir),"GBK"));		
            while((t = br.readLine()) != null){
                charArray = t.toCharArray();
                num1 = num2 = num = charArray.length;
                for(j = 0; j < num; j++){
                	//减去空格和tab
                    if(charArray[j] == ' ' || charArray[j] == 9){
                        num2--;
                    }
                }
                charNumber[0] = charNumber[0] + num1;
                charNumber[1] = charNumber[1] + num2;
            }
            br.close();
            return charNumber;
        }catch(Exception ex){
            ex.getMessage();
            System.out.println("文件不存在");
            return charNumber;
        }
    }
}
