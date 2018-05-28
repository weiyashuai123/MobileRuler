package com.wys.rulerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * 直尺控件-实例化控件后必须调用.build()方法来绘制。
 * 
 * @author 魏亚帅
 * @update at 2018年5月22日
 **/
public class StraightedgeView extends View {

	public static final int HORIZONTAL = 0;
	public static final int VERTCAL = 1;

	// 控件宽度-单位mm
	private double width_mm;
	// 控件高度-单位mm
	private double height_mm;
	// 控件宽度-分辨率px
	private int pxWidth;
	// 控件高度-分辨率px
	private int pxHeight;
	// 绘制刻度的长度-默认值15
	private int lineLength = 15;
	// 绘制刻度的画笔
	private Paint mPaint;

	// 直尺的方向
	private int orientation = HORIZONTAL;

	// 获取相对分辨率-用于计算真实尺寸
	DisplayMetrics dm = getResources().getDisplayMetrics();

	public StraightedgeView(Context context) {
		this(context, null);
	}

	public StraightedgeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 预定义用到的变量防止初始化出错
		width_mm = 1;
		height_mm = 1;
		pxHeight = 1;
		pxWidth = 1;
		// 定义刻度画笔
		mPaint = new Paint();
		mPaint.setColor(Color.parseColor("#000000"));
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setStrokeWidth(1);
		mPaint.setTextSize(12 * dm.xdpi / 160);
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
		// 将cm转为mm
		width_mm = width_cm * 10;
		height_mm = height_cm * 10;
		if (h > w) {
			orientation = VERTCAL;
		} else {
			orientation = HORIZONTAL;
		}
	}

	public StraightedgeView setTextSize(int size) {
		mPaint.setTextSize(size);
		return this;
	}

	public StraightedgeView setLineLength(int length) {
		lineLength = length;
		return this;
	}

	//控件重新绘制
	public void build() {
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawRuler(canvas);
	}

	private void drawRuler(Canvas canvas) {
		if (orientation == HORIZONTAL) {
			// 横向绘制刻度
			for (int i = 0; i < width_mm; i++) {
				drawHorizontal(canvas, i);
			}
			canvas.drawText("单位：cm",pxWidth - mPaint.measureText("单位：cm") - 100,lineLength * 2+ mPaint.getTextSize(),mPaint);
		} else {
			// 纵向绘制刻度
			for (int i = 0; i < height_mm; i++) {
				drawVertical(canvas, i);
			}
			canvas.drawText("单位：cm",lineLength * 2+ mPaint.getTextSize(),mPaint.getTextSize(),mPaint);
		}
	}

	// 绘制第 i 条刻度（纵向）
	private void drawVertical(Canvas canvas, int i) {
		int length = lineLength;
		if (i % 5 == 0) {
			length = lineLength * 15 / 10;
			if (i % 10 == 0) {
				length = lineLength * 2;
				// 绘制数字 每1cm绘制一次 0 则向下偏移(防止数字越出空间边界不显示)
				if (i == 0) {
					canvas.drawText("" + i / 10, length + lineLength, mPaint.getTextSize(), mPaint);
				} else {
					canvas.drawText("" + i / 10, length + lineLength,
							(float) (i * pxHeight / height_mm + (lineLength / 2)), mPaint);
				}
			}
		}
		canvas.drawLine(0, (float) (i * pxHeight / height_mm), length, (float) (i * pxHeight / height_mm), mPaint);
	}

	// 绘制第 i 条刻度（横向）
	private void drawHorizontal(Canvas canvas, int i) {
		int length = lineLength;
		if (i % 5 == 0) {
			length = lineLength * 15 / 10;
			if (i % 10 == 0) {
				length = lineLength * 2;
				// 绘制数字 每1cm绘制一次 0 则向右偏移(防止数字越出空间边界不显示)
				if (i == 0) {
					canvas.drawText("" + i / 10, 1, length + (lineLength * 15 / 10), mPaint);
				} else {
					canvas.drawText("" + i / 10, (float) (i * pxWidth / width_mm - (lineLength / 4)),
							length + (lineLength * 15 / 10), mPaint);
				}
			}
		}
		canvas.drawLine((float) (i * pxWidth / width_mm), 0, (float) (i * pxWidth / width_mm), length, mPaint);
	}

	// /**
	// * 将小数四色五入转换成整数
	// */
	// private int toInt(double number) {
	// return Integer.parseInt(new java.text.DecimalFormat("0").format(number));
	// }

}
