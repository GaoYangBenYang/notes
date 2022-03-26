## 指标监控
    
    未来每一个微服务在云上部署以后，我们都需要对其进行监控、追踪、审计、控制等。
    SpringBoot就抽取了Actuator场景，使得我们每个微服务快速引用即可获得生产级别的应用监控、审计等功能。
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```