package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import semantic_analysis.Four;
import semantic_analysis.Sym;
import semantic_analysis.Smanticanalysis;
import semantic_analysis.util;

public class GUI extends JFrame
{

	private static String file_name;
	
	public GUI()
	{
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setFont(new Font("����", Font.BOLD, 25));
		
		setTitle("SematicAnalysis");    //������ʾ���ڱ���
		setSize(850,700);    //���ô�����ʾ�ߴ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //�ô����Ƿ���Թر�
		getContentPane().setLayout(null);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(15, 25, 300, 500);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane1);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 17));
		scrollPane1.setViewportView(textArea);

			
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setToolTipText("");
		scrollPane2.setBackground(SystemColor.menu);
		scrollPane2.setBounds(350, 25, 450, 400);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane2);
		
		String[] name1 = new String[] {"���","��Ԫʽ", "����ַ"};
        JTable table1 = new JTable(new DefaultTableModel(new Object[][] {}, name1));
        table1.setForeground(Color.BLACK);
        table1.setFillsViewportHeight(true);
        table1.setFont(new Font("����", Font.BOLD, 15));
		table1.setBackground(new Color(255, 255, 255));
		scrollPane2.setViewportView(table1);
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane3.setBackground(SystemColor.menu);
		scrollPane3.setBounds(0, 0, 0, 0);
		getContentPane().add(scrollPane3);
		

	
		
		JScrollPane scrollPane4 = new JScrollPane();
		scrollPane4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane4.setBackground(SystemColor.menu);
		scrollPane4.setBounds(350, 450, 450, 200);
		getContentPane().add(scrollPane4);
		
		String[] name3 = new String[] {"������"};
		JTable table3 = new JTable(new DefaultTableModel(new Object[][] {}, name3));
		table3.setForeground(Color.RED);
		table3.setFont(new Font("����", Font.BOLD, 15));
		table3.setFillsViewportHeight(true);
		table3.setBackground(Color.WHITE);
		scrollPane4.setViewportView(table3);

		
		JButton button1 = new JButton("\u6253\u5F00\u6587\u4EF6");
		button1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JFileChooser file_open_filechooser = new JFileChooser();
				file_open_filechooser.setCurrentDirectory(new File("."));
				file_open_filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = file_open_filechooser.showOpenDialog(scrollPane1);
				
				if (result == JFileChooser.APPROVE_OPTION) // ֤����ѡ��
				{
					file_name = file_open_filechooser.getSelectedFile().getPath();
					// ��ȡ�ļ���д��JTextArea����
					try
					{
						FileReader reader = new FileReader(file_name);
			            BufferedReader br = new BufferedReader(reader);
			            String line;

			            while ((line = br.readLine()) != null)
			            {
							textArea.append(line);
							textArea.append("\n");
			            }
			            
						/*textArea.setText("");
						InputStream in = new FileInputStream(file);
						int tempbyte;
						while ((tempbyte=in.read()) != -1) 
						{
							textArea.append(""+(char)tempbyte);
						}*/
						reader.close();
					}
					catch(Exception event)
					{
						event.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "��ȡ���", "", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		button1.setFont(new Font("����", Font.BOLD, 20));
		button1.setBounds(15, 550, 150, 40);
		getContentPane().add(button1);
		
		JButton button2 = new JButton("\u8BED\u4E49\u5206\u6790");
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				List<Stack<Sym>> table = new ArrayList<Stack<Sym>>();  // ���ű�
				List<String> three_addr = new ArrayList<String>();  // ����ַָ������
				List<Four> four_addr = new ArrayList<Four>();  // ����ַָ������
				List<String> errors = new ArrayList<String>();  // ��������

				Smanticanalysis se = new Smanticanalysis(file_name,table,three_addr,four_addr,errors);
				
				Object[][] gui_ins = util.gui_ins(three_addr,four_addr);

				Object[][] gui_errors = util.gui_errors(errors);
				
				
				DefaultTableModel model1 = new DefaultTableModel(gui_ins,name1);
				table1.setModel(model1);
			
				DefaultTableModel mode2 = new DefaultTableModel(gui_errors,name3);
				table3.setModel(mode2);
			
			}
		});
		button2.setFont(new Font("����", Font.BOLD, 20));
		button2.setBounds(175, 550, 150, 40);
		getContentPane().add(button2);

		setVisible(true);    //���ô����Ƿ�ɼ�
	}
	
	
    public static void main(String[] agrs)
    {
        new GUI();    //����һ��ʵ��������
    }
}
