package com.m4m.lieroz.m4m_mobile.ui.object;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.data.network.model.Sensor;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseViewHolder;
import com.m4m.lieroz.m4m_mobile.ui.sensor.SensorActivity;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

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

        @BindView(R.id.sensor_switch)
        Switch mSwitch;

        @BindView(R.id.sensor_icon)
        ImageView mIcon;

        @BindView(R.id.current_progress)
        View mCurrentProgress;

        @BindView(R.id.current_expected_progress)
        View mCurrentExpectedProgress;

        @BindView(R.id.prev_year_progress)
        View mPrevYearProgress;

        @BindView(R.id.prev_year_expected_progress)
        View mPrevYearExpectedProgress;

        @BindView(R.id.year_avg_progress)
        View mYearAvgProgress;

        @BindView(R.id.year_avg_expected_progress)
        View mYearAvgExpectedProgress;

        Sensor sensor;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, SensorActivity.class);
                    intent.putExtra("title", mCardTitleView.getText());
                    intent.putExtra("objectName", mObjectName);
                    intent.putExtra("sensor", (new Gson()).toJson(sensor));
                    context.startActivity(intent);
                }
            });
        }

        protected void clear() {

        }
    }

    private String mObjectName;
    private List<Sensor> mUserSensorsResponseList;

    public ObjectAdapter(List<Sensor> userSensorsResponseList) {
        mUserSensorsResponseList = userSensorsResponseList;
    }

    public void setUserSensorsResponseList(List<Sensor> userSensorsResponseList) {
        mUserSensorsResponseList = userSensorsResponseList;
        notifyDataSetChanged();
    }

    public void setObjectName(String objectName) {
        mObjectName = objectName;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ObjectAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.object_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        Context context = holder.itemView.getContext();
        Sensor sensor = mUserSensorsResponseList.get(position);

        viewHolder.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    viewHolder.mCardView.setEnabled(true);
                    viewHolder.mCardView.setAlpha(1.0f);
                } else {
                    viewHolder.mCardView.setEnabled(false);
                    viewHolder.mCardView.setAlpha(0.5f);
                }
            }
        });

        switch (sensor.getCharacteristics().getSensorType()) {
            case 1:
                viewHolder.mIcon.setImageResource(R.drawable.ic_electricity);
                break;
            case 2:
                viewHolder.mIcon.setImageResource(R.drawable.ic_water);
                break;
            case 3:
                viewHolder.mIcon.setImageResource(R.drawable.ic_hot_water);
                break;
            case 4:
                viewHolder.mIcon.setImageResource(R.drawable.ic_gas);
                break;
            default:
                viewHolder.mIcon.setImageResource(R.drawable.ic_menu_share);
                break;
        }

        double currMonth = sensor.getStats().getMonth();
        double prevYear = sensor.getStats().getPrevMonth();
        double yearAvg = sensor.getStats().getPrevYear();
        double max = Math.max(Math.max(currMonth, prevYear), yearAvg);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        Objects.requireNonNull(windowManager).getDefaultDisplay().getSize(size);
        int width = size.x;
        double single = width / max;

        int currentProgressWidth = (int) Math.round(single * currMonth);
        int currentExpectedProgressWidth = width - currentProgressWidth;
        viewHolder.mCurrentProgress.setLayoutParams(new LinearLayout.LayoutParams(currentProgressWidth, 4));
        viewHolder.mCurrentExpectedProgress.setLayoutParams(new LinearLayout.LayoutParams(currentExpectedProgressWidth < 0 ? 0 : currentExpectedProgressWidth, 4));

        int prevYearProgressWidth = (int) Math.round(single * prevYear);
        int prevYearExpectedProgressWidth = width - prevYearProgressWidth;
        viewHolder.mPrevYearProgress.setLayoutParams(new LinearLayout.LayoutParams(prevYearProgressWidth, 4));
        viewHolder.mPrevYearExpectedProgress.setLayoutParams(new LinearLayout.LayoutParams(prevYearExpectedProgressWidth < 0 ? 0 : prevYearExpectedProgressWidth, 4));

        int yearAvgProgressWidth = (int) Math.round(single * yearAvg);
        int yearAvgExpectedProgressWidth = width - yearAvgProgressWidth;
        viewHolder.mYearAvgProgress.setLayoutParams(new LinearLayout.LayoutParams(yearAvgProgressWidth, 4));
        viewHolder.mYearAvgExpectedProgress.setLayoutParams(new LinearLayout.LayoutParams(yearAvgExpectedProgressWidth < 0 ? 0 : yearAvgExpectedProgressWidth, 4));

        viewHolder.mCardTitleView.setText(sensor.getName());
        viewHolder.mChargeView.setText(String.format(Locale.ENGLISH, "%.2f %s", sensor.getPayments().getCharge(), context.getResources().getString(R.string.currency_format)));
        viewHolder.mOverpaymentView.setText(String.format(Locale.ENGLISH, "%.2f %s", sensor.getPayments().getOverpayment(), context.getResources().getString(R.string.currency_format)));
        viewHolder.mTotalView.setText(String.format(Locale.ENGLISH, "%.2f %s", sensor.getPayments().getForPayment(), context.getResources().getString(R.string.currency_format)));
        viewHolder.mCurrentMonthView.setText(String.format(Locale.ENGLISH, "%.2f %s", sensor.getStats().getMonth(), sensor.getCharacteristics().getUnitOfMeasurement()));
        viewHolder.mPrevYearView.setText(String.format(Locale.ENGLISH, "%.2f %s", sensor.getStats().getPrevMonth(), sensor.getCharacteristics().getUnitOfMeasurement()));
        viewHolder.mYearAvgView.setText(String.format(Locale.ENGLISH, "%.2f %s", sensor.getStats().getPrevYear(), sensor.getCharacteristics().getUnitOfMeasurement()));
        viewHolder.sensor = sensor;
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mUserSensorsResponseList.size();
    }
}
