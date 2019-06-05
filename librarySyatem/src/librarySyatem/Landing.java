package librarySyatem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class Landing extends JFrame{
	
	private static int count=0;
	private static JButton bt1;//登陆按钮
	private static JButton bt2;//退出按钮
	private static JLabel jl_1;//登录的版面
	private static JFrame jf_1;//登录的框架
    private static JTextField jtext1;//用户名 
    private static JPasswordField jtext2;//密码
    private static JLabel jl_admin;//"用户名"
    private static JLabel jl_password;//"密码"
    
    public Landing (){
    	
    	Font font =new Font("黑体", Font.ITALIC, 20);//字体备选：PLAIN
	    jf_1=new JFrame("登录界面");
		jf_1.setSize(450, 400);
		
		jl_1=new JLabel();
		
		jl_admin=new JLabel("用户名");
		jl_admin.setBounds(20, 50, 60, 50);
		jl_admin.setFont(font);
		
		jl_password=new JLabel("密码");
		jl_password.setBounds(20, 120, 60, 50);
		jl_password.setFont(font);
		
		bt1=new JButton("登录");         
		bt1.setBounds(90, 250, 100, 50);
		bt1.setFont(font);
 
		bt2=new JButton("退出");
		bt2.setBounds(250, 250, 100, 50);
		bt2.setFont(font);
 
		jtext1=new JTextField(10);
		jtext1.setBounds(150, 50, 250, 50);
		jtext1.setFont(font);

		jtext2=new JPasswordField(10);
		jtext2.setBounds(150, 120, 250, 50);
		jtext2.setFont(font);
		
		jl_1.add(jtext1);
		jl_1.add(jtext2);
		
		jl_1.add(jl_admin);
		jl_1.add(jl_password);
		jl_1.add(bt1);
		jl_1.add(bt2);
		
		jf_1.add(jl_1);
		jf_1.setVisible(true);//设定窗体的可视化
		jf_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体的关闭方式
		
	}
	public static void main(String[] args) {
		
		Landing hl =new Landing();
		
		//登录按钮
		ActionListener bt1_ls=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String admin=jtext1.getText();
				char[] password=jtext2.getPassword();
				String str=String.valueOf(password); //将char数组转化为string类型
				
				if(admin.equals("root")&&str.equals("123456"))
				{
					
					System.out.println("管理员用户登录");   
					System.out.println("用户名:"+admin);
					System.out.println("密码:"+str);
					hl.jf_1.dispose();//关闭当前界面
					Main ml=new Main();//将要跳转的界面
					
				}
				else if(admin.equals("user")&&str.equals("654321")) 
				{
					
					System.out.println("普通用户登录");   
					System.out.println("用户名:"+admin);
					System.out.println("密码:"+str);
					hl.jf_1.dispose();
					general nl=new general();
		
				}
				
				else {
					
					count++;
					System.out.println("用户名或密码错误！");
					if(count==3){
						System.out.println("连续输入错误三次！自动退出");
						hl.jf_1.dispose();
						
					}
				}								
			}
		};
		bt1.addActionListener(bt1_ls);
		
		//退出按钮
		ActionListener bt2_ls=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		};
        bt2.addActionListener(bt2_ls);		
     }
}
