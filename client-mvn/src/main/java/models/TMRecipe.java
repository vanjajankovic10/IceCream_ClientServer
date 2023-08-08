package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.DomainObject;
import domain.Recipe;

public class TMRecipe extends AbstractTableModel{
	List<DomainObject> list = new ArrayList<>();
    String[] columns = {"name","short code", "comment", "packaging" };
    public TMRecipe(){
        
    }
    public TMRecipe(List<DomainObject> list){
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
        Recipe r = (Recipe) list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return r.getName();
            case 1:
                return r.getShortCode();
            case 2: 
                return r.getComment();
            case 3: 
                return r.getPackaging().getName();
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
