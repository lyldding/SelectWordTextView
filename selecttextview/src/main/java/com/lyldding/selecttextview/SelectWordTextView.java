package com.lyldding.selecttextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * @author https://github.com/lyldding/SelectWordTextView
 */
public class SelectWordTextView extends AppCompatTextView {
    private static final String TAG = "SelectWordTextVIew";
    private OnClickWordListener mOnClickWordListener;
    private int mTouchSlop;
    private int mSelectedColor;

    private int[] mTempPosition = new int[]{-1, -1};

    public SelectWordTextView(Context context) {
        super(context);
        init(context, null);
    }

    public SelectWordTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SelectWordTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SelectWordTextView);
        mSelectedColor = array.getColor(R.styleable.SelectWordTextView_selectColor, Color.parseColor("#FF4040"));
        array.recycle();
    }

    float downX = 0;
    float downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mOnClickWordListener != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    if (Math.abs(event.getX() - downX) < mTouchSlop
                            && Math.abs(event.getY() - downY) < mTouchSlop) {
                        String word = handleClickWord(getText().toString(), getOffsetForPosition(event.getX(), event.getY()));
                        //  Log.e(TAG, "onTouchEvent: click word = " + word);
                        if (!TextUtils.isEmpty(word)) {
                            mOnClickWordListener.onClickWord(word);
                        }
                    }
                    break;
                default:
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int[] getWordPosition(String text, int offsetPosition) {
        //   Log.e(TAG, "getWord offsetPosition =: " + offsetPosition);
        if (offsetPosition >= text.length() || offsetPosition < 0
                || !isLetter(text.charAt(offsetPosition))) {
            return null;
        }
        int endPositionOfWord = 0;
        int startPositionOfWord = 0;
        for (int i = offsetPosition; i < text.length(); i++) {
            if (i == text.length() - 1) {
                endPositionOfWord = i;
                break;
            }
            if (isEdge(text.charAt(i))) {
                endPositionOfWord = i;
                break;
            }

        }
        for (int i = offsetPosition; i >= 0; i--) {
            if (i == 0) {
                startPositionOfWord = i;
                break;
            }
            if (isEdge(text.charAt(i))) {
                startPositionOfWord = i + 1;
                break;
            }

        }
        Log.e(TAG, "getWord: startPositionOfWord = " + startPositionOfWord + " endPositionOfWord =" + endPositionOfWord);
        return new int[]{startPositionOfWord, endPositionOfWord};

    }

    private String handleClickWord(String text, int offsetPosition) {
        //取消
        if (offsetPosition >= mTempPosition[0] && offsetPosition < mTempPosition[1]) {
            mTempPosition[0] = -1;
            mTempPosition[1] = -1;
            setText(text);
            return null;
        }
        int[] wordPosition = getWordPosition(text, offsetPosition);
        if (wordPosition == null) {
            return null;
        }
        int start = wordPosition[0];
        int end = wordPosition[1];

        mTempPosition[0] = start;
        mTempPosition[1] = end;

        SpannableString stringBuilder = new SpannableString(text);
        stringBuilder.setSpan(new ForegroundColorSpan(mSelectedColor),
                start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        setText(stringBuilder);
        return text.substring(start, end);
    }

    private boolean isEdge(char curr) {
        return curr == ' ' || curr == '\n'
                || (!isLetter(curr) && curr != '\'');
    }


    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z')
                || (c >= 'A' && c <= 'Z');
    }

    public void setOnClickWordListener(OnClickWordListener onClickWordListener) {
        mOnClickWordListener = onClickWordListener;
    }

    public interface OnClickWordListener {
        void onClickWord(String word);
    }

    public void setSelectedColor(@ColorInt int selectedColor) {
        mSelectedColor = selectedColor;
    }
}
