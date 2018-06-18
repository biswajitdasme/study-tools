package com.example.studytoolsTemp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.activity.PDFViewerActivity;
import com.example.studytoolsTemp.models.FileInfo;

import java.util.ArrayList;

import static com.example.studytoolsTemp.data.constant.AppConstant.BASE_URL;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.FileListViewHolder> {

    private final ArrayList<FileInfo> mFileList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public FileListAdapter(Context context, ArrayList<FileInfo> mFileList) {
        this.mFileList = mFileList;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public FileListAdapter.FileListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mLayoutInflater.inflate(R.layout.file_list_item, parent, false);
        return new FileListViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FileListAdapter.FileListViewHolder holder, int position) {
        FileInfo file = mFileList.get(position);

        final String fileDescription = file.getDescription();
        final String fileName = file.getFilename();
        String uploaderName = file.getUsername();

        holder.mDescriptionTextView.setText(fileDescription);
        holder.mUploaderTextView.setText("By : " + uploaderName);

        holder.mDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(BASE_URL + "file/" + fileName));

                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(intent);
                }
            }
        });

        holder.mOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), PDFViewerActivity.class);

                intent.putExtra("url", BASE_URL + "file/" + fileName);
                intent.putExtra("name", fileDescription);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFileList.size();
    }

    public class FileListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mDescriptionTextView;
        public final TextView mUploaderTextView;
        public final Button mDownButton;
        public final Button mOpenButton;
        final FileListAdapter fileListAdapter;

        public FileListViewHolder(View itemView, FileListAdapter fileListAdapter) {
            super(itemView);
            this.fileListAdapter = fileListAdapter;
            mDescriptionTextView = itemView.findViewById(R.id.textView_fileName);
            mUploaderTextView = itemView.findViewById(R.id.textView_uploaderName);
            mDownButton = itemView.findViewById(R.id.button_download);
            mOpenButton = itemView.findViewById(R.id.button_open);
            //  itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            String fileName = mFileList.get(position).getFilename();
            Toast.makeText(mContext, fileName, Toast.LENGTH_SHORT).show();


        }
    }
}
