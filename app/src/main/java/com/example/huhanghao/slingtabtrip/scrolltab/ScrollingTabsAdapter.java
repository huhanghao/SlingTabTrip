package com.example.huhanghao.slingtabtrip.scrolltab;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.huhanghao.slingtabtrip.MApplication;
import com.example.huhanghao.slingtabtrip.R;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;



public class ScrollingTabsAdapter implements TabAdapter {

	private int layoutStyle = -1;
	private String[] mTitles;

	public ScrollingTabsAdapter(String[] mTitles) {
		this.mTitles = mTitles;
	}

	public ScrollingTabsAdapter(String[] mTitles, int layoutStyle) {
		this.mTitles = mTitles;
		this.layoutStyle = layoutStyle;
	}

	@Override
	public View getView(int position) {
		TextView tab = null;
		if (layoutStyle == -1) {
			tab = (TextView) LayoutInflater.from(MApplication.getInstance().getApplicationContext()).inflate(R.layout.lib_base_tabs, null);
		} else {
			tab = (TextView) LayoutInflater.from(MApplication.getInstance().getApplicationContext()).inflate(layoutStyle,null);
		}
		Set<String> tab_sets = new HashSet<String>(Arrays.asList(mTitles));
		String[] tabs_new = new String[tab_sets.size()];
		int cnt = 0;
		for (int i = 0; i < mTitles.length; i++) {
			if (tab_sets.contains(mTitles[i])) {
				tabs_new[cnt] = mTitles[i];
				cnt++;
			}
		}
		if (position < tabs_new.length)
			tab.setText(tabs_new[position].toUpperCase());
		return tab;
	}

}
