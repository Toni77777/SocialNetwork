package by.grodno.toni7777.socialnetwork.util;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import by.grodno.toni7777.socialnetwork.R;

public final class ImageLoad {

    public static void loadImage(ImageView image, String url) {
        Glide.with(image.getContext()).load(url)
                .load(url)
                .placeholder(R.drawable.circle)
                .error(R.drawable.circle)
                .into(image);
    }

    public static void loadCircleImage(ImageView image, String url) {
        Glide.with(image.getContext())
                .load(url).asBitmap().centerCrop()
                .placeholder(R.drawable.circle)
                .error(R.drawable.circle)
                .into(new BitmapImageViewTarget(image) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(image.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        image.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    private ImageLoad() {
    }
}
