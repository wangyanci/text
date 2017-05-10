package com.example.wangyanci.greensun.good.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wangyanci.greensun.R;
import com.example.wangyanci.greensun.good.ViewHolder.GridSpacingItemDecoration;
import com.example.wangyanci.greensun.good.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyanci on 2017/5/1.
 */

public class Fragment_3 extends android.support.v4.app.Fragment implements MyRecyclerViewAdapter.OnItemClickListener {

   private  List<Integer>resIds;
    private RecyclerView recycler;

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        recycler.setLayoutManager(layoutManager );
//        MyRecyclerViewAdapter myRecyclerViewAdapter=new MyRecyclerViewAdapter(getData());
//        recycler.setAdapter(myRecyclerViewAdapter);
//        recycler.addItemDecoration(new GridSpacingItemDecoration(2,10,true));
//        recycler .setItemAnimator(new DefaultItemAnimator());
//        recycler.setHasFixedSize(true );
//        myRecyclerViewAdapter.setOnItemClickListener(this);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_3,null);
        recycler=(RecyclerView)view.findViewById(R.id.rc_lv);
        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
     recycler.setLayoutManager(layoutManager );
     MyRecyclerViewAdapter myRecyclerViewAdapter=new MyRecyclerViewAdapter(getData());
       recycler.setAdapter(myRecyclerViewAdapter);
        recycler.addItemDecoration(new GridSpacingItemDecoration(2,5,true));
       //recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        recycler .setItemAnimator(new DefaultItemAnimator());
       recycler.setHasFixedSize(true );
       myRecyclerViewAdapter.setOnItemClickListener(this);


        return view;
    }





    private List<Integer >getData(){
        resIds =new ArrayList<>() ;
        for(int i=0;i<80;i++) {
            resIds.add(R.drawable.p23);
            resIds.add(R.drawable.p22);
        }


        return  resIds ;
    }



    public void onClick(RecyclerView.ViewHolder viewHolder, int position){
        Toast .makeText(getContext(),"position:"+position ,Toast.LENGTH_SHORT).show();
    }


}
