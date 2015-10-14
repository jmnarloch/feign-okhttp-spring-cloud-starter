# Spring Cloud Netflix Feign OkHttp

> A Spring Cloud Feign extension that configures OkHttp.

[![Build Status](https://travis-ci.org/jmnarloch/feign-okhttp-spring-cloud-starter.svg?branch=master)](https://travis-ci.org/jmnarloch/feign-okhttp-spring-cloud-starter)

## Features

Configures Feign to use OkHttp.

## Setup

Add the Spring Cloud starter to your project:

```xml
<dependency>
  <groupId>io.jmnarloch</groupId>
  <artifactId>feign-okhttp-spring-cloud-starter</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Properties

The only supported property is `feign.okhttp.enabled` which allows to disable this extension. 

```
feign.okhttp.enabled=true 
```

## License

Apache 2.0