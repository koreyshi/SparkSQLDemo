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
	private static JButton bt1;//��½��ť
	private static JButton bt2;//�˳���ť
	private static JLabel jl_1;//��¼�İ���
	private static JFrame jf_1;//��¼�Ŀ��
    private static JTextField jtext1;//�û��� 
    private static JPasswordField jtext2;//����
    private static JLabel jl_admin;//"�û���"
    private static JLabel jl_password;//"����"
    
    public Landing (){
    	
    	Font font =new Font("����", Font.ITALIC, 20);//���屸ѡ��PLAIN
	    jf_1=new JFrame("��¼����");
		jf_1.setSize(450, 400);
		
		jl_1=new JLabel();
		
		jl_admin=new JLabel("�û���");
		jl_admin.setBounds(20, 50, 60, 50);
		jl_admin.setFont(font);
		
		jl_password=new JLabel("����");
		jl_password.setBounds(20, 120, 60, 50);
		jl_password.setFont(font);
		
		bt1=new JButton("��¼");         
		bt1.setBounds(90, 250, 100, 50);
		bt1.setFont(font);
 
		bt2=new JButton("�˳�");
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
		jf_1.setVisible(true);//�趨����Ŀ��ӻ�
		jf_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���Ĺرշ�ʽ
		
	}
	public static void main(String[] args) {
		
		Landing hl =new Landing();
		
		//��¼��ť
		ActionListener bt1_ls=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String admin=jtext1.getText();
				char[] password=jtext2.getPassword();
				String str=String.valueOf(password); //��char����ת��Ϊstring����
				
				if(admin.equals("root")&&str.equals("123456"))
				{
					
					System.out.println("����Ա�û���¼");   
					System.out.println("�û���:"+admin);
					System.out.println("����:"+str);
					hl.jf_1.dispose();//�رյ�ǰ����
					Main ml=new Main();//��Ҫ��ת�Ľ���
					
				}
				else if(admin.equals("user")&&str.equals("654321")) 
				{
					
					System.out.println("��ͨ�û���¼");   
					System.out.println("�û���:"+admin);
					System.out.println("����:"+str);
					hl.jf_1.dispose();
					general nl=new general();
		
				}
				
				else {
					
					count++;
					System.out.println("�û������������");
					if(count==3){
						System.out.println("��������������Σ��Զ��˳�");
						hl.jf_1.dispose();
						
					}
				}								
			}
		};
		bt1.addActionListener(bt1_ls);
		
		//�˳���ť
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
