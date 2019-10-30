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
                getTemp();
            }
        });
    }

    private void getTemp() {
        cityName = editTextName.getText().toString();
        BackgroundRun br = new BackgroundRun(cityName, this);

        br.start();
    }

    public void checkInvalidInput() {
        // Set Toast location for invalid input
        Toast toast = Toast.makeText(this, "Please enter a valid city!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.START, 35, -470);
        toast.show();
    }
}

