FROM java:8
WORKDIR /app
COPY target/scala-2.11/warehouse-assembly-0.1-SNAPSHOT.jar ./
COPY src/main/resources/ /resources
CMD java -jar warehouse-assembly-0.1-SNAPSHOT.jar --inventory-path "/resources/inventory.json" --products-path "/resources/products.json"