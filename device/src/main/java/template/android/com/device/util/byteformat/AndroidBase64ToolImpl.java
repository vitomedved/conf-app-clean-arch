package template.android.com.device.util.byteformat;

import android.util.Base64;

import template.android.com.domain.utils.byteformat.Base64Tool;

public final class AndroidBase64ToolImpl implements Base64Tool {

    @Override
    public String toBase64(final byte[] input) {
        return Base64.encodeToString(input, Base64.DEFAULT);
    }
}
