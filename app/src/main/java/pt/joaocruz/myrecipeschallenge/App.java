package pt.joaocruz.myrecipeschallenge;

import android.app.Application;

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
    }

    public static App getInstance() {
        return instance;
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
