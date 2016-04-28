package android.curso.navegacion1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.curso.navegacion1.tabs.Pagina1;
import android.curso.navegacion1.tabs.Pagina2;
import android.curso.navegacion1.tabs.Pagina3;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TabHost mTabHostInfo;
    private ViewPager mViwePagerInfo;
    private TabsAdapter mTabsAdapterInfo;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        mTabHostInfo = (TabHost)findViewById(android.R.id.tabhost);
        mTabHostInfo.setup();

        mViwePagerInfo = (ViewPager)findViewById(R.id.pagerInfo);
        mTabsAdapterInfo = new TabsAdapter(this, mTabHostInfo, mViwePagerInfo);

        TabWidget widget=mTabHostInfo.getTabWidget();
        widget.setVisibility(View.VISIBLE);

        mTabsAdapterInfo.addTab(mTabHostInfo.newTabSpec("Pagina1").setIndicator("Pagina1"), Pagina1.class, null);
        mTabsAdapterInfo.addTab(mTabHostInfo.newTabSpec("Pagina2").setIndicator("Pagina2"), Pagina2.class, null);
        mTabsAdapterInfo.addTab(mTabHostInfo.newTabSpec("Pagina3").setIndicator("Pagina3"), Pagina3.class, null);

        for (int i = 0; i < mTabHostInfo.getTabWidget().getChildCount(); i++) {
            View view = mTabHostInfo.getTabWidget().getChildAt(i);
            view.setBackgroundResource(R.drawable.tab_indicator);
            TextView tv = (TextView) mTabHostInfo.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        }

        ControlBarraSuperior(this,R.color.colorPrimary);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static public void ControlBarraSuperior(Activity activity, int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(color));
        }
    }
}
