package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utility.DbUtil;

public class Index extends JFrame implements ActionListener
{
	
	Color frame = new Color(64,128,128);
	Container con ;
	String name,fname,r_number,DOB;
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	JButton viewMarks,viewAttd,fillMarks,fillAttd,Back;
	JLabel head,nameJLabel,fnameJLabel,rollJLabel,dobJLabel,nameval,fnameval,rollval,dobval;
	JPanel heading,body;
	
	
	
	public Index(String roll_number) 
	{
		r_number = roll_number;
			getValues(roll_number);
		
			setSize(600,400);
			setLocation(300, 100);
			setTitle("Index");
			
			con =getContentPane();
			con.setBackground(frame);

			
			heading = new JPanel();
			heading.setBackground(Color.lightGray);
			con.add(heading,BorderLayout.NORTH);
			
			body = new JPanel();
			con.add(body);
			body.setBackground(frame);
			body.setLayout(null);
			
			head = new JLabel("Student Details Management");
			head.setBounds(50, 20, 400, 50);
			head.setFont(new Font("times new roman", 1 , 30));
			heading.add(head);
			
			
			
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
			
			
			fillMarks = new JButton("Fill Marks");
			fillMarks.setBounds(100,100,150,50);
			fillMarks.setBackground(Color.ORANGE);
			fillMarks.addActionListener(this);
			body.add(fillMarks);
			
			fillAttd = new JButton("Fill Attendance");
			fillAttd.setBounds(350,100,150,50);
			fillAttd.setBackground(Color.ORANGE);
			fillAttd.addActionListener(this);
			body.add(fillAttd);
			
			viewMarks = new JButton("View Marks");
			viewMarks.setBounds(100,200,150,50);
			viewMarks.setBackground(Color.ORANGE);
			viewMarks.addActionListener(this);
			body.add(viewMarks);
			
			
			
			viewAttd = new JButton("View Attandence");
			viewAttd.setBounds(350,200,150,50);
			viewAttd.setBackground(Color.ORANGE);
			viewAttd.addActionListener(this);
			body.add(viewAttd);
	
			Back = new JButton("Back");
			Back.setBounds(230,265,150,50);
			Back.setBackground(Color.red);
			Back.setForeground(Color.white);
			Back.addActionListener(this);
			body.add(Back);
	
			setVisible(true);
	}
	
	private void getValues(String roll_number)
	{
		try 
		{
			conn = DbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select name,father_name,dob from jiet_student where roll_number = '"+roll_number+"'");
			
			if (rs.next())
			{
				name = rs.getString(1);
				fname = rs.getString(2);
				DOB = rs.getString(3);
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
		new Index("1");
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		
		if (source==fillAttd) 
		{
			dispose();
			new FillAttd(r_number);
		}
		else if (source==fillMarks) 
		{
			dispose();
			new FillMarks(r_number);
		}
		else if (source==viewAttd) 
		{

			try {
				conn = DbUtil.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from jiet_attd where roll_number = '"+r_number+"'");
				
				if (rs.next())
				{

					dispose();
					new ViewAttd(r_number);
				}
				else {
					JOptionPane.showMessageDialog(null, "Attendance Not Filled", "No Data Found", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				DbUtil.closeAll(rs, stmt, conn);
			}

		}
		else if (source==viewMarks) 
		{
			
			try {
				conn = DbUtil.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from jiet_marks where roll_number = '"+r_number+"'");
				
				if (rs.next())
				{

					dispose();
					new ViewMarks(r_number);
				}
				else {
					JOptionPane.showMessageDialog(null, "Marks Not Filled", "No Data Found", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				DbUtil.closeAll(rs, stmt, conn);
			}
			
		}
		
		else if (source==Back) 
		{
			dispose();
			new InitialTab();
		}
	}
}
