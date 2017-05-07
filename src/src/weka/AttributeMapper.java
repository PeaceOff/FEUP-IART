package weka;

import java.util.ArrayList;
import java.util.HashMap;


public class AttributeMapper {

    private static HashMap<String ,String> attributes = null;

    public static String getAttributeName(String i){
        return attributes.get(i);
    }

    public static HashMap<String ,String> get_attributes(){
        return attributes;
    }

    static {
        attributes = new HashMap<String,String>();

        attributes.put("Attr1","net profit / total assets");
        attributes.put("Attr2","total liabilities / total assets");
        attributes.put("Attr3","working capital / total assets");
        attributes.put("Attr4","current assets / short-term liabilities");
        attributes.put("Attr5","[(cash + short-term securities + receivables - short-term liabilities) / (operating expenses - depreciation)] * 365");
        attributes.put("Attr6","retained earnings / total assets");
        attributes.put("Attr7","EBIT / total assets");
        attributes.put("Attr8","book value of equity / total liabilities");
        attributes.put("Attr9","sales / total assets");
        attributes.put("Attr10","equity / total assets");
        attributes.put("Attr11","(gross profit + extraordinary items + financial expenses) / total assets");
        attributes.put("Attr12","gross profit / short-term liabilities");
        attributes.put("Attr13","(gross profit + depreciation) / sales");
        attributes.put("Attr14","(gross profit + interest) / total assets");
        attributes.put("Attr15","(total liabilities * 365) / (gross profit + depreciation)");
        attributes.put("Attr16","(gross profit + depreciation) / total liabilities");
        attributes.put("Attr17","total assets / total liabilities");
        attributes.put("Attr18","gross profit / total assets");
        attributes.put("Attr19","gross profit / sales");
        attributes.put("Attr20","(inventory * 365) / sales");
        attributes.put("Attr21","sales (n) / sales (n-1)");
        attributes.put("Attr22","profit on operating activities / total assets");
        attributes.put("Attr23","net profit / sales");
        attributes.put("Attr24","gross profit (in 3 years) / total assets");
        attributes.put("Attr25","(equity - share capital) / total assets");
        attributes.put("Attr26","(net profit + depreciation) / total liabilities");
        attributes.put("Attr27","profit on operating activities / financial expenses");
        attributes.put("Attr28","working capital / fixed assets");
        attributes.put("Attr29","logarithm of total assets");
        attributes.put("Attr30","(total liabilities - cash) / sales");
        attributes.put("Attr31","(gross profit + interest) / sales");
        attributes.put("Attr32","(current liabilities * 365) / cost of products sold");
        attributes.put("Attr33","operating expenses / short-term liabilities");
        attributes.put("Attr34","operating expenses / total liabilities");
        attributes.put("Attr35","profit on sales / total assets");
        attributes.put("Attr36","total sales / total assets");
        attributes.put("Attr37","(current assets - inventories) / long-term liabilities");
        attributes.put("Attr38","constant capital / total assets");
        attributes.put("Attr39","profit on sales / sales");
        attributes.put("Attr40","(current assets - inventory - receivables) / short-term liabilities");
        attributes.put("Attr41","total liabilities / ((profit on operating activities + depreciation) * (12/365))");
        attributes.put("Attr42","profit on operating activities / sales");
        attributes.put("Attr43","rotation receivables + inventory turnover in days");
        attributes.put("Attr44","(receivables * 365) / sales");
        attributes.put("Attr45","net profit / inventory");
        attributes.put("Attr46","(current assets - inventory) / short-term liabilities");
        attributes.put("Attr47","(inventory * 365) / cost of products sold");
        attributes.put("Attr48","EBITDA (profit on operating activities - depreciation) / total assets");
        attributes.put("Attr49","EBITDA (profit on operating activities - depreciation) / sales");
        attributes.put("Attr50","current assets / total liabilities");
        attributes.put("Attr51","short-term liabilities / total assets");
        attributes.put("Attr52","(short-term liabilities * 365) / cost of products sold)");
        attributes.put("Attr53","equity / fixed assets");
        attributes.put("Attr54","constant capital / fixed assets");
        attributes.put("Attr55","working capital");
        attributes.put("Attr56","(sales - cost of products sold) / sales");
        attributes.put("Attr57","(current assets - inventory - short-term liabilities) / (sales - gross profit - depreciation)");
        attributes.put("Attr58","total costs /total sales");
        attributes.put("Attr59","long-term liabilities / equity");
        attributes.put("Attr60","sales / inventory");
        attributes.put("Attr61","sales / receivables");
        attributes.put("Attr62","(short-term liabilities *365) / sales");
        attributes.put("Attr63","sales / short-term liabilities");
        attributes.put("Attr64","sales / fixed assets");

    }

}
