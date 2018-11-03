package com.m4m.lieroz.m4m_mobile.ui.object;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.data.network.model.Sensor;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseViewHolder;
import com.m4m.lieroz.m4m_mobile.ui.sensor.SensorActivity;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ObjectAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.sensor_card)
        CardView mCardView;

        @BindView(R.id.sensor_card_title)
        TextView mCardTitleView;

        @BindView(R.id.sensor_charge_view)
        TextView mChargeView;

        @BindView(R.id.sensor_overpayment_view)
        TextView mOverpaymentView;

        @BindView(R.id.sensor_total_view)
        TextView mTotalView;

        @BindView(R.id.sensor_current_month_view)
        TextView mCurrentMonthView;

        @BindView(R.id.sensor_prev_year_view)
        TextView mPrevYearView;

        @BindView(R.id.sensor_year_avg_view)
        TextView mYearAvgView;

        Sensor sensor;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = SensorActivity.getStartIntent(context);
                    intent.putExtra("title", mCardTitleView.getText());
                    intent.putExtra("address", mAddress);
                    intent.putExtra("sensor", (new Gson()).toJson(sensor));
                    context.startActivity(intent);
                }
            });
        }

        protected void clear() {

        }
    }

    private String mAddress;
    private List<Sensor> mUserSensorsResponseList;

    public ObjectAdapter(List<Sensor> userSensorsResponseList) {
        mUserSensorsResponseList = userSensorsResponseList;
    }

    public void setUserSensorsResponseList(List<Sensor> userSensorsResponseList) {
        mUserSensorsResponseList = userSensorsResponseList;
        notifyDataSetChanged();
    }
    public void setAddress(String address) {
        mAddress = address;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ObjectAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.object_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Context context = holder.itemView.getContext();
        Sensor sensor = mUserSensorsResponseList.get(position);

        viewHolder.mCardTitleView.setText(sensor.getName());
        viewHolder.mChargeView.setText(String.format(Locale.ENGLISH ,"%.2f %s", sensor.getPayments().getCharge(), context.getResources().getString(R.string.currency_format)));
        viewHolder.mOverpaymentView.setText(String.format(Locale.ENGLISH ,"%.2f %s", sensor.getPayments().getOverpayment(), context.getResources().getString(R.string.currency_format)));
        viewHolder.mTotalView.setText(String.format(Locale.ENGLISH ,"%.2f %s", sensor.getPayments().getForPayment(), context.getResources().getString(R.string.currency_format)));
        viewHolder.mCurrentMonthView.setText(String.format(Locale.ENGLISH ,"%.2f %s", sensor.getStats().getMonth(), sensor.getCharacteristics().getUnitOfMeasurement()));
        viewHolder.mPrevYearView.setText(String.format(Locale.ENGLISH ,"%.2f %s", sensor.getStats().getPrevMonth(), sensor.getCharacteristics().getUnitOfMeasurement()));
        viewHolder.mYearAvgView.setText(String.format(Locale.ENGLISH ,"%.2f %s", sensor.getStats().getPrevYear(), sensor.getCharacteristics().getUnitOfMeasurement()));
        viewHolder.sensor = sensor;
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mUserSensorsResponseList.size();
    }
}
