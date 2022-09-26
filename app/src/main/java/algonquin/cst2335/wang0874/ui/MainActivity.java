package algonquin.cst2335.wang0874.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.wang0874.data.MainViewModel;
import algonquin.cst2335.wang0874.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        TextView mytext = variableBinding.textview;
        Button btn = variableBinding.mybutton;
        EditText myedit = variableBinding.myedittext;

        btn.setOnClickListener(click -> {
            model.editString.postValue(myedit.getText().toString());
        });

        model.editString.observe(this, s->{
            variableBinding.textview.setText("Your edit text has: "+s);
        });


        model.isSelected.observe(this,isChecked ->{
            variableBinding.checkBox.setChecked(isChecked);
            variableBinding.switch1.setChecked(isChecked);
            variableBinding.radioButton.setChecked(isChecked);
        } );

        variableBinding.checkBox.setOnCheckedChangeListener((checkBox, isChecked)-> {
            model.isSelected.postValue(isChecked);
            Toast toast = Toast.makeText(this, "You clicked on the "+ "Checkbox" + " and it is now: " + isChecked , Toast.LENGTH_SHORT);
            toast.show();
        });

        variableBinding.switch1.setOnCheckedChangeListener((switch1, isChecked)-> {
            model.isSelected.postValue(isChecked);
            Toast toast = Toast.makeText(this, "You clicked on the "+ "Switch" + " and it is now: " + isChecked , Toast.LENGTH_SHORT);
            toast.show();
        });

        variableBinding.radioButton.setOnCheckedChangeListener((radioButton, isChecked)-> {
            model.isSelected.postValue(isChecked);
            Toast toast = Toast.makeText(this, "You clicked on the "+ "Radio Button" + " and it is now: " + isChecked , Toast.LENGTH_SHORT);
            toast.show();
        });

        variableBinding.myimagebutton.setOnClickListener(click -> {
            Toast toast = Toast.makeText(this, "The width =" + variableBinding.myimagebutton.getWidth() + " and height = " + variableBinding.myimagebutton.getHeight(), Toast.LENGTH_SHORT);
            toast.show();
        });

    }}


