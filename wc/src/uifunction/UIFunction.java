package uifunction;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import filefunction.*;

public class UIFunction extends JFrame{
	JTextPane textPane = new JTextPane();
	JFileChooser filechooser = new JFileChooser();			//����һ��JFileChooser�������ڴ򿪣������ļ�
	AllFunction allFunction = new AllFunction();
	CharFunction charFunction = new CharFunction();
	WordFunction wordFunction = new WordFunction();
	LineFunction lineFunction = new LineFunction();
	int[] charNumber = new int[]{0, 0};
	int wordNumber = 0;
	int[] allLineNumber = new int[]{0, 0, 0, 0};
	int lineNumber = 0;
	public void UIView(){
		//����JFrame����
		JFrame frame = new JFrame("wc.exe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//��ˮ����
		frame.setLayout(new FlowLayout());
		JButton buttonFile = new JButton("ѡ���ļ�");					//����button
		buttonFile.addActionListener(new InputFile());
		textPane.setPreferredSize(new Dimension(500,500));
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonFile, textPane);		//���밴ť���ı���
		frame.setSize(1000, 500);
		frame.setLocation(500, 300);
		frame.getContentPane().add(sp);
		frame.setVisible(true);
	}
	class InputFile implements ActionListener{
		//button��������
		String[] Suffix = {".txt", ".c", ".java", ".py", ".cpp", ".h"};
		int judge = 0;
		public void actionPerformed(ActionEvent e){    					//�����ļ�
			int i = filechooser.showOpenDialog(UIFunction.this);		//��һ�����ļ��ĶԻ���
			if(i == filechooser.APPROVE_OPTION){						//�����ȷ�ϡ�ͼ�꣬�����������
				textPane.setText("");									//����settext����ֱ������ĵ�
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
					textPane.setText("���������ո񣩣�" + charNumber[0] + "\n�����������ո񣩣�" + charNumber[1] + "\n�ַ���:" + wordNumber);	
					textPane.setText(textPane.getText() + "\n������:" + lineNumber + "\n���У�" + allLineNumber[0] + "\n�����У�" + allLineNumber[1] + "" + "\nע���У�" + allLineNumber[2]);
					//����ʱ�൱���ַ���������
				}
				else{
					textPane.setText("��֧�ָ��ļ�����" + "\n��֧��txt,c,java,py,cpp,h");
				}
			}
		}
		
	}
}
