package com.android.template.utils.view;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import rx.functions.Action0;

public final class ViewUtilsImpl implements ViewUtils {

    @Override
    public void underlineTextView(final TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void setViewGroupEnabled(final ViewGroup viewGroup, final boolean enabled) {
        for (int i = 0, childCount = viewGroup.getChildCount(); i < childCount; i++) {
            final View child = viewGroup.getChildAt(i);
            child.setEnabled(enabled);
            if (child instanceof ViewGroup) {
                setViewGroupEnabled((ViewGroup) child, enabled);
            }
        }
    }

    @Override
    public void doOnPreDraw(final View itemView, final Action0 actionToPerform, final boolean renderFrame) {
        itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                itemView.getViewTreeObserver().removeOnPreDrawListener(this);
                actionToPerform.call();

                return renderFrame;
            }
        });
    }

    @Override
    public Rect getBoundingRectangleOnScreen(final View view) {
        final int[] position = new int[2];
        view.getLocationOnScreen(position);
        return new Rect(position[0], position[1], position[0] + view.getWidth(), position[1] + view.getHeight());
    }

    @Override
    public Rect getClickableSpanLocationOnScreen(final View view, final ClickableSpan clickableSpan) {
        final TextView parentTextView = (TextView) view;

        final Rect parentTextViewRect = new Rect();

        // Initialize values for the computing of clickedText position
        final SpannableString completeText = (SpannableString) (parentTextView).getText();
        final Layout textViewLayout = parentTextView.getLayout();

        final double startOffsetOfClickedText = completeText.getSpanStart(clickableSpan);
        final double endOffsetOfClickedText = completeText.getSpanEnd(clickableSpan);
        final double startXCoordinatesOfClickedText = textViewLayout.getPrimaryHorizontal((int) startOffsetOfClickedText);
        final double endXCoordinatesOfClickedText = textViewLayout.getPrimaryHorizontal((int) endOffsetOfClickedText);

        // Get the rectangle of the clicked text
        final int currentLineStartOffset = textViewLayout.getLineForOffset((int) startOffsetOfClickedText);
        final int currentLineEndOffset = textViewLayout.getLineForOffset((int) endOffsetOfClickedText);
        final boolean keywordIsInMultiLine = currentLineStartOffset != currentLineEndOffset;
        textViewLayout.getLineBounds(currentLineStartOffset, parentTextViewRect);

        // Update the rectangle position to his real position on screen
        final int[] parentTextViewLocation = {0, 0};
        parentTextView.getLocationOnScreen(parentTextViewLocation);

        final double parentTextViewTopAndBottomOffset = (
                parentTextViewLocation[1] -
                        parentTextView.getScrollY() +
                        parentTextView.getCompoundPaddingTop()
        );
        parentTextViewRect.top += parentTextViewTopAndBottomOffset;
        parentTextViewRect.bottom += parentTextViewTopAndBottomOffset;

        parentTextViewRect.left += (
                parentTextViewLocation[0] +
                        startXCoordinatesOfClickedText +
                        parentTextView.getCompoundPaddingLeft() -
                        parentTextView.getScrollX()
        );
        parentTextViewRect.right = (int) (
                parentTextViewRect.left +
                        endXCoordinatesOfClickedText -
                        startXCoordinatesOfClickedText
        );

        final int x = parentTextViewRect.left;
        final int y = parentTextViewRect.top;

        return new Rect(x, y, parentTextViewRect.right, parentTextViewRect.bottom);
    }

    @Override
    public void adjustPosition(final View view, final Rect source, final Rect target) {
        final float scaleX = source.width() / (float) target.width();
        final float scaleY = source.height() / (float) target.height();
        final int deltaX = source.left - target.left;
        final int deltaY = source.top - target.top;

        view.setScaleX(scaleX);
        view.setScaleY(scaleY);
        view.setTranslationX(deltaX);
        view.setTranslationY(deltaY);
    }
}
