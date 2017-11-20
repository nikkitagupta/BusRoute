package in.nikita.busroute.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import in.nikita.busroute.R;


/**
 * Created by Nikita Gupta on 17-11-2017.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private int menuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        menuLayout = R.menu.default_menu;
    }

    // Add toolbar, toolbar title and enable back button
    protected void initToolBar(Toolbar toolbar, String title) {
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    protected void startActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    // Add specified menu based on the the menu layout passed inside the Activity
    protected void addMenu(int menuLayout) {
        this.menuLayout = menuLayout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menuLayout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
