package io.buddyguard.iot;

import com.microsoft.azure.iot.service.sdk.Device;
import com.microsoft.azure.iot.service.sdk.RegistryManager;

import java.util.List;
import java.util.UUID;

/**
 * Created by radu on 23/10/15.
 */
public class DeviceManagement {

    public static RegistryManager registryManager = null;

    static {
        try {
            registryManager = RegistryManager.createFromConnectionString("HostName=BG-Dev-IoT-Hub.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=5vRxbfKxaaXmcf3zqlCa/CCNkIYCWVGQhLwRuFCEphk=");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        // create a device
        Device device = createDevice();
        System.out.println("ID: " + device.getId() + " Key: " + device.getPrimaryKey());

        // list all devices
        List<Device> devices = listAllDevices();
        devices.forEach(d -> System.out.println("ID: " + d.getId() + " Key: " + d.getPrimaryKey()));

        // remove the created device
        removeDevice(device.getId());
    }

    public static Device createDevice() throws Exception {
        Device device = Device.createFromId(UUID.randomUUID().toString());
        registryManager.addDevice(device);
        return device;
    }

    public static List<Device> listAllDevices() throws Exception {
        return registryManager.getDevices(Integer.MAX_VALUE);
    }

    public static void removeDevice(String deviceId) throws Exception {
        registryManager.removeDevice(deviceId);
    }
}
