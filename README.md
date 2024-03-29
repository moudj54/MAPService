Install the required components:

	# apt-get install postgresql-9.1-postgis php5-mcrypt python3 htop elinks openjdk-7-jdk tomcat7 tomcat7-user tomcat7-admin tomcat7-examples maven subversion git ant openvpn postgis postgresql-9.1 phppgadmin activemq apache2-mpm-itk screen 

Enable ActiveMQ:

	# ln -s /etc/activemq/instances-available/main/ /etc/activemq/instances-enabled/

Create PostGIS database:

	# su postgres
	$ psql template1
	> CREATE USER map WITH PASSWORD 'xxx';
	> CREATE DATABASE map;
	> GRANT ALL PRIVILEGES ON DATABASE map TO map;
	> \q
	$ psql -d map -f /usr/share/postgresql/9.1/contrib/postgis-1.5/postgis.sql
	$ psql -d map -f /usr/share/postgresql/9.1/contrib/postgis-1.5/spatial_ref_sys.sql

Configure Apache:

	# a2enmod mpm_itk proxy proxy_http rewrite

Inside Apache's VirtualHost:

	ProxyPass /api/map/v1/map/populate !
        ProxyPassReverse /api/map/v1/map/populate !
	ProxyPass /api/map/v1/ http://127.0.0.1:8080/map/
	ProxyPassReverse /api/map/v1/ http://127.0.0.1:8080/map/
	<IfModule mpm_itk_module>
		AssignUserId map map
	</IfModule>
	DocumentRoot /home/map/site/MAPSite/MAP/public/

Configure Tomcat (tomcat-users.xml):

	<role rolename="manager-script"/>
	<user username="map" password="xxx" roles="manager-gui,admin-gui,manager-script"/>

Deploy the WARs:

	$ git clone https://github.com/kotfu/tomcat-manager.git
	$ tomcat-manager.py --user=map --password=xxx http://localhost:8080/manager/text deploy /map services/MAPService/target/map.war
	$ tomcat-manager.py --user=map --password=xxx http://localhost:8080/manager/text deploy /collector services/MAPCollector/target/MAPCollector.war

Add the 'admin' user:

	# su postgres
	$ psql -d map
	> INSERT INTO user_entity VALUES ('admin', true, '5f4dcc3b5aa765d61d8327deb882cf99');

Populate the database:

	$ curl -X GET "http://admin:password@127.0.0.1:8080/map/populate?path=tools%2Fart01%2Fschede_v.3.xlsx" 
	$ curl -X GET "http://admin:password@127.0.0.1:8080/map/populate/collect"
