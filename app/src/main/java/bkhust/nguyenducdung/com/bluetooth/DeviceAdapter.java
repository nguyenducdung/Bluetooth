package bkhust.nguyenducdung.com.bluetooth;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceHolder> {
    private List<Devices> devices;
    private Context context;

    public DeviceAdapter(List<Devices> devices, Context context) {
        this.devices = devices;
        this.context = context;
    }

    @NonNull
    @Override
    public DeviceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list, viewGroup, false);
        return new DeviceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHolder deviceHolder, int i) {
        deviceHolder.tvName.setText(devices.get(i).getDeviceName());
        deviceHolder.tvAddress.setText(devices.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    class DeviceHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvAddress;

        public DeviceHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }
    }
}
