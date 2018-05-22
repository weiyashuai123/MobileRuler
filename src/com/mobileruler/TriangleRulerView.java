package com.mobileruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * 三角尺
 **/
public class TriangleRulerView extends View {

	private int width_mm;// 控件长度-单位mm
	private int height_mm;// 控件宽度-单位mm
	private int pxHeight;
	private int pxWidth;
	private int lineLength = 15;
	private Paint mPaint;

	// 获取相对分辨率-用于计算真实尺寸
	DisplayMetrics dm = getResources().getDisplayMetrics();

	public TriangleRulerView(Context context) {
		this(context, null);
	}

	public TriangleRulerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		width_mm = 1;
		height_mm = 1;
		pxHeight = 1;
		pxWidth = 1;
		mPaint = new Paint();
		mPaint.setColor(Color.parseColor("#000000"));
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setStrokeWidth(1);
		mPaint.setAntiAlias(true);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		pxHeight = h;
		pxWidth = w;
		// 得到控件真实物理长度-英寸
		double Inches_width = w / dm.xdpi;
		double Inches_heigh = h / dm.ydpi;
		// 单位转化-转为厘米cm
		double width_cm = Inches_width * 2.54;
		double height_cm = Inches_heigh * 2.54;
		// 将cm转为mm并转为int型
		width_mm = toInt(width_cm * 10);
		height_mm = toInt(height_cm * 10);
	}

	public TriangleRulerView setTextSize(int size_dp) {
		mPaint.setTextSize(size_dp * dm.xdpi / 160);
		return this;
	}

	public TriangleRulerView setLineLength(int length_dp) {
		lineLength = (int) (length_dp * dm.xdpi / 160);
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
		//绘制横向刻度
		for (int i = 0; i < width_mm; i++) {
			float lengthNow = i * pxWidth / width_mm;
			if (lengthNow < lineLength) {
				// 三角尺绘制折叠处（横竖交界处）
				canvas.drawLine(lengthNow, 0, lengthNow, lengthNow, mPaint);
			} else {
				drawHorizontal(canvas, i);
			}
		}
		//绘制纵向刻度
		for (int i = 0; i < height_mm; i++) {
			float lengthNow = i * pxHeight / height_mm;
			if (lengthNow < lineLength) {
				// 三角尺绘制折叠处（横竖交界处）
				canvas.drawLine(0, lengthNow, lengthNow, lengthNow, mPaint);
			} else {
				drawVertical(canvas, i);
			}
		}
	}

	private void drawVertical(Canvas canvas, int i) {
		int length = lineLength;
		if (i % 5 == 0) {
			length = lineLength * 15 / 10;
			if (i % 10 == 0) {
				length = lineLength * 2;
				canvas.drawText("" + i / 10, length + lineLength, i * pxHeight / height_mm + (lineLength / 2), mPaint);
			}
		}
		canvas.drawLine(0, i * pxHeight / height_mm, length, i * pxHeight / height_mm, mPaint);
	}

	private void drawHorizontal(Canvas canvas, int i) {
		int length = lineLength;
		if (i % 5 == 0) {
			length = lineLength * 15 / 10;
			if (i % 10 == 0) {
				length = lineLength * 2;
				canvas.drawText("" + i / 10, i * pxWidth / width_mm - 3, length + (lineLength * 15 / 10), mPaint);
			}
		}
		canvas.drawLine(i * pxWidth / width_mm, 0, i * pxWidth / width_mm, length, mPaint);
	}

	/**
	 * 将小数四色五入转换成整数
	 */
	private int toInt(double number) {
		return Integer.parseInt(new java.text.DecimalFormat("0").format(number));
	}

}
