package org.mstudio.homeland4android.util;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/09
 * desc   :
 */

public class IconFactory {

    private int index = 0;
    private String mIconListString = "home,inbox,magic,diamond,coffee,cube,database,cogs,desktop,leaf,laptop," +
            "mobile,shield,tasks,sitemap,sliders,sun-o,tint,tree,trophy,tty,umbrella,shopping-bag," +
            "plug,map,lemon-o,key,keyboard-o,industry,id-badge,id-card-o,hdd-o,glass,globe," +
            "compass,cloud,code,clone,bullhorn,building,bolt,beer,bars,asterisk,anchor,archive," +
            "bookmark,bug,briefcase,calculator,calendar,circle-o-notch,check-circle,child";

    private String[] mIconList = mIconListString.split(",");

    public String getIcon() {
        try {
            return "faw-" + mIconList[index++];
        } catch (ArrayIndexOutOfBoundsException e) {
            index = 0;
            return "faw-" + mIconList[index++];
        }
    }

}
