package com.roy.musicpirate.coreui;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

/**
 * Builds the the primary ActionBar for an Activity.
 */
public class ToolbarBuilder {
    @IdRes
    final int mId;

    @StringRes
    int mTitleResId;
    String mHexColorCode;

    public ToolbarBuilder(@IdRes int id) {
        mId = id;
    }

    public ToolbarBuilder title(@StringRes int resId) {
        mTitleResId = resId;
        return this;
    }

    public ToolbarBuilder backgroundColor(String hexColorCode) {
        mHexColorCode = hexColorCode;
        return this;
    }

/*    public Toolbar build(AppCompatActivity activity) {
        checkNotNull(activity, "activity cannot be null");
        Toolbar toolbar =  (Toolbar) checkNotNull(activity.findViewById(mId), "toolbar not found in layout");

        mHexColorCode = TextUtils.isEmpty(mHexColorCode) ? ThemeManager.getBackgroundColor() : mHexColorCode;
        if (!TextUtils.isEmpty(mHexColorCode)) {
            ThemeManager.setBackgroundColor(toolbar, mHexColorCode);
        }

        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            if (mTitleResId > 0) {
                actionBar.setTitle(mTitleResId);
            }
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        APISupportUtil.setViewElevation(toolbar, activity.getResources().getDimension(R.dimen.toolbar_elevation));
        return toolbar;
    }*/
}
