Introduction
-------------------------

The project shows how to connect to a multi-node cassandra cluster.

The cluster is managed with CCM (Cassandra Cluster Manager).

Create cluter and data model
-------------------------

Here the commands I've used to create the cluster with ccm:

	ccm create cluster_test_1 -v 2.0.12
	ccm populate -n 3
	ccm start
	
After you have created the cluster, open cql shell:
	
	ccm node1 cqlsh
	
Create keyspace and table:

	create KEYSPACE playlist WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 3 };
	create TABLE users( firstname text, lastname text, age int, email text, PRIMARY KEY(lastname, firstname) );
	
	
Note that the partition key lastname is a good choice. Use firstname as clustering column.
  
Compile
-------------------------

To compile use the following command:

mvn clean compile assembly:single