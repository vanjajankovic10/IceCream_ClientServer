package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.*;
import domain.Component;
import domain.DomainObject;
import domain.Tehnolog;
import models.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AllComponents extends JFrame {
	JFrame mainForm;
    TMComponent tm;
    List<DomainObject> components;
    Tehnolog t;
    
    private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("ALL COMPONENTS");
	private JTextField textField;
	private JTable table;
	private JTextField txtFilter;
	private JTable tblAllComp;

    
    public AllComponents(MainForm mf,Tehnolog t) {
        initComponents();
        this.t = t;
        mainForm = mf;
        prepareForm();
    }
    
    private void prepareForm() {
        try{
            components = ClientController.getInstance().getComponents();
            tm = new TMComponent(components);
            tblAllComp.setModel(tm);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
    
	public void initComponents() {
		getContentPane().setLayout(null);
		
		JLabel lblAllComponents = new JLabel("ALL COMPONENTS");
		lblAllComponents.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAllComponents.setBounds(181, 27, 120, 21);
		getContentPane().add(lblAllComponents);
		
		txtFilter = new JTextField();
		txtFilter.setColumns(10);
		txtFilter.setBounds(37, 74, 318, 19);
		getContentPane().add(txtFilter);
		
		tblAllComp = new JTable();
		tblAllComp.setBounds(37, 124, 318, 161);
		getContentPane().add(tblAllComp);
		
		JButton btnAdd = new JButton("Add new");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddCompActionPerformed(e);
			}
		});
		btnAdd.setBounds(365, 135, 95, 21);
		getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditActionPerformed(e);
			}
		});
		btnEdit.setBounds(365, 166, 95, 21);
		getContentPane().add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteActionPerformed(e);
			}
		});
		btnDelete.setBounds(365, 197, 95, 21);
		getContentPane().add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelActionPerformed(e);
			}
		});
		btnCancel.setBounds(365, 323, 95, 21);
		getContentPane().add(btnCancel);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchActionPerformed(e);
			}
		});
		btnSearch.setBounds(365, 74, 95, 21);
		getContentPane().add(btnSearch);
		
		JButton btnCreateRecipe = new JButton("Create recipe");
		btnCreateRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewRecipeActionPerformed(e);
			}
		});
		btnCreateRecipe.setBounds(365, 264, 95, 21);
		getContentPane().add(btnCreateRecipe);
	}

	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
        dispose();
    }                                         

    private void btnAddCompActionPerformed(java.awt.event.ActionEvent evt) {                                           
       NewComponent newcomp = new NewComponent(this);
       newcomp.setVisible(true);
       this.setVisible(false);
    }                                          

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {                                          
        try {
            String filter = txtFilter.getText().trim();
            List<DomainObject> filtered = ClientController.getInstance().findComponents(filter);
            if(filtered.isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "System can't find components", "Error", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                tm = new TMComponent(filtered);
                tblAllComp.setModel(tm);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }                                         

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                          
        int row = tblAllComp.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(rootPane, "Select component you want to delete", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            Component selectedC = (Component) tm.getList().get(row);
            int decision = JOptionPane.showConfirmDialog(this, "Do you want to delete this component: " + selectedC.getName() + "?", "", JOptionPane.WARNING_MESSAGE);
            if(decision == 0){
                try {
                    Component comp = ClientController.getInstance().deleteComponent(selectedC);
                    JOptionPane.showMessageDialog(rootPane, "Deleted component " + selectedC.getName(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    prepareForm();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }                                         

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {                                        
        int row = tblAllComp.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(rootPane, "Select component you want to edit", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try {
                Component selectedC = (Component) tm.getList().get(row);
                NewComponent editF = new NewComponent(mainForm, selectedC);
                editF.setVisible(true);
                this.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }                                       

    private void btnNewRecipeActionPerformed(java.awt.event.ActionEvent evt) {                                             
        NewRecipe nr = new NewRecipe(this,t);
        nr.setVisible(true);
    }
	
	

}
