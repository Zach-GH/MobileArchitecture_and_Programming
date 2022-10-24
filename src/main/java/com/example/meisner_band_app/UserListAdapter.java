package com.example.meisner_band_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<UserMap> usersList;

    public UserListAdapter(Context context, int layout, ArrayList<UserMap> usersList) {
        this.context = context;
        this.layout = layout;
        this.usersList = usersList;
    }

    @Override
    public int getCount() {
        return usersList.size();
    }

    @Override
    public Object getItem(int position) {
        return usersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView userName, userPass;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        UserListAdapter.ViewHolder holder = new UserListAdapter.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.userName = (TextView) row.findViewById(R.id.userName);
            holder.userPass = (TextView) row.findViewById(R.id.userPass);
            row.setTag(holder);
        }
        else {
            holder = (UserListAdapter.ViewHolder) row.getTag();
        }

        UserMap user = usersList.get(position);

        holder.userName.setText(user.getUsername());
        holder.userPass.setText(user.getPassword());

        return row;
    }
}
