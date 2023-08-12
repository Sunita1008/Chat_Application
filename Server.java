package chattingapplication;
import javax.swing.*;
import javax.swing.border.*;
//import javax.swing.border.EmptyBorder;

import java.awt.*;
//import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
public class Server extends JFrame implements ActionListener{
	
	JTextField text;
	JPanel a1;
	static DataOutputStream dout;
	 static Box vertical= Box.createVerticalBox();
	 
	static  JFrame f=new JFrame();
	Server()
	{
		f.setLayout(null);
		
		JPanel p1=new JPanel();
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,400,70);
		p1.setLayout(null);
		f.add(p1);
		
		  
//		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/arrows.jpg"));
//		Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
//		ImageIcon i3=new ImageIcon(i2);
//		JLabel back=new JLabel(i3);
//		back.setBounds(5,20,25,25);
//		p1.add(back);
//		//p1.setVisible(true);
//		
//		back.addMouseListener(new MouseAdapter() {
//			public void MouseClicked(MouseEvent ae)
//			{
//				System.exit(0);
//			}
//		});
//		
//		ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/arrows.jpg"));
//		Image i5=i4.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT);
//		ImageIcon i6=new ImageIcon(i5);
//		JLabel profile=new JLabel(i3);
//		profile.setBounds(40,10,50,50);
//		p1.add(profile);
		
		JLabel name=new JLabel("Onu");
		name.setBounds(110,15,100,18);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("SAN SERIF",Font.BOLD,18));
		p1.add(name);
		
		JLabel status=new JLabel("Active Now");
		status.setBounds(110,35,100,18);
		status.setForeground(Color.WHITE);
		status.setFont(new Font("SAN SERIF",Font.BOLD,14));
		p1.add(status);
		
		 a1=new JPanel();
		a1.setBounds(4,75,379,350);
		f.add(a1);
		
		 text=new JTextField();
		text.setBounds(4,430,309,30);
		text.setFont(new Font("SAN_SERIF",Font.PLAIN,15));
		f.add(text);
		
		JButton send =new JButton("Send");
		send.setBounds(320,430,64,30);
		send.setBackground(new Color(7,94,84));
		send.setForeground(Color.WHITE);
		send.addActionListener(this);
		f.add(send);
		
		
		f.setSize(400,500);
		f.setLocation(200,50);
		//setUndecorated(true);
		f.getContentPane().setBackground(Color.WHITE);
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		try {
		String out=text.getText();
		//System.out.println(out);
		
		//JLabel output=new JLabel(out);
		
		JPanel p2=formatLabel(out);
		//p2.add(output);
		
		a1.setLayout(new BorderLayout());
		
		JPanel right= new JPanel(new BorderLayout());
		right.add(p2, BorderLayout.LINE_END);
		vertical.add(right);
		vertical.add(Box.createVerticalStrut(15));
		
		a1.add(vertical, BorderLayout.PAGE_START);
		
		dout.writeUTF(out);
		text.setText("");
		
		f.repaint();
		f.invalidate();
		f.validate();
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	public static JPanel formatLabel (String out)
	{
		JPanel panel= new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel output=new JLabel(out);
		output.setFont(new Font("Tahoma",Font.PLAIN,16));
		output.setBackground(new Color(37,211,102));
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,50));
		
		panel.add(output);
		
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
		
		JLabel time=new JLabel();
		
		time.setText(sdf.format(cal.getTime()));
		panel.add(time);
		
		return panel;
	}
	public static void main(String[] args) {
		
		new Server();
		try {
			ServerSocket skt =new ServerSocket(6001);
			while(true)
			{
				Socket s=skt.accept(); //to infinitely accept msg
				DataInputStream din=new DataInputStream(s.getInputStream()); //to recv
				dout=new DataOutputStream(s.getOutputStream());//to send
				
				while(true)
				{
					String msg = din.readUTF();
					JPanel panel=formatLabel(msg);
					
					JPanel left=new JPanel(new BorderLayout());
					left.add(panel,BorderLayout.LINE_START); //receved msgs will be shown on the left
					vertical.add(left);
					f.validate();
				
				}
			    
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
