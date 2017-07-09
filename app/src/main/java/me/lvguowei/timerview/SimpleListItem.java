package me.lvguowei.timerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleListItem extends ViewGroup {

    private ImageView icon;
    private TextView title;
    private TextView subtitle;

    public SimpleListItem(Context context) {
        super(context);
    }

    public SimpleListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        icon = (ImageView) findViewById(R.id.icon);
        title = (TextView) findViewById(R.id.title);
        subtitle = (TextView) findViewById(R.id.subtitle);

    }

    /**
     * Validates if a set of layout parameters is valid for a child of this ViewGroup
     */
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    /**
     * @return A set of default layout parameters when given a child with no layout parameters.
     */
    @SuppressWarnings("ResourceType")
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    /**
     * @return A set of layout parameters created from attributes passed in XML.
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * Called when {@link #checkLayoutParams(LayoutParams)} fails.
     *
     * @return A set of valid layout parameters for this ViewGroup that copies appropriate/valid
     * attributes from the supplied, not-so-good parameters.
     */
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return generateDefaultLayoutParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // measure icon
        measureChildWithMargins(icon, widthMeasureSpec, 0, heightMeasureSpec, 0);

        // figure out how much width the icon used
        MarginLayoutParams lp = (MarginLayoutParams) icon.getLayoutParams();
        int widthUsed = icon.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;

        // measure title
        measureChildWithMargins(title, widthMeasureSpec, widthUsed, heightMeasureSpec, 0);

        // measure subtitle
        measureChildWithMargins(subtitle, widthMeasureSpec, widthUsed, heightMeasureSpec, title.getMeasuredHeight());

        // Calculate this view's measured width and height

        // figure out how much total space the icon used
        int iconWidth = icon.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        int iconHeight = icon.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
        lp = (MarginLayoutParams) title.getLayoutParams();

        // figure out how much total space the title used
        int titleWidth = title.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        int titleHeight = title.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
        lp = (MarginLayoutParams) subtitle.getLayoutParams();

        // figure out how much total space the subtitle used
        int subtitleWidth = subtitle.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        int subtitleHeight = subtitle.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

        // the width taken by the children + padding
        int width = getPaddingTop() + getPaddingBottom() + iconWidth + Math.max(titleWidth, subtitleWidth);

        // the height taken by the children + padding
        int height = getPaddingTop() + getPaddingBottom() + Math.max(iconHeight, titleHeight + subtitleHeight);

        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams lp = (MarginLayoutParams) icon.getLayoutParams();

        // figure out the x and y coordinate of the icon
        int x = getPaddingLeft() + lp.leftMargin;
        int y = getPaddingTop() + lp.topMargin;

        // layout the icon
        icon.layout(x, y, x + icon.getMeasuredWidth(), y + icon.getMeasuredHeight());

        // calculate the x-coordinate of the title: icon's right coordinate + icon's right margin
        x += icon.getMeasuredWidth() + lp.rightMargin;

        // add in the title's left margin
        lp = (MarginLayoutParams) title.getLayoutParams();
        x += lp.leftMargin;

        // calculate the y-coordinate of the title: this ViewGroup's top padding +
        // the title's top margin
        y = getPaddingTop() + lp.topMargin;

        // layout the title
        title.layout(x, y, x + title.getMeasuredWidth(), y + title.getMeasuredHeight());

        // the subtitle has the same x-coordinate as the title

        // calculate the y-coordinate of the subtitle: the title's bottom coordinate + the title's bottom margin
        y += title.getMeasuredHeight() + lp.bottomMargin;
        lp = (MarginLayoutParams) subtitle.getLayoutParams();

        // add the subtitle's top margin
        y += lp.topMargin;

        // layout the subtitle
        subtitle.layout(x, y, x + subtitle.getMeasuredWidth(), y + subtitle.getMeasuredHeight());
    }
}
