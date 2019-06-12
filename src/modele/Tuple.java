package modele;

import com.sun.javafx.collections.ObservableIntegerArrayImpl;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Tuple {

	private Object key;
	private IntegerProperty value;
	
	public Tuple(Object o1, int o2) {
		this.key=o1;
		this.value = new SimpleIntegerProperty(o2);
	}
	
	public Object getKey() {
		return this.key;
	}
	public IntegerProperty getValueProperty() {
		return this.value;
	}
	public int getValue() {
		return this.value.getValue();
	}
	
	public void setKey(Object obj) {
		this.key=obj;
	}
	
	public void setValue(int value) {
		this.value.set(value);
	}
	
	public void increment() {
		this.setValue(this.getValue()+1);
	}
	public void decrement() {
		this.setValue(this.getValue()-1);
	}
}
