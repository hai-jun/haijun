# haijun后台

haijun物料管理系统是一款基于springcloud+vue实现的前后端完全分离的后台管理系统

数据库使用的是关系型数据库mysql 

包装请求，前后端交互使用restful风格，请求需经过token验证，也可以自定义不经过token验证的请求路径

# 技术栈

eureka的注册中心、网关、服务提供者、消费者、配置中心

feign

Hystrix

mybatis-plus

Transactional

jwt

...

# 快速部署
1.使用根路径下resourse文件夹下的sql文件生成数据库

2.将haijun-config文件clone到你自己的github或者码云仓库，修改配置的数据库IP地址为你上一步生成的数据库IP地址

3.clone项目https://github.com/hai-jun/haijun.git clone到本地通过编辑器运行或直接使用容器运行

  容器运行
  
  运行docker login --username=duyulongNB@1865045669554094.onaliyun.com registry.cn-hangzhou.aliyuncs.com
  
  password:123321132q.
  
  即可以使用我的阿里云RAM子账号登录到容器镜像服务。
  
  然后把resourse下的docker-compose.yml 拷贝到你的本地路径，在此路径下执行docker-compose run -d docker-compose.yml,前提是你已经安装了Docker;
  
  接下来会拉取我的镜像并在你本地跑四个容器，成功之后就可以部署前端查看效果了
  
# 文件解析
haijun-apigateway 是网关文件，在application.yml中配置了路由规则，将所有以“order”开头的请求都路由到order-client微服务；

其下filter文件有多个，起到校验token、处理跨域访问、包装请求参数的作用；

其下conf文件夹下的配置文件的作用是读取配置到配置中心的数据源配置；

其余皆为常见的分层架构，没什么好讲的；

业务代码主要在order-client微服务
