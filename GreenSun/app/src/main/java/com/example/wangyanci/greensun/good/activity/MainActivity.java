package com.example.wangyanci.greensun.good.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangyanci.greensun.R;
import com.example.wangyanci.greensun.good.activity.fragment.Fragment_1;
import com.example.wangyanci.greensun.good.activity.fragment.Fragment_2;
import com.example.wangyanci.greensun.good.activity.fragment.Fragment_3;
import com.example.wangyanci.greensun.good.adapter.MyFragmentAdapter;
import com.example.wangyanci.greensun.good.util.SerchEditView.SerchEditView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangyanci on 2017/4/30.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {


    public DrawerLayout drawerLayout;
    private List<String> mDatas;
    private FragmentTabHost fragmentTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {Fragment_1.class, Fragment_2.class, Fragment_3.class};
    private int imageViewArray[] = {R.drawable.icon_email, R.drawable.icon_pwd, R.drawable.icon_account};
    private String textViewArray[] = {"首页", "分类", "购物"};
    private List<Fragment> list = new ArrayList<Fragment>();
    private ViewPager viewPager;
    private SerchEditView search_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//以下代码设置状态栏透明，底部导航栏不透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Android 5.0以上 statusBar背景全透明
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

//以下代码设置状态栏透明，底部导航栏也透明
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }

        //onHide();
        // onShow();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//以下代码解决有DrawerLayout的布局状态栏有阴影的情况
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            drawerLayout.setFitsSystemWindows(true);
            drawerLayout.setClipToPadding(false);
        }


//        mDatas=new ArrayList<String>();
//        for(int i=0;i<100;i++) {
//            mDatas.add("条目"+i);
//        }
//        init();

        init2();
    }


    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        recyclerView.setHasFixedSize(true);
        RecyclerView.Adapter recyclerViewAdapter = new TestAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.action_sign_in, R.string.action_sign_in_short);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View headView = navigationView.inflateHeaderView(R.layout.head_view);

        ImageView imageView = (ImageView) headView.findViewById(R.id.head_iv);
        imageView.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
                final Snackbar snackbar = Snackbar.make(view, "关注PANDA，赢得好礼", Snackbar.LENGTH_LONG);
                snackbar.setAction("关注站点", new View.OnClickListener() {
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
                break;
            case R.id.head_iv:
                Toast.makeText(this, "图片设置暂未开放", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_1:
                Toast.makeText(this, "菜单1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_2:
                Toast.makeText(this, "菜单2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_3:
                Toast.makeText(this, "菜单3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_4:
                Toast.makeText(this, "菜单4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_5:
                Toast.makeText(this, "菜单5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_6:
                Toast.makeText(this, "菜单6", Toast.LENGTH_SHORT).show();
                break;
        }
        item.setCheckable(false);
        item.setChecked(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.textlist, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.textView.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public MyViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.tv);
            }

        }

    }


//onHide（）函数隐藏状态栏
//    public void onHide() {
//
//
//        //4.1及以上通用flags组合
//        int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    flags | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//            );
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            getWindow().getDecorView().setSystemUiVisibility(flags);
//        }}
//
//
//    public void onShow(){
//        //5.0以上手动设置statusBar为透明。不能使用getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //因为这在4.4,5.0确实是全透明，但是在6.0是半透明!!!
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//
//        );
//    }


    @Override
    public void onTabChanged(String tabId) {

        int position = fragmentTabHost.getCurrentTab();
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        TabWidget widget = fragmentTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        fragmentTabHost.setCurrentTab(position);
        widget.setDescendantFocusability(oldFocusability);
    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }


    private View getTabItemView(int i) {
        View view = layoutInflater.inflate(R.layout.tab_content, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_tv);
        textView.setText(textViewArray[i]);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_iv);
        imageView.setImageResource(imageViewArray[i]);
        return view;
    }

    public void init2() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.action_sign_in, R.string.action_sign_in_short);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View headView = navigationView.inflateHeaderView(R.layout.head_view);


        TextInputLayout search_ti = (TextInputLayout) findViewById(R.id.searchti);
        final SerchEditView search_et = (SerchEditView) findViewById(R.id.search_et);
        search_et.setFocusable(true);
        search_et.setFocusableInTouchMode(true);


        ((SerchEditView) search_et).setOnClickSearchListener(new SerchEditView.OnClickSearchListener() {

            public void onClickSearch() {
                SerchEditView search_et = (SerchEditView) findViewById(R.id.search_et);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                Toast.makeText(MainActivity.this, "怎么了", Toast.LENGTH_SHORT).show();
                search_et.setFocusable(true);
                search_et.setFocusableInTouchMode(true);
            }
        });


// toolbar .setNavigationIcon(R.drawable.back);
//       toolbar .setNavigationOnClickListener(new View.OnClickListener(){
//            public void onClick(View view ){
//                getSupportActionBar() .setDisplayHomeAsUpEnabled(false);
//                toolbar .setNavigationIcon(null);
//                search_et.setFocusable(false );
//                search_et.setFocusableInTouchMode(false);
//            }
//        });


        ImageView imageView = (ImageView) headView.findViewById(R.id.head_iv);
        imageView.setOnClickListener(this);


        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.addOnPageChangeListener(this);
        layoutInflater = LayoutInflater.from(this);

        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.pager);
        fragmentTabHost.setOnTabChangedListener(this);

        int count = textViewArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(textViewArray[i]).setIndicator(getTabItemView(i));
            fragmentTabHost.addTab(tabSpec, fragmentArray[i], null);
            fragmentTabHost.setTag(i);
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.btn_white_normal);
        }

        Fragment_1 fragment1 = new Fragment_1();
        Fragment_2 fragment2 = new Fragment_2();
        Fragment_3 fragment3 = new Fragment_3();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);

        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
    }


//进行事件分发，确定EditText焦点问题

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //如果点击除EditText以外的其他VIew，键盘回收
            if (v instanceof SerchEditView) {
                View nextFocus = findViewFocus((ViewGroup) (MainActivity.this).findViewById(android.R.id.content), event);
                if (nextFocus != null && nextFocus instanceof SerchEditView) {
                    return super.dispatchTouchEvent(event);
                }
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    //AndroidUtils.hideSoftKeyboard(this,contentView);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    ((ViewGroup) (MainActivity.this).findViewById(android.R.id.content)).requestFocus();
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

