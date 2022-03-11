package com.iamnoah1.influx.demo;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;

import com.influxdb.query.FluxTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuditController {
    @Autowired
    InfluxDBClient client;

    @PostMapping("/audit")
    public void createAuditEntry(@RequestBody AuditModel audit) {
        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writeMeasurement(WritePrecision.MS, audit);
    }

    @GetMapping("/audit")
    public Object[] getAuditEntries() {
        String query = String.format("from(bucket: \"%s\") |> range(start: -1h)", "my-bucket");
        List<FluxTable> tables = client.getQueryApi().query(query, "my-org");
        return tables.toArray();
    }
}
