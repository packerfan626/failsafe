package dori.dangeralert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__settings);

        Button accountButton = (Button) findViewById(R.id.account_button);
        accountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Main_Settings.this, Account_Settings.class));
            }
        });

        Button notificationsButton = (Button) findViewById(R.id.notifications_button);
        notificationsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Main_Settings.this, Notification_Settings.class));
            }
        });

        Button helpButton = (Button) findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Main_Settings.this, HelpSupport.class));
            }
        });
    }

}
