package com.example.studytools.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studytools.R;
import com.example.studytools.models.ExamResult;

import java.util.ArrayList;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ResultListViewHolder> {

    private final ArrayList<ExamResult> mResultList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public ResultListAdapter(Context context, ArrayList<ExamResult> mResultList) {
        this.mResultList = mResultList;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public ResultListAdapter.ResultListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mLayoutInflater.inflate(R.layout.result_list_item, parent, false);
        return new ResultListViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultListAdapter.ResultListViewHolder holder, int position) {
        ExamResult exam = mResultList.get(position);

        final String name = exam.getName();
        final String examTitle = exam.getExamTitle();
        final int result = exam.getResult();

        holder.mNameTextView.setText(name);
        holder.mTitleTextView.setText(examTitle);
        holder.mScoreTextView.setText("Result : " + String.valueOf(result));
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public class ResultListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public final TextView mNameTextView;
        public final TextView mTitleTextView;
        public final TextView mScoreTextView;

        final ResultListAdapter fileListAdapter;

        public ResultListViewHolder(View itemView, ResultListAdapter fileListAdapter) {
            super(itemView);
            this.fileListAdapter = fileListAdapter;
            mNameTextView = itemView.findViewById(R.id.textView_StudentName);
            mTitleTextView = itemView.findViewById(R.id.textView_examResultTitle);
            mScoreTextView = itemView.findViewById(R.id.textView_examResult);
            //  itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            String examTitle = mResultList.get(position).getExamTitle();
            Toast.makeText(mContext, examTitle, Toast.LENGTH_SHORT).show();
        }
    }
}
