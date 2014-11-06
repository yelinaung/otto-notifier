package com.yelinaung.ottonotifier;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.yelinaung.ottonotifier.adapter.SlidingTabAdapter;
import com.yelinaung.ottonotifier.ui.widget.SlidingTabLayout;

public class MainActivity extends FragmentActivity {

  @InjectView(R.id.viewpager) ViewPager viewPager;

  SlidingTabLayout slidingTabLayout = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
    slidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);

    int mAccentColor = getResources().getColor(R.color.accent);
    int mPrimaryColor = getResources().getColor(R.color.primary);

    slidingTabLayout.setSelectedIndicatorColors(mAccentColor);
    slidingTabLayout.setBackgroundColor(mPrimaryColor);

    FragmentManager fm = getSupportFragmentManager();

    viewPager.setAdapter(new SlidingTabAdapter(fm, MainActivity.this));

    slidingTabLayout.setDistributeEvenly(true);
    slidingTabLayout.setViewPager(viewPager);
  }
}
