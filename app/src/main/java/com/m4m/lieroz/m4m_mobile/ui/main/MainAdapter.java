package com.m4m.lieroz.m4m_mobile.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
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

        double currMonth = object.getPayments().getCurrentMonth();
        double prevYear = object.getPayments().getPrevYear();
        double yearAvg = object.getPayments().getYearAvg();
        double max = Math.max(Math.max(currMonth, prevYear), yearAvg);

        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);
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
