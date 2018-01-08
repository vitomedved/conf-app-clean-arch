package com.android.template.utils.view;

import android.graphics.Rect;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rx.functions.Action0;

public interface ViewUtils {

    void doOnPreDraw(View itemView, Action0 actionToPerform, boolean renderFrame);

    void underlineTextView(TextView textView);

    void setViewGroupEnabled(ViewGroup viewGroup, boolean enabled);

    Rect getBoundingRectangleOnScreen(View view);

    Rect getClickableSpanLocationOnScreen(View view, ClickableSpan clickableSpan);

    void adjustPosition(View view, Rect source, Rect target);
}
