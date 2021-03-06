package me.doapps.curiosidad.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.doapps.curiosidad.R;
import me.doapps.curiosidad.utils.UtilFonts;

/**
 * Created by jonathan on 30/11/2014.
 */
public class Dialog_Curiosity extends AlertDialog {
    private Interface_Close interface_close;


    public Dialog_Curiosity(Context context) {
        super(context);
        initDialog();
    }

    protected Dialog_Curiosity(Context context, int theme) {
        super(context, theme);
        initDialog();
    }

    protected Dialog_Curiosity(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_curiosity, null);
        setView(view);

        setCancelable(false);

        Button btn_accept = (Button) view.findViewById(R.id.btn_accept);
        TextView txt_message = (TextView) view.findViewById(R.id.txt_message);
        txt_message.setTypeface(UtilFonts.setHelveticaThin(getContext()));
        btn_accept.setTypeface(UtilFonts.setHelveticaBold(getContext()));

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interface_close.getClose(true);
            }
        });
    }

    public interface Interface_Close{
        void getClose(boolean flag);
    }
    public void setInterface_close(Interface_Close interface_close){
        this.interface_close = interface_close;
    }
}
