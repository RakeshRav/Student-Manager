package core;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class LogIn extends JFrame implements ActionListener
{
	JLabel logLabel,userLabel,passLabel,msgLabel;
	JTextField userField;
	JPasswordField passwordField;
	JButton loginButton,cancel,registerButton,forgotButton;
	
	public LogIn() 
	{
		setTitle("Log-In");
		setLocation(300, 100);
		setSize(400, 500);
		
		Container con = getContentPane();
		con.setLayout(null);
		con.setBackground(new Color(64, 128, 128));
	
		
		
		logLabel = new JLabel("LogIn");
		logLabel.setBounds(50, 50,200,50);
		logLabel.setFont(new Font("times new roman", 01, 30));
		logLabel.setForeground(Color.white);
		con.add(logLabel);
		
		msgLabel = new JLabel();
		msgLabel.setBounds(50, 130,300,20);
		msgLabel.setFont(new Font("times new roman", 01, 18));
		msgLabel.setForeground(Color.white);
		con.add(msgLabel);
		
		
		userLabel = new JLabel("Username  : ");
		userLabel.setBounds(50, 180, 80, 20);
		userLabel.setFont(new Font("Times New Roman", 0, 15));
		userLabel.setForeground(Color.white);
		con.add(userLabel);
		
		passLabel = new JLabel("Password  : ");
		passLabel.setBounds(50, 230, 80, 20);
		passLabel.setFont(new Font("Times New Roman", 0, 15));
		passLabel.setForeground(Color.white);
		con.add(passLabel);
		
		userField = new JTextField();
		userField.setBounds(150 , 180 , 150 , 20);
		userField.addActionListener(this);
		con.add(userField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150 , 230 , 150 , 20);
		passwordField.addActionListener(this);
		con.add(passwordField);
		
		loginButton = new JButton("LogIn");
		loginButton.setBounds(150, 270, 80 ,30);
		loginButton.setBackground(Color.green);
		loginButton.addActionListener(this);
		con.add(loginButton);
		
		
		cancel = new JButton("Cancel");
		cancel.setBounds(260, 270, 80 ,30);
		cancel.setBackground(Color.red);
		cancel.setForeground(Color.white);
		
		cancel.addActionListener(this);
		con.add(cancel);
		
		
		
		setVisible(true);
	}
	public static void main(String[] args) 
	{
		LogIn l = new LogIn();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Object source = arg0.getSource();
		String username = userField.getText();
		String password = passwordField.getText();
		
		if(source == registerButton)
		{
			dispose();
			new Register();
		}
		else if(source == cancel)
		{
			dispose();
			
		}
		else if(source == loginButton)
		{
			if(username.equals(""))
			{
				msgLabel.setText("*username is required");
				msgLabel.setForeground(Color.red);
			}
			else if(password.equals(""))
			{
				msgLabel.setText("*password is required");
				msgLabel.setForeground(Color.red);
			}
			else 
			{
				if(username.equals("rao") && password.equals("123"))
				{
					dispose();
					new InitialTab();
				}
				else
				{
					msgLabel.setText("*enter valid username and password");
					msgLabel.setForeground(Color.red);
				}
			}
		
		}
	}
}
