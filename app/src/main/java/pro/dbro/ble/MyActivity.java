package pro.dbro.ble;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MyActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    BLECentral mScanner;
    BLEPeripheral mAdvertiser;

    @InjectView(R.id.scanToggle)      ToggleButton mScanToggle;
    @InjectView(R.id.advertiseToggle) ToggleButton mAdvertiseToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mScanner = new BLECentral(this);
        mAdvertiser = new BLEPeripheral(this);

        ButterKnife.inject(this);
        mScanToggle.setOnCheckedChangeListener(this);
        mAdvertiseToggle.setOnCheckedChangeListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mScanner.isIsScanning()) mScanner.stop();

        if (mAdvertiser.isAdvertising()) mAdvertiser.stop();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        if (compoundButton == mScanToggle)
        {
            if (checked)
                mScanner.start();
            else
                mScanner.stop();
        }
        else if (compoundButton == mAdvertiseToggle)
        {
            if (checked)
                mAdvertiser.start();
            else
                mAdvertiser.stop();

        }

    }
}
