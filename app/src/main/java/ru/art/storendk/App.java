package ru.art.storendk;

import android.app.Application;
import ru.art.storendk.di.components.DaggerStoreComponent;
import ru.art.storendk.di.components.StoreComponent;
import ru.art.storendk.di.modules.StoreModule;

/**
 * Created by NArtur on 16.06.2016.
 */
public class App extends Application {

  private StoreComponent mStoreComponent;

  @Override public void onCreate() {
    super.onCreate();

    mStoreComponent = DaggerStoreComponent.builder()
        .storeModule(new StoreModule())
        .build();
  }

  public StoreComponent getStoreComponent() {
    return mStoreComponent;
  }
}
