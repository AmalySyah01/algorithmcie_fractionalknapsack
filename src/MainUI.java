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
    private JTextField Capacityfield;
    private JButton setButton;
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
        setButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for (int i = 1; i < 1001; i++)
                {
                    Capacityfield.addItem("" + i);
                }
            }
        });
        btnAdd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textArea1.setText(textArea1.getText() + "|COMPANY - " + txtObject.getText() + " |" + "|\tVALUE - " + String.valueOf(txtValue.getText()) + " |" + "|\tCAPITAL - " + String.valueOf(txtWeight.getText()) + " |\n");
                FractionalKnapSack temp = new FractionalKnapSack(txtObject.getText(),Integer.parseInt(String.valueOf(txtValue.getText())),Integer.parseInt(String.valueOf(txtWeight.getText())));
                totalUser.add(temp);
            }
        });

        btnCalculate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                FractionalKnapSack.sortListByProfitASC(totalUser);
                List<FractionalKnapSack> selectedValueFitInCapcity = FractionalKnapSack.getValueFitInCapacity(totalUser, Integer.parseInt(String.valueOf(Capacityfield.getSelectedItem())));

                float totalProfit = 0;
                textArea1.setText("| TOTAL PROFIT : " + totalProfit);

                for (FractionalKnapSack item : selectedValueFitInCapcity)
                {
                    totalProfit += item.getProfit();
                }

                textArea1.setText("| BENEFIT TOTAL CAPITAL : " + totalProfit + "| CAPACITY : " + Integer.parseInt(String.valueOf(Capacityfield.getSelectedItem())) + "\n");
                textArea1.setText(textArea1.getText() + "======================================================================================\n");

                for (FractionalKnapSack item : selectedValueFitInCapcity)
                {
                    System.out.println("|COMPANY |" + item.getObject() + "|PROFIT |" + item.getProfit() + "|CAPITAL |" + item.getWeight() + "|CAPACITY-CAPITAL |" + item.getWeightMinusCapacity() + "|DENSITY |" + item.getProfitDevideWeight());
                    textArea1.setText(textArea1.getText() + "| COMPANY - " + item.getObject() + "\t|CAPITAL - " + item.getWeight() + "\t|VALUE - " + item.getProfit() + "\t|DENSITY - " + item.getProfitDevideWeight() + "\n");
                }

                textArea1.setText(textArea1.getText() + "======================================================================================\n");

                totalUser.clear();
            }
        });
    }

}
