package org.mstudio.homeland4android.ui.topics;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.mstudio.homeland4android.R;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/13
 * desc   :
 */
public class UserWithNumbersView extends RelativeLayout {

    private TextView tvNumbers;
    private int mNumber;

    public UserWithNumbersView(Context context) {
        this(context, null);
    }

    public UserWithNumbersView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserWithNumbersView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.user_with_numbers, this, true);
        tvNumbers = (TextView) relativeLayout.findViewById(R.id.tvNumbers);
    }

    public void updateNumber(int number) {
        mNumber = number;
        if (number == 0) {
            tvNumbers.setVisibility(View.GONE);
        } else {
            tvNumbers.setVisibility(View.VISIBLE);
            if (number < 100) {
                tvNumbers.setText(String.valueOf(number));
            } else {
                tvNumbers.setText("99+");
            }
        }
        invalidate();
    }

}
