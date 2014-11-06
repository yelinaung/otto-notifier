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
import com.yelinaung.ottonotifier.event.FirstFragResultEvent;
import com.yelinaung.ottonotifier.event.FirstFragSwipeEvent;
import com.yelinaung.ottonotifier.event.SecondResultEvent;

/**
 * Created by Ye Lin Aung on 14/11/06.
 */
public class FirstFragment extends Fragment {

  @InjectView(R.id.first_swipe_refresh_view) SwipeRefreshLayout swipeRefreshLayout;
  @InjectView(R.id.first_result_text) TextView firstResultText;

  public FirstFragment() {
  }

  public static FirstFragment getInstance() {
    return new FirstFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_first, container, false);
    ButterKnife.inject(this, rootView);

    swipeRefreshLayout.setEnabled(true);
    swipeRefreshLayout.setColorSchemeResources(R.color.swipe_refresh_color1,
        R.color.swipe_refresh_color2, R.color.swipe_refresh_color3, R.color.swipe_refresh_color4);

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        BusProvider.getInstance().post(new FirstFragSwipeEvent());
      }
    });

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

  @Subscribe public void onFirstSwipe(FirstFragSwipeEvent event) {
    doFakeWork();
    BusProvider.getInstance().post(new FirstFragResultEvent("Hello meow~"));
    BusProvider.getInstance().post(new SecondResultEvent("Hello meow to Second ~"));
  }

  @Subscribe public void onFirstResult(FirstFragResultEvent event) {
    firstResultText.setText(event.result);
    swipeRefreshLayout.setRefreshing(false);
  }

  private void doFakeWork() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
