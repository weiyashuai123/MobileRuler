package com.mobileruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * 直尺
 **/
public class StraightedgeView extends View {

	public static final int HORIZONTAL = 0;
	public static final int VERTCAL = 1;

	private int mmx;
	private int mmy;
	private int pxheight;
	private int pxWidth;
	private int lineLength = 15;
	private Paint mPaint;

	private int orientation = HORIZONTAL;
	
	DisplayMetrics dm = getResources().getDisplayMetrics();

	public StraightedgeView(Context context) {
		this(context, null);
	}

	public StraightedgeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mmx = 1;
		mmy = 1;
		pxheight = 1;
		pxWidth = 1;
		mPaint = new Paint();
		mPaint.setColor(Color.parseColor("#000000"));
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setStrokeWidth(1);
		mPaint.setAntiAlias(true);
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		pxheight = h;
		pxWidth = w;

	}

	public StraightedgeView setSize(int x, int y) {
		mmx = x;
		mmy = y;
		return this;
	}

	public StraightedgeView setTextSize(float size_px) {
		mPaint.setTextSize(size_px);
		return this;
	}

	public StraightedgeView setLineLength(int length) {
		lineLength = length;
		return this;
	}

	public void build() {
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawRuler(canvas);
	}

	private void drawRuler(Canvas canvas) {

		for (int i = 0; i < mmy; i++) {
//			float lengthNow = i * pxheight / mmy;
			//三角尺绘制折叠处（横竖交界处）
			// if (lengthNow < lineLength) {
			// canvas.drawLine(0, lengthNow, lengthNow, lengthNow, mPaint);
			// canvas.drawLine(lengthNow, 0, lengthNow, lengthNow, mPaint);
			// } else {
			if (orientation == HORIZONTAL) {
				drawHorizontal(canvas, i);
			} else {
				drawVertical(canvas, i);
			}
		}

	}

	// }

	private void drawVertical(Canvas canvas, int i) {
		int length = lineLength;
		if (i % 5 == 0) {
			length = lineLength * 15 / 10;
			if (i % 10 == 0) {
				length = lineLength * 2;
				canvas.drawText("" + i / 10, length + lineLength, i * pxheight / mmy + (lineLength / 2), mPaint);
			}
		}
		canvas.drawLine(0, i * pxheight / mmy, length, i * pxheight / mmy, mPaint);
	}

	private void drawHorizontal(Canvas canvas, int i) {

		int length = lineLength;
		if (i % 5 == 0) {
			length = lineLength * 15 / 10;
			if (i % 10 == 0) {
				length = lineLength * 2;
				canvas.drawText("" + i / 10, i * pxWidth / mmx - 3, length + (lineLength * 15 / 10), mPaint);
			}
		}
		canvas.drawLine(i * pxWidth / mmx, 0, i * pxWidth / mmx, length, mPaint);
	}
	
	/**
	 * 将小数四色五入转换成整数
	 */
	private int toInt(double number) {
		return Integer.parseInt(new java.text.DecimalFormat("0").format(number));
	}

}
