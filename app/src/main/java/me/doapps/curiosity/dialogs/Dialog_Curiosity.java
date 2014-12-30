package me.doapps.curiosity.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import me.doapps.curiosity.R;
import me.doapps.curiosity.utils.UtilFonts;

/**
 * Created by jonathan on 30/11/2014.
 */
public class Dialog_Curiosity extends AlertDialog {
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

        setCancelable(true);

        TextView txt_message = (TextView) view.findViewById(R.id.txt_message);
        txt_message.setTypeface(UtilFonts.setHelveticaThin(getContext()));
    }
}
