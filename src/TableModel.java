import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel
{

    private String[] columnNames = {"Column 1", "Column 2", "Column 3"};
    private Object[][] data = {{"Row 1, Column 1", "Row 1, Column 2", "Row 1, Column 3"},
            {"Row 2, Column 1", "Row 2, Column 2", "Row 2, Column 3"},
            {"Row 3, Column 1", "Row 3, Column 2", "Row 3, Column 3"}};

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public int getRowCount()
    {
        return data.length;
    }

    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col)
    {
        return data[row][col];
    }

    public Class getColumnClass(int c)
    {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col)
    {
        return false;
    }
}
