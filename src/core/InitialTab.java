package core;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import utility.DbUtil;

public class InitialTab extends JFrame implements ActionListener
{
	Container con;
	JLabel rollNo,name,DOBLabel,headlabel,orJLabel,msgLabel,info;
	JTextField rollnoField,nameField,DOBField;
	JButton submit,logout,register,all;
	Connection conn ;
	Statement stmt;
	ResultSet rs;
	
	
	public InitialTab()
	{
		setTitle("Enter Details");
		setLocation(300, 100);
		setSize(400, 540);
		
		con = getContentPane();
		con.setLayout(null);
		con.setBackground(new Color(64, 128, 128));
		
		headlabel = new JLabel("Enter Roll number or name of student");
		headlabel.setBounds(50, 50, 300, 30);
		headlabel.setFont(new Font("times new roman", 01, 15));
		headlabel.setForeground(Color.white);
		con.add(headlabel);
	
		
		rollNo = new JLabel("Roll No.");
		rollNo.setBounds(50, 100, 100, 30);
		rollNo.setFont(new Font("times new roman", 01, 20));
		rollNo.setForeground(Color.white);
		con.add(rollNo);
		
		orJLabel = new JLabel("OR");
		orJLabel.setBounds(150, 150, 100, 50);
		orJLabel.setFont(new Font("times new roman", 01, 30));
		orJLabel.setForeground(Color.white);
		con.add(orJLabel);
	
	
	
		name = new JLabel("Name");
		name.setBounds(50, 220, 100, 30);
		name.setFont(new Font("times new roman", 01, 20));
		name.setForeground(Color.white);
		con.add(name);
		
		
		DOBLabel = new JLabel("Date Of Birth");
		DOBLabel.setBounds(50, 270, 200, 30);
		DOBLabel.setFont(new Font("times new roman", 01, 20));
		DOBLabel.setForeground(Color.white);
		con.add(DOBLabel);
		
		
		rollnoField = new JTextField();
		rollnoField.setBounds(200, 100, 150, 25);
		con.add(rollnoField);
	
		nameField = new JTextField();
		nameField.setBounds(200, 220, 150, 25);
		con.add(nameField);
	
		DOBField = new JTextField();
		DOBField.setBounds(200, 270, 150, 25);
		con.add(DOBField);
		
		info = new JLabel("(dd/mm/yyyy)");
		info.setBounds(200, 300, 200, 30);
		info.setFont(new Font("times new roman", 01, 20));
		info.setForeground(Color.white);
		con.add(info);
	
		
		msgLabel = new JLabel();
		msgLabel.setBounds(50,20,300,30);
		msgLabel.setFont(new Font("times new roman", 1 , 20));
		
		con.add(msgLabel);
		
		
		submit = new JButton("Submit");
		submit.setBounds(100, 350, 100, 30);
		submit.setBackground(Color.green);
		submit.addActionListener(this);
		con.add(submit);
		

		register = new JButton("Register");
		register.setBounds(100, 400, 250, 40);
		register.setBackground(Color.orange);
		register.addActionListener(this);
		con.add(register);

		all = new JButton("Details of All Students");
		all.setBounds(100, 450, 250, 40);
		all.setBackground(Color.pink);
		all.addActionListener(this);
		con.add(all);

		
		logout = new JButton("Logoff");
		logout.setBounds(250, 350, 100, 30);
		logout.setBackground(Color.red);
		logout.setForeground(Color.white);
		logout.addActionListener(this);
		con.add(logout);
		
		
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new InitialTab();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{

		Object source = arg0.getSource();
		
		if (source==submit)
		{
			if (rollnoField.getText().equals(""))
			{
				if (nameField.getText().equals("") || DOBField.getText().equals("")) 
				{

					msgLabel.setText("*Fill Atleast One Choice");
					msgLabel.setForeground(Color.red);	
				}
				else
				{
					try 
					{
						conn = DbUtil.getConnection();
						stmt = conn.createStatement();
						rs = stmt.executeQuery("select * from jiet_student where name = '"+nameField.getText()+"' and dob = '"+DOBField.getText()+"'");
						
						if(rs.next())
						{
							dispose();
							new Index(rs.getString("roll_number"));
						}
						else
						{
							msgLabel.setText("*Invalid name or DOB");
							msgLabel.setForeground(Color.red);	
							
						}
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					finally
					{
						DbUtil.closeAll(rs, stmt, conn);
					}
					
				}
				
			}
			else
			{
				try 
				{
					conn = DbUtil.getConnection();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from jiet_student where roll_number = '"+rollnoField.getText()+"'");
					
					if(rs.next())
					{
						dispose();
						new Index(rollnoField.getText());
					}
					else
					{
						msgLabel.setText("*Invalid Roll Number");
						msgLabel.setForeground(Color.red);	
						
					}
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				finally
				{
					DbUtil.closeAll(rs, stmt, conn);
				}
				
			}
		}
		
		else if(source == logout) 
		{
			new LogIn();
			dispose();
		}
		else if (source == register)
		{
			new Register();
			dispose();
		}
		else if (source==all)
		{
			new StudentManager();
			dispose();
		}
		
	}
}
