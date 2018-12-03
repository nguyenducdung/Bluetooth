package bkhust.nguyenducdung.com.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLED = 0;
    private static final int REQUEST_DISCOVERABLE = 0;
    private Button btnTurnOn;
    private Button btnTurnOff;
    private RecyclerView rcList;
    private BluetoothAdapter bluetoothAdapter;
    private List<Devices> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        displayListOfFoundDevices();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        btnTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_ENABLED);
            }
        });
        btnTurnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothAdapter.disable();
            }
        });
    }

    private void initView() {
        btnTurnOn = findViewById(R.id.btn_open);
        btnTurnOff = findViewById(R.id.btn_off);
        rcList = findViewById(R.id.rc_list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void displayListOfFoundDevices() {
        bluetoothAdapter.startDiscovery();
        final BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_NAME);
                    devices.add(new Devices(device.getName(), device.getAddress()));
                    DeviceAdapter adapter = new DeviceAdapter(devices, getApplicationContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    rcList.setLayoutManager(linearLayoutManager);
                    rcList.setAdapter(adapter);
                }
            }
        };
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
    }
}
