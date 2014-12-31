package me.doapps.curiosidad.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import me.doapps.curiosidad.beans.Curiosidad_DTO;
import me.doapps.curiosidad.R;
import me.doapps.curiosidad.utils.UtilFonts;

/**
 * Created by Gantz on 28/11/14.
 */
public class Dialog_Detail extends AlertDialog {

    private Curiosidad_DTO curiosidad_dto;
    private ActionBarActivity actionBarActivity;

    public Dialog_Detail(Context context, Curiosidad_DTO curiosidad_dto) {
        super(context);
        this.curiosidad_dto = curiosidad_dto;
        initDialog();
    }

    public Dialog_Detail(Context context, Curiosidad_DTO curiosidad_dto, int theme) {
        super(context, theme);
        this.curiosidad_dto = curiosidad_dto;
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_detail, null);
        setView(view);

        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);
        //TextView txt_share = (TextView)view.findViewById(R.id.txt_share);

        titulo.setTypeface(UtilFonts.setHelveticaBold(getContext()));
        descripcion.setTypeface(UtilFonts.setHelveticaThin(getContext()));
        //txt_share.setTypeface(UtilFonts.setHelveticaMedium(getContext()));

        titulo.setText(curiosidad_dto.getNameCuriosidad().toUpperCase());
        descripcion.setText(curiosidad_dto.getDescriptionCuriosidad());
        /*txt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Curiosidades");
                    String sAux = "\nTe invito a descargar esta aplicación:\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=me.doapps.pondetuparte&hl=es\n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    getContext().startActivity(Intent.createChooser(i, "Compartir"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });*/
    }
}
