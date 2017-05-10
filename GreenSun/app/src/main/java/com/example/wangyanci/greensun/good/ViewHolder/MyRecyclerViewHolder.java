package com.example.wangyanci.greensun.good.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.wangyanci.greensun.R;

/**
 * Created by wangyanci on 2017/5/1.
 */

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageview;
    public MyRecyclerViewHolder(View itemView) {
        super(itemView);
        imageview=(ImageView)itemView.findViewById(R.id.imageView);
    }
}
