package com.android.template.utils.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.annimon.stream.Stream;

import java.lang.reflect.Method;

import io.reactivex.functions.Action;

public final class ViewUtilsImpl implements ViewUtils {

    private static final String SET_SHOW_SOFT_INPUT_ON_FOCUS_METHOD_NAME = "setShowSoftInputOnFocus";

    private static final View.OnTouchListener INSTANCE = new DummyOnTouchListener();

    private final Context context;

    private Method setShowSoftInputOnFocusMethod;

    public ViewUtilsImpl(final Context context) {
        this.context = context;
    }

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
    public void doOnPreDraw(final View itemView, final Action actionToPerform, final boolean renderFrame) {
        itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                itemView.getViewTreeObserver().removeOnPreDrawListener(this);
                try {
                    actionToPerform.run();
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                }

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

    @Override
    public void disableViews(final View view, final View... views) {
        makeViewsEnabled(false, view, views);
    }

    @Override
    public void enableViews(final View view, final View... views) {
        makeViewsEnabled(true, view, views);
    }

    private void makeViewsEnabled(final boolean enabled, final View view, final View... views) {
        view.setEnabled(enabled);
        Stream.of(views)
              .forEach(theView -> theView.setEnabled(enabled));
    }

    @Override
    public void makeVisible(final View view, final View... views) {
        setViewVisibility(View.VISIBLE, view, views);
    }

    @Override
    public void makeInvisible(final View view, final View... views) {
        setViewVisibility(View.INVISIBLE, view, views);
    }

    @Override
    public void makeGone(final View view, final View... views) {
        setViewVisibility(View.GONE, view, views);
    }

    private void setViewVisibility(final int visibility, final View view, final View... views) {
        view.setVisibility(visibility);
        Stream.of(views)
              .forEach(theView -> theView.setVisibility(visibility));
    }

    @Override
    public void makeVisibleAndAnimateIn(final View view, final View... views) {
        if (isViewHidden(view)) {
            makeVisibleAndAnimateIn(view);
        }

        Stream.of(views)
              .filter(this::isViewHidden)
              .forEach(this::makeVisibleAndAnimateIn);
    }

    private boolean isViewHidden(final View view) {
        return view.getVisibility() == View.GONE || view.getVisibility() == View.INVISIBLE;
    }

    private void makeVisibleAndAnimateIn(final View view) {
        view.setVisibility(View.VISIBLE);
        view.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
    }

    @Override
    public void makeGoneAndAnimateOut(final View view, final View... views) {
        if (isViewVisible(view)) {
            makeGoneAndAnimateOut(view);
        }

        Stream.of(views)
              .filter(this::isViewVisible)
              .forEach(this::makeGoneAndAnimateOut);
    }

    private void makeGoneAndAnimateOut(final View view) {
        final Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        animation.setAnimationListener(new ChangeViewVisibilityOnAnimationEndAnimationListener(view, View.GONE));
        view.startAnimation(animation);
    }

    private boolean isViewVisible(final View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void disableKeyboardOnEditText(final EditText editText, final EditText... editTexts) {
        editText.setOnTouchListener(INSTANCE);

        for (EditText item : editTexts) {
            item.setOnTouchListener(INSTANCE);
        }
    }

    @Override
    public void disableKeyboardOnEditTextV2(final EditText editText, final EditText... editTexts) {
        disableKeyboardOnEditTextV2Internal(editText);

        for (EditText item : editTexts) {
            disableKeyboardOnEditTextV2Internal(item);
        }
    }

    private void disableKeyboardOnEditTextV2Internal(final EditText editText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            editText.setShowSoftInputOnFocus(false);

        } else {
            try {
                getSetShowSoftInputOnFocusMethod().invoke(editText, false);
            } catch (final Exception e) {
                // ignore
            }
        }
    }

    private Method getSetShowSoftInputOnFocusMethod() throws Exception {

        if (setShowSoftInputOnFocusMethod == null) {
            setShowSoftInputOnFocusMethod = EditText.class.getMethod(SET_SHOW_SOFT_INPUT_ON_FOCUS_METHOD_NAME, boolean.class);
            setShowSoftInputOnFocusMethod.setAccessible(true);
        }

        return setShowSoftInputOnFocusMethod;
    }

    private static final class DummyOnTouchListener implements View.OnTouchListener {

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(final View v, final MotionEvent event) {
            return true;
        }
    }

    private static final class ChangeViewVisibilityOnAnimationEndAnimationListener extends SimpleAnimationListener {

        private final View view;
        private final int targetVisibility;

        private ChangeViewVisibilityOnAnimationEndAnimationListener(final View view, final int targetVisibility) {
            this.view = view;
            this.targetVisibility = targetVisibility;
        }

        @Override
        public void onAnimationEnd(final Animation animation) {
            super.onAnimationEnd(animation);
            view.setVisibility(targetVisibility);
        }
    }
}
