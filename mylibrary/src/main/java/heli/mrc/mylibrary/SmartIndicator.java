package heli.mrc.mylibrary;

/*
 * Copyright (C) 2013 Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import ohos.agp.components.*;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.app.Context;
import ohos.multimodalinput.event.TouchEvent;



public class SmartIndicator extends DirectionalLayout implements Component.TouchEventListener, Component.DrawTask, Component.EstimateSizeListener {
    private Integer mAccentColor;
    private Integer mFadeAccentColor;
    private Integer mTextLightColor;
    private Integer mTextDarkColor;
    private int touchViewPosition;
    private int pickedViewPosition;
    private float segmentWidth;
    private float indicatorLineHeight;
    Paint paint;
    private Color accentColor;
    private Color fadeAccentColor;
    private Color lightAccentColor;
    private Color darkAccentColor;
    private boolean onDowning;
    public SmartIndicator(Context context) {
        super(context);
    }

    public SmartIndicator(Context context, AttrSet attrSet) {
        super(context, attrSet);
        init(context, attrSet);
    }



    public SmartIndicator(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
    }


    private void init(Context context, AttrSet attrSet) {
        indicatorLineHeight = AttrHelper.vp2px(3,getContext());
        if (attrSet.getAttr("accentColor").isPresent()) {
            mAccentColor =  attrSet.getAttr("accentColor").get().getColorValue().getValue();
        }
        if (attrSet.getAttr("textLightColor").isPresent()) {
            mTextLightColor =  attrSet.getAttr("textLightColor").get().getColorValue().getValue();
        }
        if (attrSet.getAttr("indicatorHeight").isPresent()) {
            indicatorLineHeight =  attrSet.getAttr("indicatorHeight").get().getDimensionValue();
        }

        if (mAccentColor == null) {
            mAccentColor = 0xffff4081;
        }

        if (mTextLightColor == null) {
            mTextLightColor = 0xff212121;
        }

        mFadeAccentColor =mAccentColor & 0x30FFFFFF;
        mTextDarkColor =mTextLightColor & 0xAAFFFFFF;

        accentColor = new Color(mAccentColor);
        fadeAccentColor = new Color(mFadeAccentColor);
        lightAccentColor = new Color(mTextLightColor);
        darkAccentColor = new Color(mTextDarkColor);

        paint =new Paint();
        setOrientation(DirectionalLayout.HORIZONTAL);



        setEstimateSizeListener(this);

        setTouchEventListener(this);
        addDrawTask(this);
    }


    @Override
    public boolean onTouchEvent(Component component, TouchEvent touchEvent) {
        float eventX= touchEvent.getPointerPosition(0).getX();
        int action =touchEvent.getAction();
        switch (action){
            case TouchEvent.PRIMARY_POINT_DOWN:
                 touchViewPosition = getCurrentTouchPosition(eventX);
                onDowning=true;
                invalidate();
                break;
            case TouchEvent.PRIMARY_POINT_UP:
                pickedViewPosition = getCurrentTouchPosition(eventX);
                onDowning=false;
                invalidate();
                updateChildViewState();
                if (componentClickListener!=null) {
                    componentClickListener.onComponentClicked(this, pickedViewPosition);
                }
                break;
            case TouchEvent.CANCEL: //when intercepted
                break;

        }

        return true;
    }




    private int getCurrentTouchPosition(float touchX){
        int childCount = getChildCount();
         segmentWidth = getWidth()/childCount;
        return Float.valueOf(touchX/segmentWidth).intValue();
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        if (segmentWidth==0) {
            segmentWidth = component.getWidth()/getChildCount();
        }
        //draw bottom line
        float lineStartX = pickedViewPosition *segmentWidth;
        canvas.drawRect(lineStartX,getHeight()-indicatorLineHeight,lineStartX+segmentWidth,getHeight(),paint,accentColor);

        //draw downBackGround
        float backGroundStartX = touchViewPosition*segmentWidth;
        if (onDowning) {
            canvas.drawRect(backGroundStartX,0,backGroundStartX+segmentWidth,component.getBottom(),paint,fadeAccentColor);
        }

    }

    @Override
    public boolean onEstimateSize(int width, int height) {

        //adjust view's weight
        int childCount = getChildCount();
        segmentWidth = getWidth()/childCount;
        for (int i = 0; i <getChildCount() ; i++) {
            Component childView = getComponentAt(i);
            if(childView instanceof Text){ //init text color;
                updateTextState(((Text)childView),i==pickedViewPosition?lightAccentColor:darkAccentColor);
            }
            childView.setWidth(0);
            LayoutConfig layoutConfig = (LayoutConfig) getComponentAt(i).getLayoutConfig();
            layoutConfig.weight=1;
            childView.setLayoutConfig(layoutConfig);
        }
        setAlignment(LayoutAlignment.VERTICAL_CENTER);
        return false;
    }


    public interface  OnComponentClickListener{
        void onComponentClicked(ComponentContainer parent,int index);
    }

    public void setComponentClickListener(OnComponentClickListener componentClickListener) {
        this.componentClickListener = componentClickListener;
    }

    private OnComponentClickListener componentClickListener;


    private void updateChildViewState() {
        for (int i = 0; i <getChildCount() ; i++) {
            Component childView = getComponentAt(i);
            if(childView instanceof Text){
                updateTextState(((Text)childView),i==pickedViewPosition?lightAccentColor:darkAccentColor);
            }
        }
    }

    private void updateTextState(Text text,Color targetColor){
        text.setTextColor(targetColor);
    }



    public void setAccentColor(Integer mAccentColor) {
        this.mAccentColor = mAccentColor;
        mFadeAccentColor =mAccentColor & 0x30FFFFFF;
        accentColor = new Color(mAccentColor);
        fadeAccentColor = new Color(mFadeAccentColor);
        invalidate();
    }

    public void setTextLightColor(Integer mTextLightColor) {
        this.mTextLightColor = mTextLightColor;
        mTextDarkColor =mTextLightColor & 0xAAFFFFFF;
        lightAccentColor = new Color(mTextLightColor);
        darkAccentColor = new Color(mTextDarkColor);
        invalidate();
    }

}
