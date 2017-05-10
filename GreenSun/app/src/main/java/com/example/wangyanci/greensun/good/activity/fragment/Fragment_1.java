package com.example.wangyanci.greensun.good.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangyanci.greensun.R;
import com.example.wangyanci.greensun.good.util.LeidaView.PentagonView;

/**
 * Created by wangyanci on 2017/5/1.
 */

public class Fragment_1 extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_1,null);
        PentagonView pentagonView=(PentagonView)view.findViewById(R.id.pv);
        return view;
    }
}
