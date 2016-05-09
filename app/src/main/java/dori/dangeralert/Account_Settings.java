package dori.dangeralert;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Account_Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__settings);

        Button passwordChange = (Button) findViewById(R.id.changePasswordButton);
        Button accountChange = (Button) findViewById(R.id.changeRequest);


        accountChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Button accountChange = (Button) v;
                AlertDialog.Builder builder = new AlertDialog.Builder(Account_Settings.this);
                builder.setTitle("Submit Account Changes for Approval?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AlertDialog.Builder confirmed = new AlertDialog.Builder(Account_Settings.this);
                        confirmed.setMessage("You will be sent an email when your Account changes have" +
                                " accepted");
                        confirmed.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        confirmed.show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AlertDialog.Builder cancelled = new AlertDialog.Builder(Account_Settings.this);
                        cancelled.setMessage("Request has been cancelled");
                        cancelled.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        cancelled.show();
                    }
                });
        builder.show();
            }
        });
    }

}
