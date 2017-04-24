package com.roy.musicpirate.coreui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.roy.musicpirate.R;

/**
 * @author prabhat.roy
 */

public class CustomMediaControlsUI extends LinearLayout {

    public CustomMediaControlsUI(Context context) {
        super(context);
        init();
    }

    public CustomMediaControlsUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomMediaControlsUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.layout_media_controls, this);
    }

}
