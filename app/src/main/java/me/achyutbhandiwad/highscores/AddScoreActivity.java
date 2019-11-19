/*Written by Achyut Bhandiwad for CS6326.001, assignment 5,starting October 21, 2018
 * Net ID: aab180004
 * This class is the activity for adding scores
 * This activity takes user input of Name, Score, Date/Time and validates it. If the save button is pressed the data is sent to the MainActivity
 */

package me.achyutbhandiwad.highscores;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddScoreActivity extends AppCompatActivity {

    EditText name;
    EditText score;
    EditText date;

    MenuItem save;

    boolean buttonFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);

        setTitle("Add Score");

        name = findViewById(R.id.newName);
        score = findViewById(R.id.newScore);
        date = findViewById(R.id.newDate);


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        date.setText(sdf.format(new Date()));

        name.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                validate();
            }
        });

        score.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                validate();
            }
        });

        date.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                validate();
            }
        });


    }

    private void validate(){
        /*Checking mandatory */
        String newName = name.getText().toString();
        String newScore = score.getText().toString();
        String newDate = date.getText().toString();
        boolean nameValid =  false,scoreValid = false, dateValid = false;

        if(newName.length() > 0 && newName.length() <=30){
            name.setError(null);
            nameValid = true;
        }else{
            name.setError("Name should be between 1-30 characters");
        }

        if(!newScore.isEmpty()){
            try {
                int intScore = Integer.parseInt(newScore);
                if (intScore <= 0) {
                    score.setError("Score should be a positive integer");
                } else {
                    scoreValid = true;
                    score.setError(null);
                }
            } catch (Exception e){
                score.setError("Score should be a positive integer");
            }
        }
        /*Check for the correctness of the date and also check if the date is future data or not*/
        if(!newDate.isEmpty()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            dateFormat.setLenient(false);
            Date current = new Date();
            try {
                Date nDate = dateFormat.parse(newDate.trim());
                int value = current.compareTo(nDate);
                if(value<0){
                    buttonFlag = false;
                    date.setError("Date cannot be from the future");
                }
                dateValid = true;
                date.setError(null);

            } catch (ParseException pe) {
                date.setError("Date should of the format MM/dd/yyyy HH:mm:ss");

            }

        }
        buttonFlag = nameValid && scoreValid && dateValid;
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_score, menu);
        save = menu.findItem(R.id.save);
        save.setEnabled(buttonFlag);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.save);

        if (buttonFlag) {
            item.setEnabled(true);
            name.setError(null);
            date.setError(null);
            score.setError(null);
            item.getIcon().setAlpha(255);
        } else {
            // disabled
            item.setEnabled(false);
            item.getIcon().setAlpha(130);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if(item.getItemId() == R.id.save){
            Intent returnIntent = new Intent();
            returnIntent.putExtra("newName", name.getText().toString());
            returnIntent.putExtra("newScore", score.getText().toString());
            returnIntent.putExtra("newDate", date.getText().toString());

            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            return true;
        }

        return false;
    }
}
