# Apache Kafka with Spring Boot
> Features
> - Apache Avro [https://avro.apache.org/docs/1.11.1/specification/]
>  for Schema validation

# Setup
Avro Schema is define and created under `resources` folder under (<i>.avsc</i>) file type.
> To generate the class file based on the *.avsc* file you need to run this to maven. 
```shell
mvn generate-resources
```

# Run

Initiate the kafka components
```shell
docker-compose up -d
```
![alt text](https://img001.prntscr.com/file/img001/HziOe-vdT5S-hpNDTWj_bA.png "Docker")



Run the `SpringKafkaDemoApplication` separately.

Then, Run the `AvroKafkaProducer` to test the avro data to kafka.

Validate the data from the kafka UI.
[Kafka UI](http://localhost:8081)