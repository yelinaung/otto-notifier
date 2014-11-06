package com.yelinaung.ottonotifier.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.squareup.otto.Subscribe;
import com.yelinaung.ottonotifier.R;
import com.yelinaung.ottonotifier.event.BusProvider;
import com.yelinaung.ottonotifier.event.SecondResultEvent;

/**
 * Created by Ye Lin Aung on 14/11/06.
 */
public class SecondFragment extends Fragment {

  @InjectView(R.id.second_swipe_refresh_view) SwipeRefreshLayout swipeRefreshLayout;
  @InjectView(R.id.second_result_text) TextView secondResultText;

  public SecondFragment() {
  }

  public static SecondFragment getInstance() {
    return new SecondFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_second, container, false);
    ButterKnife.inject(this, rootView);

    swipeRefreshLayout.setColorSchemeResources(R.color.swipe_refresh_color1,
        R.color.swipe_refresh_color2, R.color.swipe_refresh_color3, R.color.swipe_refresh_color4);

    return rootView;
  }

  @Override public void onResume() {
    super.onResume();
    BusProvider.getInstance().register(this);
  }

  @Override public void onPause() {
    super.onPause();
    BusProvider.getInstance().unregister(this);
  }

  @Subscribe public void onSecondResult(SecondResultEvent event) {
    secondResultText.setText(event.result);
  }
}
