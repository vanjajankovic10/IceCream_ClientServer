package forms;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ClientController;
import domain.DomainObject;
import domain.Recipe;
import domain.Tehnolog;
import models.*;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AllRecipes extends JFrame {

	JFrame mainForm;
    TMRecipe tm;
    List<DomainObject> recipes;
    Tehnolog t;
    
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("ALL RECIPES");
	private JTextField txtFilter;
	private JTable tblAllRecipes;


	public AllRecipes(JFrame mf, Tehnolog t) throws Exception {
        initComponents();
        mainForm = mf;
        this.t = t;
        prepareForm();
    }
	
	 private void prepareForm() throws Exception {
	        recipes = ClientController.getInstance().getRecipes();
	        tm = new TMRecipe(recipes);
	        tblAllRecipes.setModel(tm);
	    }
	 
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchActionPerformed(e);
			}
		});
		btnSearch.setBounds(355, 57, 85, 21);
		contentPane.add(btnSearch);
		
		txtFilter = new JTextField();
		txtFilter.setBounds(27, 57, 318, 19);
		contentPane.add(txtFilter);
		txtFilter.setColumns(10);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(171, 10, 120, 21);
		contentPane.add(lblNewLabel);
		
		tblAllRecipes = new JTable();
		tblAllRecipes.setBounds(27, 107, 318, 161);
		contentPane.add(tblAllRecipes);
		
		JButton btnAdd = new JButton("Add new");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddRecipeActionPerformed(e);
			}
		});
		btnAdd.setBounds(355, 118, 85, 21);
		contentPane.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 btnEditActionPerformed(e);
			}
		});
		btnEdit.setBounds(355, 149, 85, 21);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteActionPerformed(e);
			}
		});
		btnDelete.setBounds(355, 180, 85, 21);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelActionPerformed(e);
			}
		});
		btnCancel.setBounds(355, 306, 85, 21);
		contentPane.add(btnCancel);
	}

	 private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
	        dispose();
	    }                                         

	    private void btnAddRecipeActionPerformed(java.awt.event.ActionEvent evt) {                                             
	        NewRecipe rf = new NewRecipe(this, t);
	        rf.setVisible(true);
	        this.setVisible(false);
	    }                                            

	    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {                                          
	        try {
	            String filter = txtFilter.getText().trim();
	            List<DomainObject> filtered = ClientController.getInstance().findRecipes(filter);
	            if(filtered.isEmpty()){
	                JOptionPane.showMessageDialog(rootPane, "System can't find recipes " , "Error", JOptionPane.INFORMATION_MESSAGE);
	            }
	            else{
	                tm = new TMRecipe(filtered);
	                tblAllRecipes.setModel(tm);
	            }
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        
	    }                                         

	    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                          
	        int row = tblAllRecipes.getSelectedRow();
	        if(row == -1){
	            JOptionPane.showMessageDialog(rootPane, "Select recipe you want to delete", "Error", JOptionPane.INFORMATION_MESSAGE);
	        }
	        else{
	            Recipe selectedR = (Recipe) tm.getList().get(row);
	            int decision = JOptionPane.showConfirmDialog(this, "Do you want to delete this recipe: " + selectedR.getName() + "?", "", JOptionPane.WARNING_MESSAGE);
	            if(decision == 0){
	                try {
	                    Recipe rec = ClientController.getInstance().deleteRecipe(selectedR);
	                    JOptionPane.showMessageDialog(rootPane, "Deleted recipe " + selectedR.getName(), "Success", JOptionPane.INFORMATION_MESSAGE);
	                    prepareForm();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
	        
	    }                                         

	    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {                                        
	        int row = tblAllRecipes.getSelectedRow();
	        if(row == -1){
	            JOptionPane.showMessageDialog(rootPane, "Select recipe you want to delete", "Error", JOptionPane.INFORMATION_MESSAGE);
	        }
	        else{
	            Recipe selectedR = (Recipe) tm.getList().get(row);
	            NewRecipe nr = new NewRecipe(this, t, selectedR);
	            nr.setVisible(true);
	            this.setVisible(false);
	        }
	    }
}
