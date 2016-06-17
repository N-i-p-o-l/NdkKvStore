package ru.art.storendk.di.components;

import dagger.Component;
import ru.art.storendk.di.modules.StoreModule;
import ru.art.storendk.di.scopes.UserScope;
import ru.art.storendk.model.Store;
import ru.art.storendk.ui.activity.StoreActivity;

/**
 * Created by NArtur on 16.06.2016.
 */

@UserScope
@Component(modules = {StoreModule.class})
public interface StoreComponent {
  void inject(StoreActivity activity);
}
