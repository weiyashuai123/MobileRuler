package com.wys.rulerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class RulerView extends RelativeLayout {

    private StraightedgeView lineRuler;
    private TriangleRulerView triangleRuler;
    private ImageView image_hor,image_ver;

    private int ruler_type = 0;
    private int backgroundResource;
    private int scaleLength = dp2dx(15);
    private int scaleColor = Color.BLACK;
    private int scaleTextSize = dp2dx(12);

    public RulerView(Context context) {
        this(context, null);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrRes(context, attrs);
        buildView(context);
    }

    private void buildView(Context context) {
        switch (ruler_type) {
            case 0:
                lineRuler = new StraightedgeView(context);
                image_hor = new ImageView(context);
                lineRuler.setLineLength(scaleLength);
                lineRuler.setTextSize(scaleTextSize);
                LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
                image_hor.setLayoutParams(lp);
                lineRuler.setLayoutParams(lp);
                image_hor.setScaleType(ImageView.ScaleType.CENTER_CROP);
                image_hor.setImageResource(backgroundResource);
                lineRuler.build();
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    private void readAttrRes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RulerView);
        backgroundResource = typedArray.getResourceId(R.styleable.RulerView_ruler_backgroundImage, R.drawable.wood);
        ruler_type = typedArray.getInteger(R.styleable.RulerView_ruler_type, 0);
        scaleLength = (int) typedArray.getDimension(R.styleable.RulerView_ruler_scaleLength, scaleLength);
        scaleColor = typedArray.getInteger(R.styleable.RulerView_ruler_scaleColor, scaleColor);
        typedArray.recycle();
    }

    private int dp2dx(int dpValue) {
        int dxValue = (int) getResources().getDisplayMetrics().density * dpValue;
        return dxValue;
    }

}
