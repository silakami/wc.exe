package filefunction;

import java.io.*;


public class LineFunction{
    public int lineCount(String dir){
        String t = null;
        int lineNumber = 0;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dir)));
            while((t = br.readLine()) != null){			
                lineNumber++;
            }
            br.close();
            return lineNumber;
        }catch(Exception ex){
            ex.getMessage();
            System.out.println("文件不存在");
            return lineNumber;
        }
    }
}
