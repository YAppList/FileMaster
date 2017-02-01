package com.ejia.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.example.yangzhongyu.myapplication.R;

/**
 * Created by yangzhongyu on 2017/2/1.
 */
public class AlertDialogFragment extends DialogFragment {


    public interface DialogFragmentClickImpl {
        void doPositiveClick();

        void doNegativeClick();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.icon)
                .setTitle("确认退出应用")
                .setMessage("确认退出应用")
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DialogFragmentClickImpl impl = (DialogFragmentClickImpl) getActivity();
                                impl.doPositiveClick();
                            }
                        }
                )
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DialogFragmentClickImpl impl = (DialogFragmentClickImpl) getActivity();
                                impl.doNegativeClick();
                            }
                        }
                )
                .create();
    }
}