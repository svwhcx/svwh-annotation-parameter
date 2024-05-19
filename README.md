# svwh-annotation-parameter

## 介绍
在SpringBoot的开发中，`@Valid`注解适用于需要验证全部参数的情况，但很多使用一个实体是不需要验证全部参数的，
只需要验证部分参数，这时候分组就太麻，本库提供了基于注解的参数验证的方式验证方法的参数是否合法
不必和业务耦合,也不需要定义分组。基本的`@NotNull`的用法如下：
```java

@NotNull(key = {"#name"},tipMessage="变量名称不可为null")
public void test(String name, Integer age) {
    // 业务逻辑
}
```
如上面的例子可见，对于要验证的参数名称前，必须要使用#作为识别，因为本库采用的是基于SPEL表达式进行参数的解析。
注解中的`tipMessage`是当校验不通过时，返回给前端的字符串,默认的格式为：`参数：#xxx.xxx 不能为null`
## 使用
提供了12个注解可使用，分别为
- `@MustMin`：可重复使用
- `@MustMax`：可重复使用
- `@MustEmail`：可重复使用
- `@MustLength`：可重复使用
- `@NotNull`
- `@NotBlank`
- `@NotEmpty`
- `@MustPast`
- `@MustFuture`
- `@MustPattern`
- `@MustTrue`
- `@MustFalse`

## 注意事项
在使用本库的时候，很麻烦的一个问题就是在字符串中输入参数时，IDEA不会进行自动提示，因此可以采用
我编写的一个基于`#`开头的字符串的参数自动提示，弥补idea的不足，提高使用体验。
本库使用的Spring版本为`5.2.3`

