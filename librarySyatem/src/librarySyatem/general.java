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
		jb1 = new JButton("查询");
		jb1.addActionListener(this);
		jl1 = new JLabel("请输入图书号：");

		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jb2 = new JButton("浏览");
		jb2.addActionListener(this);
		

		jp2 = new JPanel();
		jp2.add(jb2);

		// 创建模型对象
		sm = new Model();

		// 初始化
		jt = new JTable(sm);

		jsp = new JScrollPane(jt);

		// 将jsp放入到jframe中
		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");
		
		setTitle("图书信息管理系统");
		setSize(800, 600);//设定窗口的大小
		setVisible(true);//设定窗体的可视化
		setDefaultCloseOperation(EXIT_ON_CLOSE);//设置窗体的关闭方式
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
		// 判断是哪个按钮被点击
		
		if (arg0.getSource() == jb1) {
			
			System.out.println("开始进行查询操作");
			String name = this.jtf.getText().trim();
			
			// 写一个查询sql语句
			String sql = "select * from books where bookId = '" + name + "' ";
			System.out.println("查询完成");
			
			// 构建一个数据模型对象，并更新
			sm = new Model(sql);
			
			// 更新jtable
			jt.setModel(sm);

		}
         
		else if(arg0.getSource() == jb2){
			
			            // 构建一个数据模型类，并更新
			sm = new Model();
						
						// 更新jtable
			jt.setModel(sm);

		}
	}
	
}
