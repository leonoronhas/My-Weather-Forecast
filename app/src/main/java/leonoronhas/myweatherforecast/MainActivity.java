package leonoronhas.myweatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    ListView listView;
    Button tempButton;
    Button forecastButton;
    String cityName;
    int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get input(city name) from user and save it to String
        editTextName = findViewById(R.id.editNameField);
        tempButton = findViewById(R.id.buttonTemp);
        forecastButton = findViewById(R.id.buttonForecast);
        listView = findViewById(R.id.listViewField);

        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = 1;
                getTemp();

            }
        });

        forecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = 2;
                getForecast();

            }
        });
    }

    private void getTemp() {
        cityName = editTextName.getText().toString();
        BackgroundRun br = new BackgroundRun(cityName, this, choice);

        br.start();
    }

    private void getForecast() {
        cityName = editTextName.getText().toString();
        BackgroundRun br = new BackgroundRun(cityName, this, choice);
        br.start();
    }
}

