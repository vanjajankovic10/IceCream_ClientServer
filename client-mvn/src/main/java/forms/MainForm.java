package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Tehnolog;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame {

	private JPanel contentPane;
	private Tehnolog tehnolog;
	private JLabel tehn;


	public MainForm(Tehnolog t) {
		initComponents(t);
		
	}
	public void initComponents(Tehnolog t) {
		this.tehnolog = t;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tehnolog:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 27, 68, 23);
		contentPane.add(lblNewLabel);
		
		tehn = new JLabel("");
		tehn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tehn.setBounds(77, 27, 89, 23);
		tehn.setText(t.getName() + " " + t.getSurname());
		contentPane.add(tehn);
		
		JLabel lblNewLabel_2 = new JLabel("components");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(38, 71, 89, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("recipes");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(38, 184, 89, 23);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("Add new component");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddNewComponent(e);
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(63, 104, 151, 21);
		contentPane.add(btnNewButton);
		
		JButton btnSearchComponents = new JButton("Search components");
		btnSearchComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchComponents(e);
			}
		});
		btnSearchComponents.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearchComponents.setBounds(63, 135, 151, 21);
		contentPane.add(btnSearchComponents);
		
		JButton btnAddNewRecipe = new JButton("Add new recipe");
		btnAddNewRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddNewRecipe(e);
			}
		});
		btnAddNewRecipe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddNewRecipe.setBounds(63, 217, 151, 21);
		contentPane.add(btnAddNewRecipe);
		
		JButton btnSearchRecipes = new JButton("Search recipes");
		btnSearchRecipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchRecipes(e);
			}
		});
		btnSearchRecipes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearchRecipes.setBounds(63, 248, 151, 21);
		contentPane.add(btnSearchRecipes);
		
		
	}

	private void btnAddNewComponent(ActionEvent e) {
		 NewComponent nc = new NewComponent(this);
	     nc.setVisible(true);
		
	}
	
	private void btnAddNewRecipe(ActionEvent e) {
		 NewRecipe nr = new NewRecipe(this, tehnolog);
	        nr.setVisible(true);
		
	}
	
	private void btnSearchComponents(ActionEvent e) {
		 AllComponents ac = new AllComponents(this, tehnolog);
	        ac.setVisible(true);
		
	}
	
	private void btnSearchRecipes(ActionEvent e) {
		 try {
	            AllRecipes ar = new AllRecipes(this,tehnolog);
	            ar.setVisible(true);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		
	}
	
}
