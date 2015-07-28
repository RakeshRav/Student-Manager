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
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument.BranchElement;

import utility.DbUtil;

public class FillAttd extends JFrame implements ActionListener
{
	Color frame = new Color(64,128,128);
	Container con ;
	String name,fname,r_number,DOB,month,branch,sem,course;
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	JButton submit,cancel;
	JLabel head,nameJLabel,fnameJLabel,msgJLabel,rollJLabel,dobJLabel,nameval,fnameval,rollval,dobval,fill;
	JPanel heading,body;
	JTextField fillval;
	
	
	public FillAttd(String roll_number) 
	{
		r_number = roll_number;
		getValues(roll_number);
		
		
	
		setSize(600,400);
		setLocation(300, 100);
		setTitle("Fill Attendance");
		
		con =getContentPane();
		con.setBackground(frame);

		
		heading = new JPanel();
		heading.setBackground(Color.YELLOW);
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
	
		msgJLabel = new JLabel();
		msgJLabel.setBounds(100,210,300,50);
		msgJLabel.setFont(new Font("times new roman", 1, 25));
		body.add(msgJLabel);
	
		
		getMonth();
		boolean state = getStatus();
		
		if (state)
		{
			
		
		fill = new JLabel("Fill Attendance of "+month);
		fill.setBounds(100, 100, 200, 20);
		fill.setForeground(Color.white);
		body.add(fill);
		
		fillval = new JTextField();
		fillval.setBounds(300, 100, 70, 20);
		body.add(fillval);
		
		
		
		submit = new JButton("Submit");
		submit.setBounds(100, 150, 150, 40);
		submit.setBackground(Color.green);
		submit.addActionListener(this);
		body.add(submit);

		
		}
		else
		{
			msgJLabel.setText("*Attendance already Filled");
			msgJLabel.setForeground(Color.red);
		}
		
	
		
		cancel = new JButton("Cancel");
		cancel.setBounds(300, 150, 150, 40);
		cancel.setBackground(Color.red);
		cancel.setForeground(Color.white);
		cancel.addActionListener(this);
		body.add(cancel);

		
		setVisible(true);
		

	}
	
	private boolean getStatus() {
		
		// TODO Auto-generated method stub
		String cmonth;
		
		try {
			conn = DbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select month from jiet_attd where roll_number='"+r_number+"'");
			while(rs.next())
			{
				cmonth = rs.getString("month");
				if (month.equals(cmonth))
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

	private void getMonth()
	{
		Calendar c = Calendar.getInstance();
		int i = c.get(Calendar.MONTH);
		if (i==0)
		{
			month = "December";
		}
		else if (i==1)
		{
			month = "january";
		}
		else if (i==2)
		{
			month = "February";
		}
		else if (i==3)
		{
			month = "March";
		}
		else if (i==4)
		{
			month = "April";
		}
		else if (i==5)
		{
			month = "May";
		}
		else if (i==6)
		{
			month = "June";
		}
		else if (i==7)
		{
			month = "July";
		}
		else if (i==8)
		{
			month = "August";
		}
		else if (i==9)
		{
			month = "September";
		}
		else if (i==10)
		{
			month = "October";
		}
		else if (i==11)
		{
			month = "November";
		}
	}

	private void getValues(String roll_number)
	{
		try 
		{
			conn = DbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from jiet_student where roll_number = '"+roll_number+"'");
			
			if (rs.next())
			{
				name = rs.getString("name");
				fname = rs.getString("father_name");
				DOB = rs.getString("dob");
				branch = rs.getString("branch");
				course = rs.getString("course");
				sem = rs.getString("sem");
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
		new FillAttd("1");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		Object source = arg0.getSource();
		
		if (source==submit) 
		{
			if (fillval.getText().equals("")) 
			{
				msgJLabel.setText("*fill Attendence");
				msgJLabel.setBackground(Color.red);
			}
			else
			{
				try {
					conn = DbUtil.getConnection();
					stmt = conn.createStatement();
					stmt.executeUpdate("insert into jiet_attd values('"+r_number+"','"+name+"','"+DOB+"','"+branch+"','"+sem+"','"+month+"','"+fillval.getText()+"')");
					
					JOptionPane.showMessageDialog(null, "Attendence Successfully Filled", "Attendence Filled", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new Index(r_number);
				
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else if (source == cancel)
		{
			dispose();
			new Index(r_number);
				
		}
		
	}


}
