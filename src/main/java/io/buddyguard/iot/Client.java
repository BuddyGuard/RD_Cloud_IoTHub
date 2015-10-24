package io.buddyguard.iot;

import com.microsoft.azure.iot.service.sdk.Device;
import com.microsoft.azure.iothub.*;

/**
 * Created by radu on 23/10/15.
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Device device = DeviceManagement.createDevice();

        DeviceClient client = new DeviceClient(
                "BG-Dev-IoT-Hub.azure-devices.net",
                device.getId(),
                device.getPrimaryKey(),
                IotHubClientProtocol.HTTPS);

        client.open();
        Message message = new Message("TEST MESSAGE");
        client.sendEventAsync(message, (statusCode, obj) -> System.out.println(statusCode.toString()), 1);

        client.close();
    }
}
