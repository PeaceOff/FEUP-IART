package weka;

import java.util.HashMap;

/**
 * Created by PeaceOff on 06-05-2017.
 */
public class AttributeMapper {

    private HashMap<Integer,String> attributes = null;

    public String getAttributeName(int i){
        return attributes.get(i);
    }

    public AttributeMapper() {

        attributes = new HashMap<Integer,String>();

        attributes.put(0,"net profit / total assets");
        attributes.put(1,"total liabilities / total assets");
        attributes.put(2,"working capital / total assets");
        attributes.put(3,"current assets / short-term liabilities");
        attributes.put(4,"[(cash + short-term securities + receivables - short-term liabilities) / (operating expenses - depreciation)] * 365");
        attributes.put(5,"retained earnings / total assets");
        attributes.put(6,"EBIT / total assets");
        attributes.put(7,"book value of equity / total liabilities");
        attributes.put(8,"sales / total assets");
        attributes.put(9,"equity / total assets");
        attributes.put(10,"(gross profit + extraordinary items + financial expenses) / total assets");
        attributes.put(11,"gross profit / short-term liabilities");
        attributes.put(12,"(gross profit + depreciation) / sales");
        attributes.put(13,"(gross profit + interest) / total assets");
        attributes.put(14,"(total liabilities * 365) / (gross profit + depreciation)");
        attributes.put(15,"(gross profit + depreciation) / total liabilities");
        attributes.put(16,"total assets / total liabilities");
        attributes.put(17,"gross profit / total assets");
        attributes.put(18,"gross profit / sales");
        attributes.put(19,"(inventory * 365) / sales");
        attributes.put(20,"sales (n) / sales (n-1)");
        attributes.put(21,"profit on operating activities / total assets");
        attributes.put(22,"net profit / sales");
        attributes.put(23,"gross profit (in 3 years) / total assets");
        attributes.put(24,"(equity - share capital) / total assets");
        attributes.put(25,"(net profit + depreciation) / total liabilities");
        attributes.put(26,"profit on operating activities / financial expenses");
        attributes.put(27,"working capital / fixed assets");
        attributes.put(28,"logarithm of total assets");
        attributes.put(29,"(total liabilities - cash) / sales");
        attributes.put(30,"(gross profit + interest) / sales");
        attributes.put(31,"(current liabilities * 365) / cost of products sold");
        attributes.put(32,"operating expenses / short-term liabilities");
        attributes.put(33,"operating expenses / total liabilities");
        attributes.put(34,"profit on sales / total assets");
        attributes.put(35,"total sales / total assets");
        attributes.put(36,"(current assets - inventories) / long-term liabilities");
        attributes.put(37,"constant capital / total assets");
        attributes.put(38,"profit on sales / sales");
        attributes.put(39,"(current assets - inventory - receivables) / short-term liabilities");
        attributes.put(40,"total liabilities / ((profit on operating activities + depreciation) * (12/365))");
        attributes.put(41,"profit on operating activities / sales");
        attributes.put(42,"rotation receivables + inventory turnover in days");
        attributes.put(43,"(receivables * 365) / sales");
        attributes.put(44,"net profit / inventory");
        attributes.put(45,"(current assets - inventory) / short-term liabilities");
        attributes.put(46,"(inventory * 365) / cost of products sold");
        attributes.put(47,"EBITDA (profit on operating activities - depreciation) / total assets");
        attributes.put(48,"EBITDA (profit on operating activities - depreciation) / sales");
        attributes.put(49,"current assets / total liabilities");
        attributes.put(50,"short-term liabilities / total assets");
        attributes.put(51,"(short-term liabilities * 365) / cost of products sold)");
        attributes.put(52,"equity / fixed assets");
        attributes.put(53,"constant capital / fixed assets");
        attributes.put(54,"working capital");
        attributes.put(55,"(sales - cost of products sold) / sales");
        attributes.put(56,"(current assets - inventory - short-term liabilities) / (sales - gross profit - depreciation)");
        attributes.put(57,"total costs /total sales");
        attributes.put(58,"long-term liabilities / equity");
        attributes.put(59,"sales / inventory");
        attributes.put(60,"sales / receivables");
        attributes.put(61,"(short-term liabilities *365) / sales");
        attributes.put(62,"sales / short-term liabilities");
        attributes.put(63,"sales / fixed assets");

    }

}
