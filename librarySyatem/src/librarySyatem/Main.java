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

public  class Main extends JFrame implements ActionListener {
	
	JPanel jp1, jp2;
	JLabel jl1, jl2;
	JButton jb1, jb2, jb3, jb4,jb5;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	
	Model sm;
	
	Statement stat = null;
	PreparedStatement ps;
	Connection ct = null;
	ResultSet rs = null;

	public Main() {

		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("查询");
		jb1.addActionListener(this);
		jl1 = new JLabel("请输入图书号：");

		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jb2 = new JButton("添加");
		jb2.addActionListener(this);
		jb3 = new JButton("修改");
		jb3.addActionListener(this);
		jb4 = new JButton("删除");
		jb4.addActionListener(this);
		jb5 = new JButton("浏览");
		jb5.addActionListener(this);

		jp2 = new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);

		// 创建模型对象
		sm = new Model();

		// 初始化
		jt = new JTable(sm);

		jsp = new JScrollPane(jt);

		// 将jsp放入到jframe中
		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");
		
		this.setTitle("图书信息管理系统");
		this.setSize(800, 600);//设定窗口的大小
		this.setVisible(true);//设定窗体的可视化
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//设置窗体的关闭方式
		

	}

	public void actionPerformed(ActionEvent arg0) {
		
		// 判断是哪个按钮被点击
		
		if (arg0.getSource() == jb1) {
			
			System.out.println("开始进行查询操作");
			String name = this.jtf.getText().trim();// 因为把对表的数据封装到StuModel中，可以比较简单的完成查询
			
			// 写一个查询sql语句
			String sql = "select * from books where bookId = '" + name + "' ";
			System.out.println("查询完成");
			
			// 构建一个数据模型类，并更新
			sm = new Model(sql);
			
			// 更新jtable
			jt.setModel(sm);

		}

		//弹出添加界面
		else if (arg0.getSource() == jb2) {
			System.out.println("开始进行添加操作");
			Add sa = new Add(this, "添加图书信息", true);

			// 重新再获得新的数据模型,
			System.out.println("添加成功");
			
			sm = new Model();
			jt.setModel(sm);
 
		} else if (arg0.getSource() == jb4) {
			// 删除记录
			System.out.println("开始进行删除操作");
			// 得到图书号
			int rowNum = this.jt.getSelectedRow();// getSelectedRow会返回给用户点中的行
			
			// 如果该用户一行都没有选，就返回-1
			if (rowNum == -1) {
				// 提示
				JOptionPane.showMessageDialog(this, "请选中一行");
				return;
			}
			// 得到图书号
			String bookId = (String) sm.getValueAt(rowNum, 0);
			System.out.println("已成功删除图书号:" + bookId + "的信息");

			// 连接数据库,完成删除任务
			try {
				// 1.加载驱动
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 2.连接数据库
				String url = "jdbc:mysql://localhost:3306/book?useSSL=false&serverTimezone=UTC";
				String user = "root";
				String passwd = "123356";

				ct = DriverManager.getConnection(url, user, passwd);
				System.out.println("连接成功");
				ps = ct.prepareStatement("delete from books where bookId = ?");
				ps.setString(1, bookId);
				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;

					}
					if (ps != null) {
						ps.close();
						ps = null;
					}
					if (ct != null) {
						ct.close();
						ct = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			sm = new Model();
			// 更新jtable
			jt.setModel(sm);
		} else if (arg0.getSource() == jb3) {
			
			System.out.println("开始进行修改操作");
			// 用户修改
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// 提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			// 显示对话框
			String bookId = (String) sm.getValueAt(rowNum, 0);

			// 连接数据库,完成删除任务
			try {
				// 1.加载驱动
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 2.连接数据库
				String url = "jdbc:mysql://localhost:3306/book?useSSL=false&serverTimezone=UTC";
				String user = "root";
				String passwd = "123356";

				ct = DriverManager.getConnection(url, user, passwd);
				System.out.println("连接成功");
				ps = ct.prepareStatement("delete from books where bookId = ?");
				ps.setString(1, bookId);
				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				//释放资源
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;

					}
					if (ps != null) {
						ps.close();
						ps = null;
					}
					if (ct != null) {
						ct.close();
						ct = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Update su = new Update(this, "修改图书信息", true, sm, rowNum);
			
			System.out.println("修改完成");
			
			sm = new Model();
			
			// 更新jtable
			jt.setModel(sm);
		}
		else if(arg0.getSource() == jb5){
			
			            // 构建一个数据模型类，并更新
			sm = new Model();
						
						// 更新jtable
			jt.setModel(sm);

		}
	}
	
}
