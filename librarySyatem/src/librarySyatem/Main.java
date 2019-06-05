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
		jb1 = new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1 = new JLabel("������ͼ��ţ�");

		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jb2 = new JButton("���");
		jb2.addActionListener(this);
		jb3 = new JButton("�޸�");
		jb3.addActionListener(this);
		jb4 = new JButton("ɾ��");
		jb4.addActionListener(this);
		jb5 = new JButton("���");
		jb5.addActionListener(this);

		jp2 = new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);

		// ����ģ�Ͷ���
		sm = new Model();

		// ��ʼ��
		jt = new JTable(sm);

		jsp = new JScrollPane(jt);

		// ��jsp���뵽jframe��
		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");
		
		this.setTitle("ͼ����Ϣ����ϵͳ");
		this.setSize(800, 600);//�趨���ڵĴ�С
		this.setVisible(true);//�趨����Ŀ��ӻ�
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//���ô���Ĺرշ�ʽ
		

	}

	public void actionPerformed(ActionEvent arg0) {
		
		// �ж����ĸ���ť�����
		
		if (arg0.getSource() == jb1) {
			
			System.out.println("��ʼ���в�ѯ����");
			String name = this.jtf.getText().trim();// ��Ϊ�ѶԱ�����ݷ�װ��StuModel�У����ԱȽϼ򵥵���ɲ�ѯ
			
			// дһ����ѯsql���
			String sql = "select * from books where bookId = '" + name + "' ";
			System.out.println("��ѯ���");
			
			// ����һ������ģ���࣬������
			sm = new Model(sql);
			
			// ����jtable
			jt.setModel(sm);

		}

		//������ӽ���
		else if (arg0.getSource() == jb2) {
			System.out.println("��ʼ������Ӳ���");
			Add sa = new Add(this, "���ͼ����Ϣ", true);

			// �����ٻ���µ�����ģ��,
			System.out.println("��ӳɹ�");
			
			sm = new Model();
			jt.setModel(sm);
 
		} else if (arg0.getSource() == jb4) {
			// ɾ����¼
			System.out.println("��ʼ����ɾ������");
			// �õ�ͼ���
			int rowNum = this.jt.getSelectedRow();// getSelectedRow�᷵�ظ��û����е���
			
			// ������û�һ�ж�û��ѡ���ͷ���-1
			if (rowNum == -1) {
				// ��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			// �õ�ͼ���
			String bookId = (String) sm.getValueAt(rowNum, 0);
			System.out.println("�ѳɹ�ɾ��ͼ���:" + bookId + "����Ϣ");

			// �������ݿ�,���ɾ������
			try {
				// 1.��������
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 2.�������ݿ�
				String url = "jdbc:mysql://localhost:3306/book?useSSL=false&serverTimezone=UTC";
				String user = "root";
				String passwd = "123356";

				ct = DriverManager.getConnection(url, user, passwd);
				System.out.println("���ӳɹ�");
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
			// ����jtable
			jt.setModel(sm);
		} else if (arg0.getSource() == jb3) {
			
			System.out.println("��ʼ�����޸Ĳ���");
			// �û��޸�
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// ��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			// ��ʾ�Ի���
			String bookId = (String) sm.getValueAt(rowNum, 0);

			// �������ݿ�,���ɾ������
			try {
				// 1.��������
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 2.�������ݿ�
				String url = "jdbc:mysql://localhost:3306/book?useSSL=false&serverTimezone=UTC";
				String user = "root";
				String passwd = "123356";

				ct = DriverManager.getConnection(url, user, passwd);
				System.out.println("���ӳɹ�");
				ps = ct.prepareStatement("delete from books where bookId = ?");
				ps.setString(1, bookId);
				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				//�ͷ���Դ
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
			Update su = new Update(this, "�޸�ͼ����Ϣ", true, sm, rowNum);
			
			System.out.println("�޸����");
			
			sm = new Model();
			
			// ����jtable
			jt.setModel(sm);
		}
		else if(arg0.getSource() == jb5){
			
			            // ����һ������ģ���࣬������
			sm = new Model();
						
						// ����jtable
			jt.setModel(sm);

		}
	}
	
}
