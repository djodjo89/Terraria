package modele;

public class Tuple {

	private Object key;
	private int value;
	
	public Tuple(Object o1,int o2) {
		this.key=o1;
		this.value=o2;
	}
	
	public Object getKey() {
		return this.key;
	}
	public int getValue() {
		return this.value;
	}
	
	public void setKey(Object obj) {
		this.key=obj;
	}
	
	public void setValue(int obj) {
		this.value=obj;
	}
	
	public void increment() {
		this.value++;
	}
	public void decrement() {
		this.value--;
	}
}
