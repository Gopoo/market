package com.flyingstudio.fscore.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by guopu on 2017/10/18.
 */

public class GlideUtil {


        //默认加载
        public static void loadImageView(Context mContext, String path, ImageView mImageView) {
            Glide.with(mContext).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESULT).dontAnimate().into(mImageView);
        }

        //指定大小
        public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
            Glide.with(mContext).load(path).override(width, height).diskCacheStrategy(DiskCacheStrategy.RESULT).dontAnimate().into(mImageView);
        }
        //指定大小、失败
        public static void loadImageViewSizeError(Context mContext, String path, int width, int height, ImageView mImageView,int errorImage) {
            Glide.with(mContext).load(path).override(width, height).diskCacheStrategy(DiskCacheStrategy.RESULT).dontAnimate().error(errorImage).into(mImageView);
        }
        //加载失败
        public static void loadImageViewError(Context mContext, String path, ImageView mImageView , int errorImageView) {
            Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.RESULT).dontAnimate().centerCrop().error(errorImageView).into(mImageView);
        }
        //预加载、中心裁剪
        public static void loadPreImageView(Context mContext, String path, ImageView mImageView , float preSize) {
            Glide.with(mContext).load(path).thumbnail(preSize).diskCacheStrategy(DiskCacheStrategy.RESULT).dontAnimate().centerCrop().into(mImageView);
        }

        //预加载、加载失败、中心裁剪
        public static void loadPreImageViewError(Context mContext, String path, ImageView mImageView , float preSize,int errorImageView) {
            Glide.with(mContext).load(path).thumbnail(preSize).diskCacheStrategy(DiskCacheStrategy.RESULT).dontAnimate().centerCrop().error(errorImageView).into(mImageView);
        }
        //加载失败、指定大小
        public static void loadpPreImageViewErrorSize(Context mContext, String path, int width, int height, ImageView mImageView , int errorImageView,float preSize) {
            Glide.with(mContext).load(path).override(width, height).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESULT).dontAnimate().error(errorImageView).thumbnail(preSize).into(mImageView);
        }

        //加载中、加载失败、中心裁剪
        public static void loadImageViewErrorLoading(Context mContext, String path, ImageView mImageView, int lodingImage, int errorImageView) {
            Glide.with(mContext).load(path).placeholder(lodingImage).diskCacheStrategy(DiskCacheStrategy.RESULT).centerCrop().dontAnimate().error(errorImageView).into(mImageView);
        }

        //加载中、加载失败、指定大小
        public static void loadImageViewErrorLoadingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
            Glide.with(mContext).load(path).override(width, height).placeholder(lodingImage).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESULT).dontAnimate().error(errorImageView).into(mImageView);
        }
        //加载失败、指定大小
        public static void loadImageViewErrorSize(Context mContext, String path, int width, int height, ImageView mImageView , int errorImageView) {
            Glide.with(mContext).load(path).override(width, height).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESULT).dontAnimate().error(errorImageView).into(mImageView);
        }
        //清理磁盘缓存
        public static void GuideClearDiskCache(Context mContext) {
            //理磁盘缓存 需要在子线程中执行
            Glide.get(mContext).clearDiskCache();
        }

        //清理内存缓存
        public static void GuideClearMemory(Context mContext) {
            //清理内存缓存  可以在UI主线程中进行
            Glide.get(mContext).clearMemory();
        }


}
