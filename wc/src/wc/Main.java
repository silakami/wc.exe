package wc;

import java.util.*;
import java.util.regex.Pattern;
import filereader.*;
import uifunction.UIFunction;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	System.out.println("--------------WC程序-------------");
	System.out.println("操作说明如下：");
        System.out.println("统计字符数:wc.exe -c [文件路径]");
        System.out.println("统计词数:wc.exe -w [文件路径]");
        System.out.println("统计行数:wc.exe -l [文件路径]");
        System.out.println("统计空行、代码行、注释行:wc.exe -a [文件路径]");
        System.out.println("递归处理文件（可以为一个文件目录或者带有通配符）:wc.exe -s -上面有的操作 [文件路径]");
        System.out.println("以上操作可以多个一起使用，如：wc.exe -s -a [文件路径]");
        System.out.println("可视化界面:wc.exe -x");
        System.out.println("退出操作:quit");
        System.out.println("--------------------------------------------");
        System.out.println("请输入指令：");
		
        String fileHandle = null;
        String[] fileSpit = null;
        //使用正则表达式规定输入格式
        final String FILEMATCH = "(wc.exe|WC.exe)(\\s+(-c|-w|-l|-a|-s|-x))+(\\s+\\S+)";
        FileReader fileReader = new FileReader();
        UIFunction uiFunction = new UIFunction();
        
		while(true){
			Scanner instruct = new Scanner(System.in);
			fileHandle = instruct.nextLine();
			fileSpit = fileHandle.split("\\s+");
			//图形化界面
			if(fileHandle.contains("-x") && fileSpit.length == 2){
				uiFunction.UIView();
				System.out.println("--------------------------------------------");
				System.out.println("请输入指令：");
				continue;
			}
			if(fileHandle.equals("quit")){
				System.out.println("程序已退出");
				break;
			}
			//-a,-s,-w,-l,-c操作
			if(Pattern.matches(FILEMATCH, fileHandle))
				fileReader.Chooser(fileSpit);
			else
				System.out.println("输入错误，请重新输入");
			System.out.println("--------------------------------------------");
			System.out.println("请输入指令：");
		}
	}
}
