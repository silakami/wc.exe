package uifunction;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import filefunction.*;

public class UIFunction extends JFrame{
	JTextPane textPane = new JTextPane();
	JFileChooser filechooser = new JFileChooser();			//创建一个JFileChooser对象，用于打开，保存文件
	AllFunction allFunction = new AllFunction();
	CharFunction charFunction = new CharFunction();
	WordFunction wordFunction = new WordFunction();
	LineFunction lineFunction = new LineFunction();
	int[] charNumber = new int[]{0, 0};
	int wordNumber = 0;
	int[] allLineNumber = new int[]{0, 0, 0, 0};
	int lineNumber = 0;
	public void UIView(){
		//构建JFrame容器
		JFrame frame = new JFrame("wc.exe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//流水布局
		frame.setLayout(new FlowLayout());
		JButton buttonFile = new JButton("选择文件");					//设置button
		buttonFile.addActionListener(new InputFile());
		textPane.setPreferredSize(new Dimension(500,500));
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonFile, textPane);		//加入按钮和文本框
		frame.setSize(1000, 500);
		frame.setLocation(500, 300);
		frame.getContentPane().add(sp);
		frame.setVisible(true);
	}
	class InputFile implements ActionListener{
		//button监听方法
		String[] Suffix = {".txt", ".c", ".java", ".py", ".cpp", ".h"};
		int judge = 0;
		public void actionPerformed(ActionEvent e){    					//导入文件
			int i = filechooser.showOpenDialog(UIFunction.this);		//打开一个打开文件的对话框
			if(i == filechooser.APPROVE_OPTION){						//点击“确认”图标，进行下面操作
				textPane.setText("");									//调用settext方法直接清空文档
				File file = filechooser.getSelectedFile();
				String str = file.toString();
				for(String strs : Suffix){
					if(str.endsWith(strs)){
						judge = 1;
					}
				}
				if(judge == 1){
					charNumber = charFunction.charCount(str);
					wordNumber = wordFunction.WordCount(str);
					allLineNumber = allFunction.AllLineCount(str);
					lineNumber = lineFunction.lineCount(str);
					judge = 0;
					textPane.setText("字数（含空格）：" + charNumber[0] + "\n字数（不含空格）：" + charNumber[1] + "\n字符数:" + wordNumber);	
					textPane.setText(textPane.getText() + "\n总行数:" + lineNumber + "\n空行：" + allLineNumber[0] + "\n代码行：" + allLineNumber[1] + "" + "\n注释行：" + allLineNumber[2]);
					//读入时相当于字符串的连接
				}
				else{
					textPane.setText("不支持该文件类型" + "\n仅支持txt,c,java,py,cpp,h");
				}
			}
		}
		
	}
}
