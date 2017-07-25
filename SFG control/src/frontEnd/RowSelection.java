package frontEnd;

public class RowSelection {
	
	private String tableName;
	private int selectedRow;

	public RowSelection(int selectedRow, String tableName) {
		this.selectedRow = selectedRow;
		this.tableName = tableName;
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public String getTableName() {
		return tableName;
	}

}

