package me.doapps.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import me.doapps.beans.Curiosidad_DTO;
import me.doapps.curiosity.Curiosity;
import me.doapps.curiosity.R;

/**
 * Created by Gantz on 28/11/14.
 */
public class Dialog_Detail extends AlertDialog {

    private Curiosidad_DTO curiosidad_dto;
    private ActionBarActivity actionBarActivity;

    public Dialog_Detail(Context context, ActionBarActivity actionBarActivity) {
        super(context);
        this.actionBarActivity = actionBarActivity;
        initDialog();
    }

    public Dialog_Detail(Context context, ActionBarActivity actionBarActivity, int theme) {
        super(context, theme);
        this.actionBarActivity = actionBarActivity;
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_detail, null);
        setView(view);

        curiosidad_dto = ((Curiosity) actionBarActivity).getCuriosidad_DTO();

        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);

        titulo.setText(curiosidad_dto.getNameCuriosidad().toUpperCase());
        descripcion.setText(curiosidad_dto.getDescriptionCuriosidad());
    }
}
