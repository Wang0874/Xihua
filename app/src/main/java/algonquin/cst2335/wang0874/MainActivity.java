package algonquin.cst2335.wang0874;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import algonquin.cst2335.wang0874.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        //prefs.getString("VariableName", "");
        String emailAddress = prefs.getString("Email", "");

//        Log.e("MainActivity", "In onCreate()");
        //loads the XML file on screen;
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        binding.emailAddress.setText(emailAddress);
        binding.button.setOnClickListener(click ->
        {
            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);

            nextPage.putExtra("EmailAddress", binding.emailAddress.getText().toString());
            prefs.edit().putString("Email", binding.emailAddress.getText().toString()).commit();
            startActivity(nextPage);
        });


    }

}