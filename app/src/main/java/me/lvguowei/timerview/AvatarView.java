package me.lvguowei.timerview;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class AvatarView extends FrameLayout {
    private ImageView imageView;
    private TextView textView;

    public AvatarView(@NonNull Context context) {
        super(context);
        init();
    }

    public AvatarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AvatarView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.avatar_view, this);
        imageView = (ImageView) findViewById(R.id.avatar_image);
        textView = (TextView) findViewById(R.id.avatar_text);
    }

    public void setAvatar(Avatar avatar) {
        if (avatar.res > 0) {
            imageView.setVisibility(VISIBLE);
            imageView.setImageResource(avatar.res);
        } else {
            imageView.setVisibility(GONE);
            textView.setText(avatar.name);
        }
    }
}
