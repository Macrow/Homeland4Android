package org.mstudio.homeland4android.util;

import br.tiagohm.markdownview.css.InternalStyleSheet;
import br.tiagohm.markdownview.css.styles.Github;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/05
 * desc   :
 */
public class MarkDownStyle {
    public static InternalStyleSheet getStyle() {
        InternalStyleSheet style = new Github();
        style.addRule("img", "max-width:100%");
        style.addRule("body", "padding:10px");
        return style;
    }
}
