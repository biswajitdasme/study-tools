package com.example.studytoolsTemp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.activity.student.StudentExamList;
import com.example.studytoolsTemp.activity.student.StudentQuizActivity;
import com.example.studytoolsTemp.models.Exam;

import java.util.ArrayList;

public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ExamListViewHolder>  {

    private final ArrayList<Exam> mExamList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public ExamListAdapter(Context context, ArrayList<Exam> mExamList) {
        this.mExamList = mExamList;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public ExamListAdapter.ExamListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mLayoutInflater.inflate(R.layout.exam_list_item, parent, false);
        return new ExamListViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamListAdapter.ExamListViewHolder holder, int position) {
        Exam exam = mExamList.get(position);

        final String examTitle = exam.getTitle();
        final int examId = exam.getId();

        holder.mTitleTextView.setText(examTitle);

        holder.mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), StudentQuizActivity.class);
                intent.putExtra("examId",examId);
               // mContext.startActivity(intent);
                ((Activity) mContext).startActivityForResult(intent, StudentExamList.REQUEST_QUIZ_CODE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExamList.size();
    }

    public class ExamListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView mTitleTextView;
        public final Button mStartButton;

        final ExamListAdapter fileListAdapter;

        public ExamListViewHolder(View itemView, ExamListAdapter fileListAdapter) {
            super(itemView);
            this.fileListAdapter = fileListAdapter;
            mTitleTextView = itemView.findViewById(R.id.textView_examTitle);
            mStartButton = itemView.findViewById(R.id.button_start_exam);
            //  itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            String examTitle = mExamList.get(position).getTitle();
            Toast.makeText(mContext,examTitle,Toast.LENGTH_SHORT).show();
        }
    }
}
