package com.example.wangyanci.greensun.good.util.SerchEditView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.wangyanci.greensun.R;



/**
 * Created by wangyanci on 2017/5/2.
 */

public class SerchEditView extends AppCompatEditText implements View.OnFocusChangeListener,TextWatcher{

        private Drawable mClearDrawable;
        /**
         * 控件是否有焦点
         */
        private boolean hasFoucs;
        public OnClickSearchListener mOnClickSearchListener;
        public interface OnClickSearchListener{
            public void onClickSearch();
        }
        public void setOnClickSearchListener(OnClickSearchListener onClickSearchListener){
            this.mOnClickSearchListener=onClickSearchListener;
        }
        public SerchEditView(Context context) {
            this(context, null);
        }

        public SerchEditView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        public SerchEditView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }

        private void init(final Context context) {
            //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
            final Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.ic_delet);
            setTextColor(getCurrentHintTextColor());
            mClearDrawable=drawable;
            mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth()-60, mClearDrawable.getIntrinsicHeight()-60);
            //默认设置隐藏图标
            setClearIconVisible(false);
            //设置焦点改变的监听
            setOnFocusChangeListener(this);
            //设置输入框里面内容发生改变的监听
            addTextChangedListener(this);
        }

        /**
         * 当手指抬起的位置在clean的图标的区域
         * 我们将此视为进行清除操作
         * getWidth():得到控件的宽度
         * event.getX():抬起时的坐标(该坐标是相对于控件本身而言的)，可以理解成距离左边框的距离
         * getTotalPaddingRight():clean的图标左边缘至控件右边缘的距离
         * getPaddingRight():clean的图标右边缘至控件右边缘的距离
         * 于是:
         * getWidth() - getTotalPaddingRight()表示:
         * 控件左边到clean的图标左边缘的区域
         * getWidth() - getPaddingRight()表示:
         * 控件左边到clean的图标右边缘的区域
         * 所以这两者之间的区域刚好是clean的图标的区域
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (getCompoundDrawables()[2] != null) {
                    boolean searchable=event.getX()>((getWidth()-getPaddingRight()+80));
                    boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                            && (event.getX() < ((getWidth() - getPaddingRight())));
                    if (touchable) {
                        this.setText("");
                    }
                    if(searchable&&mOnClickSearchListener!=null){
                        Toast.makeText(getContext(),"search",Toast.LENGTH_SHORT).show();
                        mOnClickSearchListener.onClickSearch();
                    }
                }
            }

            return super.onTouchEvent(event);
        }

        /**
         * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
         */
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            this.hasFoucs = hasFocus;
            if (hasFocus) {
                setClearIconVisible(getText().length() > 0);
            } else {
                setClearIconVisible(false);
            }
        }

        /**
         * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
         * @param visible
         */
        protected void setClearIconVisible(boolean visible) {
            Drawable right = visible ? mClearDrawable : null;
            setCompoundDrawables(getCompoundDrawables()[0],
                    getCompoundDrawables()[1] ,right, getCompoundDrawables()[3]);

        }

        /**
         * 当输入框里面内容发生变化的时候回调的方法
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int count,
                                  int after) {
            if(hasFoucs){
                setClearIconVisible(s.length() > 0);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    }


