package kingfire.ebrahimkarimi.circularimageviewtransition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

public class CircularImageView extends AppCompatImageView {
    private float radius;
    private RectF bitmapRF;
    private Path clipPath;

    public CircularImageView(Context context) {
        super(context);
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircularImageView, defStyleAttr, 0);
        radius = a.getFloat(R.styleable.CircularImageView_radius, 0);
        clipPath = new Path();
//        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        clipPath.reset();
        clipPath.addRoundRect(bitmapRF,radius,radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmapRF = new RectF(0, 0, w, h);
//        Log.e("size changed", "w = " + w + " h = " + h + " old w = " + oldw + " old h  = " + oldh);
    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    private void requestLayoutSupport() {
        View parent = (View) getParent();
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.EXACTLY);
        parent.measure(widthSpec, heightSpec);
        parent.layout(parent.getLeft(), parent.getTop(), parent.getRight(), parent.getBottom());
    }

}
