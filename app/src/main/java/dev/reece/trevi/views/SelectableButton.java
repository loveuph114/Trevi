package dev.reece.trevi.views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Created by reececheng on 2019/1/10.
 */

public class SelectableButton extends AppCompatButton implements ISelectable {

    public SelectableButton(Context context) {
        super(context);
        init();
    }

    public SelectableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelectableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        setBackgroundColor(Color.parseColor("#333333"));
        setTextColor(Color.WHITE);
        setText("確定");
    }

    @Override
    public void showSelected() {
    }

    @Override
    public void showSelectedColumn() {
        setBackgroundColor(Color.parseColor("#ff0000"));
    }

    @Override
    public void clear() {
        setBackgroundColor(Color.parseColor("#333333"));
    }
}
