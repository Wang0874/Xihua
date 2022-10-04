package algonquin.cst2335.wang0874;

import androidx.activity.result.ActivityResult;

import androidx.activity.result.ActivityResultCallback;
//import androidx.activity.result.ActivityResultLauncher;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import algonquin.cst2335.wang0874.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding binding;
    ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent data = result.getData();
                        Bitmap thumbnail = data.getParcelableExtra("data");
                        binding.profileImage.setImageBitmap(thumbnail);

                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        binding.textView.setText("Welcome " + emailAddress);

        binding.button2.setOnClickListener(click ->
                {

                    Intent call = new Intent(Intent.ACTION_DIAL);
                    String phoneNumber = binding.editTextPhone.getText().toString();
                    call.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(call);

                }
        );

       binding.button3.setOnClickListener(click ->
       {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);



        cameraResult.launch(cameraIntent);
        });

    }

}