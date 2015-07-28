package core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import utility.DbUtil;


public class StudentManager extends JFrame implements MouseListener,KeyListener,FocusListener,ActionListener
{
	
	Container con;
	int maxid;
	
	Color frame = new Color(64,128,128);
	
	
	JPanel detailPanel,headPanel,tabPanel,searchPanel;
	JTable tab1;
	JScrollPane sp;
	JLabel headLabel,totalR,mainLabel,fatherLabel,ageLabel,nameLabel,addressLabel,emailLabel,genderLabel,courseLabel,rollJLabel,branchJLabel,semJLabel;
	JLabel nameField,addressArea,fnamefield,agefield,emailField,courseField,gender,roll,branch,sem;
	JTextField searchField;
	
	JButton Close;
	
	
	String data[][]={};
	
	
	String heading[]={"Roll No.","Name","Father's Name","Date Of Birth"};
	
 
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	
	
	
	public StudentManager() 
	{
		setTitle("Student Manager");
		setSize(new Dimension(getMaximumSize()));
		
		con= getContentPane();
		con.setBackground(frame);
		
		headPanel = new JPanel();
		headPanel.setBackground(new Color(139,245,201));
		con.add(headPanel,BorderLayout.NORTH);
		
		headLabel = new JLabel("Student Manager");
		headLabel.setFont(new Font("Times new roman", 1, 30));
		headPanel.add(headLabel);
		
		
		detailPanel = new JPanel();
		detailPanel.setBackground(frame);
		con.add(detailPanel);
		detailPanel.setLayout(null);
		
		tabPanel=new JPanel();
		con.add(tabPanel,BorderLayout.WEST);
		//tabPanel.setBackground(frame);
		tabPanel.setLayout(new BorderLayout());
		
		
		nameLabel = new JLabel("Name");
		detailPanel.add(nameLabel);
		
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		searchPanel.setBackground(Color.orange);
		tabPanel.add(searchPanel,BorderLayout.NORTH);
		
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(200, 30));
		searchField.setText("Enter A Keyword To Search");
		searchField.setForeground(Color.gray);
		searchField.addFocusListener(this);
		searchField.addKeyListener(this);
		searchPanel.add(searchField);
		
		totalR = new JLabel("Total Records = 0");
		searchPanel.add(totalR);

		
		mainLabel = new JLabel("Student-Details");
		mainLabel.setBounds(100,10,150,20);
		detailPanel.add(mainLabel);
		
		rollJLabel = new JLabel("Roll Number");
		detailPanel.add(rollJLabel);

		fatherLabel = new JLabel("Father's Name");
		detailPanel.add(fatherLabel);
		

		ageLabel = new JLabel("Date of birth");
		detailPanel.add(ageLabel);

		
		genderLabel = new JLabel("Gender");
		detailPanel.add(genderLabel);
		
		emailLabel = new JLabel("Email");
		detailPanel.add(emailLabel);

		courseLabel = new JLabel("Course");
		detailPanel.add(courseLabel);
		
		branchJLabel = new JLabel("Branch");
		detailPanel.add(branchJLabel);

		semJLabel = new JLabel("Semester");
		detailPanel.add(semJLabel);


		addressLabel = new JLabel("Address");
		detailPanel.add(addressLabel);


		roll = new JLabel(); 
		roll.setBounds(150,50,150,20);
		detailPanel.add(roll);
		
		nameField = new JLabel();
		nameField.setBounds(150,90,150,20);
		detailPanel.add(nameField);

		fnamefield = new JLabel();
		fnamefield.setBounds(150,130,150,20);
		detailPanel.add(fnamefield);
		
		agefield = new JLabel(); 
		agefield.setBounds(150,170,150,20);
		detailPanel.add(agefield);
	
		gender = new JLabel(); 
		gender.setBounds(150,210,150,20);
		detailPanel.add(gender);
	
	

	
		emailField = new JLabel();
		emailField.setBounds(150,250,200,20);
		detailPanel.add(emailField);
		
		courseField = new JLabel();
		courseField.setBounds(150,290,150,20);
		detailPanel.add(courseField);

		
		branch = new JLabel(); 
		branch.setBounds(150,330,150,20);
		detailPanel.add(branch);

