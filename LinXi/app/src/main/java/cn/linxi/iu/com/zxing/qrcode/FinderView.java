package cn.linxi.iu.com.zxing.qrcode;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import cn.linxi.iu.com.R;

/**
 * - 屏幕中间模拟激光扫描线效果的View。它是个纯粹的动画，与二维码识别过程没有任何关系。
 * @author dswitkin@google.com (Daniel Switkin)
 * @author yoojiachen@gmail.com (Yoojia.Chen)
 * @since 1.0
 */
public final class FinderView extends View {

    private final static int ANIMATION_DELAY = 2000;

	private final Paint mPaint;
    private final int mMaskColor;

//    private Bitmap bitmapBounds;

	public FinderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		final Resources resources = getResources();
		mMaskColor = 0xAA525252; // 遮掩层颜色
        // cache images
//        bitmapBounds = BitmapFactory.decodeResource(resources, R.drawable.ic_scan_capture);
	}

	@Override
	public void onDraw(Canvas canvas) {
        final int width = canvas.getWidth();
        final int height = canvas.getHeight();
        final int wh = width > height ? height : width;
        final int boxLength = (int) (wh*0.6);
        final int left = (width - boxLength)/2;
        final int top = (height - boxLength)/2;
        final int right = left + boxLength;
        final int bottom = top + boxLength;
        final Rect frame = new Rect(left, top, right, bottom);
        RectF f = new RectF(left, top, right, bottom);
        canvas.save();
        canvas.clipRect(frame, Region.Op.XOR);
        canvas.drawColor(mMaskColor);
        canvas.restore();
        canvas.save();
        drawEdges(canvas, f);
        canvas.restore();
//        postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top, frame.right, frame.bottom);
//        canvas.drawBitmap(bitmapBounds, frame, frame, new Paint());
//        canvas.save();
//        canvas.restore();
    }

    private void drawEdges(Canvas canvas, RectF f) {
//		mPaint.setColor(Color.WHITE);
//        final float _x = box.right - mBmpTopRight.getWidth();
//        final float _y = box.bottom - mBmpBottomLeft.getHeight();
//		canvas.drawBitmap(bitmapBounds, null, f, mPaint);
//		canvas.drawBitmap(mBmpTopRight, _x, box.top, mPaint);
//		canvas.drawBitmap(mBmpBottomLeft, box.left, _y, mPaint);
//		canvas.drawBitmap(mBmpBottomRight, _x, _y, mPaint);
	}

}
