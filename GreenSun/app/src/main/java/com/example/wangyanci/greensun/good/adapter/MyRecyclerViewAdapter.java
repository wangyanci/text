package com.example.wangyanci.greensun.good.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangyanci.greensun.R;
import com.example.wangyanci.greensun.good.ViewHolder.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyanci on 2017/5/1.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {

    private List<Integer> resIds;
    private List<Integer>mHeight;


    public MyRecyclerViewAdapter( List<Integer> resIds){
        this.resIds=resIds;
        mHeight =new ArrayList<>();
        for(int i=0;i<resIds.size();i++){
            mHeight.add((int)(100+Math .random()*300));
        }
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()) .inflate(R.layout.recycler_view_item,parent,false );
        return new MyRecyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final MyRecyclerViewHolder holder, final int position) {
        holder.imageview.setImageResource(resIds.get(position ));




        ViewGroup .LayoutParams layoutParams =holder .imageview.getLayoutParams();
        layoutParams .height =mHeight .get(position);
        holder .imageview .setLayoutParams(layoutParams);

        holder.imageview.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(holder ,position );
                }
            }
        });

        holder.imageview.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View view ){
                if(onItemLongClickListener!=null ){
                    onItemLongClickListener.onLongClick(holder ,position);
                    return true ;
                }
                return false ;
            }
        });
    }

    @Override
    public int getItemCount() {
        return resIds.size();
    }


    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener=onItemLongClickListener;
    }


  public   interface OnItemClickListener{
        void onClick(RecyclerView.ViewHolder viewHolder,int position);
    }

   public  interface OnItemLongClickListener{
        void onLongClick(RecyclerView.ViewHolder viewHolder,int position);
    }
}
