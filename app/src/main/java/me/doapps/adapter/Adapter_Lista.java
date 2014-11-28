package me.doapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import me.doapps.beans.Curiosidad_DTO;
import me.doapps.curiosity.R;

public class Adapter_Lista extends BaseAdapter {

    private Context context;
    private List<Curiosidad_DTO> curiosidads;
    private LayoutInflater inflater;

    public Adapter_Lista(Context context, List<Curiosidad_DTO> curiosidads) {
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

            holder.txtname = (TextView) convertView.findViewById(R.id.txt_curiosidad);
            holder.txtnombre = (TextView) convertView.findViewById(R.id.txt_nombre_curiosidad);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txtname.setText(curiosidad_DTO.getInitialCuriosidad().toUpperCase());
        holder.txtnombre.setText(curiosidad_DTO.getNameCuriosidad().toUpperCase());

        return convertView;
    }

    static class Holder {
        private TextView txtname;
        private TextView txtnombre;
    }
}
