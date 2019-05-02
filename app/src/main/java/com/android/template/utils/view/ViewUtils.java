package com.android.template.utils.view;

import android.graphics.Rect;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.functions.Action;

public interface ViewUtils {

    void doOnPreDraw(View itemView, Action actionToPerform, boolean renderFrame);

    void underlineTextView(TextView textView);

    void setViewGroupEnabled(ViewGroup viewGroup, boolean enabled);

    Rect getBoundingRectangleOnScreen(View view);

    Rect getClickableSpanLocationOnScreen(View view, ClickableSpan clickableSpan);

    void adjustPosition(View view, Rect source, Rect target);

    void disableViews(View view, View... views);

    void enableViews(View view, View... views);

    void makeVisible(View view, View... views);

    void makeInvisible(View view, View... views);

    void makeGone(View view, View... views);

    void makeVisibleAndAnimateIn(View view, View... views);

    void makeGoneAndAnimateOut(View view, View... views);

    void disableKeyboardOnEditText(EditText editText, EditText... editTexts);

    void disableKeyboardOnEditTextV2(EditText editText, EditText... editTexts);
}
