package com.academy.tracuunganhkt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class EconomicsAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Economics> economicsList;

    public EconomicsAdapter(Context context, int layout, List<Economics> economicsList) {
        this.context = context;
        this.layout = layout;
        this.economicsList = economicsList;
    }

    @Override
    public int getCount() {
        return economicsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtId;
        TextView txtLevel;
        TextView txtTitleName;
        TextView txtName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();

        if (convertView==null) {
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(layout,null);
            holder.txtId = convertView.findViewById(R.id.txtId);
            holder.txtLevel = convertView.findViewById(R.id.txtLevel);
            holder.txtTitleName = convertView.findViewById(R.id.txtTitleName);
            holder.txtName = convertView.findViewById(R.id.txtName);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Economics economics = economicsList.get(position);
        holder.txtId.setText("MÃ : "+economics.getId());
        holder.txtLevel.setText("CẤP : "+economics.getLevel());
        holder.txtName.setText(economics.getName());
        return convertView;
    }
}
