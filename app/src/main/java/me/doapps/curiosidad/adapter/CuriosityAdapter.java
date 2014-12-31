package me.doapps.curiosidad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import me.doapps.curiosidad.R;
import me.doapps.curiosidad.beans.Curiosity_DTO;

/**
 * Created by mili on 30/12/2014.
 */
public class CuriosityAdapter extends BaseAdapter implements Filterable{
    private ArrayList<Curiosity_DTO> curiosidad_dtos;
    private ArrayList<Curiosity_DTO> mcuriosidad_dtos;
    private LayoutInflater inflater;
    private Context context;

    public CuriosityAdapter(ArrayList<Curiosity_DTO> curiosidad_dtos, Context context){
        this.curiosidad_dtos = curiosidad_dtos;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.mcuriosidad_dtos = new ArrayList<Curiosity_DTO>();
        this.mcuriosidad_dtos.addAll(curiosidad_dtos);
    }

    @Override
    public int getCount() {
        return curiosidad_dtos.size();
    }

    @Override
    public Object getItem(int position) {
        return curiosidad_dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        Curiosity_DTO curiosidad_dto = curiosidad_dtos.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_curiosity, parent, false);
            holder = new Holder();

            holder.txt_id = (TextView) convertView.findViewById(R.id.txt_id);
            holder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txt_name.setText(curiosidad_dto.getNameCuriosidad());
        holder.txt_id.setText(curiosidad_dto.getId());

        return convertView;
    }


    class Holder {
        TextView txt_name;
        TextView txt_id;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<Curiosity_DTO> filterList = new ArrayList<Curiosity_DTO>();
                for (int i = 0; i < mcuriosidad_dtos.size(); i++) {
                    if ((mcuriosidad_dtos.get(i).getNameCuriosidad().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        Curiosity_DTO curiosidad_dto = new Curiosity_DTO();
                        curiosidad_dto.setId(mcuriosidad_dtos.get(i).getId());
                        curiosidad_dto.setNameCuriosidad(mcuriosidad_dtos.get(i).getNameCuriosidad());
                        curiosidad_dto.setDescriptionCuriosidad(mcuriosidad_dtos.get(i).getDescriptionCuriosidad());
                        curiosidad_dto.setImage_url(mcuriosidad_dtos.get(i).getImage_url());
                        filterList.add(curiosidad_dto);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mcuriosidad_dtos.size();
                results.values = mcuriosidad_dtos;
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            curiosidad_dtos = (ArrayList<Curiosity_DTO>) results.values;
            notifyDataSetChanged();
        }
    };

}
