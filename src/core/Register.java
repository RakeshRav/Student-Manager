package core;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utility.DbUtil;


public class Register extends JFrame implements ActionListener
{
	JLabel headingLabel, nameLabel, fatherLabel,ageLabel,emailLabel,addressLabel,genderLabel,countryLabel,courseLabel,msgLabel,Branch,semester,age1Label;
	JTextField nameField,fatherField,emailField,ageField;
	JTextArea addressArea;
	JRadioButton maleButton,femaleButton;
	JButton registerButton, cancel;
	JComboBox courseBox,branch,sem;
	int id;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	
	Color frameColor = new Color(64, 128, 128);
	
	public Register() 
	{
		setTitle("Registration-Form");
		setSize(500,670);
		setLocation(300,50);
		
		Container con = getContentPane();
		con.setBackground(frameColor);
		con.setLayout(null);
		
		headingLabel = new JLabel("Registration-Form");
		headingLabel.setBounds(80, 20, 300,50);
		headingLabel.setFont(new Font("Times new roman", 01, 25));
		headingLabel.setForeground(Color.WHITE);
		con.add(headingLabel);
		
		nameLabel = new JLabel("Name");
		nameLabel.setBounds(50, 100, 80, 20);
		nameLabel.setFont(new Font("Times new roman", 0, 15));
		nameLabel.setForeground(Color.WHITE);
		con.add(nameLabel);
		
		fatherLabel = new JLabel("Father's Name");
		fatherLabel.setBounds(50, 150, 100, 20);
		fatherLabel.setFont(new Font("Times new roman", 0, 15));
		fatherLabel.setForeground(Color.WHITE);
		con.add(fatherLabel);
		
		
		ageLabel = new JLabel("Date Of Birth");
		ageLabel.setBounds(50, 200, 150, 20);
		ageLabel.setFont(new Font("Times new roman", 0, 15));
		ageLabel.setForeground(Color.WHITE);
		con.add(ageLabel);
		
		emailLabel = new JLabel("Email-address");
		emailLabel.setBounds(50, 250, 100, 20);
		emailLabel.setFont(new Font("Times new roman", 0, 15));
		emailLabel.setForeground(Color.WHITE);
		con.add(emailLabel);
		
		genderLabel = new JLabel("Gender");
		genderLabel.setBounds(50, 300, 80, 20);
		genderLabel.setFont(new Font("Times new roman", 0, 15));
		genderLabel.setForeground(Color.WHITE);
		con.add(genderLabel);
		
		
		courseLabel = new JLabel("Course");
		courseLabel.setBounds(50, 350, 80, 20);
		courseLabel.setFont(new Font("Times new roman", 0, 15));
		courseLabel.setForeground(Color.WHITE);
		con.add(courseLabel);
		
		Branch = new JLabel("Branch");
		Branch.setBounds(50, 400, 80, 20);
		Branch.setFont(new Font("Times new roman", 0, 15));
		Branch.setForeground(Color.WHITE);
		con.add(Branch);
		
		semester = new JLabel("Semester");
		semester.setBounds(50, 450, 80, 20);
		semester.setFont(new Font("Times new roman", 0, 15));
		semester.setForeground(Color.WHITE);
		con.add(semester);
		
		
		addressLabel = new JLabel("Address");
		addressLabel.setBounds(50, 500, 80, 20);
		addressLabel.setFont(new Font("Times new roman", 0, 15));
		addressLabel.setForeground(Color.WHITE);
		con.add(addressLabel);
		
		
		nameField = new JTextField();
		nameField.setBounds(180, 100, 130, 20);
		con.add(nameField);
		

		fatherField = new JTextField();
		fatherField.setBounds(180, 150, 130, 20);
		con.add(fatherField);
		
		msgLabel = new JLabel();
	
		con.add(msgLabel);
		
		ageField = new JTextField();
		ageField.setBounds(180, 200, 130, 20);
		con.add(ageField);


		age1Label = new JLabel("(DD/MM/YYYY)");
		age1Label.setBounds(320, 200, 150, 20);
		age1Label.setFont(new Font("Times new roman", 0, 15));
		age1Label.setForeground(Color.WHITE);
		con.add(age1Label);

		emailField = new JTextField();
		emailField.setBounds(180, 250, 130, 20);
		con.add(emailField);
		
		maleButton = new JRadioButton("Male");
		maleButton.setBounds(180, 300, 70, 20);
		maleButton.setBackground(frameColor);
		con.add(maleButton);
		

		femaleButton = new JRadioButton("Female");
		femaleButton.setBounds(250, 300, 70, 20);
		femaleButton.setBackground(frameColor);
		con.add(femaleButton);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(maleButton);
		bg.add(femaleButton);
		
		courseBox = new JComboBox();
		courseBox.setBounds(180 , 350 , 130 , 20);
		courseBox.addItem("--select--");
		courseBox.addItem("B.tech");
		courseBox.addItem("M.tech");
		con.add(courseBox);
		
		branch = new JComboBox();
		branch.setBounds(180, 400,130,20);
		branch.addItem("--select--");
		branch.addItem("CSE");
		branch.addItem("ECE");
		branch.addItem("EE");
		branch.addItem("ME");
		branch.addItem("IT");
		branch.addItem("CE");
		con.add(branch);
		
		sem = new JComboBox();
		sem.setBounds(180, 450, 130, 20);
		sem.addItem("--select--");
		sem.addItem("1");
		sem.addItem("2");
		sem.addItem("3");
		sem.addItem("4");
		sem.addItem("5");
		sem.addItem("6");
		sem.addItem("7");
		sem.addItem("8");
		con.add(sem);
		
		addressArea = new JTextArea();
		addressArea.setBounds(180, 500, 200 , 50);
		con.add(addressArea);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(80 , 580, 100, 30);
		registerButton.setBackground(Color.yellow);
		registerButton.addActionListener(this);
		con.add(registerButton);
		

		cancel = new JButton("Cancel");
		cancel.setBounds(210 , 580, 100, 30);
		cancel.setBackground(Color.red);
		cancel.setForeground(Color.white);
		cancel.addActionListener(this);
		con.add(cancel);
		
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		Register r = new Register();
		
	}
	
