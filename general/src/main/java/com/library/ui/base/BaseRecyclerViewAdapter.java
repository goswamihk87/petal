package com.library.ui.base;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.library.ui.listener.ItemClickListener;

import java.util.List;


/**
 * Base class for all adapters for {@link RecyclerView}
 *
 * @param <Model>      model pojo class
 * @param <ViewHolder> static inner class that extends {@link RecyclerView.ViewHolder}
 */
public abstract class BaseRecyclerViewAdapter<Model, ViewHolder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<ViewHolder> {

    private final Context mContext;
    private final List<Model> mList;
    private final LayoutInflater mInflater;

    @Nullable
    private ItemClickListener<Model> mItemClickListener;

    public BaseRecyclerViewAdapter(Context context, List<Model> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @CallSuper
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model model = mList.get(position);
        bind(holder, model);
        if (mItemClickListener == null) return;
        holder.itemView.setOnClickListener(view -> {
            if (mItemClickListener == null) return; //listening click is asynchronous
            mItemClickListener.onItemClicked(holder.getAdapterPosition(), model);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @CallSuper
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mItemClickListener = null;
        super.onDetachedFromRecyclerView(recyclerView);
    }

    //region abstract methods
    @Override
    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    protected abstract void bind(ViewHolder holder, Model model);
    //endregion

    //region public api methods
    @Nullable
    public ItemClickListener<Model> getItemClickListener() {
        return mItemClickListener;
    }

    public void setItemClickListener(@Nullable ItemClickListener<Model> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @NonNull
    public Context getContext() {
        return mContext;
    }

    @NonNull
    public List<Model> getList() {
        return mList;
    }

    @NonNull
    public LayoutInflater getInflater() {
        return mInflater;
    }
    //endregion

}
