package com.iamnoah1.influx.demo;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;

import com.influxdb.query.FluxTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class AuditController {

    @PostMapping("/audit")
    public void createAuditEntry(@RequestBody AuditModel audit) {
        Point auditEntry = Point.measurement("audit")
                .time(Instant.now().toEpochMilli(), WritePrecision.MS)
            .addField("name", audit.name);

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://127.0.0.1:8086", "0mGVvhjebwHhcNIGDVMlSJK68zBi6TmKPy-tQgmA8IcW4MLtYotEfe3V2hMAmLjdcouVk4TeeDaJqgWkuJvytw==".toCharArray(), "my-org", "my-bucket");

        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

        writeApi.writePoint(auditEntry);
    }
    @GetMapping("/audit")
    public Object[] getAuditEntries() {
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://127.0.0.1:8086", "0mGVvhjebwHhcNIGDVMlSJK68zBi6TmKPy-tQgmA8IcW4MLtYotEfe3V2hMAmLjdcouVk4TeeDaJqgWkuJvytw==".toCharArray(), "my-org", "my-bucket");
        String query = String.format("from(bucket: \"%s\") |> range(start: -1h)", "my-bucket");
        List<FluxTable> tables = influxDBClient.getQueryApi().query(query, "my-org");
        return tables.toArray();
    }
}
