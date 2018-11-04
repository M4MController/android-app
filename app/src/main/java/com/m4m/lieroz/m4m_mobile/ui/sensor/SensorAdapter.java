package com.m4m.lieroz.m4m_mobile.ui.sensor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseViewHolder;

import butterknife.ButterKnife;

public class SensorAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public class ViewHolder extends BaseViewHolder {

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }
    }

    public SensorAdapter() {
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SensorAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rule, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
