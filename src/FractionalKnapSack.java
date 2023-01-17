import java.util.*;

public class FractionalKnapSack
{
    private String object;
    private float profit;
    private float weight;
    private  float weightMinusCapacity;
    private float profitDevideWeight;

    FractionalKnapSack(String  object, float profit, float weight)
    {
        this.object = object;
        this.profit = profit;
        this.profitDevideWeight = profit/(float) weight;
        this.weight = weight;
    }

    public float getProfit()
    {
        return profit;
    }
    public float getProfitDevideWeight()
    {
        return profitDevideWeight;
    }
    public void setProfit(float profit)
    {
        this.profit = profit;
    }
    public String getObject()
    {
        return object;
    }
    public float getWeight()
    {
        return weight;
    }
    public float getWeightMinusCapacity()
    {
        return weightMinusCapacity;
    }
    public void setWeightMinusCapacity(float weightMinusCapacity)
    {
        this.weightMinusCapacity = weightMinusCapacity;
    }
    public void setWeight(float weight)
    {
        this.weight = weight;
    }

    //=========================================================== STATIC BELOW ME ===========================================================

    public static void sortListByProfitASC(List<FractionalKnapSack> list)
    {
        Collections.sort(list, new Comparator<FractionalKnapSack>() {
            @Override
            public int compare(FractionalKnapSack o1, FractionalKnapSack o2) {
                return Double.compare(o1.getProfitDevideWeight(), o2.getProfitDevideWeight());
            }
        });

        revlist(list);
    }

    public static <T> void revlist(List<T> list)
    {
        if (list.size() <= 1 || list == null) return;

        T value = list.remove(0);
        revlist(list);
        list.add(value);
    }

    public static List<FractionalKnapSack> getValueFitInCapacity(List<FractionalKnapSack> list, float capacity)
    {
        //Create new temporary Array
        List<FractionalKnapSack> temp = new ArrayList<>();
        //40
        float temptCapacity = capacity;

        for (FractionalKnapSack item: list)
        {
            if(temptCapacity >= 0)
            {
                //Tolak item dengan weight semasa - 50
                temptCapacity = temptCapacity - item.getWeight();
                item.setWeightMinusCapacity(temptCapacity);

                if(temptCapacity == 0)
                {
                    temp.add(item);
                    break;
                }

                if(temptCapacity <= 0)
                {
                    float previouseRemainingWeight = temp.get(temp.size() - 1).getWeightMinusCapacity();
                    float currProfit = (item.getProfit()) * ((float) previouseRemainingWeight/(float)item.getWeight());
                    item.setProfit(currProfit);

                    item.setWeightMinusCapacity(previouseRemainingWeight - previouseRemainingWeight);
                    item.setWeight(previouseRemainingWeight);
                    temp.add(item);
                    break;
                }

                temp.add(item);
            }
        }

        return temp;
    }
}
