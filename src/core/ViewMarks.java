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
import javax.swing.JTextField;

import utility.DbUtil;

public class ViewMarks extends JFrame implements ActionListener
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
	
	

	
	public ViewMarks(String roll_number)
	{		
		
			r_number = roll_number;
			getValues(roll_number);
		
			setSize(600,600);
			setLocation(300, 100);
			setTitle("Marks");
			
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
			
			subJLabel1 = new JLabel(sem+branchCode+1);
			subJLabel1.setBounds(170, 150, 100, 20);
			subJLabel1.setForeground(Color.white);
			body.add(subJLabel1);
			
			subJLabel2 = new JLabel(sem+branchCode+2);
			subJLabel2.setBounds(170, 190, 100, 20);
			subJLabel2.setForeground(Color.white);
			body.add(subJLabel2);
			
			subJLabel3 = new JLabel(sem+branchCode+3);
			subJLabel3.setBounds(170, 230, 100, 20);
			subJLabel3.setForeground(Color.white);
			body.add(subJLabel3);
			
			subJLabel4 = new JLabel(sem+branchCode+4);
			subJLabel4.setBounds(170, 270, 100, 20);
			subJLabel4.setForeground(Color.white);
			body.add(subJLabel4);
			
			subJLabel5 = new JLabel(sem+branchCode+5);
			subJLabel5.setBounds(170, 310, 100, 20);
			subJLabel5.setForeground(Color.white);
			body.add(subJLabel5);
			
			subJLabel6 = new JLabel(sem+branchCode+6);
			subJLabel6.setBounds(170, 350, 100, 20);
			subJLabel6.setForeground(Color.white);
			body.add(subJLabel6);
			
			subJLabel1Field = new JLabel(marks.get(0)+"");
			subJLabel1Field.setBounds(300, 150, 100, 25);
			body.add(subJLabel1Field);
			

			subJLabel2Field = new JLabel(marks.get(1)+"");
			subJLabel2Field.setBounds(300, 190, 100, 25);
			body.add(subJLabel2Field);
			

			subJLabel3Field = new JLabel(marks.get(2)+"");
			subJLabel3Field.setBounds(300, 230, 100, 25);
			body.add(subJLabel3Field);

			subJLabel4Field = new JLabel(marks.get(3)+"");
			subJLabel4Field.setBounds(300, 270, 100, 25);
			body.add(subJLabel4Field);

			subJLabel5Field = new JLabel(marks.get(4)+"");
			subJLabel5Field.setBounds(300, 310, 100, 25);
			body.add(subJLabel5Field);

			subJLabel6Field = new JLabel(marks.get(5)+"");
			subJLabel6Field.setBounds(300, 350, 100, 25);
			body.add(subJLabel6Field);
			

			totalj = new JLabel("Total");
			totalj.setBounds(170, 390, 100, 25);
			totalj.setForeground(Color.white);
			body.add(totalj);
			
			totalval = new JLabel(marks.get(6)+"");
			totalval.setBounds(300, 390, 100, 25);
			body.add(totalval);
			

			
			if(midterm==2)
			{
				subJLabel7Field = new JLabel(marks.get(7)+"");
				subJLabel7Field.setBounds(400, 150, 100, 25);
				body.add(subJLabel7Field);
				

				subJLabel8Field = new JLabel(marks.get(8)+"");
				subJLabel8Field.setBounds(400, 190, 100, 25);
				body.add(subJLabel8Field);
				

				subJLabel9Field = new JLabel(marks.get(9)+"");
				subJLabel9Field.setBounds(400, 230, 100, 25);
				body.add(subJLabel9Field);

				subJLabel10Field = new JLabel(marks.get(10)+"");
				subJLabel10Field.setBounds(400, 270, 100, 25);
				body.add(subJLabel10Field);

				subJLabel11Field = new JLabel(marks.get(11)+"");
				subJLabel11Field.setBounds(400, 310, 100, 25);
				body.add(subJLabel11Field);

				subJLabel12Field = new JLabel(marks.get(12)+"");
				subJLabel12Field.setBounds(400, 350, 100, 25);
				body.add(subJLabel12Field);
				
				totalval1 = new JLabel(marks.get(13)+"");
				totalval1.setBounds(400, 390, 100, 25);
				body.add(totalval1);
				
				edit2 = new JButton("Edit");
				edit2.setBounds(370, 110, 70, 30);
				edit2.setBackground(Color.orange);
				edit2.addActionListener(this);
				body.add(edit2);
			
				

			}
						
			submitButton = new JButton("Close");
			submitButton.setBounds(170, 450, 150, 40);
			submitButton.setBackground(Color.green);
			submitButton.addActionListener(this);
			body.add(submitButton);
			
			edit1 = new JButton("Edit");
			edit1.setBounds(270	, 110, 70, 30);
			edit1.setBackground(Color.orange);
			edit1.addActionListener(this);
			body.add(edit1);
			
			
			
			
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
		
		try 
		{
			conn = DbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from jiet_marks where roll_number = '"+roll_number+"'");
			while(rs.next())
			{
				marks.add(rs.getInt("sub1"));
				marks.add(rs.getInt("sub2"));
				marks.add(rs.getInt("sub3"));
				marks.add(rs.getInt("sub4"));
				marks.add(rs.getInt("sub5"));
				marks.add(rs.getInt("sub6"));
				marks.add(rs.getInt("total"));
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
		
		try 
		{
			conn = DbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select max(midterm) from jiet_marks where roll_number = '"+roll_number+"'");
			if(rs.next())
			{
				midterm = rs.getInt(1);
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
		new ViewMarks("1");
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if(source==submitButton)
		{
			dispose();
			new Index(r_number);
		}
		else if (source == edit1) 
		{
			dispose();
			new FillMarks(r_number,1);
		}
		else if (source == edit2)
		{
			dispose();
			new FillMarks(r_number,2);
		}
		
	}


}
