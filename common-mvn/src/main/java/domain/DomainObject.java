package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface DomainObject extends Serializable{
	public String getTable();
    public String getParams();
    public String getPK();
    public String getCompositePK();
    public int getPKvalue();
    public List<DomainObject> RSTable(ResultSet rs);
    public String getUpdate();
    public void setPKvalue(int pk);
    public String columns();
    public String getSearchAttribute();
    public String getAttributeValue();
    public String getJoin();
}
