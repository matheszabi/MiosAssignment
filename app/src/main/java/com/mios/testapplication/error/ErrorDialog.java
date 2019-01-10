package com.mios.testapplication.error;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;

public class ErrorDialog {

    public static void showDialog(Context ctx, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx, android.R.style.Theme_Material_Dialog_Alert);
        builder.setMessage(msg);

        builder.setCancelable(false);
        builder.setIcon(android.R.drawable.ic_dialog_alert);



        builder.setPositiveButton(
                "OK",
                (dialog, id) -> dialog.dismiss());

        AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 200, 200)));
        alertDialog.show();
    }
}
