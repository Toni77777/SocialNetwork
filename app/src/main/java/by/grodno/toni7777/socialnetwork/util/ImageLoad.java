package by.grodno.toni7777.socialnetwork.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public final class ImageLoad {

    public static void loadImage(ImageView image, String url) {
        Glide.with(image.getContext()).load(url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
    }

    private ImageLoad() {
    }
}
