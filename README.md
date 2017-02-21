# ssh_project
AbstractRoutingDataSource 该类充当了DataSource的路由中介,能在运行时根据某种key值来动态的切换到真正的数据源上.类中的方法determineCurrentLookupKey()是spring jdbc用来从targetDataSources中查找数据源的,如果返回null,则使用defaultTargetDataSource指定的默认数据源.

#控制反转(ioc)对象依赖于对象b,那么对象a在初始化或者运行到某点时,自己必须主动去创建对象b或者使用已经创建的对象b,无论创建还是使用,控制权都在自己手上.引入ioc后,对象a和对象b之间失去了直接联系,当对象a运行到需要对象b的时候,ioc容器会主动创建对象b,并把对象b注入到对象a需要的地方.

#依赖注入(di)控制反转是一种思想,依赖注入是一种设计模式.
依赖注入的方式:
1.set注入 假设有一个类,类中需要实例化一个Dao对象,那么就可以定义一个private的Dao成员变量,然后创建Dao的set方法(这是ioc的注入入口).
private Dao dao;
public void setDao(Dao dao){this.dao = dao;}.
在spring配置文件applicationContext.xml中,
<bean id="" class="">
		<property name="dao" ref="dao"/>
</bean>
<bean id="dao" class="Dao"></bean>
2.构造器注入  指带有参数的构造函数注入
public SpringAction(Dao dao,User user){
	this.dao = dao;
	this.user = user;
}
在spring配置文件中,
<bean id="" class="">
	<constructor-arg index="0" ref="dao"/>
	<constructor-arg index="1" ref="user"/>
</bean>
<bean id="dao" class="Dao"></bean>
<bean id="user" class="User"></bean>
3.使用注解 
@Autowired  可以对成员变量,方法,构造函数进行标注,来自动完成装配的工作,总是采用byType的自动装配策略;
@Qualifier(精确的自动装配)  根据类型自动装配,当spring上下文存在不止一个UserDao类型的bean时,就会抛出BeanCreationException异常,如果不存在,也会抛出此异常.通过使用这个注解,允许bean表示来指定自动装配,通常会为@Qualifier指定一个名字,表示精确定位id为这个名字的bean.
@Autowired
private void setUserDao(@Qualifier("userDao")UserDao userDao);
@Resource  默认是按byName自动注入,有两个属性非常重要分别是name和type.
Resource的装配顺序,
*如果同时指定了name和type,则从spring上下文中找到唯一匹配的bean进行装配,找不到则抛出异常
*如果指定了name,则从上下文中查找名字匹配的bean进行装配,找不到则抛出异常
*如果指定了type,则从上下文中查找类型匹配的唯一的bean进行装配,找不到或找到多个,都会抛出异常
*如果没有指定name,type,则自动按照byName的方式进行装配

@PostConstruct  在方法上加此注解,这个方法就会在bean初始化之后被spring执行.
@PreDestory  在bean销毁之前调用

@Component 在类上加此注解就将该类定义为一个bean了,还可分为@Repository 对应dao组件类 @Service 对应业务逻辑组件 @Controller 对应控制器组件
