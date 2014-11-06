package com.yelinaung.ottonotifier.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.yelinaung.ottonotifier.R;
import com.yelinaung.ottonotifier.ui.FirstFragment;
import com.yelinaung.ottonotifier.ui.SecondFragment;

/**
 * Created by Ye Lin Aung on 14/07/08.
 */
public class SlidingTabAdapter extends FragmentPagerAdapter {

  private Context mContext;

  public SlidingTabAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.mContext = context;
  }

  @Override public int getCount() {
    return 2;
  }

  @Override public Fragment getItem(int position) {
    Fragment f = null;
    switch (position) {
      case 0:
        f = FirstFragment.getInstance();
        break;
      case 1:
        f = SecondFragment.getInstance();
        break;
    }
    return f;
  }

  @Override public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return mContext.getString(R.string.first_fragment);
      case 1:
        return mContext.getString(R.string.second_fragment);
    }
    return null;
  }
}
