package underground.university.com.lithium;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class Maps extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DbHelper db;
    private TextView textDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DbHelper(getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        try {
            drawer.setDrawerListener(toggle);
        }
        catch (Exception e)
        {

        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        try {
            navigationView.setNavigationItemSelectedListener(this);
        }
        catch (Exception e)
        {

        }

        textDebug = (TextView)findViewById(R.id.parserContent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        textDebug.setText("");

        if(id == R.id.nav_etage1)
        {
            for(HashMap<String, String> row : db.select(Contract.Event.TABLE_NAME, Contract.Event.COLUMN_LOCATION + " like 'D1%'"))
            {
                textDebug.setText(textDebug.getText() + "Début : " + row.get(Contract.Event.COLUMN_START) + "\n");
                textDebug.setText(textDebug.getText() + "Fin : " + row.get(Contract.Event.COLUMN_END) + "\n");
                textDebug.setText(textDebug.getText() + "Salle : " + row.get(Contract.Event.COLUMN_LOCATION) + "\n");
                textDebug.setText(textDebug.getText() + "Evènement : " + row.get(Contract.Event.COLUMN_CAPTION) + "\n");
                textDebug.setText(textDebug.getText() + "Description : " + row.get(Contract.Event.COLUMN_DESCRIPTION) + "\n\n");
            }
        }
        else if(id == R.id.nav_etage2)
        {
            for(HashMap<String, String> row : db.select(Contract.Event.TABLE_NAME, Contract.Event.COLUMN_LOCATION + " like 'D2%'"))
            {
                textDebug.setText(textDebug.getText() + "Début : " + row.get(Contract.Event.COLUMN_START) + "\n");
                textDebug.setText(textDebug.getText() + "Fin : " + row.get(Contract.Event.COLUMN_END) + "\n");
                textDebug.setText(textDebug.getText() + "Salle : " + row.get(Contract.Event.COLUMN_LOCATION) + "\n");
                textDebug.setText(textDebug.getText() + "Evènement : " + row.get(Contract.Event.COLUMN_CAPTION) + "\n");
                textDebug.setText(textDebug.getText() + "Description : " + row.get(Contract.Event.COLUMN_DESCRIPTION) + "\n\n");
            }
        }
        else if(id == R.id.nav_etage3)
        {
            for(HashMap<String, String> row : db.select(Contract.Event.TABLE_NAME, Contract.Event.COLUMN_LOCATION + " like 'D3%'"))
            {
                textDebug.setText(textDebug.getText() + "Début : " + row.get(Contract.Event.COLUMN_START) + "\n");
                textDebug.setText(textDebug.getText() + "Fin : " + row.get(Contract.Event.COLUMN_END) + "\n");
                textDebug.setText(textDebug.getText() + "Salle : " + row.get(Contract.Event.COLUMN_LOCATION) + "\n");
                textDebug.setText(textDebug.getText() + "Evènement : " + row.get(Contract.Event.COLUMN_CAPTION) + "\n");
                textDebug.setText(textDebug.getText() + "Description : " + row.get(Contract.Event.COLUMN_DESCRIPTION) + "\n\n");
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        try {
            drawer.closeDrawer(GravityCompat.START);
        }
        catch (Exception e)
        {

        }
        return true;
    }
}
