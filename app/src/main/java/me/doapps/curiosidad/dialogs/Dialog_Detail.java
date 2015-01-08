package me.doapps.curiosidad.dialogs;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

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

        final String temp_url = curiosidad_dto.getUrl();
        final String temp_data = curiosidad_dto.getNameCuriosidad()+": "+curiosidad_dto.getDescriptionCuriosidad();

        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);
        TextView txt_url = (TextView) view.findViewById(R.id.txt_url);
        ImageView img_photo = (ImageView) view.findViewById(R.id.img_photo);



        ImageView img_share = (ImageView) view.findViewById(R.id.img_share);
        ImageView img_whatsapp = (ImageView) view.findViewById(R.id.img_whatsapp);
        ImageView img_twitter = (ImageView) view.findViewById(R.id.img_twitter);
        ImageView img_facebook = (ImageView) view.findViewById(R.id.img_facebook);

        titulo.setTypeface(UtilFonts.setHelveticaBold(getContext()));
        descripcion.setTypeface(UtilFonts.setHelveticaThin(getContext()));

        titulo.setText(curiosidad_dto.getNameCuriosidad().toUpperCase());
        descripcion.setText(curiosidad_dto.getDescriptionCuriosidad());
        txt_url.setText(curiosidad_dto.getUrl());
        Picasso.with(getContext()).load(curiosidad_dto.getPhoto()).placeholder(R.drawable.curiosity_default).centerCrop().fit().into(img_photo);

        img_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse(temp_url));
                getContext().startActivity(webIntent);
            }
        });

        img_share.setOnClickListener(new View.OnClickListener() {
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
                    Log.e("error intent share", e.toString());
                }
            }
        });

        img_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, temp_data);
                try {
                    getContext().startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "No tiene instalado Whatsapp!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String twitterPackage = "com.twitter.android";
                String errorMessage = "You should install Twitter app first";

                    Intent tweetIntent = new Intent(Intent.ACTION_SEND);
                    tweetIntent.setType("text/*");
                    tweetIntent.setPackage(twitterPackage);
                    tweetIntent.putExtra(Intent.EXTRA_TEXT, temp_data);
                try{
                    getContext().startActivity(tweetIntent);
                }catch (ActivityNotFoundException ex){
                    Toast.makeText(getContext(), "No tiene instalado Twitter!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        img_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String facebookPackage = "com.facebook.katana";
                String errorMessage = "You should install Twitter app first";

                Intent fbIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                fbIntent.setType("text/plain");
                fbIntent.setPackage(facebookPackage);
                fbIntent.putExtra(Intent.EXTRA_TEXT, temp_data);
                try{
                    getContext().startActivity(fbIntent);
                }catch (ActivityNotFoundException ex){
                    Toast.makeText(getContext(), "No tiene instalado Facebook!", Toast.LENGTH_SHORT).show();
                }*/

                /*Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "\"https://play.google.com/store/apps/details?id=me.doapps.pondetuparte&hl=es\\n\\n\"");
                PackageManager pm = v.getContext().getPackageManager();
                List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
                for (final ResolveInfo app : activityList) {
                    if ((app.activityInfo.name).contains("facebook")) {
                        final ActivityInfo activity = app.activityInfo;
                        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        shareIntent.setComponent(name);
                        try{
                            v.getContext().startActivity(shareIntent);
                        }catch (ActivityNotFoundException ex){
                            Toast.makeText(getContext(), "No tiene instalado Facebook!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }*/


                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, (String) v.getTag(R.string.app_name));
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, (String) v.getTag(R.drawable.ic_launcher));
                PackageManager pm = v.getContext().getPackageManager();

                List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
                for (final ResolveInfo app : activityList) {
                    if ((app.activityInfo.name).contains("facebook")) {
                        final ActivityInfo activity = app.activityInfo;
                        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        shareIntent.setComponent(name);
                        try{
                            v.getContext().startActivity(shareIntent);
                        }catch (ActivityNotFoundException ex){
                            Toast.makeText(getContext(), "No tiene instalado Facebook!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }


            }
        });
    }
}
