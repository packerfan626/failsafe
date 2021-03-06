package dori.dangeralert;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.util.List;

public class MainScreen extends AppCompatActivity {
    private String m_Text = "";
    boolean activate = false;
    private static final int VIDEO_CAPTURE = 101;
    int numOfAttempts = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //Instance for when Danger button is clicked on
        Button dangerButton = (Button) findViewById(R.id.danger_button);
        dangerButton.setText("Tap Here to Activate the DangerSignal");

        dangerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Initialize Variables for use
                final Button danger_button = (Button) v;
                final int redColorValue = Color.RED;
                final int normalValue = Color.GRAY;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainScreen.this);
                builder.setTitle("Confirmation:");

                //Set-Up Input
                final EditText input = new EditText(MainScreen.this);
                //Specifying Input Type
                //If button current text is == DangerAlert ACTIVATED
                if (danger_button.getText() == "DangerAlert ACTIVATED") {
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    builder.setView(input);
                    //If a password is entered and "OK" is pressed
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            m_Text = input.getText().toString();
                            if (m_Text != "") {
                                danger_button.setText("Tap Here to Activate the DangerSignal");
                                danger_button.setBackgroundColor(normalValue);
                            }
                        }
                    });
                    //If cancel is selected
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            m_Text = "";
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    builder.setMessage("Are you sure you want to ACTIVATE the DangerAlert Alarm? Timer: 00:10 seconds until automatic alert.");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            danger_button.setText("DangerAlert ACTIVATED");
                            danger_button.setBackgroundColor(redColorValue);

                            File mediaFile =
                                    new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                            + "/myvideo.mp4");

                            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                            Uri videoUri = Uri.fromFile(mediaFile);

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                            startActivityForResult(intent, VIDEO_CAPTURE);

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder verify = new AlertDialog.Builder(MainScreen.this);
                            verify.setTitle("Confirm: Enter password to cancel request");
                            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                            verify.setMessage("Please enter Password to DeActivate Alarm. ");


                            verify.setView(input);
                            m_Text = input.getText().toString();

                            verify.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (m_Text == "") {
                                        dialog.cancel();
                                    }
                                }
                            });
                            verify.show();
                            dialog.cancel();

                        }
                    });
                    builder.show();
                }
            }
        });

        //Opens the settings page
        Button settingsButton = (Button) findViewById(R.id.settings_button);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, Main_Settings.class));

            }
        });


        Button viewButton = (Button) findViewById(R.id.view_button);
        viewButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            startActivity(new Intent(MainScreen.this, ViewMap.class));
        }
        });
    }


    private static final int SPEECH_REQUEST_CODE = 0;

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            if(spokenText == "Danger")
            {
                final int redColorValue = Color.RED;
                Button dangerButton = (Button) findViewById(R.id.danger_button);
                dangerButton.setText("DangerAlert ACTIVATED");
                dangerButton.setBackgroundColor(redColorValue);

                File mediaFile =
                        new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                + "/myvideo.mp4");

                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                Uri videoUri = Uri.fromFile(mediaFile);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                startActivityForResult(intent, VIDEO_CAPTURE);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}
