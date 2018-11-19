package com.library.android.common.viewholder;

import android.view.View;

import com.library.android.common.databinding.LayoutProgressBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LoadMoreViewHolder extends RecyclerView.ViewHolder {

    public LayoutProgressBinding mBinding;

    public LoadMoreViewHolder(@NonNull View itemView, LayoutProgressBinding mBinding) {
        super(itemView);
        this.mBinding = mBinding;
    }
}
