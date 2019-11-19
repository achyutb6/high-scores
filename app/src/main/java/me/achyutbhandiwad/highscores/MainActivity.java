/*Written by Achyut Bhandiwad for CS6326.001, assignment 5,starting October 21, 2018
 * Net ID: aab180004
 * This class is the activity for showing scores
 * When the Add button is pressed it goes to another activity to save a score and based the result from the other class it displays the results
 */

package me.achyutbhandiwad.highscores;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int ADD_SCORE_REQUEST = 1;  // The request code
    List<Score> scoresData; //list of scores
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoresData = new ArrayList<>();
        listView  = findViewById(R.id.scoresList);
        displayScores();

    }


    /*
    * Method to load the menu items on action bar
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        System.out.println("button pressed");
        if(item.getItemId() == R.id.add){ //check if the menu item pressed is "add"
            openAddScoreActivity();
            System.out.println("button pressed");
            return true;
        }

        return false;
    }

    /*
    * Method to open the add score activity
    * */
    private void openAddScoreActivity() {
        Intent addScoreIntent = new Intent(this,AddScoreActivity.class);
        startActivityForResult(addScoreIntent, ADD_SCORE_REQUEST);
    }

    /*
    * Method to inflate the menu options
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    /*
    * Helper method to display the high scores in list view after reading from the file
    * */
    private void displayScores(){
        FileIO fio = new FileIO(this);
        scoresData = fio.readFile();
        Collections.sort(scoresData);
        StableArrayAdapter scoreAdapter = new StableArrayAdapter(this,R.layout.scorelayout,scoresData);
        listView.setAdapter(scoreAdapter);
    }

    /*
    * This method is used to get the result from the Add score intent and if any score is added it writes it to file and updates list view
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_SCORE_REQUEST){
            /*If there is any new score added then update the listview with the new scores*/
            if(resultCode==RESULT_OK){

                String newName = data.getStringExtra("newName");
                String newScore = data.getStringExtra("newScore");
                String newDate = data.getStringExtra("newDate");
                
                Score newScoreObject = new Score(newName,newScore,newDate);
                
                scoresData.add(newScoreObject);

                FileIO fio = new FileIO(this);
                fio.writeFile(scoresData);
                displayScores();
            }
            if(resultCode==RESULT_CANCELED){
                /*If nothing is added in save score activity then display a message that nothing was added*/
                String message = "Nothing added";
                Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
