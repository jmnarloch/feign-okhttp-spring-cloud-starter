/**
 * Copyright (c) 2015 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jmnarloch.spring.cloud.feign;

import com.netflix.loadbalancer.ILoadBalancer;
import feign.Client;
import feign.Feign;
import feign.okhttp.OkHttpClient;
import feign.ribbon.LBClientFactory;
import feign.ribbon.RibbonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Auto configures the Feign OkHttp client.
 *
 * @author Jakub Narloch
 */
@Configuration
@ConditionalOnClass({ com.squareup.okhttp.OkHttpClient.class, Feign.class, ILoadBalancer.class })
@ConditionalOnProperty(value = "feign.okhttp.enabled", matchIfMissing = true)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class OkHttpClientAutoConfiguration {

    @Autowired(required = false)
    private com.squareup.okhttp.OkHttpClient httpClient;

    @Resource(name = "cachingLBClientFactory")
    private LBClientFactory lbClientFactory;

    @Bean
    public Client feignClient() {
        RibbonClient.Builder builder = RibbonClient.builder();

        if (httpClient != null) {
            builder.delegate(new OkHttpClient(httpClient));
        } else {
            builder.delegate(new OkHttpClient());
        }

        if (lbClientFactory != null) {
            builder.lbClientFactory(lbClientFactory);
        }

        return builder.build();
    }
}
