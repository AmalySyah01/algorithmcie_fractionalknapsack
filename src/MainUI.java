import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainUI extends JFrame
{
    private JPanel mainPanel;
    private JPanel parentPanel;
    private JTextField txtItem;
    private JButton btnAdd;
    private JTextArea textArea1;
    private JButton btnCalculate;
    private JTextField txtValue;
    private JTextField txtWeight;
    private JTextField txtCapacity;
    private JTable table1;
    private JButton btnClear;
    private JButton btnDelete;
    private JTable table2;
    private JPanel panelTable;
    public  List<FractionalKnapSack> totalUser;

    public DefaultTableModel model;

    public MainUI(String title)
    {
        super(title);

        this.setDefaultCloseOperation(3);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);


        String[] columnNames = {"Item (Label)", "Weight ", "Value", "Density"};
        Object[][] data = {

        };
        model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);

        totalUser = new ArrayList<>();
        btnAdd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {


                if(txtItem.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Error : " + "Item (Label) Cannot Be Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else
                {
                    for (int i = 0; i < table1.getModel().getRowCount(); i++) {
                        Object value = table1.getModel().getValueAt(i, 0);
                        System.out.print(value + " ");

                        if(txtItem.getText().equals(value))
                        {
                            JOptionPane.showMessageDialog(null, "Error : " + "Item (Label) must be unique", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                try
                {

                    Float.parseFloat(String.valueOf(txtValue.getText()));
                    Float.parseFloat(String.valueOf(txtWeight.getText()));
                    Float.parseFloat(String.valueOf(txtCapacity.getText()));
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }



                Object[]data = {txtItem.getText(),txtWeight.getText() ,txtValue.getText(), "-"};
                model.addRow(data);
                table1.setModel(model);
            }
        });

        btnCalculate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                for (int i = 0; i < table1.getModel().getRowCount(); i++) {
                    Object item = table1.getModel().getValueAt(i, 0);
                    Object weight = table1.getModel().getValueAt(i, 1);
                    Object value = table1.getModel().getValueAt(i, 2);
                    try{
                        totalUser.add(new FractionalKnapSack(item + "", Float.parseFloat(weight + ""),Float.parseFloat(value + "")));
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                FractionalKnapSack.sortListByProfitASC(totalUser);
                List<FractionalKnapSack> selectedValueFitInCapcity = null;
                try{
                    selectedValueFitInCapcity = FractionalKnapSack.getValueFitInCapacity(totalUser, Float.parseFloat(String.valueOf(txtCapacity.getText())));
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                float totalProfit = 0;
                for (FractionalKnapSack item : selectedValueFitInCapcity)
                {
                    totalProfit += item.getProfit();
                }

                clearTable();
                for (FractionalKnapSack item : selectedValueFitInCapcity)
                {
                    Object[]dataItem = {item.getObject() + "",item.getWeight() + "" ,item.getProfit() + "", item.getProfitDevideWeight() + ""};
                    model.addRow(dataItem);
                }

                String[] columnNames2 = {"Capacity", "Total Weight "};
                Object[][] data2 = {{txtCapacity.getText(), totalProfit}};
                DefaultTableModel model2 = new DefaultTableModel(data2, columnNames2);
                table2.setModel(model2);
                table1.setModel(model);

                totalUser.clear();
            }
        });

        btnClear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clearTable();
            }
        });
        btnDelete.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                try
                {
                    System.out.println(table1.getSelectedColumn());
                    model.removeRow(table1.getSelectedRow());

                    table1.setModel(model);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error : " + "Please select row to be deleted", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    private void clearTable()
    {
        String[] columnNames = {"Item (Label)", "Weight ", "Value", "Density"};
        Object[][] data = {};
        model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);
    }

}


