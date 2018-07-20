package com.estate.utils;

import com.estate.enums.OptionEnum;

import java.util.ArrayList;
import java.util.List;

public class CheckboxBuildingUtils {
    public static List<String> getCheckBox(){

        List<String> checkOption = new ArrayList<>();
        for(OptionEnum item: OptionEnum.values()){
            checkOption.add(item.getValue());
        }
        return checkOption;
    }
    public static List<Integer> getNumberOfChecked( String title){
        List<Integer> integers = new ArrayList<>();
        if(title == null){
            return null;
        }
        String []ArrayChecked = title.split(",");
        List<String> listChecked = new ArrayList<>();
        for(String item:ArrayChecked){
            listChecked.add(item);
        }
        for(String item :listChecked){
            for(OptionEnum i: OptionEnum.values()){
                if(item.equals(i.getValue())){
                    integers.add(i.ordinal());
                    break;
                }
            }
        }
        return integers;
    }
}
