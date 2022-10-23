package algonquin.cst2335.wang0874;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This program checks the user's password meets requirements.
 * @author Xihua Wang
 * @version 1.0
 * @since 2022-10-22
 */

public class MainActivity extends AppCompatActivity {

    /**This holds the text at the center of the screen*/
    private TextView tv = null;
    /**This holes the password*/
    private EditText et = null;
    /**This holds the login button*/
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //loads the XML file on screen;
        setContentView(R.layout.activity_main);
        /* This is the "Type your Password:"  */
        TextView tv = findViewById(R.id.textView);
        /* This is the password  */
        EditText et = findViewById(R.id.editText);
        /* This is the login button  */
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(click -> {
            String password = et.getText().toString();

            if (checkPasswordComplexity(password)) {
                tv.setText("Your password meets the requirements");
            } else {
                tv.setText("You shall not pass!");
            }
        });
    }
        /** This function checks if the password at least contains an Upper Case letter,
         * a lower case letter, a number, and a special symbol including(#$%^&*!@?).
         * @param pw The password to be checked
         * @return Return true if the password meets requirements
         */
        boolean checkPasswordComplexity(String pw) {

            boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
            foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

            for (int i = 0; i < pw.length(); i++){
                if ( Character.isUpperCase(pw.charAt(i)) ){
                    foundUpperCase = true;
                }
                if ( Character.isLowerCase(pw.charAt(i)) ) {
                    foundLowerCase = true;
                }
                if ( Character.isDigit(pw.charAt(i)) ) {
                    foundNumber = true;
                }
                if ( isSpecialCharacter(pw.charAt(i)) ) {
                    foundSpecial = true;
                }
            }
            if(!foundUpperCase) {
                Toast.makeText(this, "The password is missing an upper case letter.", Toast.LENGTH_LONG).show();
                return false;
            }

            else if(!foundLowerCase) {
                Toast.makeText(this, "The password is missing a lower case letter.", Toast.LENGTH_LONG).show();
                return false;
            }

            else if(!foundNumber) {
                Toast.makeText(this, "The password is missing a number.", Toast.LENGTH_LONG).show();
                return false;
            }

            else if(!foundSpecial) {
                Toast.makeText(this, "The password is missing a special character.", Toast.LENGTH_LONG).show();
                return false;
            }
            else {
                return true;
            }
        }
    /** This function checks all special character in the password
     * @param c The special character which will be checked
     * @return Return true if the the password contains special character
     */
    private boolean isSpecialCharacter(char c){
        switch (c) {
            case '?':
            case '!':
            case '@':
            case '$':
            case '%':
            case '&':
            case '*':
            case '#':
            case '^':
                return true;
            default:
                return false;
        }
    }

}
