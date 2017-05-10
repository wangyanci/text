package com.example.wangyanci.greensun.good.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wangyanci.greensun.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    public List<String> mpopuList;
    public EditText editText;
    public TextInputLayout textInputLayout;
    public popupadapter madapter;
    public PopupWindow popupWindow = null;
    public ImageButton ib;
    public EditText et_account;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_login);


    }


    public void login(View view) {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }


    public void register(View view) {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivity(intent);
    }


    public void showPopupWindow(View view) {

        if (popupWindow == null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);



            editText = (EditText) findViewById(R.id.email);
            ListView mListView = new ListView(this);
            mListView.setDividerHeight(0);
            mListView.setVerticalScrollBarEnabled(false);


            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String str = mpopuList.get(position);
                    editText.setText(str);
                    popupWindow.dismiss();
                }
            });

            textInputLayout = (TextInputLayout) findViewById(R.id.tl);

            mpopuList = new ArrayList<String>();
            for (int i = 0; i < 30; i++) {
                mpopuList.add("658764652" + i);
            }

            madapter = new popupadapter(mpopuList);
            mListView.setAdapter(madapter);

            popupWindow = new PopupWindow(mListView, editText.getWidth(), 350);
            popupWindow.setBackgroundDrawable(new ColorDrawable(000000));

            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        popupWindow.showAsDropDown(editText, 0, 0);

                    }
                }, 30L);
            }
            else{popupWindow.showAsDropDown(editText, 0, 0);}

            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.update();


        } else if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        popupWindow.showAsDropDown(editText, 0, 0);

                    }
                }, 30L);
            } else {
                popupWindow.showAsDropDown(editText, 0, 0);
            } ;
        }

    }


    public class popupadapter extends BaseAdapter {

        private List list;


        public popupadapter(List list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            popupViewHolder mViewHoler = null;


            if (convertView == null) {
                convertView = View.inflate(LoginActivity.this, R.layout.login_popupwindowlistitem, null);
                mViewHoler = new popupViewHolder(convertView);

                convertView.setTag(mViewHoler);
            } else {
                mViewHoler = (popupViewHolder) convertView.getTag();

            }
            mViewHoler.tv_account.setText((String) list.get(position));
            mViewHoler.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    madapter.notifyDataSetChanged();
                    if (list.size() == 0) {
                        popupWindow.dismiss();
                    }

                }
            });
            return convertView;
        }


        public class popupViewHolder {
            private ImageView iv_hrad;
            private TextView tv_account;
            private ImageView iv_delete;

            public popupViewHolder(View view) {
                iv_hrad = (ImageView) view.findViewById(R.id.iv_hrad);
                tv_account = (TextView) view.findViewById(R.id.tv_account);
                iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
            }
        }

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //如果点击除EditText以外的其他VIew，键盘回收
            if (v instanceof EditText) {
                View nextFocus = findViewFocus((ViewGroup) (LoginActivity.this).findViewById(android.R.id.content), event);
                if (nextFocus != null && nextFocus instanceof EditText) {
                    return super.dispatchTouchEvent(event);
                }
                if (nextFocus != null && nextFocus instanceof ImageButton) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    ib.setFocusable(true);
                    ib.setFocusableInTouchMode(true);
                    ib.requestFocus();
                    v.clearFocus();
                    ib.onTouchEvent(event);
                }


                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    //AndroidUtils.hideSoftKeyboard(this,contentView);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    ((ViewGroup) (LoginActivity.this).findViewById(android.R.id.content)).requestFocus();
                    v.clearFocus();
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private View findViewFocus(ViewGroup viewGroup, MotionEvent event) {
        View view = null;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            view = viewGroup.getChildAt(i);
            Rect outRect = new Rect();
            view.getGlobalVisibleRect(outRect);
            if (outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                if (view instanceof ViewGroup) {
                    return findViewFocus((ViewGroup) view, event);
                } else {
                    return view;
                }
            }
        }
        return null;
    }

}

