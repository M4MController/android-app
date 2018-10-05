package com.m4m.lieroz.m4m_mobile.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseViewHolder;
import com.m4m.lieroz.m4m_mobile.ui.object.ObjectActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.object_card)
        CardView mCardView;

        @BindView(R.id.object_card_title)
        TextView mCardTitle;

        @BindView(R.id.object_card_street_view)
        TextView mCardStreet;

        @BindView(R.id.object_status)
        ImageView mStatusIcon;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    context.startActivity(new Intent(context, ObjectActivity.class));
                }
            });
        }

        protected void clear() {

        }
    }

    private List<UserRelationsResponse.Object> mUserObjectsResponseList;

    public MainAdapter(ArrayList<UserRelationsResponse.Object> userObjectsResponseList) {
        mUserObjectsResponseList = userObjectsResponseList;
    }

    public void setUserObjectsResponseList(List<UserRelationsResponse.Object> userObjectsResponseList) {
        mUserObjectsResponseList = userObjectsResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        int icon = position % 3;
        switch (icon) {
            case 0:
                viewHolder.mStatusIcon.setImageResource(R.drawable.ic_ok);
                break;
            case 1:
                viewHolder.mStatusIcon.setImageResource(R.drawable.ic_warning);
                break;
            case 2:
                viewHolder.mStatusIcon.setImageResource(R.drawable.ic_error);
                break;
        }

        viewHolder.mCardTitle.setText(mUserObjectsResponseList.get(position).getName());
        viewHolder.mCardStreet.setText(mUserObjectsResponseList.get(position).getAddress());
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mUserObjectsResponseList.size();
    }
}
