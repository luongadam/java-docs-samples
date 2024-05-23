/*
 * Copyright 2024 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package compute.ipaddress;

// [START compute_ip_address_list_static_external]

import com.google.cloud.compute.v1.Address;
import com.google.cloud.compute.v1.AddressesClient;
import com.google.cloud.compute.v1.GetAddressRequest;
import com.google.cloud.compute.v1.GetGlobalAddressRequest;
import com.google.cloud.compute.v1.GlobalAddressesClient;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class GetStaticIPAddress {

  public static void main(String[] args)
          throws IOException, ExecutionException, InterruptedException, TimeoutException {
    // TODO(developer): Replace these variables before running the sample.
    // Project ID or project number of the Cloud project you want to use.
    String projectId = "your-project-id";
    // Region where the VM and IP is located.
    String region = "your-region-id";
    // Name of the address to assign.
    String addressName = "your-addressName";

    getStaticIPAddress(projectId, region, addressName);
  }

  // Retrieves a static external IP address, either regional or global.
  public static Address getStaticIPAddress(String projectId, String region, String addressName)
          throws IOException {
    if (region != null) {
      // Use regional client if a region is specified
      try (AddressesClient client = AddressesClient.create()) {
        GetAddressRequest request = GetAddressRequest.newBuilder()
                .setProject(projectId)
                .setRegion(region)
                .setAddress(addressName)
                .build();

        return client.get(request);
      }
    } else {
      // Use global client if no region is specified
      try (GlobalAddressesClient client = GlobalAddressesClient.create()) {
        GetGlobalAddressRequest request = GetGlobalAddressRequest.newBuilder()
                .setProject(projectId)
                .setAddress(addressName)
                .build();

        return client.get(request);
      }
    }
  }
}
// [END compute_ip_address_list_static_external]