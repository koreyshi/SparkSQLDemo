package librarySyatem;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public  class general extends JFrame implements ActionListener {
	
	JPanel jp1, jp2;
	JLabel jl1, jl2;
	JButton jb1, jb2;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	
	Model sm;

	public general() {
		
		
		
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1 = new JLabel("������ͼ��ţ�");

		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jb2 = new JButton("���");
		jb2.addActionListener(this);
		

		jp2 = new JPanel();
		jp2.add(jb2);

		// ����ģ�Ͷ���
		sm = new Model();

		// ��ʼ��
		jt = new JTable(sm);

		jsp = new JScrollPane(jt);

		// ��jsp���뵽jframe��
		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");
		
		setTitle("ͼ����Ϣ����ϵͳ");
		setSize(800, 600);//�趨���ڵĴ�С
		setVisible(true);//�趨����Ŀ��ӻ�
		setDefaultCloseOperation(EXIT_ON_CLOSE);//���ô���Ĺرշ�ʽ
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
		// �ж����ĸ���ť�����
		
		if (arg0.getSource() == jb1) {
			
			System.out.println("��ʼ���в�ѯ����");
			String name = this.jtf.getText().trim();
			
			// дһ����ѯsql���
			String sql = "select * from books where bookId = '" + name + "' ";
			System.out.println("��ѯ���");
			
			// ����һ������ģ�Ͷ��󣬲�����
			sm = new Model(sql);
			
			// ����jtable
			jt.setModel(sm);

		}
         
		else if(arg0.getSource() == jb2){
			
			            // ����һ������ģ���࣬������
			sm = new Model();
						
						// ����jtable
			jt.setModel(sm);

		}
	}
	
}
