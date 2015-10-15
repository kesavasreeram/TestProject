/**
 * 
 */
package solutions.egen.rrs.model;

/**
 * @author Kesava
 *	Helper method to assist in knowing if a table is available for reservation
 */
public class TableDetails
{
	private int tableSize = -1;
	private int tableId = -1;
	private boolean isTableAvailable = false;
	
	/**
	 * @return the tableSize
	 */
	public int getTableSize() {
		return tableSize;
	}

	/**
	 * @param tableSize the tableSize to set
	 */
	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}

	/**
	 * @return the isTableAvailable
	 */
	public boolean isTableAvailable() {
		return isTableAvailable;
	}

	/**
	 * @param isTableAvailable the isTableAvailable to set
	 */
	public void setTableAvailable(boolean isTableAvailable) {
		this.isTableAvailable = isTableAvailable;
	}

	/**
	 * @return the tableId
	 */
	public int getTableId() {
		return tableId;
	}

	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	
}
