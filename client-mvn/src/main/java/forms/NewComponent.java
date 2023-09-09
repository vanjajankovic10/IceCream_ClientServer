package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClientController;
import domain.Category;
import domain.Component;
import domain.DomainObject;
import json.JsonReport;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class NewComponent extends JFrame {
	String status;
	Component component;
	JFrame mainFrame;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtShortCode;
	private JTextField txtProducer;
	private JComboBox cbCategory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NewComponent().setVisible(true);
			}
		});
	}
	public NewComponent() {
		initComponents();
		fillCB();
	}
	public NewComponent(JFrame frame) {
		initComponents();
		this.setTitle("New Component");
        fillCB();
        txtID.setEnabled(false);
        mainFrame = frame;
        this.status = "new";
        component = new Component(0,"","", "", null);
        
	}
	
	public NewComponent(JFrame main, Component comp) throws Exception{
        initComponents();
        this.setTitle("Edit Component");
        fillCB();
        this.mainFrame = main;
        this.component = comp;
        txtID.setEnabled(false);
        fill();
        this.status = "edit";
        
    }
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add component");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(163, 10, 121, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Component ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(25, 62, 93, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(25, 93, 93, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Short code:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(25, 124, 93, 21);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Producer:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(25, 155, 93, 21);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Category:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1_1.setBounds(25, 186, 93, 21);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		txtID = new JTextField();
		txtID.setBounds(128, 62, 156, 21);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(128, 95, 156, 21);
		contentPane.add(txtName);
		
		txtShortCode = new JTextField();
		txtShortCode.setColumns(10);
		txtShortCode.setBounds(128, 126, 156, 21);
		contentPane.add(txtShortCode);
		
		txtProducer = new JTextField();
		txtProducer.setColumns(10);
		txtProducer.setBounds(128, 157, 156, 21);
		contentPane.add(txtProducer);
		
		cbCategory = new JComboBox();
		cbCategory.setBounds(128, 187, 156, 21);
		contentPane.add(cbCategory);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveComponent(e);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(66, 248, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelActionPerformed(e);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancel.setBounds(209, 249, 85, 21);
		contentPane.add(btnCancel);
	}
	
	private void fillCB(){
	    try{
	        List<DomainObject> listCategory = ClientController.getInstance().getComponentCategory();
	        cbCategory.setModel(new DefaultComboBoxModel(listCategory.toArray()));
	        cbCategory.setSelectedItem(null);
	    }   catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
	
	 private void fill() throws Exception {
	     txtID.setText(component.getComponentID() + "");
	     txtName.setText(component.getName());
	     txtProducer.setText(component.getProducer());
	     txtShortCode.setText(component.getShortCode());
	     int index = getIndex(component.getCategory().getName());
	     cbCategory.setSelectedIndex(index);
	 }
	private int getIndex(String category) throws Exception{
	     List<DomainObject> listCategory = ClientController.getInstance().getComponentCategory();
	     for(int i = 0; i < listCategory.size(); i++){
	         Category c = (Category) listCategory.get(i);
	         if(c.getName().equals(category)){
	             return i;
	         }
	     }
	     return -1;
	}
	private void set(String name, String producer, String shortCode, Category category) {
	     component.setName(name);
	     component.setProducer(producer);
	     component.setShortCode(shortCode);
	     component.setCategory(category);
	}
	
	private void btnSaveComponent(ActionEvent e) {
		try {
            if(txtName.getText().isEmpty() || txtProducer.getText().isEmpty() || txtShortCode.getText().isEmpty() || cbCategory.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "System can't save component","Error", JOptionPane.INFORMATION_MESSAGE);
        }     
            String name = txtName.getText().trim();
            String producer = txtProducer.getText().trim();
            String shortCode = txtShortCode.getText().trim();
            Category category = (Category) cbCategory.getSelectedItem();
            set(name, producer, shortCode, category);
            Component c;
            if(status.equals("new")){
                c = (Component) ClientController.getInstance().saveComponent(component);
                JOptionPane.showMessageDialog(this, "Sucessfully added component!","Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                c = ClientController.getInstance().editComponent(component);
                JOptionPane.showMessageDialog(this, "Sucessfully edited component!","Success", JOptionPane.INFORMATION_MESSAGE);
            }
            Map<Component, LocalDateTime> addComp = new HashMap<>();
            addComp.put(c, LocalDateTime.now());
            JsonReport.setNewComp(addComp);
            JsonReport.generateReportC();
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.dispose();
    }                                         

	
	
	
	
	
	
	
	
	
	
	
}
