package com.aruntech;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import org.springframework.beans.factory.annotation.Autowired;

public class RibbonConfiguration {

    @Autowired
    IClientConfig ribbonClient;

    public IPing ribbonPing(IClientConfig config) {
        return new PingUrl();
    }

    public IRule ribbonRule(IClientConfig config) {
        return new AvailabilityFilteringRule();
    }
}
