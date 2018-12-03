package bkhust.nguyenducdung.com.bluetooth;

public class Devices {
    private String deviceName;
    private String address;
    private boolean connected;

    public String getDeviceName() {
        return deviceName;
    }

    public String getAddress() {
        return address;
    }

    public boolean isConnected() {
        return connected;
    }

    public Devices(String deviceName, String address) {
        this.deviceName = deviceName;
        this.address = address;
    }

    public Devices(String deviceName, String address, boolean connected) {
        this.deviceName = deviceName;
        this.address = address;
        //noinspection PointlessBooleanExpression
        if (connected == true) {
            this.connected = true;
        }
        else {
            this.connected = false;
        }
    }
}
