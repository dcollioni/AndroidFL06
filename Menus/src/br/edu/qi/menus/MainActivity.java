package br.edu.qi.menus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends Activity {

	// Declara o layout
	LinearLayout layoutMain;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Pega o layout pelo ID
        layoutMain = (LinearLayout)
        		findViewById(R.id.layout_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(getBaseContext(), 
            				getString(R.string.action_settings), 
            				Toast.LENGTH_SHORT).show();
        	return true;
        }
        else if (id == R.id.action_sobre) {
        	Toast.makeText(
        			getBaseContext(),
        			getString(R.string.autor),
        			Toast.LENGTH_SHORT).show();
        	return true;
        }
        else if (id == R.id.action_azul) {
        	layoutMain.setBackgroundResource(R.color.azul);
        }
        return super.onOptionsItemSelected(item);
    }
}
