package dev.reece.trevi.views;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by reececheng on 2019/1/9.
 */

public class RandomView extends ViewGroup {

    private int mColumn;
    private int mRow;
    private int mMargin;

    private HashMap<String, ISelectable> mViewMap = new HashMap<>();
    private ArrayList<ISelectable> nSelectedList = new ArrayList<>();

    private Timer mTimer = new Timer();
    private RandomHandler mRandomHandler;

    static class RandomHandler extends Handler {
        WeakReference<RandomView> weakReference;

        RandomHandler(RandomView randomView) {
            weakReference = new WeakReference<>(randomView);
        }

        @Override
        public void handleMessage(Message msg) {
            RandomView view = weakReference.get();
            if(view != null) {
                view.nextRandom();
            }
        }
    }

    public RandomView(Context context) {
        super(context);
        init();
    }

    public RandomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RandomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressWarnings("NewApi")
    public RandomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mColumn = 0;
        mRow = 0;
        setInsideMargin(8);
        mRandomHandler = new RandomHandler(this);
    }

    public void setColumnAndRow(int column, int row) {
        this.mColumn = column;
        this.mRow = row;

        int childSum = column * row;

        int columnId;
        int rowId;

        for(int i=0; i < childSum; i++) {
            SelectableTextView textView = new SelectableTextView(getContext());

            rowId = (i / column)+1;
            columnId = (i % column)+1;

            mViewMap.put(genKey(columnId, rowId), textView);
            addView(textView);
        }

        // 多塞一排按鈕
        mRow++;
        for (int i=1; i <= mColumn; i++) {
            SelectableButton button = new SelectableButton(getContext());
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    clear();
                }
            });

            mViewMap.put(genKey(i, mRow), button);
            addView(button);
        }

        invalidate();
    }

    private String genKey(int column, int row) {
        return row +"/"+ column;
    }

    public void setInsideMargin(int margin) {
        this.mMargin = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin, getResources().getDisplayMetrics()));
        invalidate();
    }

    public void random() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                mRandomHandler.sendMessage(message);
            }
        },0, 10*1000);
    }

    private void nextRandom() {
        clear();

        // mRow-1 因為目前的 mRow 有包含按鈕，-1把按鈕那排去掉
        int ranRow = (int)(Math.random() * (mRow-1) +1);
        int ranColumn = (int)(Math.random() * mColumn +1);

        selected(ranColumn, ranRow);
    }


    private void clear() {
        for(ISelectable selectable : nSelectedList) {
            selectable.clear();
        }

        nSelectedList.clear();
    }

    private void selected(int column, int row) {
        // i < mRow 因為 button
        for(int i=1; i<=mRow; i++) {
            String key = genKey(column, i);

            if(mViewMap.containsKey(key)) {
                ISelectable selectable = mViewMap.get(key);

                if(i == row) {
                    selectable.showSelected();
                } else {
                    selectable.showSelectedColumn();
                }

                nSelectedList.add(selectable);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int painterX = 0;
        int painterY = 0;

        int childCount = getChildCount();
        for ( int i = 0; i < childCount; i++ ) {

            View child = getChildAt(i);

            int width  = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            // 換行
            if (i != 0 && i % mColumn == 0) {
                painterX = 0;
                painterY += (height + mMargin);
            }

            // 畫 child
            child.layout(painterX,
                    painterY,
                    painterX + width,
                    painterY + height);

            // 更新 x position
            painterX += (width + mMargin);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int rootWidth = getMeasuredWidth();
        int rootHeight = getMeasuredHeight();

        int width  = (rootWidth - ((mColumn-1) * mMargin)) / mColumn;
        int height = (rootHeight - ((mRow-1) * mMargin)) / mRow;

        int childCount = getChildCount();
        for ( int i = 0; i < childCount; i++ ) {
            View child = getChildAt(i);

            int widthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

            child.measure(widthSpec, heightSpec);
        }

        setMeasuredDimension(resolveSize(getMeasuredWidth(), widthMeasureSpec), resolveSize(getMeasuredHeight(), heightMeasureSpec));
    }
}
