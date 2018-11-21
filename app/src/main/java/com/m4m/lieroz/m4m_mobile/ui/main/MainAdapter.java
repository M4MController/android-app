package com.m4m.lieroz.m4m_mobile.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.data.network.model.Object;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseViewHolder;
import com.m4m.lieroz.m4m_mobile.ui.object.ObjectActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.object_card)
        CardView mCardView;

        @BindView(R.id.object_card_title_view)
        TextView mCardTitleView;

        @BindView(R.id.object_card_address_view)
        TextView mStreetTitleView;

        @BindView(R.id.object_current_month_view)
        TextView mCurrentMonthView;

        @BindView(R.id.object_prev_year_view)
        TextView mPrevYearView;

        @BindView(R.id.object_year_avg_view)
        TextView mYearAvgView;

        public int mId;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, ObjectActivity.class);
                    intent.putExtra("title", mCardTitleView.getText());
                    intent.putExtra("address", mStreetTitleView.getText());
                    intent.putExtra("id", mId);
                    context.startActivity(intent);
                }
            });
        }

        protected void clear() {

        }
    }

    private List<Object> mUserObjectsResponseList;

    public MainAdapter(ArrayList<Object> userObjectsResponseList) {
        mUserObjectsResponseList = userObjectsResponseList;
    }

    public void setUserObjectsResponseList(List<Object> userObjectsResponseList) {
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
        Context context = holder.itemView.getContext();
        Object object = mUserObjectsResponseList.get(position);
        viewHolder.mId = object.getId();

        viewHolder.mCardTitleView.setText(object.getName());
        viewHolder.mStreetTitleView.setText(object.getAddress());
        viewHolder.mCurrentMonthView.setText(String.format(Locale.ENGLISH, "%.2f %s", object.getPayments().getCurrentMonth(), context.getResources().getString(R.string.currency_format)));
        viewHolder.mPrevYearView.setText(String.format(Locale.ENGLISH, "%.2f %s", object.getPayments().getPrevYear(), context.getResources().getString(R.string.currency_format)));
        viewHolder.mYearAvgView.setText(String.format(Locale.ENGLISH, "%.2f %s", object.getPayments().getYearAvg(), context.getResources().getString(R.string.currency_format)));
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mUserObjectsResponseList.size();
    }
}
