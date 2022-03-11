package com.iamnoah1.influx.demo;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

@Measurement(name = "audit")
public class AuditModel {
    @Column()
    public String name;

    @Column(timestamp = true)
    public Instant time;
}
