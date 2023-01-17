import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainUI extends JFrame
{
    private JPanel mainPanel;
    private JPanel parentPanel;
    private JTextField txtObject;
    private JButton btnAdd;
    private JTextArea textArea1;
    private JButton btnCalculate;
    private JTextField txtValue;
    private JTextField txtWeight;
    private JTextField txtCapacity;
    public  List<FractionalKnapSack> totalUser;

    public MainUI(String title)
    {
        super(title);

        this.setDefaultCloseOperation(3);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);

        totalUser = new ArrayList<>();
        btnAdd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
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

                textArea1.setText(textArea1.getText() + "|NAME - " + txtObject.getText() + " |" + "|\tVALUE - " + String.valueOf(txtValue.getText()) + " |" + "|\tWEIGHT - " + String.valueOf(txtWeight.getText()) + " |\n");
                FractionalKnapSack temp = new FractionalKnapSack(txtObject.getText(),Float.parseFloat(String.valueOf(txtValue.getText())),Float.parseFloat(String.valueOf(txtWeight.getText())));
                totalUser.add(temp);
            }
        });

        btnCalculate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                FractionalKnapSack.sortListByProfitASC(totalUser);
                List<FractionalKnapSack> selectedValueFitInCapcity = FractionalKnapSack.getValueFitInCapacity(totalUser, Float.parseFloat(String.valueOf(txtCapacity.getText())));

                float totalProfit = 0;
                textArea1.setText("| TOTAL PROFIT : " + totalProfit);

                for (FractionalKnapSack item : selectedValueFitInCapcity)
                {
                    totalProfit += item.getProfit();
                }

                textArea1.setText("| BENEFIT TOTAL WEIGHT : " + totalProfit + "| CAPACITY : " + Float.parseFloat(String.valueOf(txtCapacity.getText())) + "\n");
                textArea1.setText(textArea1.getText() + "======================================================================================\n");

                for (FractionalKnapSack item : selectedValueFitInCapcity)
                {
                    System.out.println("|OBJECT |" + item.getObject() + "|PROFIT |" + item.getProfit() + "|WEIGHT |" + item.getWeight() + "|CAPACITY-WEIGHT |" + item.getWeightMinusCapacity() + "|DENSITY |" + item.getProfitDevideWeight());
                    textArea1.setText(textArea1.getText() + "| Item - " + item.getObject() + "\t|WIGHT - " + item.getWeight() + "\t|VALUE - " + item.getProfit() + "\t|DENSITY - " + item.getProfitDevideWeight() + "\n");
                }

                textArea1.setText(textArea1.getText() + "======================================================================================\n");

                totalUser.clear();
            }
        });
    }

}
