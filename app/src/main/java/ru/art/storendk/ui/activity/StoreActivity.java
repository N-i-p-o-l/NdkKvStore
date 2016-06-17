package ru.art.storendk.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import javax.inject.Inject;
import ru.art.storendk.App;
import ru.art.storendk.R;
import ru.art.storendk.model.Store;
import ru.art.storendk.model.StoreType;

public class StoreActivity extends AppCompatActivity {

  @Inject Store mStore;

  private EditText mKeyText, mValueText;
  private Button mGetValue, mSetValue;
  private Spinner mTypeSpinner;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_store);

    mKeyText = (EditText) findViewById(R.id.keyText);
    mValueText = (EditText) findViewById(R.id.valueText);
    mTypeSpinner = (Spinner) findViewById(R.id.typeSpinner);
    mGetValue = (Button) findViewById(R.id.getValueButton);
    mSetValue = (Button) findViewById(R.id.setValueButton);

    mGetValue.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onGetValue();
      }
    });

    mSetValue.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onSetValue();
      }
    });

    ((App) getApplication()).getStoreComponent().inject(this);
  }

  private void onGetValue() {
    String lKey = mKeyText.getText().toString();
    //StoreType lType = (StoreType) mTypeSpinner.getSelectedItem().toString().equals("String");
    StoreType lType = mTypeSpinner.getSelectedItem().toString().equals("String") ?
        StoreType.String : StoreType.Integer;


    switch (lType) {
      case Integer:
        mValueText.setText(Integer.toString(mStore.getInteger(lKey)));
        break;
      case String:
        mValueText.setText(mStore.getString(lKey));
        break;
    }
  }

  private void onSetValue() {
    String lKey = mKeyText.getText().toString();
    String lValue = mValueText.getText().toString();
    //StoreType lType = (StoreType) mTypeSpinner.getSelectedItem();
    StoreType lType = mTypeSpinner.getSelectedItem().toString().equals("String") ?
        StoreType.String : StoreType.Integer;

    try {
      switch (lType) {
        case Integer:
          mStore.setInteger(lKey, Integer.parseInt(lValue));
          break;
        case String:
          mStore.setString(lKey, lValue);
          break;
      }
    } catch (NumberFormatException eNumberFormatException) {
      Toast.makeText(getApplicationContext(), "Incorrect value",
          Toast.LENGTH_LONG).show();
    }
    Toast.makeText(getApplicationContext(), "Value setted",
        Toast.LENGTH_LONG).show();
  }
}
