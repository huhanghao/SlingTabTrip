package com.example.huhanghao.slingtabtrip.scrolltab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ScrollableTabView extends HorizontalScrollView {
	private int tabNum;
	private int currentTab;

	private TabAdapter mAdapter = null;
	private final LinearLayout mContainer;

	private final ArrayList<View> mTabs = new ArrayList<View>();

	private TabCallBack tabCallBack;
	private LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
			android.view.ViewGroup.LayoutParams.MATCH_PARENT);
	public ScrollableTabView(Context context) {
		this(context, null);
	}

	public ScrollableTabView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ScrollableTabView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
		this.setHorizontalScrollBarEnabled(false);
		this.setHorizontalFadingEdgeEnabled(false);

		mContainer = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		mContainer.setBackgroundColor(0x00000000);
		mContainer.setLayoutParams(params);
		mContainer.setOrientation(LinearLayout.HORIZONTAL);

		this.addView(mContainer);
	}

	public void setAdapter(TabAdapter tabAdapter) {
		this.mAdapter = tabAdapter;
		if (mAdapter != null) {
			initTabs();
		}
	}

	public void setAdapter(TabAdapter tabAdapter, int tabNum) {
		this.mAdapter = tabAdapter;
		this.tabNum = tabNum;
		if (mAdapter != null) {
			initTabs();
		}
	}

	public void setViewPage(int currentTab) {
		this.currentTab = currentTab;
		if (currentTab >= 0) {
			initTabs();
		}
	}

	private void initTabs() {
		mContainer.removeAllViews();
		mTabs.clear();

		if (mAdapter == null) {
			return;
		}
		for (int i = 0; i < tabNum; i++) {
			final int index = i;
			View tab = mAdapter.getView(i);
			mContainer.addView(tab,childParams);
			tab.setFocusable(true);
			mTabs.add(tab);

			tab.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					selectTab(index);
					tabClick(index);
				}
			});
		}
		selectTab(0);
		tabClick(0);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			selectTab(currentTab);
		}
	}

	// 将选择的tab放在中间
	public void selectTab(int position) {
		for (int i = 0, pos = 0; i < mContainer.getChildCount(); i++, pos++) {
			View tab = mContainer.getChildAt(i);
			tab.setSelected(pos == position);
		}
		View selectView = mContainer.getChildAt(position);
		final int w = selectView.getMeasuredWidth();
		final int l = selectView.getLeft();
		final int x = l - this.getWidth() / 2 + w / 2;
		smoothScrollTo(x, this.getScrollY());
	}

	public void setTabCallBack(TabCallBack callback) {
		this.tabCallBack = callback;
	}

	public void tabClick(int position) {
		if (null != tabCallBack) {
			tabCallBack.onPageSelected(position);
		}
	}


	public interface TabCallBack {
		void onPageSelected(int position);
	}

}
