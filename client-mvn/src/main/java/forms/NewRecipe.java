package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClientController;
import domain.Component;
import domain.DomainObject;
import domain.Packaging;
import domain.Recipe;
import domain.RecipeItem;
import domain.Tehnolog;
import models.TMComponent;
import models.TMRecipe;
import models.TMRecipeItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewRecipe extends JFrame {
	private String status;
	private Tehnolog t;
	TMComponent allTM;
	private TMRecipeItem addedTM;
	Recipe recipe = new Recipe();
	double quantity =0;
	JFrame mainForm;
	List<DomainObject> allComponents;
    List<DomainObject> addedItems = new ArrayList<>();
	
	private JPanel contentPane;
	private JTable tblAllComp;
	private JTable tblAddedComp;
	private JTextField txtQuantityRecipe;
	private JTextField txtShortCode;
	private JTextField txtName;
	private JTextField txtID;
	private JComboBox cbPackaging;
	private JTextField txtQuantityComp;
	private JButton btnAddComp;
	private JButton btnRemove;
	private JLabel lblTehnolog;
	private JTextArea txtComment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NewRecipe().setVisible(true);
			}
		});
	}

	public NewRecipe(JFrame main, Tehnolog t) {
		initComponents();
		this.t = t;
		this.setTitle("New Recipe");
        status = "new";
        mainForm = main;
        prepareForm();
	}
	
	public NewRecipe(AllRecipes main, Tehnolog t, Recipe r) {
		initComponents();
        this.setTitle("Edit Recipe");
        status = "edit";
        this.t = t;
        mainForm = main;
        recipe = r;
        quantity = r.getQuantity();
        prepareForm();
	}
	private void prepareForm() {
		txtID.setEnabled(false);
        txtQuantityRecipe.setEnabled(false);
        fillCB();
        fillComponentsTable();
        if(status.equals("edit")){
            btnAddComp.setEnabled(false);
            btnRemove.setEnabled(false);
            fill();
            fillAddedTable();
        }
		
	}

	private void prepareItemsTM() {
        addedTM = new TMRecipeItem(addedItems);
        tblAddedComp.setModel(addedTM);
    }

    private void set(String name, String shortCode,String comment, Packaging packaging) {
        recipe.setName(name);
        recipe.setComment(comment);
        recipe.setShortCode(shortCode);
        recipe.setPackaging(packaging);
        recipe.setComponents(addedItems);
        recipe.setQuantity(quantity);
        recipe.setTehnolog(t);
    }
    
	private void fill() {
		txtID.setText(recipe.getRecipeID()+"");
        txtName.setText(recipe.getName());
        txtShortCode.setText(recipe.getShortCode());
        txtComment.setText(recipe.getComment());
        int index = getIndex(recipe.getPackaging().getName());
        cbPackaging.setSelectedIndex(index);
        txtQuantityRecipe.setText(recipe.getQuantity()+"");
        lblTehnolog.setText(recipe.getTehnolog().getName()+" " + recipe.getTehnolog().getSurname());
	}

	private int getIndex(String name) {
        try {
            List<DomainObject> listPackaging = ClientController.getInstance().getPackaging();
            for(int i = 0; i < listPackaging.size(); i++){
                Packaging p = (Packaging) listPackaging.get(i);
                if(p.getName().equals(name)){
                    return i;
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
	
	private void fillAddedTable() {
		addedItems = recipe.getComponents();
        addedTM = new TMRecipeItem(addedItems);
        tblAddedComp.setModel(addedTM);
	}

	private void fillComponentsTable() {
		try{
            allComponents = ClientController.getInstance().getComponents();
            allTM = new TMComponent(allComponents);
            tblAllComp.setModel(allTM);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	private void fillCB() {
		try {
            List<DomainObject> listPackaging = ClientController.getInstance().getPackaging();
            cbPackaging.setModel(new DefaultComboBoxModel(listPackaging.toArray()));
            cbPackaging.setSelectedItem(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }		
	}

	public NewRecipe() {
		initComponents();
	}
	
	private void btnRemoveCompActionPerformed(java.awt.event.ActionEvent evt) {                                              
        int row = tblAddedComp.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(rootPane, "Select component you want to remove from recipe", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            RecipeItem selectedItem = (RecipeItem) addedTM.getList().get(row);
            addedItems.remove(selectedItem);
            quantity -= selectedItem.getQuantity();
            txtQuantityRecipe.setText(quantity+"");
            prepareItemsTM();
        }
    }
	
	private void btnAddCompActionPerformed(java.awt.event.ActionEvent evt) {                                           
        int row = tblAllComp.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(rootPane, "Select component you want to add to recipe", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(txtQuantityComp.getText().isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Add quantity for component you want to add to recipe", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
           
            Component selectedComp = (Component) allTM.getList().get(row);
            boolean added = false;
            for(DomainObject item:addedItems){
                RecipeItem ri = (RecipeItem) item;
                if(ri.getComponent().equals(selectedComp)){
                    JOptionPane.showMessageDialog(rootPane, "Component is already added to recipe", "Error", JOptionPane.INFORMATION_MESSAGE);
                    added = true;
                }
            }
            if(!added){
                RecipeItem item = new RecipeItem();
                item.setComponent(selectedComp);
                double quant = Double.valueOf(txtQuantityComp.getText().trim());
                
                item.setQuantity(quant);
                quantity += item.getQuantity();
                if(quantity <=100){
                    addedItems.add(item);
                
                }
                else{
                   JOptionPane.showMessageDialog(rootPane, "Recipe quantity is above 100", "Error", JOptionPane.INFORMATION_MESSAGE);

                }
                
                prepareItemsTM();
                txtQuantityRecipe.setText(quantity+"");
                txtQuantityComp.setText("");
            }   
        }   
    }
	
	private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(txtName.getText().isEmpty() || txtShortCode.getText().isEmpty() ||
                cbPackaging.getSelectedItem() == null || addedItems.size() < 2){
            JOptionPane.showMessageDialog(rootPane, "System can't add recipe!", "Error", JOptionPane.INFORMATION_MESSAGE);
         }
        else{
            String name = txtName.getText().trim();
            String shortCode = txtShortCode.getText().trim();
            String comment = txtComment.getText();
            Packaging packaging = (Packaging) cbPackaging.getSelectedItem();
            set(name, shortCode,comment, packaging);
            Recipe re;
            if (status.equals("new")) {
                try {
                    re = (Recipe) ClientController.getInstance().saveRecipe(recipe);
                    JOptionPane.showMessageDialog(this, "Successfully saved recipe!","Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                try {
                    re = (Recipe) ClientController.getInstance().editRecipe(recipe);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(this, "Successfully edited recipe!","Success", JOptionPane.INFORMATION_MESSAGE);
            }
            mainForm.setVisible(true);
            dispose();
        }
    }
	
	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
        dispose();
    }
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add recipe");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(153, 21, 105, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tehnolog:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(28, 53, 68, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(28, 85, 68, 22);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(28, 117, 68, 22);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Short code:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(28, 149, 68, 22);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Comment:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(28, 181, 68, 22);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Packaging:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_5.setBounds(28, 245, 68, 22);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Recipe quantity:");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_6.setBounds(28, 277, 105, 22);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("Choose components:");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_7.setBounds(28, 331, 130, 22);
		contentPane.add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_7_1 = new JLabel("Added components:");
		lblNewLabel_1_7_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_7_1.setBounds(389, 72, 130, 22);
		contentPane.add(lblNewLabel_1_7_1);
		
		tblAllComp = new JTable();
		tblAllComp.setBounds(26, 363, 337, 137);
		contentPane.add(tblAllComp);
		
		tblAddedComp = new JTable();
		tblAddedComp.setBounds(378, 104, 337, 137);
		contentPane.add(tblAddedComp);
		
		btnRemove = new JButton("Remove component");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRemoveCompActionPerformed(e);
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRemove.setBounds(461, 271, 151, 21);
		contentPane.add(btnRemove);
		
		txtQuantityRecipe = new JTextField();
		txtQuantityRecipe.setBounds(128, 280, 141, 19);
		contentPane.add(txtQuantityRecipe);
		txtQuantityRecipe.setColumns(10);
		
		txtShortCode = new JTextField();
		txtShortCode.setColumns(10);
		txtShortCode.setBounds(128, 152, 141, 19);
		contentPane.add(txtShortCode);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(128, 120, 141, 19);
		contentPane.add(txtName);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(128, 88, 141, 19);
		contentPane.add(txtID);
		
		txtComment = new JTextArea();
		txtComment.setBounds(128, 181, 141, 54);
		contentPane.add(txtComment);
		
		cbPackaging = new JComboBox();
		cbPackaging.setBounds(129, 247, 140, 21);
		contentPane.add(cbPackaging);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Quantity:");
		lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_6_1.setBounds(28, 529, 105, 22);
		contentPane.add(lblNewLabel_1_6_1);
		
		txtQuantityComp = new JTextField();
		txtQuantityComp.setColumns(10);
		txtQuantityComp.setBounds(117, 532, 141, 19);
		contentPane.add(txtQuantityComp);
		
		btnAddComp = new JButton("Add component");
		btnAddComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddCompActionPerformed(e);
			}
		} );
		btnAddComp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddComp.setBounds(28, 584, 151, 21);
		contentPane.add(btnAddComp);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveActionPerformed(e);
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSave.setBounds(507, 584, 105, 21);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelActionPerformed(e);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancel.setBounds(647, 584, 105, 21);
		contentPane.add(btnCancel);
		
		lblTehnolog = new JLabel("");
		lblTehnolog.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTehnolog.setBounds(128, 53, 68, 22);
		contentPane.add(lblTehnolog);
	}
}
