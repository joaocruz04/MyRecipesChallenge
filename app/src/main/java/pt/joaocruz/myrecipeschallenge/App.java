package pt.joaocruz.myrecipeschallenge;

import android.app.Application;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import pt.joaocruz.myrecipeschallenge.dagger.AppComponent;
import pt.joaocruz.myrecipeschallenge.dagger.AppModule;
import pt.joaocruz.myrecipeschallenge.dagger.DaggerAppComponent;

/**
 * Created by jcruz on 13.07.17.
 */

public class App extends Application {

    private AppComponent appComponent;
    private static App instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
        initImageLoader();
    }

    private void initImageLoader() {

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static App getInstance() {
        return instance;
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
