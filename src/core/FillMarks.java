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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utility.DbUtil;

public class FillMarks extends JFrame implements ActionListener
{
	Color frame = new Color(64,128,128);
	Container con ;
	String name,fname,r_number,DOB,sem,branch,branchCode;
	int s1,s2,s3,s4,s5,s6,total;
	int midterm;
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	JTextField subJLabel1Field,subJLabel2Field,subJLabel3Field,subJLabel4Field,subJLabel5Field,subJLabel6Field;
	JButton update;
	JLabel head,msgJLabel,nameJLabel,fnameJLabel,rollJLabel,dobJLabel,nameval,fnameval,rollval,dobval,subJLabel1,subJLabel2,subJLabel3,subJLabel4,subJLabel5,subJLabel6;
	JPanel heading,body;
	JButton submitButton,cancel;
	
	
	public FillMarks(String roll_number) 
	{
		r_number = roll_number;
		getValues(roll_number,0);
	
		setSize(600,600);
		setLocation(300, 100);
		setTitle("Fill Marks");
		
		con =getContentPane();
		con.setBackground(frame);

		
		heading = new JPanel();
		heading.setBackground(Color.orange);
		con.add(heading,BorderLayout.NORTH);
		
		body = new JPanel();
		con.add(body);
		body.setBackground(frame);
		body.setLayout(null);
		
		head = new JLabel("Fill Marks Of Student");
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
		
		cancel = new JButton("Cancel");
		cancel.setBounds(200, 400, 150, 40);
		cancel.setBackground(Color.red);
		cancel.setForeground(Color.white);
		cancel.addActionListener(this);
		body.add(cancel);
		
		
		boolean status = getStatus(roll_number); 
		
		if (status)
		{
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
			
			subJLabel1Field = new JTextField();
			subJLabel1Field.setBounds(300, 150, 100, 25);
			body.add(subJLabel1Field);
			

			subJLabel2Field = new JTextField();
			subJLabel2Field.setBounds(300, 190, 100, 25);
			body.add(subJLabel2Field);
			

			subJLabel3Field = new JTextField();
			subJLabel3Field.setBounds(300, 230, 100, 25);
			body.add(subJLabel3Field);

			subJLabel4Field = new JTextField();
			subJLabel4Field.setBounds(300, 270, 100, 25);
			body.add(subJLabel4Field);

			subJLabel5Field = new JTextField();
			subJLabel5Field.setBounds(300, 310, 100, 25);
			body.add(subJLabel5Field);

			subJLabel6Field = new JTextField();
			subJLabel6Field.setBounds(300, 350, 100, 25);
			body.add(subJLabel6Field);
			
			
			submitButton = new JButton("Submit");
			submitButton.setBounds(170, 400, 150, 40);
			submitButton.setBackground(Color.green);
			submitButton.addActionListener(this);
			body.add(submitButton);
			
			cancel.setBounds(350, 400, 150, 40);
			
			if (midterm==1) 
			{
				msgJLabel.setText("Enter II Midterm Marks");
				msgJLabel.setForeground(Color.green);
			}
			else if (midterm==0)
			{
				msgJLabel.setText("Enter I Midterm Marks");
				msgJLabel.setForeground(Color.green);
			}

		}
		else
		{
			msgJLabel.setText("Marks Already Filled");
			msgJLabel.setForeground(Color.red);
		}
		
		
		setVisible(true);

	
	}
	
	
	private boolean getStatus(String roll_number) 
	{
		try {
			conn = DbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select max(midterm) from jiet_marks where roll_number='"+roll_number+"'");
			
			if(rs.next())
			{
				midterm = rs.getInt(1);
				if (midterm==2)
				{
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(rs, stmt, conn);
		}
		return true;
	}


	private void getValues(String roll_number,int midterms)
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
		
		if(midterms>0)
		{
			try {
				conn = DbUtil.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from jiet_marks where roll_number = '"+roll_number+"' and midterm = "+midterms);
				
				if (rs.next()) 
				{
					s1 = rs.getInt("sub1");
					s2 = rs.getInt("sub2");
					s3 = rs.getInt("sub3");
					s4 = rs.getInt("sub4");
					s5 = rs.getInt("sub5");
					s6 = rs.getInt("sub6");
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
	}
	
	public FillMarks(String roll_number,int midterm_no)
	{
		r_number = roll_number;
		midterm = midterm_no;
		getValues(roll_number,midterm_no);
	
		setSize(600,600);
		setLocation(300, 100);
		setTitle("Update Marks");
		
		con =getContentPane();
		con.setBackground(frame);

		
		heading = new JPanel();
		heading.setBackground(Color.orange);
		con.add(heading,BorderLayout.NORTH);
		
		body = new JPanel();
		con.add(body);
		body.setBackground(frame);
		body.setLayout(null);
		
		head = new JLabel("Update Marks Of Student");
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
		
		subJLabel1Field = new JTextField();
		subJLabel1Field.setBounds(300, 150, 100, 25);
		subJLabel1Field.setText(s1+"");
		body.add(subJLabel1Field);
		

		subJLabel2Field = new JTextField();
		subJLabel2Field.setBounds(300, 190, 100, 25);
		
		subJLabel2Field.setText(s2+"");
		body.add(subJLabel2Field);
		

		subJLabel3Field = new JTextField();
		subJLabel3Field.setBounds(300, 230, 100, 25);
		subJLabel3Field.setText(s3+"");
		body.add(subJLabel3Field);

		subJLabel4Field = new JTextField();
		subJLabel4Field.setBounds(300, 270, 100, 25);
		subJLabel4Field.setText(s4+"");
		body.add(subJLabel4Field);

		subJLabel5Field = new JTextField();
		subJLabel5Field.setBounds(300, 310, 100, 25);
		subJLabel5Field.setText(s5+"");
		body.add(subJLabel5Field);

		subJLabel6Field = new JTextField();
		subJLabel6Field.setBounds(300, 350, 100, 25);
		subJLabel6Field.setText(s6+"");
		body.add(subJLabel6Field);
		
		
		update = new JButton("Update");
		update.setBounds(170, 400, 150, 40);
		update.setBackground(Color.green);
		update.addActionListener(this);
		body.add(update);

		
		
		cancel = new JButton("Cancel");
		cancel.setBounds(340, 400, 150, 40);
		cancel.setBackground(Color.red);
		cancel.setForeground(Color.white);
		cancel.addActionListener(this);
		body.add(cancel);
		
		setVisible(true);

	}
	
	public static void main(String[] args) {
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Object source = arg0.getSource();
		
		if (source == submitButton) 
		{
			if (subJLabel1Field.getText().equals("") || subJLabel2Field.getText().equals("") || subJLabel3Field.getText().equals("") || subJLabel4Field.getText().equals("") || subJLabel5Field.getText().equals("") || subJLabel6Field.getText().equals("")) 
			{

				msgJLabel.setText("*fill all the fields");
				msgJLabel.setForeground(Color.red);
				
			}
			else
			{
				s1 = Integer.parseInt(subJLabel1Field.getText());
				s2 = Integer.parseInt(subJLabel2Field.getText());
				s3 = Integer.parseInt(subJLabel3Field.getText());
				s4 = Integer.parseInt(subJLabel4Field.getText());
				s5 = Integer.parseInt(subJLabel5Field.getText());
				s6 = Integer.parseInt(subJLabel6Field.getText());
				
				total = s1+s2+s3+s4+s5+s6;
				
				try {
					conn = DbUtil.getConnection();
					stmt = conn.createStatement();
					stmt.executeUpdate("insert into jiet_marks values('"+r_number+"','"+name+"','"+DOB+"','"+branch+"','"+sem+"',"+s1+","+s2+","+s3+","+s4+","+s5+","+s6+","+total+","+(++midterm)+")");
					JOptionPane.showMessageDialog(null, "Marks Successfully Filled", "Marks Filled", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new Index(r_number);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					DbUtil.closeAll(rs, stmt, conn);
				}
				
			}
			
		}
		else if (source == cancel) 
		{
			dispose();
			new Index(r_number);
		}
		else if (source == update) 
		{
			if (subJLabel1Field.getText().equals("") || subJLabel2Field.getText().equals("") || subJLabel3Field.getText().equals("") || subJLabel4Field.getText().equals("") || subJLabel5Field.getText().equals("") || subJLabel6Field.getText().equals("")) 
			{

				msgJLabel.setText("*fill all the fields");
				msgJLabel.setForeground(Color.red);
				
			}
			else
			{
				s1 = Integer.parseInt(subJLabel1Field.getText());
				s2 = Integer.parseInt(subJLabel2Field.getText());
				s3 = Integer.parseInt(subJLabel3Field.getText());
				s4 = Integer.parseInt(subJLabel4Field.getText());
				s5 = Integer.parseInt(subJLabel5Field.getText());
				s6 = Integer.parseInt(subJLabel6Field.getText());
				
				total = s1+s2+s3+s4+s5+s6;
				
				try {
					conn = DbUtil.getConnection();
					stmt = conn.createStatement();
					stmt.executeUpdate("update jiet_marks set sub1="+s1+",sub2="+s2+",sub3="+s3+",sub4="+s4+",sub5="+s5+",sub6="+s6+",total="+total+" where roll_number='"+r_number+"' and midterm = "+midterm+"");
					JOptionPane.showMessageDialog(null, "Marks Successfully Updated", "Marks Updated", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new Index(r_number);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					DbUtil.closeAll(rs, stmt, conn);
				}
				
			}

		}
	}


}
