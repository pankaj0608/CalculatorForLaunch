package whizkid.amaya.calculatorforlaunch;

public class TestDialog {
//
//    package com.example.customlistviewdialog;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnCancelListener;
//import android.content.DialogInterface.OnDismissListener;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//    public class CustomListViewDialogActivity extends Activity {
//
//        Button buttonOpenDialog;
//
//        String KEY_TEXTPSS = "TEXTPSS";
//        static final int CUSTOM_DIALOG_ID = 0;
//
//        ListView dialog_ListView;
//
//        String[] listContent = {
//                "January", "February", "March", "April",
//                "May", "June", "July", "August", "September",
//                "October", "November", "December"};
//
//        /**
//         * Called when the activity is first created.
//         */
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.main);
//
//            buttonOpenDialog = (Button) findViewById(R.id.opendialog);
//            buttonOpenDialog.setOnClickListener(new Button.OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//                    showDialog(CUSTOM_DIALOG_ID);
//                }
//            });
//
//        }
//
//        @Override
//        protected Dialog onCreateDialog(int id) {
//
//            Dialog dialog = null;
//
//            switch (id) {
//                case CUSTOM_DIALOG_ID:
//                    dialog = new Dialog(CustomListViewDialogActivity.this);
//                    dialog.setContentView(R.layout.dialoglayout);
//                    dialog.setTitle("Custom Dialog");
//
//                    dialog.setCancelable(true);
//                    dialog.setCanceledOnTouchOutside(true);
//
//                    dialog.setOnCancelListener(new OnCancelListener() {
//
//                        @Override
//                        public void onCancel(DialogInterface dialog) {
//// TODO Auto-generated method stub
//                            Toast.makeText(CustomListViewDialogActivity.this,
//                                    "OnCancelListener",
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//                    dialog.setOnDismissListener(new OnDismissListener() {
//
//                        @Override
//                        public void onDismiss(DialogInterface dialog) {
//// TODO Auto-generated method stub
//                            Toast.makeText(CustomListViewDialogActivity.this,
//                                    "OnDismissListener",
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });
//
////Prepare ListView in dialog
//                    dialog_ListView = (ListView) dialog.findViewById(R.id.dialoglist);
//                    ArrayAdapter<String> adapter
//                            = new ArrayAdapter<String>(this,
//                            android.R.layout.simple_list_item_1, listContent);
//                    dialog_ListView.setAdapter(adapter);
//                    dialog_ListView.setOnItemClickListener(new OnItemClickListener() {
//
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view,
//                                                int position, long id) {
//// TODO Auto-generated method stub
//                            Toast.makeText(CustomListViewDialogActivity.this,
//                                    parent.getItemAtPosition(position).toString() + " clicked",
//                                    Toast.LENGTH_LONG).show();
//                            dismissDialog(CUSTOM_DIALOG_ID);
//                        }
//                    });
//
//                    break;
//            }
//
//            return dialog;
//        }
//
//        @Override
//        protected void onPrepareDialog(int id, Dialog dialog, Bundle bundle) {
//// TODO Auto-generated method stub
//            super.onPrepareDialog(id, dialog, bundle);
//
//            switch (id) {
//                case CUSTOM_DIALOG_ID:
////
//                    break;
//            }
//        }
//    }
}
