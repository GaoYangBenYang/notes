## 配置类开启防护
```java
//    http.csrf().disable();    //关闭csrf防护
```

## 页面中添加隐藏域
```html
<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}">
```