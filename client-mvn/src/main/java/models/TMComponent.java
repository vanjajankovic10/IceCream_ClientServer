package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Component;
import domain.DomainObject;

public class TMComponent extends AbstractTableModel{
	List<DomainObject> list = new ArrayList<>();
    String[] columns = {"name","short code", "producer", "category" };
    public TMComponent(){
        
    }
    public TMComponent(List<DomainObject> list){
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
        Component c = (Component) list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return c.getName();
            case 1:
                return c.getShortCode();
            case 2: 
                return c.getProducer();
            case 3: 
                return c.getCategory().getName();
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
