package com.m4m.lieroz.m4m_mobile.ui.object;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseViewHolder;
import com.m4m.lieroz.m4m_mobile.ui.sensor.SensorActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ObjectAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.sensor_card)
        CardView mCardView;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    context.startActivity(new Intent(context, SensorActivity.class));
                }
            });
        }

        protected void clear() {

        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ObjectAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.object_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
