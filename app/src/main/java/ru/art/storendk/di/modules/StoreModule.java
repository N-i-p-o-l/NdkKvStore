package ru.art.storendk.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.art.storendk.model.Store;

/**
 * Created by NArtur on 16.06.2016.
 */

@Module
public class StoreModule {

  @Provides
  Store provideStore() {
    return new Store();
  }
}
