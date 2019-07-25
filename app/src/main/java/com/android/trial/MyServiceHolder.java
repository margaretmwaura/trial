package com.android.trial;

import android.support.annotation.Nullable;

public class MyServiceHolder
{
    api_service myService = null;

    @Nullable
    public api_service get() {
        return myService;
    }

    public void set(api_service myService) {
        this.myService = myService;
    }
}
