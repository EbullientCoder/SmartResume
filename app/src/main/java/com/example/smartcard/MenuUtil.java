package com.example.smartcard;




import com.example.smartcard.model.MenuModel;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {

    public static final int HOME_FRAGMENT_CODE = 0;
    public static final int CV_FRAGMENT_CODE = 1;
    public static final int PORTFOLIO_FRAGMENT_CODE = 2;
    public static final int TEAM_FRAGMENT_CODE = 3;


    public static List<MenuModel> getMenuList() {
        List<MenuModel> list = new ArrayList<>();
        // first menu item is selected
        list.add(new MenuModel(R.drawable.ic_baseline_home_24,HOME_FRAGMENT_CODE,true));
        list.add(new MenuModel(R.drawable.ic_baseline_school_24,CV_FRAGMENT_CODE,false));
        list.add(new MenuModel(R.drawable.ic_baseline_group_24,TEAM_FRAGMENT_CODE,false));
        list.add(new MenuModel(R.drawable.ic_baseline_dashboard_24,PORTFOLIO_FRAGMENT_CODE,false));

        return list;
    }
}
