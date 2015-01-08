package me.doapps.curiosidad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.doapps.curiosidad.beans.Curiosidad_DTO;
import me.doapps.curiosidad.R;

public class Adapter_Lista extends BaseAdapter {

    private Context context;
    private ArrayList<Curiosidad_DTO> curiosidads;
    private LayoutInflater inflater;

    public Adapter_Lista(Context context, ArrayList<Curiosidad_DTO> curiosidads) {
        this.context = context;
        this.curiosidads = curiosidads;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return curiosidads.size();
    }

    @Override
    public Object getItem(int position) {
        return curiosidads.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        Curiosidad_DTO curiosidad_DTO = curiosidads.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_curiosidad, parent, false);
            holder = new Holder();

            holder.txtnombre = (TextView) convertView.findViewById(R.id.txt_nombre_curiosidad);
            holder.img_curiosity = (ImageView)convertView.findViewById(R.id.img_curiosity);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txtnombre.setText(curiosidad_DTO.getNameCuriosidad().toUpperCase());
        Picasso.with(context).load(curiosidad_DTO.getImage_url()).placeholder(R.drawable.curiosity_default).centerCrop().fit().into(holder.img_curiosity);

        return convertView;
    }

    static class Holder {
        private TextView txtnombre;
        private ImageView img_curiosity;
    }
}
