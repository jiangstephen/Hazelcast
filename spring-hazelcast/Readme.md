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
