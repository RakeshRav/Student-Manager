package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.DbUtil;

public class ViewAttd extends JFrame implements ActionListener
{
	Color frame = new Color(64,128,128);
	Container con ;
	String name,fname,r_number,DOB,sem,branch,branchCode;
	int total,midterm;
	
	ArrayList<Integer> marks = new ArrayList<Integer>();

	Connection conn;
	Statement stmt;
	ResultSet rs;
	JLabel subJLabel1Field,subJLabel2Field,subJLabel3Field,subJLabel4Field,subJLabel5Field,subJLabel6Field,totalj,totalval;
	JLabel subJLabel7Field,subJLabel8Field,subJLabel9Field,subJLabel10Field,subJLabel11Field,subJLabel12Field,totalval1;
	JButton edit1,edit2;
	JLabel head,msgJLabel,nameJLabel,fnameJLabel,rollJLabel,dobJLabel,nameval,fnameval,rollval,dobval,subJLabel1,subJLabel2,subJLabel3,subJLabel4,subJLabel5,subJLabel6;
	JPanel heading,body;
	JButton submitButton;

	
	public ViewAttd(String roll_number) {
		
		r_number = roll_number;
		getValues(roll_number);
	
		setSize(600,600);
		setLocation(300, 100);
		setTitle("Attendance");
		
		con =getContentPane();
		con.setBackground(frame);

		
		heading = new JPanel();
		heading.setBackground(Color.orange);
		con.add(heading,BorderLayout.NORTH);
		
		body = new JPanel();
		con.add(body);
		body.setBackground(frame);
		body.setLayout(null);
		
		head = new JLabel("Marks Of Student");
		head.setBounds(50, 20, 400, 50);
		head.setFont(new Font("times new roman", 1 , 30));
		heading.add(head);
		
		
		msgJLabel = new JLabel();
		msgJLabel.setBounds(150,90,300,50);
		msgJLabel.setFont(new Font("times new roman", 1, 25));
		body.add(msgJLabel);
		
		nameJLabel = new JLabel("Name");
		nameJLabel.setBounds(50, 20, 100, 20);
		nameJLabel.setForeground(Color.white);
		body.add(nameJLabel);
		
		rollJLabel = new JLabel("Roll Number");
		rollJLabel.setBounds(320, 20, 100, 20);
		rollJLabel.setForeground(Color.white);
		body.add(rollJLabel);
		
		dobJLabel = new JLabel("Date Of Birth");
		dobJLabel.setBounds(320, 50, 100, 20);
		dobJLabel.setForeground(Color.white);
		body.add(dobJLabel);
		
		
		fnameJLabel = new JLabel("Father Name");
		fnameJLabel.setBounds(50, 50, 100, 20);
		fnameJLabel.setForeground(Color.white);
		body.add(fnameJLabel);
		
		nameval = new JLabel(": "+name);
		nameval.setBounds(170, 20, 100, 20);
		nameval.setForeground(Color.white);
		body.add(nameval);
		
		rollval= new JLabel(": "+r_number);
		rollval.setBounds(450, 20, 100, 20);
		rollval.setForeground(Color.white);
		body.add(rollval);
		
		dobval = new JLabel(": "+DOB);
		dobval.setBounds(450, 50, 100, 20);
		dobval.setForeground(Color.white);
		body.add(dobval);
		
		
		fnameval = new JLabel(": "+fname);
		fnameval.setBounds(170, 50, 100, 20);
		fnameval.setForeground(Color.white);
		body.add(fnameval);
		
		submitButton = new JButton("Close");
		submitButton.setBounds(170, 450, 150, 40);
		submitButton.setBackground(Color.red);
		submitButton.setForeground(Color.white);
		submitButton.addActionListener(this);
		body.add(submitButton);
		
		
		setVisible(true);
	}

	private void getValues(String roll_number)
	{


		try 
		{
			conn = DbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select name,father_name,dob,sem,branch from jiet_student where roll_number = '"+roll_number+"'");
			
			if (rs.next())
			{
				name = rs.getString(1);
				fname = rs.getString(2);
				DOB = rs.getString(3);
				sem = rs.getString(4);
				branch = rs.getString(5);
				
				if (branch.equals("cse"))
				{
					branchCode="cs";
				}
				else if (branch.equals("ece"))
				{
					branchCode = "ec";
					
				}
				else if (branch.equals("me"))
				{
					branchCode = "me";
					
				}
				else if (branch.equals("civil"))
				{
					branchCode = "ce";
					
				}
				else if (branch.equals("ee"))
				{
					branchCode = "ee";
					
				}
				else if (branch.equals("it"))
				{
					branchCode = "it";
					
				}
			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(rs, stmt, conn);
		}

	}
	
	public static void main(String[] args) {
		new ViewAttd("1");
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		
		if (source== submitButton) 
		{
			dispose();
			new Index(r_number);
			
		}
	}

}
