package leonoronhas.myweatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    ListView listView;
    Button tempButton;
    String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get input(city name) from user and save it to String
        editTextName = findViewById(R.id.editNameField);
        tempButton = findViewById(R.id.buttonTemp);

        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityName = editTextName.getText().toString();
                BackgroundRun br = new BackgroundRun(cityName);

                br.start();

            }
        });


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    BackgroundRun backgroundRun = new BackgroundRun(cityName);
                    WeatherCurrentTemp currentTemp = new WeatherCurrentTemp();
                     String data = currentTemp.toStrings();
                    backgroundRun.run();


               System.out.println("INSIDE RUNONUITHREAD");
                }
            });
        }
    }

