# high-scores
Android application to store high scores

Designing for mobile is different from designing for a large-screen PC.  
This assignment is to write the part of a game that tracks high scores.  
The fields of interest are the name of the user, the score, and the date/time that the user got that score.  
For purposes of this assignment you will enter all three fields.  
Here are the details:

#Fields:
  A.	Player name.  This should allow a maximum of 30 characters.  No error checking is needed other than not empty.
  B.	Score.  This should accept only positive integers.
  C.	Date/time.  Must be validity-checked.  You can auto-populate this with the current date/time.
  
  
When the program comes up, you should see a list of the high scores.  That list should show the name of the person, the date, and the score in columns, and must be in numerical order by high score.  In the case of duplicate scores, the most recent date should take precedence.  The list must scroll if there are more scores than will fit on the screen.  This is your main Activity.
An add button on the action bar will bring up a separate screen (Android activity) to add a new high score.  
When you finish adding and save, the add screen should close and the new score should be in the list.  
The Save button can be either on the entry screen or on the action bar, your choice. 
Scores are stored in a tab-delimited text file, not in a SQLite database.  
You should completely rewrite this file when any changes are made.  
The I/O should be in a separate class, not in the main activity.  
You are allowed to use Java container class sorting functions rather than writing your own.
Apply both Android design principles and the various things we have learned about design so far in the course.
