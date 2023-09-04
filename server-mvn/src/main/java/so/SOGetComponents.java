package so;

import java.util.List;

import database.DBBroker;
import domain.Component;
import domain.DomainObject;

public class SOGetComponents extends SO{
	 private List<DomainObject> list;
	 public SOGetComponents() {
		super();
	}
		
	public SOGetComponents(DBBroker dbb) {
		super(dbb);
	}
	 @Override
	 protected void performOperation() throws Exception {
	      list = dbb.getAll(new Component());
	 }  
	 public List<DomainObject> getList(){
		 return list;
	 }
}