		sem = new JLabel(); 
		sem.setBounds(150,370,150,20);
		detailPanel.add(sem);
		
		addressArea = new JLabel();
		addressArea.setBounds(150,410,500,20);
		detailPanel.add(addressArea);
		
		Close = new JButton("Close");
		Close.setBounds(250, 500, 100, 30);
		Close.setBackground(Color.red);
		Close.setForeground(Color.white);
		Close.addActionListener(this);
		detailPanel.add(Close);
		
		
		setTable("select roll_number,name,father_name,dob from jiet_student");
		
		setVisible(true);
	}
	
	
	
	public void setTable(String sql)
	{
		
		
		if(sp!=null)
		{
			tabPanel.remove(sp);
		}
		int records = 0;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int rows = 0;
		try 
		{
			conn = DbUtil.getConnection();
			st= conn.createStatement();
			rs= st.executeQuery("select count(name) from jiet_student");
			if(rs.next())
			{
				rows = rs.getInt(1);
			}
			
			
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(rs, st, conn);
						
		}
		
		data = new String[rows][4];
		
		
		try
		{
			conn = DbUtil.getConnection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			
			for(int i=0;rs.next();i++)
			{
				records++;
				data[i][0]= rs.getString("roll_number");
				data[i][1]= rs.getString("name");
				data[i][2]= rs.getString("father_name");
				data[i][3]= rs.getString("dob");
			
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(rs, st, conn);
		}
		
		
		
		tab1 = new JTable(data,heading);
		sp = new JScrollPane(tab1);
		tab1.addMouseListener(this);
		totalR.setText("Total records = "+records);
		tabPanel.add(sp);
		tabPanel.revalidate();
		
}
		
		




	public static void main(String[] args) 
	{
		new StudentManager();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
		Object source = arg0.getSource();
		
		
		
		if(source == tab1)
		{
			int index;
			index = tab1.getSelectedRow();
			String roll;
			roll = data[index][0];
			
			try 
			{
				conn = DbUtil.getConnection();
				st= conn.createStatement();
				rs= st.executeQuery("select * from jiet_student where roll_number='"+roll+"'");
				
				if(rs.next())
				{
					
					rollJLabel.setBounds(50,50,100,20);
					
					nameLabel.setBounds(50,90,100,20);
					
					fatherLabel.setBounds(50,130,100,20);
					
					ageLabel.setBounds(50,170,100,20);

					genderLabel.setBounds(50,210,100,20);
					
					emailLabel.setBounds(50,250,100,20);

					courseLabel.setBounds(50,290,100,20);

					branchJLabel.setBounds(50,330,100,20);
					
					semJLabel.setBounds(50,370,100,20);
					
					addressLabel.setBounds(50,410,100,10);	
					
					
					this.roll.setText(rs.getString("roll_number"));
					
					nameField.setText(rs.getString("name"));
					
					fnamefield.setText(rs.getString("father_name"));
					
					emailField.setText(rs.getString("email"));
					
					agefield.setText(rs.getString("dob"));
					
					branch.setText(rs.getString("branch"));
					
					courseField.setText(rs.getString("course"));
					
					sem.setText(rs.getString("sem"));
					
					addressArea.setText(rs.getString("address"));
					
					gender.setText(rs.getString("gender"));
							
					detailPanel.repaint();

				}
				
				
				
				
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			finally
			{
				DbUtil.closeAll(rs, st, conn);
							
			}
					

			
		}		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		
		
		
	}



	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		Object source = arg0.getSource();
		if(source == searchField)
		{
			
			String search;
			search = searchField.getText();
			setTable("select * from jiet_student where name like '" + search + "%'");
			
		}
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void focusGained(FocusEvent arg0)
	{
		Object source = arg0.getSource();
		if(source == searchField)
		{
			searchField.setText("");
		}
	}



	@Override
	public void focusLost(FocusEvent arg0) 
	{
		Object source = arg0.getSource();
		if(source == searchField)
		{
			searchField.setText("Enter A keyword To Search");
			searchField.setForeground(Color.GRAY);
		}
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		Object source = arg0.getSource();
		
		if (source==Close) {
			new InitialTab();
			dispose();
			
		}
		
	}
	
}
