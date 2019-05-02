package template.android.com.device.device;

import android.content.ClipData;
import android.content.ClipboardManager;

import template.android.com.domain.device.ClipboardUtil;

public final class ClipboardUtilImpl implements ClipboardUtil {

    private final ClipboardManager clipboardManager;

    public ClipboardUtilImpl(final ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    @Override
    public void copyTextToClipboard(final String text) {
        final ClipData clipData = ClipData.newPlainText(text, text);
        clipboardManager.setPrimaryClip(clipData);
    }
}
