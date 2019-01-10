package dev.reece.trevi.views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Created by reececheng on 2019/1/9.
 */

public class SelectableTextView extends AppCompatTextView implements ISelectable{

    public SelectableTextView(Context context) {
        super(context);
        init();
    }

    public SelectableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelectableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(Color.parseColor("#cccccc"));
        setGravity(Gravity.CENTER);
        setTextColor(Color.WHITE);
    }

    @Override
    public void showSelected() {
        setText("random");
        setBackgroundColor(Color.parseColor("#ff0000"));
    }

    @Override
    public void showSelectedColumn() {
        setBackgroundColor(Color.parseColor("#ff0000"));
    }

    @Override
    public void clear() {
        setText("");
        setBackgroundColor(Color.parseColor("#cccccc"));
    }
}
