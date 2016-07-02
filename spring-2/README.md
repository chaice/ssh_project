1.注解的使用：
在ApplicationContext.xml的beans中配置context->schema
添加<context-component-scan base-package="在哪个包中使用注解" />
使用注解使类被spring管理，相当于在配置文件中加bean...
①@Service  ->一般在service层使用
②@Repository ->在dao层使用
③@Component  ->对于AOP的各种通知使用
//以上三个是spring官方的对于bean管理的注解
④@Named   -> 基于JSR330的java标准的bean管理的注解  依赖inject.jar

依赖注入的注解  how使用--有set方法时在set方法上面加注解，没有时直接在需要注入的属性上架注解
①@Autowired  ->spring官方的
②@Inject     ->JSR330
③@Resource   ->JSR250

注意：要使用spring的注解全部使用spring,不要把spring与java混合使用

基于注解的AOP:依赖aspects 添加aop schema
添加标签<aop:aspectj-autoproxy />
①在通知类上加@aspect
②在通知类中的任意不是通知方法上加@pointcut("切入点表达式") 此方法的方法名相当于在xml中配置的id
③before("②中的方法名") //前置通知
④afterReturning("②中的方法名" returning="返回值为")  //后置通知
⑤afterThrowing("②中的方法名" throwing="") //异常通知
⑥after("②中的方法名") //最终通知
环绕通知
⑦@around("②中的方法名")  //环绕通知


2.spring-JDBC
依赖mysql,dbcp2,spring-jdbc
添加节点
<bean id="dataSource" class="数据库连接池的完全限定名">
<property name="driverClassName" value="${jdbc.driver}" /> //name一定要注意！！！
<property name="url" value="${jdbc.url}" />
<property name="username" value="${jdbc.username}" />
<property name="password" value="${jdbc.password}" />
</bean>
读取配置文件，使用${}得到配置文件中配置的值
<context-property-placeholder location="配置文件的名字" />
创建temPlate 相当于mybatis中的sqlSession 用来调用 update(),query(),queryForObject()等方法
<bean id="temPlate" class="org.springFrameWork.jdbc.core.JdbcTemplate"> //需要数据库连接池注入
   <property name="dataSource" ref="dataSource" />
</bean>


3.jdbc事物管理器   依赖spring-tx  添加tx schema
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
  <property name="dataSource" ref="dataSource" />
</bean>
基于注解的jdbc事物管理
<tx:annotation-driven transaction-manager="transactionManager">
在需要事物管理的方法或类上面加@Transactional(属性)
属性：①rollback="碰到什么异常会回滚" ②noRollbackFor="碰到什么异常不会回滚"
 ③查询方法设置为只读 readOnly="true"④isolation="事物的隔离级别" ⑤propagation="事物的传播属性"