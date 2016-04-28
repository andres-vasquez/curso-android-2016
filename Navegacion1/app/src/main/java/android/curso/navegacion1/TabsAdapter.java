package android.curso.navegacion1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

import java.util.ArrayList;

/**
 * Created by andresvasquez on 26/4/16.
 */
public class TabsAdapter extends FragmentPagerAdapter implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
    private final Context mContext;
    private final TabHost mTabHostInfo;
    private final ViewPager mViwePagerInfo;
    private final ArrayList<Tab> mTabs = new ArrayList<Tab>();

    static final class Tab {
        private final String tag;
        private final Class<?> clss;
        private final Bundle args;

        Tab(String _tag, Class<?> _class, Bundle _args) {
            tag = _tag;
            clss = _class;
            args = _args;
        }
    }

    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context context) {
            mContext = context;
        }

        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    //Para activities
    public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager pager) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mTabHostInfo = tabHost;
        mViwePagerInfo = pager;
        mTabHostInfo.setOnTabChangedListener(this);
        mViwePagerInfo.setAdapter(this);
        mViwePagerInfo.setOnPageChangeListener(this);
    }

    //Para Fragments
    public TabsAdapter(Fragment fragment, TabHost tabHost, ViewPager pager) {
        super(fragment.getChildFragmentManager());
        mContext = fragment.getActivity();
        mTabHostInfo = tabHost;
        mViwePagerInfo = pager;
        mTabHostInfo.setOnTabChangedListener(this);
        mViwePagerInfo.setAdapter(this);
        mViwePagerInfo.setOnPageChangeListener(this);
    }


    public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
        tabSpec.setContent(new DummyTabFactory(mContext));
        String tag = tabSpec.getTag();

        Tab info = new Tab(tag, clss, args);
        mTabs.add(info);
        mTabHostInfo.addTab(tabSpec);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        Tab info = mTabs.get(position);

        return Fragment.instantiate(mContext, info.clss.getName(), info.args);

    }

    public void onTabChanged(String tabId) {
        int position = mTabHostInfo.getCurrentTab();
        mViwePagerInfo.setCurrentItem(position);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e("Posicion", "" + position);
    }

    public void onPageSelected(int position) {
        TabWidget widget = mTabHostInfo.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        mTabHostInfo.setCurrentTab(position);
        widget.setDescendantFocusability(oldFocusability);
    }

    public void onPageScrollStateChanged(int state) {
    }
}
