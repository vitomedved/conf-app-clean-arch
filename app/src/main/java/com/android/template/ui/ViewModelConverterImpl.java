package com.android.template.ui;

import template.android.com.domain.utils.DateUtils;
import template.android.com.domain.utils.StringUtils;

public final class ViewModelConverterImpl implements ViewModelConverter {

    private final DateUtils dateUtils;
    private final StringUtils stringUtils;

    public ViewModelConverterImpl(final DateUtils dateUtils, final StringUtils stringUtils) {
        this.dateUtils = dateUtils;
        this.stringUtils = stringUtils;
    }
}
