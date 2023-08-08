package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.DomainObject;
import domain.RecipeItem;

public class TMRecipeItem extends AbstractTableModel{
	List<DomainObject> list = new ArrayList<>();
    String[] columns = {"name","short code", "category", "quantity" };
    public TMRecipeItem(){
        
    }
    public TMRecipeItem(List<DomainObject> list){
        this.list = list;
    }
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
       return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RecipeItem item = (RecipeItem) list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return item.getComponent().getName();
            case 1:
                return item.getComponent().getShortCode();
            case 2: 
                return item.getComponent().getCategory().getName();
            case 3: 
                return item.getQuantity();
            default:
                return " ";
        }
    }
    
     @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public List<DomainObject> getList() {
        return list;
    }

    public void setList(List<DomainObject> list) {
        this.list = list;
    }
}
