package com.example.studytools.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.studytools.R;
import com.example.studytools.models.User;
import com.example.studytools.network.DataHandler;

import java.util.ArrayList;

public class UserListAdapter extends ArrayAdapter<User> {

    private ArrayList<User> users;
    private Context context;


    public UserListAdapter(@NonNull Context context, ArrayList<User> users) {
        super(context, 0, users);
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = LayoutInflater.from(context).inflate(R.layout.admin_list_item, parent, false);
        }

        if (row != null) {

            TextView userName = row.findViewById(R.id.tv_userName);
            TextView password = row.findViewById(R.id.tv_password);
            final Button button = row.findViewById(R.id.button_acceptUser);

            userName.setText(users.get(position).getUsername());
            password.setText(users.get(position).getPassword());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataHandler.approveUser(context,Integer.parseInt(users.get(position).getId()));

                    button.setVisibility(View.INVISIBLE);
                }
            });

        }

        return row;
    }
}
