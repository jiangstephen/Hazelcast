Download the hazelcast management center from https://download.hazelcast.com/management-center/hazelcast-management-center-3.10.2.zip

Unzip the file,  and go to the link http://localhost:8080/hazelcast-mancenter
type your own admin username/password

In order to make hazelcast successfully registered with the hazelcast management center,  the management center version should be the same as the library version used in the application,  in my case it is 

		<dependency>
			<groupId>com.hazelcast</groupId>
			<artifactId>hazelcast</artifactId>
			<version>3.10.2</version>
		</dependency>

I need to downgrade the spring-boot-starter-parent to 1.5.3.RELEASE from the 2.0.4.RELEASE in order to make the hazelcast hibernate to be compatible with the hibernate version 

I had to override the managed hibernate-core version for Spring Boot 1.5.3.RELEASE because Hazelcast didn’t work properly with 5.0.12.Final. Hazelcast needs hibernate-core in the 5.0.9.Finalversion. Otherwise, an exception occurs when starting an application.

There are couple of important notes, please read carefully
1. -Dhazelcast.jmx=true  enables the jmx for out of box hazelcast JMX beans
2. ManyToMany/OneToMany relationship, hibernate will create the collection cacheregion named [owenr object name].[collection property name]  to store the query cache result
3. OneToOne/ManyToOne, hibernate will come up with the collection region, instead it will store the foreign key dehydrated id. 
a.  Unidirectional link query is cached
b.  Bidirectional link is not cached
c.  In order to cache Didirectional link,  you need to use JoinColumn(name ='',  referencedColumnName="") in both entities
4. To enable the hibernate statistics logging 
a. -Dhibernate.generate_statistics=true
b. set the logging level of org.hibernate.stats to DEBUG