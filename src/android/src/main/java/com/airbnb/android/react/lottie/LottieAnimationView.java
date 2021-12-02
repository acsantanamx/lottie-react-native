package com.airbnb.android.react.lottie;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.MotionEvent;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class LottieAnimationView extends LottieAnimationViewProxy {

    private String LOG_TAG = "LottieAnimationView";
    private ReactContext reactContext = null;

    public LottieAnimationView(Context context) {
        super(context);

        Context ctx = this.getContext();

        while (ctx instanceof ContextWrapper) {
            if (ctx instanceof ReactContext) {
                this.reactContext = (ReactContext)ctx;
                break;
            }
            ctx = ((ContextWrapper)ctx).getBaseContext();
        }
    }

    @Override public boolean onGenericMotionEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_HOVER_ENTER) {

            Log.v(LOG_TAG, "MotionEvent.ACTION_HOVER_ENTER");

            if (this.reactContext != null) {
                this.reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        this.getId(),
                        "hoverEnter",
                        null);
            }

        } else if (event.getAction() == MotionEvent.ACTION_HOVER_EXIT) {

            Log.v(LOG_TAG, "MotionEvent.ACTION_HOVER_EXIT");

            if (this.reactContext != null) {
                this.reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        this.getId(),
                        "hoverExit",
                        null);
            }

        }

        return super.onGenericMotionEvent(event);
    }
}