	public static void setEmpty() 
	{
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Object source = arg0.getSource();
		
		Color msgColor = new Color(233, 80, 75);
		
		
		
		if(source == cancel)
		{
			dispose();
			new InitialTab();
		}
		else if(source == registerButton)
		{
			if(nameField.getText().equals(""))
			{
				msgLabel.setText("*name field should not be empty");
				msgLabel.setBounds(180, 125, 200, 15);
				msgLabel.setForeground(msgColor);
			}
			else if(fatherField.getText().equals(""))
			{
				msgLabel.setText("*Father's name should not be empty");
				msgLabel.setBounds(180, 175, 250, 15);
				msgLabel.setForeground(msgColor);
			}
			else if(ageField.getText().equals(""))
			{
				msgLabel.setText("*DOB field should not be empty");
				msgLabel.setBounds(180, 225, 200, 15);
				msgLabel.setForeground(msgColor);
			}
			else if(emailField.getText().equals(""))
			{
				msgLabel.setText("*Email field should not be empty");
				msgLabel.setBounds(180, 275, 200, 15);
				msgLabel.setForeground(msgColor);
			}
			else if(!(maleButton.isSelected() || femaleButton.isSelected()))
			{
				
				msgLabel.setText("*select gender");
				msgLabel.setBounds(180, 325, 200, 15);
				msgLabel.setForeground(msgColor);
			}
			else if (courseBox.getSelectedIndex()==0)
			{
				msgLabel.setText("*Select a course");
				msgLabel.setBounds(180, 375, 200, 15);
				msgLabel.setForeground(msgColor);	
			}
			else if (branch.getSelectedIndex()==0)
			{
				msgLabel.setText("*Select a Branch");
				msgLabel.setBounds(180, 425, 200, 15);
				msgLabel.setForeground(msgColor);	
			}
			else if (sem.getSelectedIndex()==0)
			{
				msgLabel.setText("*Select Semester");
				msgLabel.setBounds(180, 475, 200, 15);
				msgLabel.setForeground(msgColor);	
			}
			
			else if(addressArea.getText().equals(""))
			{
				msgLabel.setText("*address field should not be empty");
				msgLabel.setBounds(180, 555, 200, 15);
				msgLabel.setForeground(msgColor);
		
			}else
			{
				String gender = "male";
				if(!maleButton.isSelected())
				{
					gender = "female";
				}
				String course ="",branchCode="",sems="";
				int index;
				
				index = courseBox.getSelectedIndex();
				
				if(index==1)
				{
					course="B.tech";
				}
				else if(index==2)
				{
					course="M.tech";
				}
				
				index = branch.getSelectedIndex();
				
				if (index==1)
				{
					branchCode = "cse";
				}
				else if (index==2)
				{
					branchCode = "ece";
				}
				else if (index==3)
				{
					branchCode = "ee";
				}
				else if (index==4)
				{
					branchCode = "me";
				}
				else if (index==5)
				{
					branchCode = "it";
				}
				else if (index==6)
				{
					branchCode = "ce";
				}
				
				index = sem.getSelectedIndex();
				
				if (index == 1)
				{
					sems = "1";
				}
				else if (index == 2)
				{
					sems = "2";
				}
				else if (index == 3)
				{
					sems = "3";
				}
				else if (index == 4)
				{
					sems = "4";
				}
				else if (index == 5)
				{
					sems = "5";
				}
				else if (index == 6)
				{
					sems = "6";
				}
				else if (index == 7)
				{
					sems = "7";
				}
				else if (index == 8)
				{
					sems = "8";
				}
					
				try {
					conn = DbUtil.getConnection();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select max(roll_number) from jiet_student");
					if(rs.next())
					{
						id = rs.getInt(1);
						id++;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally
				{
					DbUtil.closeAll(rs, stmt, conn);
				}
				
				
				
				
				try
				{
					
					conn = DbUtil.getConnection();
					stmt = conn.createStatement();
					stmt.executeUpdate("insert into jiet_student values('"+id+"','"+nameField.getText()+"','"+fatherField.getText()+"','"+ageField.getText()+"','"+addressArea.getText()+"','"+ gender+"','"+emailField.getText()+"','"+course+"','"+branchCode+"','"+sems+"')");
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				finally
				{
					DbUtil.closeAll(rs, stmt, conn);
					JOptionPane op = new JOptionPane();
					op.showMessageDialog(this, "Done", "registration-successful", JOptionPane.INFORMATION_MESSAGE);
					int choice = JOptionPane.OK_OPTION;
					
				if(choice == JOptionPane.OK_OPTION)
					{
						nameField.setText("");
						fatherField.setText("");
						ageField.setText("");
						emailField.setText("");
						addressArea.setText("");
						maleButton.setSelected(false);
						femaleButton.setSelected(false);
						courseBox.setSelectedIndex(0);
						branch.setSelectedIndex(0);
						sem.setSelectedIndex(0);
						
						
					}
					
				}
				
				dispose();
				new InitialTab();
				
				
			}
		}
	}
}


			
			
		
	
		
		
	
