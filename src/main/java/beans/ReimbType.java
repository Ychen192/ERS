package beans;

public class ReimbType {

	private int typeID;
	private String typeName;
	
	public ReimbType(int typeID, String typeName) {
		super();
		this.typeID = typeID;
		this.typeName = typeName;
	}
	
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Override
	public String toString() {
		return "Types( typeID:" + typeID + " Name:" + typeName + ") ";
	}
}
