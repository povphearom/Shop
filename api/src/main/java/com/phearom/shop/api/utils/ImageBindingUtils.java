package com.phearom.shop.api.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by phearom on 3/22/16.
 */
public class ImageBindingUtils {
    @BindingAdapter("imageSrc")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).override(100, 100).centerCrop().into(imageView);
    }
}
